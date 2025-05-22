package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.BaseDto;
import ch.jubnl.vsecureflow.backend.entity.BaseEntity;
import ch.jubnl.vsecureflow.backend.mapper.BaseMapper;
import ch.jubnl.vsecureflow.backend.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<Entity extends BaseEntity, Dto extends BaseDto, Repository extends BaseRepository<Entity, Long>, Mapper extends BaseMapper<Entity, Dto>> {
    protected final Repository repository;
    protected final Mapper mapper;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public BaseService(Repository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<Dto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public List<Dto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public Page<Dto> findAll(Pageable pageable) {
        return mapper.toDtoList(repository.findAll(pageable));
    }

    @Transactional
    public Dto save(Dto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    public List<Dto> saveAll(List<Dto> dtos) {
        return mapper.toDtoList(repository.saveAll(mapper.toEntityList(dtos)));
    }

    @Transactional
    public Dto update(Dto dto) {
        return mapper.toDto(repository.saveAndFlush(mapper.toEntity(dto)));
    }

    @Transactional
    public List<Dto> updateAll(List<Dto> dtos) {
        return mapper.toDtoList(repository.saveAllAndFlush(mapper.toEntityList(dtos)));
    }

    @Transactional
    public void delete(Dto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Transactional
    public void delete(List<Dto> dtos) {
        repository.deleteAll(mapper.toEntityList(dtos));
    }

    @Transactional
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
