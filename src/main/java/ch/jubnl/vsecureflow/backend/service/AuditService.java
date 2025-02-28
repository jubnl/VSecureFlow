package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.annotation.Auditable;
import ch.jubnl.vsecureflow.backend.dto.AuditDto;
import ch.jubnl.vsecureflow.backend.entity.Audit;
import ch.jubnl.vsecureflow.backend.entity.BaseEntity;
import ch.jubnl.vsecureflow.backend.enums.AuditType;
import ch.jubnl.vsecureflow.backend.mapper.AuditMapper;
import ch.jubnl.vsecureflow.backend.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuditService extends BaseService<Audit, AuditDto, AuditRepository, AuditMapper> {
    @Autowired
    @Lazy
    private AuditService self;

    public AuditService(AuditRepository repository, AuditMapper mapper) {
        super(repository, mapper);
    }

    private static <Entity extends BaseEntity> Field getField(Entity newValue, PropertyDescriptor pd) {
        Field field = null;
        try {
            field = newValue.getClass().getDeclaredField(pd.getName());
        } catch (NoSuchFieldException e) {
            // Optionally search in superclasses
            Class<?> superClass = newValue.getClass().getSuperclass();
            while (superClass != null) {
                try {
                    field = superClass.getDeclaredField(pd.getName());
                    break;
                } catch (NoSuchFieldException ex) {
                    superClass = superClass.getSuperclass();
                }
            }
        }
        return field;
    }

    public <Entity extends BaseEntity> List<AuditDto> logAuditRecord(String auditMadeBy, AuditType auditType, Entity newValue, Entity oldValue) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(newValue.getClass());
            List<AuditDto> auditDtos = new ArrayList<>();
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                if ("class".equals(propertyDescriptor.getName())) continue;

                Field field = getField(newValue, propertyDescriptor);

                if (field != null) {
                    // Check if the field has Auditable with exclude=true.
                    if (field.isAnnotationPresent(Auditable.class) && field.getAnnotation(Auditable.class).exclude()) {
                        continue;
                    }
                }

                Method getter = propertyDescriptor.getReadMethod();
                if (getter != null) {
                    Object newProp = getActualValue(getter.invoke(newValue));
                    Object oldProp = oldValue == null ? null : getActualValue(getter.invoke(oldValue));
                    if (!Objects.equals(newProp, oldProp)) {
                        logger.info("{}: {}: property '{}' changed: {} -> {}", auditMadeBy, auditType, propertyDescriptor.getName(), oldProp, newProp);
                        AuditDto auditDto = new AuditDto();
                        auditDto.setType(auditType);
                        auditDto.setEntityId(newValue.getId());
                        auditDto.setEntityName(newValue.getClass().getPackage().getName() + "." + newValue.getClass().getSimpleName());
                        String fieldName = propertyDescriptor.getName();
                        if (fieldName == null) {
                            fieldName = "unknown";
                        }
                        auditDto.setFieldName(fieldName);
                        if (oldProp != null) {
                            auditDto.setPreviousValue(oldProp.toString());
                        } else {
                            auditDto.setPreviousValue(null);
                        }
                        auditDto.setNewValue(newProp.toString());
                        auditDtos.add(auditDto);
                    }
                }
            }
            return mapper.toDtoList(repository.saveAll(mapper.toEntityList(auditDtos)));
        } catch (Exception e) {
            logger.error("An error occurred while logging audit record", e);
            return List.of();
        }
    }

    public <Entity extends BaseEntity> List<AuditDto> auditRecord(Entity entity) {
        return auditRecord(entity.getId(), entity.getClass().getPackage() + "." + entity.getClass().getSimpleName());
    }

    public List<AuditDto> auditRecord(Long entityId, String entityName) {
        return mapper.toDtoList(repository.findAll());
//        return mapper.toDtoList(repository.findByEntityIdAndEntityName(entityId, entityName));
    }

    private Object getActualValue(Object value) {
        if (value == null) return null;
        if (value instanceof BaseEntity) {
            return ((BaseEntity) value).getId();
        }
        return value;
    }
}
