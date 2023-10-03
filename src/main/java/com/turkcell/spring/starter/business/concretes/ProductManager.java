package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForUpdateDto;
import com.turkcell.spring.starter.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }


    @Override
    public List<ProductForListingDto> getAll() {

        return productRepository.getForListing();
    }

    @Override
    public List<ProductForGetByIdDto> getById(int id) {

        return productRepository.getForIdListing();
    }

    @Override
    public void add(ProductForAddDto request) {

        productMinUnitPrice(request);
        this.productNameCanNotBeEmpty(request.getName());

        Product product = new Product();
        product.setName(request.getName());
        product.setQuantity_per_unit(request.getQuantity_per_unit());
        product.setUnit_price(request.getUnit_price());
        product.setUnits_in_stock(request.getUnit_in_stock());
        product.setReorder_level(request.getReorder_level());

        productRepository.save(product);
    }

    @Override
    public void updateProduct(int productId, ProductForUpdateDto updateProduct) {
        this.checkIfProductExistById(productId);
        this.productNameCanNotBeEmpty(updateProduct.getName());


        Product product=productRepository.findById(productId);
        product.setId(updateProduct.getId());
        product.setName(updateProduct.getName());
        product.setQuantity_per_unit(updateProduct.getQuantity_per_unit());
        product.setUnit_price(updateProduct.getUnit_price());
        product.setUnits_in_stock(updateProduct.getUnit_in_stock());
        product.setReorder_level(updateProduct.getReorder_level());

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        this.checkIfProductExistById(productId);

        Product product = productRepository.findById(productId);
        productRepository.deleteById(productId);
    }

    private void productMinUnitPrice(ProductForAddDto request) {
        if (request.getUnit_price() < 0) {
            throw new BusinessException("Fiyat değeri 0'dan küçük olamaz.");
        }
    }

    private void productNameCanNotBeEmpty(String productName) {
        Product productNameEmpty = productRepository.findByName(productName);
        if (productNameEmpty == null) {
            throw new BusinessException("Ürün adı alanı boş bırakılamaz");
        }
    }


    private void checkIfProductExistById(int id) {
        Product productId = productRepository.findById(id);
        if (productId == null) {
            throw new BusinessException("id değeri bulunamadı ");
        }

    }
}