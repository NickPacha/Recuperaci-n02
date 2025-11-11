package org.example.reculp02.mappers.base;

import java.util.List;

public interface BaseMapper <E, D>{
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOs(List<E> list);
    List<E> toEntityList(List<D> list);
}
