package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByShipName(String shipName);

    Order findById(int id);

    Order findByShipCity(String shipCity);

    Order findByFreight(Float freight);


    @Query(value = "SELECT new " +
            "com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto" +
            "(o.orderId,o.orderDate,o.requiredDate,o.shipVia,o.freight,o.shipName,o.shipCity,o.shipRegion,o.shipCountry) FROM Order o")
    List<OrderForListingDto> getForListing();

}