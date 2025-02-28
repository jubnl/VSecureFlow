package ch.jubnl.vsecureflow.frontend.views;

import ch.jubnl.vsecureflow.backend.dto.AuditDto;
import ch.jubnl.vsecureflow.backend.dto.TaskDto;
import ch.jubnl.vsecureflow.backend.dto.UserDto;
import ch.jubnl.vsecureflow.backend.enums.TaskPriority;
import ch.jubnl.vsecureflow.backend.service.TaskService;
import ch.jubnl.vsecureflow.backend.service.UserService;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PageTitle("Tasks")
@Route("tasks/:taskID?/:action?(edit)")
@Menu(order = 1, icon = LineAwesomeIconUrl.COLUMNS_SOLID)
// @RolesAllowed("USER")
@AnonymousAllowed
public class TaskView extends Div implements BeforeEnterObserver {

    private final String TASK_ID = "taskID";
    private final String TASK_EDIT_ROUTE_TEMPLATE = "tasks/%s/edit";

    private final Grid<TaskDto> grid = new Grid<>(TaskDto.class, false);
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(TaskView.class);

    // CollaborationAvatarGroup avatarGroup;

    private TextField title;
    private TextField description;
    private DateTimePicker deadline;
    private ComboBox<TaskPriority> priority;
    private ComboBox<UserDto> owner;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final Binder<TaskDto> binder;

    private TaskDto task;

    private final TaskService taskService;

    public TaskView(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
        addClassNames("tasks-view");

        // UserInfo is used by Collaboration Engine and is used to share details
        // of users to each other to able collaboration. Replace this with
        // information about the actual user that is logged, providing a user
        // identifier, and the user's real name. You can also provide the users
        // avatar by passing an url to the image as a third parameter, or by
        // configuring an `ImageProvider` to `avatarGroup`.
        UserInfo userInfo = new UserInfo(UUID.randomUUID().toString(), "Steve Lange");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

//        avatarGroup = new CollaborationAvatarGroup(userInfo, null);
//        avatarGroup.getStyle().set("visibility", "hidden");

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("title").setAutoWidth(true);
        grid.addColumn("description").setAutoWidth(true);
        grid.addColumn("deadline").setAutoWidth(true);
        grid.addColumn("priority").setAutoWidth(true);
        grid.addColumn("owner").setAutoWidth(true);
        grid.setItems(query -> taskService.findAll(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(TASK_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(TaskView.class);
            }
        });

        // Configure Form
        binder = new Binder<>(TaskDto.class);

        // Bind fields. This is where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                boolean isNew;
                if (this.task == null) {
                    this.task = new TaskDto();
                    isNew = true;
                } else {
                    isNew = false;
                }
                binder.writeBean(this.task);
                TaskDto newTask;
                if (isNew) {
                    newTask = taskService.save(this.task);
                } else {
                    newTask = taskService.update(this.task);
                }
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(TaskView.class);
                List<AuditDto> audit = taskService.audit(newTask);
                logger.info("Audit updated: {}", newTask);
                logger.info("Audit {}", audit);
                for (AuditDto auditDto : audit) {
                    logger.info("Audit: {}", auditDto);
                }
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Notification.Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> taskId = event.getRouteParameters().get(TASK_ID).map(Long::parseLong);
        if (taskId.isPresent()) {
            Optional<TaskDto> taskFromBackend = taskService.findById(taskId.get());
            if (taskFromBackend.isPresent()) {
                populateForm(taskFromBackend.get());
            } else {
                Notification.show(String.format("The requested task was not found, ID = %d", taskId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(TaskView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        title = new TextField("Title");
        title.setRequired(true);
        description = new TextField("Description");
        description.setRequired(true);
        deadline = new DateTimePicker("Starte Date Time");
        deadline.setStep(Duration.ofSeconds(1));
        priority = new ComboBox<>("Priority");
        priority.setRequired(true);
        priority.setItems(TaskPriority.values());
        owner = new ComboBox<>("Owner");
        owner.setItems(userService.findAll());
        owner.setItemLabelGenerator(UserDto::getEmail);
        owner.setRequired(true);

        formLayout.add(title, description, deadline, priority, owner);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(TaskDto value) {
        this.task = value;
        binder.readBean(this.task);
    }
}

