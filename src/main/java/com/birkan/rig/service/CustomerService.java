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

    public List<CustomerDto> findAll() {
        List<Customer> allCustomer = repository.findAll();
        return converter.toDtoList(allCustomer);
    }
}
