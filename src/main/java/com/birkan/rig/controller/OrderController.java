package com.birkan.rig.controller;

import com.birkan.rig.common.OrderDetailDto;
import com.birkan.rig.common.OrderDto;
import com.birkan.rig.common.OrderRequest;
import com.birkan.rig.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@Valid @RequestBody OrderRequest dto) {
        return ResponseEntity.ok(service.saveNewOrder(dto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDetailDto> queryOrdersById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getOrderDetailByOrderId(id));
    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<List<OrderDto>> queryOrdersByCustomerId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getOrdersByCustomerId(id));
    }

}
