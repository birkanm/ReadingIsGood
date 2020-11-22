package com.birkan.rig.converter;

import com.birkan.rig.common.CustomerDto;
import com.birkan.rig.entity.Customer;
import com.birkan.rig.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerConverterImpl implements BaseConverter<CustomerDto, Customer> {

    private final CustomerRepository customerRepository;

    @Override
    public Customer convertToEntity(CustomerDto dto) {
        Customer entity = getOrCreateEmptyEntity(dto.getPkid());
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

    private Customer getOrCreateEmptyEntity(Long entityId) {
        if (Objects.nonNull(entityId)) {
            Optional<Customer> optEntity = customerRepository.findById(entityId);
            if (optEntity.isPresent()) {
                return optEntity.get();
            }
        }
        return new Customer();
    }
}
