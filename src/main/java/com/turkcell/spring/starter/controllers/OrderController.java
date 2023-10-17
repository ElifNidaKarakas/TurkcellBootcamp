package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MessageSource messageSource;

    @GetMapping()
    public List<OrderForListingDto> getOrders() {
        List<OrderForListingDto> ordersInDb = orderService.getAll();
        return ordersInDb;
    }

    @PostMapping()
    public ResponseEntity addOrder(@RequestBody @Valid OrderForAddDto order) {
        orderService.add(order);
        return new ResponseEntity(messageSource.getMessage("OrderAdded", null , LocaleContextHolder.getLocale()), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity updateOrder(@PathVariable int orderId, @RequestBody @Valid OrderForUpdateDto updateDto) {
        orderService.updateOrder(updateDto.getId(), updateDto);
        return new ResponseEntity((messageSource.getMessage("OrderUpdate", null , LocaleContextHolder.getLocale())), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteOrder(@PathVariable("orderId") int orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity(messageSource.getMessage("orderDeleted",new Object[] {orderId}, LocaleContextHolder.getLocale()), HttpStatus.OK);
    }
}