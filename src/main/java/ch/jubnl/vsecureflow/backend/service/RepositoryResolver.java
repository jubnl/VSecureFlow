package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.entity.BaseEntity;
import ch.jubnl.vsecureflow.backend.repository.BaseRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

@Service
public class RepositoryResolver {

    private final Repositories repositories;

    // "Repositories" inspects all Spring Data repositories in the context
    public RepositoryResolver(ApplicationContext context) {
        this.repositories = new Repositories(context);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntity, ID> BaseRepository<T, ID> getRepository(Class<T> domainClass) {
        return (BaseRepository<T, ID>) repositories.getRepositoryFor(domainClass)
                .filter(repo -> repo instanceof BaseRepository<?, ?>)
                .orElseThrow(() -> new IllegalArgumentException("No JPA repository found for " + domainClass));
    }
}

