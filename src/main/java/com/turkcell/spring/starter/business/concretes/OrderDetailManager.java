package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.OrderDetailService;
import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.OrderDetail;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.orderDetail.OrderDetailForAddDto;
import com.turkcell.spring.starter.repositories.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailManager implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;

    public OrderDetailManager(OrderDetailRepository orderDetailRepository, ProductService productService) {
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
    }

    @Override
    public void addItemsToOrder(Order order, List<OrderDetailForAddDto> items) {
        for (OrderDetailForAddDto item : items) {
            OrderDetail od = OrderDetail.builder()
                    .product(Product.builder().id(item.getProductId()).build())
                    .order(Order.builder().orderId(order.getOrderId()).build())
                    .discount(0)
                    .quantity(item.getQuantity())
                    .unitPrice(0) // TODO: Find product and get unit price from productService
                    .build();
            orderDetailRepository.save(od);
        }
    }

    @Override
    public List<Object> getForListing() {
        return orderDetailRepository.getForListing();
    }

    //Eğer ilgili üründen talep edilen adet kadar yoksa adet verilebilecek max. stok olarak güncellenmelidir.
    private short RequestedProductCannotExceedTheNumberOfProductsInStock(short quantity, int productID) {

        short unitsInStock = productService.getUnitInStock(productID);
        if (quantity > unitsInStock) {
            //   productService.setUnitInStock(unitsInStock,productID);
            return unitsInStock;
        }
        //  productService.setUnitInStock(quantity,productID);
        return quantity;
    }
}