package com.birkan.rig.service;

import com.birkan.rig.common.CustomerDto;
import com.birkan.rig.converter.CustomerConverterImpl;
import com.birkan.rig.entity.Customer;
import com.birkan.rig.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    private final CustomerConverterImpl converter;

    public List<CustomerDto> getAllCustomer() {
        //todo paging mechanism should be added
        List<Customer> allCustomer = repository.findAll();
        return converter.toDtoList(allCustomer);
    }

    public CustomerDto addCustomer(CustomerDto dto) {
        Customer customer = converter.convertToEntity(dto);
        Customer savedEntity = repository.save(customer);
        return converter.convertToDto(savedEntity);
    }
}
