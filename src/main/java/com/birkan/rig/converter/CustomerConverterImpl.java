package com.birkan.rig.converter;

import com.birkan.rig.common.CustomerDto;
import com.birkan.rig.entity.Customer;
import com.birkan.rig.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class CustomerConverterImpl extends BaseConverter<CustomerDto, Customer> {

    private final CustomerRepository customerRepository;

    @Override
    public Customer convertToEntity(CustomerDto dto) {
        Customer entity;
        if (Objects.nonNull(dto.getPkid())) {
            entity = customerRepository.findById(dto.getPkid()).get();
        } else {
            entity = new Customer();
        }

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return entity;
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
