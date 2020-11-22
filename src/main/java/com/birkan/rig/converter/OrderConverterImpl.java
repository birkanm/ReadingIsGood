package com.birkan.rig.converter;

import com.birkan.rig.common.OrderDto;
import com.birkan.rig.entity.Order;
import com.birkan.rig.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderConverterImpl implements BaseConverter<OrderDto, Order> {

    private final CustomerConverterImpl customerConverter;

    private final OrderRepository orderRepository;

    @Override
    public Order convertToEntity(OrderDto dto) {
        Order entity = getOrCreateEmptyEntity(dto.getPkid());
        if (Objects.nonNull(dto.getCompleteDate())) {
            entity.setCompleteDate(new Date(dto.getCompleteDate().getTime()));
        }
        if (Objects.nonNull(dto.getOrderDate())) {
            entity.setOrderDate(new Date(dto.getOrderDate().getTime()));
        }
        entity.setCustomer(customerConverter.convertToEntity(dto.getCustomer()));
        entity.setOrderId(dto.getOrderId());
        entity.setOrderStatus(dto.getOrderStatus());
        return entity;
    }

    @Override
    public OrderDto convertToDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setPkid(entity.getPkid());
        dto.setCompleteDate(entity.getCompleteDate());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderId(entity.getOrderId());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setCustomer(customerConverter.convertToDto(entity.getCustomer()));
        return dto;
    }

    private Order getOrCreateEmptyEntity(Long entityId) {
        if (Objects.nonNull(entityId)) {
            Optional<Order> optEntity = orderRepository.findById(entityId);
            if (optEntity.isPresent()) {
                return optEntity.get();
            }
        }
        return new Order();
    }
}
