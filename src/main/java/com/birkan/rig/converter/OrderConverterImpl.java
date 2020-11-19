package com.birkan.rig.converter;

import com.birkan.rig.common.OrderDto;
import com.birkan.rig.entity.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class OrderConverterImpl extends BaseConverter<OrderDto, Order> {

    @Override
    public void convertInEntity(OrderDto dto, Order entity) {
        if (Objects.nonNull(dto.getCompleteDate())) {
            entity.setCompleteDate(new Date(dto.getCompleteDate().getTime()));
        }
        if (Objects.nonNull(dto.getOrderDate())) {
            entity.setOrderDate(new Date(dto.getOrderDate().getTime()));
        }
        entity.setOrderId(dto.getOrderId());
        entity.setOrderStatus(dto.getOrderStatus());
    }

    @Override
    public Order convertToEntity(OrderDto dto) {
        Order entity = new Order();
        convertInEntity(dto, entity);
        return entity;
    }

    @Override
    public OrderDto convertToDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setPkid(entity.getPkid());
        dto.setCompleteDate(entity.getCompleteDate());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderId(dto.getOrderId());
        dto.setOrderStatus(dto.getOrderStatus());
        return dto;
    }
}
