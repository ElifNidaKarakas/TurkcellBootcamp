package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping()
    public List<Order> getOrders(){
        List<Order> orderList=orderRepository.findAll();
        return orderList;
    }

    @GetMapping("getById")
    public Order getById(@RequestParam("id") int id){
        Order order= orderRepository.findById(id).orElseThrow();
        return order;
    }

    @PostMapping()
    public ResponseEntity addOrder(@RequestBody Order order){
        orderRepository.save(order);
        return new ResponseEntity(("sipariş eklendi"), HttpStatus.CREATED);
    }
}
