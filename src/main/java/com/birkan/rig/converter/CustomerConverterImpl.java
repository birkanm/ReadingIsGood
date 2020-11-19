package com.birkan.rig.converter;

import com.birkan.rig.common.CustomerDto;
import com.birkan.rig.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverterImpl extends BaseConverter<CustomerDto, Customer> {

    @Override
    public void convertInEntity(CustomerDto dto, Customer entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }

    @Override
    public CustomerDto convertToDto(Customer entity) {
        CustomerDto dto = new CustomerDto();
        dto.setPkid(entity.getPkid());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}
