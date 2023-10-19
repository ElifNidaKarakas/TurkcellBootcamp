package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.OrderDetailService;
import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.core.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Customer;
import com.turkcell.spring.starter.entities.Employee;
import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForListingDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import com.turkcell.spring.starter.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {
    private final MessageSource messageSource;

    private final OrderRepository orderRepository;

    private final OrderDetailService orderDetailService;

    private final ModelMapper modelMapper;

    @Override
    public List<OrderForListingDto> getAll() {

        return orderRepository.getForListing();
    }

    @Override
    @Transactional
    public void add(OrderForAddDto request) {
       // freightWithNumberBiggerThanTwentyOne(request);
        shipCityWithSameNameShouldNotExist(request);
        requiredDateCannotBePastTenseThanLocalDate(request.getRequiredDate());


        /*Order order = Order.builder()
                .customer(Customer.builder().customerId(request.getCustomer_id()).build())
                .orderDate(LocalDate.now())
                .employee(Employee.builder().employeeId(request.getEmployee_id()).build())
                .requiredDate(request.getRequiredDate())
                .shipAddress(request.getShipAddress())
                .shipCity(request.getShipCity())
                .shipName(request.getShipName())
                .shipRegion(request.getShipRegion())
                .build();*/

        Order orderFromAutoMapping = modelMapper.map(request, Order.class);
        orderFromAutoMapping = orderRepository.save(orderFromAutoMapping);  // gönderen hesaptan parayı düş
        // bu satırdan sonra order'ın id alanı set edilmiş..
        orderDetailService.addItemsToOrder(orderFromAutoMapping, request.getItems()); // gönderilen hesaba parayı göndermek

    }

    @Override
    public void updateOrder(int orderId, OrderForUpdateDto order) {
        this.OrderNameCanNotBeEmpty(order.getShipName());
        orderWithSameNameShouldNotExist(order.getShipName());

        /*Order updateOrder = new Order();
        updateOrder.setOrderId(order.getId());
        updateOrder.setOrderDate(order.getOrderDate());
        updateOrder.setRequiredDate(order.getOrderDate());
        updateOrder.setShippedDate(order.getShippedDate());
        updateOrder.setShipVia(order.getShipVia());

        updateOrder.setShipName(order.getShipName());
        updateOrder.setShipAddress(order.getShipAddress());
        updateOrder.setShipCity(order.getShipCity());
        updateOrder.setShipRegion(order.getShipRegion());
        updateOrder.setShipPostalCode(order.getShipPostalCode());
        updateOrder.setShipCountry(order.getShipCountry());
        orderRepository.save(updateOrder);*/
        Order orderFromAutoMapping = modelMapper.map(order, Order.class);
        orderRepository.save(orderFromAutoMapping);

    }

    @Override
    public void deleteOrder(int orderId) {
        Order orderToDelete = returnOrderByIdIfExists(orderId);
        orderRepository.delete(orderToDelete);
    }




    private void orderWithSameNameShouldNotExist(String shipName) {
        Order orderWithSameName = orderRepository.findByShipName(shipName);

        if (orderWithSameName != null && orderWithSameName.getShipName().length() >= 20) {
            throw new BusinessException
                    (messageSource.getMessage("OrderNameLength",
                            null, LocaleContextHolder.getLocale()));
        }
    }

    //Required Date kullanıcı tarafından gönderilmeli ve o günün tarihinden daha geçmiş bir tarih gönderilmemelidir.+
    private void requiredDateCannotBePastTenseThanLocalDate(LocalDate requiredDate) {
        if (requiredDate.isBefore(LocalDate.now())) {
            throw new BusinessException
                    (messageSource.getMessage("OrderDateControl", null, LocaleContextHolder.getLocale()));
        }
    }

    /*private void freightWithNumberBiggerThanTwentyOne(OrderForAddDto request) {
        // Order orderWithBiggerThan = orderRepository.findByShipCountry(shipCountry);
        if (request.getFreight() <= 21.5) {
            throw new BusinessException
                    (messageSource.getMessage("FreightControl", null, LocaleContextHolder.getLocale()));
        }
    }*/

    private void shipCityWithSameNameShouldNotExist(OrderForAddDto request) {
        // Order orderWithSameName = orderRepository.findByShipCity(shipCity);
        if (request.getShipCity() == null)
            throw new BusinessException
                    (messageSource.getMessage("CityNameNotNull", null, LocaleContextHolder.getLocale()));
    }


    private void OrderNameCanNotBeEmpty(String shipName) {
        Order shipNameEmpty = orderRepository.findByShipName(shipName);
        if (shipNameEmpty == null) {
            throw new BusinessException
                    (messageSource.getMessage("OrderNameNotEmpty", null, LocaleContextHolder.getLocale()));
        }
    }

    private Order returnOrderByIdIfExists(int orderId) {
        Order orderToDelete = orderRepository.findById(orderId);
        if (orderToDelete == null)
            throw new BusinessException
                    (messageSource.getMessage("OrderIdControl",
                            null, LocaleContextHolder.getLocale()));
        return orderToDelete;
    }
}
