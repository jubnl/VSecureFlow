package ch.jubnl.vsecureflow;

import ch.jubnl.vsecureflow.backend.repository.UserRepository;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication(
		scanBasePackages = {
				"ch.jubnl.vsecureflow.backend.service",
				"ch.jubnl.vsecureflow.backend.repository",
				"ch.jubnl.vsecureflow.backend.mapper",
				"ch.jubnl.vsecureflow.backend.entity",
				"ch.jubnl.vsecureflow.backend.dto",
				"ch.jubnl.vsecureflow.backend.config",
				"ch.jubnl.vsecureflow.backend.helper",
				"ch.jubnl.vsecureflow.backend.annotation",
				"ch.jubnl.vsecureflow.backend.aop",
				"ch.jubnl.vsecureflow.backend.security"
		}
)
@Theme(value = "Tasks", variant = Lumo.DARK)
@Push
public class TaskApplication  implements AppShellConfigurator {
	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Bean
	SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
																			   SqlInitializationProperties properties, UserRepository repository) {
		// This bean ensures the database is only initialized when empty
		return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
			@Override
			public boolean initializeDatabase() {
				if (repository.count() == 0L) {
					return super.initializeDatabase();
				}
				return false;
			}
		};
	}
}
