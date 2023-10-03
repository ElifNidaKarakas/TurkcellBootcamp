package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import com.turkcell.spring.starter.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderForListingDto> getAll() {
        return orderRepository.getForListing();
    }

    @Override
    public void add(OrderForAddDto request) {
        this.orderWithShippedDateGreaterThanOrderDate(request.getOrderDate(), request.getShippedDate());
        this.OrderNameCanNotBeEmpty(request.getShipName());
        Order order = new Order();
        order.setOrderDate(request.getOrderDate());
        order.setRequiredDate(request.getOrderDate());
        order.setShippedDate(request.getShippedDate());
        order.setShipVia(request.getShipVia());
        order.setFreight(request.getFreight());
        order.setShipName(request.getShipName());
        order.setShipAddress(request.getShipAddress());
        order.setShipCity(request.getShipCity());
        order.setShipRegion(request.getShipRegion());
        order.setShipPostalCode(request.getShipPostalCode());
        order.setShipCountry(request.getShipCountry());
        orderRepository.save(order);

    }

    @Override
    public void updateOrder(int orderId, OrderForUpdateDto order) {
        this.orderWithShippedDateGreaterThanOrderDate(order.getOrderDate(), order.getShippedDate());
        this.OrderNameCanNotBeEmpty(order.getShipName());
        Order updateOrder = new Order();
        updateOrder.setOrderId(order.getId());
        updateOrder.setOrderDate(order.getOrderDate());
        updateOrder.setRequiredDate(order.getOrderDate());
        updateOrder.setShippedDate(order.getShippedDate());
        updateOrder.setShipVia(order.getShipVia());
        updateOrder.setFreight(order.getFreight());
        updateOrder.setShipName(order.getShipName());
        updateOrder.setShipAddress(order.getShipAddress());
        updateOrder.setShipCity(order.getShipCity());
        updateOrder.setShipRegion(order.getShipRegion());
        updateOrder.setShipPostalCode(order.getShipPostalCode());
        updateOrder.setShipCountry(order.getShipCountry());
        orderRepository.save(updateOrder);

    }

    @Override
    public void deleteOrder(int orderId) {
        this.checkIfOrderExistById(orderId);
        Order order = new Order();
        orderRepository.deleteById(orderId);


    }

    public void orderWithShippedDateGreaterThanOrderDate(LocalDate orderDate, LocalDate shippedDate) {
        int date = orderDate.compareTo(shippedDate);

        if (date > 0) {
            throw new BusinessException("şipariş tarihi kargo tarihinden sonra olamaz.");
        }
    }

    private void checkIfOrderExistById(int id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new BusinessException("id değeri bulunamadı ");
        }
    }

    private void OrderNameCanNotBeEmpty(String shipName) {
        Order shipNameEmpty = orderRepository.findByShipName(shipName);
        if (shipNameEmpty == null) {
            throw new BusinessException("alıcı adı boş olamaz");
        }
    }
}