package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.OrderDetail;
import com.turkcell.spring.starter.entities.dtos.orderDetail.OrderDetailListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    @Query(value = "SELECT o.order_id,p.product_name FROM orders o" +
            " JOIN order_details od on(o.order_id=od.order_id) Join products p on(od.product_id=p.product_id);", nativeQuery = true)
    List<Object> getForListing();
}
