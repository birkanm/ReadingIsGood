package com.birkan.rig.controller;

import com.birkan.rig.common.CustomerDto;
import com.birkan.rig.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomer());
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto dto) {
        return ResponseEntity.ok(service.addCustomer(dto));
    }

}
