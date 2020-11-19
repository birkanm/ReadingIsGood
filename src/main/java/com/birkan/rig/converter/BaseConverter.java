package com.birkan.rig.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<D, E> {

    public abstract void convertInEntity(D dto, E entity);

    public abstract D convertToDto(E entity);

    public List<D> toDtoList(List<E> entityList) {
        return entityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
