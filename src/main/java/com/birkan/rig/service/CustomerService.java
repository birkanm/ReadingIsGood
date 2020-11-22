package com.birkan.rig.service;

import com.birkan.rig.common.CustomerDto;
import com.birkan.rig.converter.CustomerConverterImpl;
import com.birkan.rig.entity.Customer;
import com.birkan.rig.exception.CustomerNotFoundException;
import com.birkan.rig.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    private final CustomerConverterImpl converter;

    private final Logger logger;

    public CustomerDto addCustomer(CustomerDto dto) {
        Customer customer = converter.convertToEntity(dto);
        Customer savedEntity = repository.save(customer);
        return converter.convertToDto(savedEntity);
    }

    public CustomerDto findById(Long id) {
        Optional<Customer> optCustomer = repository.findById(id);
        if (!optCustomer.isPresent()) {
            logger.error("Unable to find customer with id: {}", id);
            throw new CustomerNotFoundException(String.format("Unable to find customer with id: %d", id));
        }
        return converter.convertToDto(optCustomer.get());
    }
}
