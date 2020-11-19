package com.birkan.rig.controller;

import com.birkan.rig.common.CustomerDto;
import com.birkan.rig.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        return ResponseEntity.ok(service.findAll());
    }

}
