package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.OrderDetailService;
import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Customer;
import com.turkcell.spring.starter.entities.Employee;
import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import com.turkcell.spring.starter.repositories.CustomerRepository;
import com.turkcell.spring.starter.repositories.EmployeeRepository;
import com.turkcell.spring.starter.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;

    public OrderManager(OrderRepository orderRepository, OrderDetailService orderDetailService) {
        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;
    }


    @Override
    public List<OrderForListingDto> getAll() {

        return orderRepository.getForListing();
    }

    @Override
    public void add(OrderForAddDto request) {
        Order order = Order.builder()
                .customer(Customer.builder().customerId(request.getCustomer_id()).build())
                .orderDate(LocalDate.now())
                .employee(Employee.builder().employeeId(request.getEmployee_id()).build())
                .requiredDate(request.getRequiredDate())
                .shipAddress(request.getShipAddress())
                .shipCity(request.getShipCity())
                .shipName(request.getShipName())
                .shipRegion(request.getShipRegion())
                .build();

        order = orderRepository.save(order);  // gönderen hesaptan parayı düş

        // bu satırdan sonra order'ın id alanı set edilmiş..
        orderDetailService.addItemsToOrder(order, request.getItems()); // gönderilen hesaba parayı göndermek
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

        Order order = new Order();
        orderRepository.deleteById(orderId);


    }

    public void orderWithShippedDateGreaterThanOrderDate(LocalDate orderDate, LocalDate shippedDate) {
        int date = orderDate.compareTo(shippedDate);

        if (date > 0) {
            throw new BusinessException("şipariş tarihi kargo tarihinden sonra olamaz.");
        }
    }

    private void OrderNameCanNotBeEmpty(String shipName) {
        Order shipNameEmpty = orderRepository.findByShipName(shipName);
        if (shipNameEmpty == null) {
            throw new BusinessException("alıcı adı boş olamaz");
        }
    }
}