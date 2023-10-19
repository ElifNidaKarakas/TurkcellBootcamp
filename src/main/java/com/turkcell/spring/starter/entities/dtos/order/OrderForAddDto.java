package com.turkcell.spring.starter.entities.dtos.order;

import com.turkcell.spring.starter.entities.dtos.orderDetail.OrderDetailForAddDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderForAddDto {

    private String customer_id;
    private short employee_id;
    //private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private int shipVia;
    private String freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    // private String shipPostalCode;
    //   private String shipCountry;
    private List<OrderDetailForAddDto> items;
}
