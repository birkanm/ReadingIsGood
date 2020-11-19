package com.birkan.rig.controller;

import com.birkan.rig.common.OrderDto;
import com.birkan.rig.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(service.saveNewOrder(dto));
    }

}