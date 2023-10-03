package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByShipName(String shipName);

    Order findById(int id);

    @Query(value="SELECT new " +
            "com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto(o.id,o.orderDate,o.requiredDate,o.shippedDate,o.shipVia,o.freight,o.shipName,o.shipAddress,o.shipCity,o.shipRegion,o.shipPostalCode,o.shipCountry) FROM Order o")
    List<OrderForListingDto> getForListing();
}

