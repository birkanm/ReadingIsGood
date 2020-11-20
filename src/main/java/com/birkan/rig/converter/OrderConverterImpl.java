package com.birkan.rig.converter;

import com.birkan.rig.common.PurchaseOrderDto;
import com.birkan.rig.entity.Order;
import com.birkan.rig.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
@AllArgsConstructor
public class OrderConverterImpl extends BaseConverter<PurchaseOrderDto, Order> {

    private final CustomerConverterImpl customerConverter;

    private final OrderRepository orderRepository;

    @Override
    public Order convertToEntity(PurchaseOrderDto dto) {
        Order entity;
        if (Objects.nonNull(dto.getPkid())) {
            entity = orderRepository.findById(dto.getPkid()).get();
        } else {
            entity = new Order();
        }
        if (Objects.nonNull(dto.getCompleteDate())) {
            entity.setCompleteDate(new Date(dto.getCompleteDate().getTime()));
        }
        if (Objects.nonNull(dto.getOrderDate())) {
            entity.setOrderDate(new Date(dto.getOrderDate().getTime()));
        }
        entity.setCustomer(customerConverter.convertToEntity(dto.getCustomerDto()));
        entity.setOrderId(dto.getOrderId());
        entity.setOrderStatus(dto.getOrderStatus());
        return entity;
    }

    @Override
    public PurchaseOrderDto convertToDto(Order entity) {
        PurchaseOrderDto dto = new PurchaseOrderDto();
        dto.setPkid(entity.getPkid());
        dto.setCompleteDate(entity.getCompleteDate());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderId(dto.getOrderId());
        dto.setOrderStatus(dto.getOrderStatus());
        dto.setCustomerDto(customerConverter.convertToDto(entity.getCustomer()));
        return dto;
    }
}
