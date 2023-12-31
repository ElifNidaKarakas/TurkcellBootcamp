package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.orderDetail.OrderDetailForAddDto;

import java.util.List;

public interface OrderDetailService {
    void addItemsToOrder(Order order, List<OrderDetailForAddDto> items);
    List<Object> getForListing();
}
