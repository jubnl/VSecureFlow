package ch.jubnl.vsecureflow.backend.config;

import ch.jubnl.vsecureflow.backend.repository.BaseRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "ch.jubnl.vsecureflow.backend.repository",
        repositoryBaseClass = BaseRepositoryImpl.class
)
@EntityScan(
        basePackages = "ch.jubnl.task.backend.entity"
)
public class SpringDataConfig {
}
