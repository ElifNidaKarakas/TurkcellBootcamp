package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.core.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.Supplier;
import com.turkcell.spring.starter.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForUpdateDto;
import com.turkcell.spring.starter.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;


    @Override
    public List<ProductForListingDto> getAll() {

        return productRepository.getForListing();
    }

    @Override
    public List<ProductForGetByIdDto> getById(int id) {

        return productRepository.getForIdListing();
    }
    public float getByUnitPrice(int id) {

        return productRepository.findById(id).getUnit_price();
    }

    @Override
    public short getUnitInStock(int id) {
        return (short) productRepository.findById(id).getUnits_in_stock();
    }

    //Siparişe eklenen her ürünün stok adeti quantity kadar azaltılmalıdır. +
    public void setUnitInStock(short quantity, int id) {
        Product product = productRepository.findById(id);
        product.setUnits_in_stock((short) (product.getUnits_in_stock() - quantity));
        productRepository.save(product);

    }


    public void add(ProductForAddDto request) {
        productWithSameNameShouldNotExist(request.getName());

        Product newProduct = Product.builder()
                .name(request.getName())
                .unit_price(request.getUnit_price())
                .units_in_stock(request.getUnit_in_stock())
                .category(Category.builder().categoryId(request.getCategory_id()).build())
                .supplier(Supplier.builder().supplierId(request.getSupplier_id()).build())
                .discontinued(0)
                .build();


        productRepository.save(newProduct);
    }


    @Override
    public void updateProduct(int productId, ProductForUpdateDto updateProduct) {
        this.checkIfProductExistById(productId);
        this.productNameCanNotBeEmpty(updateProduct.getName());
        this.productWithSameNameShouldNotExist(updateProduct.getName());


        Product product = productRepository.findById(productId);
        product.setId(updateProduct.getId());
        product.setName(updateProduct.getName());
        product.setQuantity_per_unit(updateProduct.getQuantity_per_unit());
        product.setUnit_price(updateProduct.getUnit_price());
        product.setUnits_in_stock(updateProduct.getUnit_in_stock());
        product.setReorder_level(updateProduct.getReorder_level());

        Supplier supplier = new Supplier();
        supplier.setSupplierId(updateProduct.getSupplier_id());

        Category category = new Category();
        category.setCategoryId(updateProduct.getCategory_id());

        // Ürün nesnesine Supplier ve Category nesnelerini atarız

        //product.setSupplier(supplier);
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        this.checkIfProductExistById(id);
        Product productToDelete = returnProductByIdIfExists(id);

        productRepository.delete(productToDelete);

    }


    private void productMinUnitPrice(ProductForAddDto request) {
        if (request.getUnit_price() < 0) {
            throw new BusinessException("Fiyat değeri 0'dan küçük olamaz.");
        }
    }

    private void productNameCanNotBeEmpty(String productName) {
        Product productNameEmpty = productRepository.findByName(productName);
        if (productNameEmpty != null) {
            throw new BusinessException("Ürün adı alanı boş bırakılamaz");
        }
    }


    private void checkIfProductExistById(int id) {
        Product productId = productRepository.findById(id);
        if (productId == null) {
            throw new BusinessException("id değeri bulunamadı ");
        }

    }

    private void productWithSameNameShouldNotExist(String productName) {
        Product productWithSameName = productRepository.findByName(productName);
        if (productWithSameName != null) {
            throw new BusinessException
                    (messageSource.getMessage("productNameAllReady", null, LocaleContextHolder.getLocale()));
        }

    }
    private Product returnProductByIdIfExists(int id) {
        Product productToDelete = productRepository.findById(id);
        if (productToDelete == null)
            //throw new BusinessException("Böyle bir ürün bulunamadı.");
            throw new BusinessException
                    (messageSource.getMessage("productNotFound", null, LocaleContextHolder.getLocale()));
        return productToDelete;
    }

}