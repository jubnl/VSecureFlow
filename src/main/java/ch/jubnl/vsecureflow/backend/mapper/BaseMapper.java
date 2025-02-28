package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.BaseDto;
import ch.jubnl.vsecureflow.backend.entity.BaseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {
    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtoList(List<E> entityList);

    default Page<D> toDtoList(Page<E> entityPage) {
        if (entityPage == null) {
            return Page.empty();
        }
        return entityPage.map(this::toDto);
    }

    List<E> toEntityList(List<D> dtoList);
}
