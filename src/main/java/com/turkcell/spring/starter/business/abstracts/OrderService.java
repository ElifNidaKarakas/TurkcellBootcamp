package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;

import java.util.List;

public interface OrderService {
    List<OrderForListingDto> getAll();
    void add(OrderForAddDto request);
    void updateOrder(int orderId, OrderForUpdateDto order);

    void deleteOrder(int orderId);

}