package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.Order;

import java.util.List;

public interface OrderService {

    void add(Order order);
    List<Order> getAll();
    Order getById(int id);
    void update(Order order);
    void delete(int id);
}