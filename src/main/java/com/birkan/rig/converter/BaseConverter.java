package com.birkan.rig.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<D, E> {

    E convertToEntity(D dto);

    D convertToDto(E entity);

    default List<D> toDtoList(List<E> entityList) {
        return entityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
