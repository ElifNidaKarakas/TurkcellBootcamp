package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.Supplier;
import com.turkcell.spring.starter.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForUpdateDto;
import com.turkcell.spring.starter.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
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

        /* Builderin alternatifi
        Product newProduct2 = new Product();
        newProduct2.setName(request.getProductName());
        Category category = new Category();
        category.setCategoryId(request.getCategoryId());
        newProduct2.setCategory(category);
         */

        productRepository.save(newProduct);
    }

       /* product.setName(request.getName());
        product.setQuantity_per_unit(request.getQuantity_per_unit());
        product.setUnit_price(request.getUnit_price());
        product.setUnits_in_stock(request.getUnit_in_stock());
        product.setReorder_level(request.getReorder_level());
        product.setDiscontinued(request.getDiscontinued());

        Supplier supplier = new Supplier();
        supplier.setSupplierId(request.getSupplier_id());

        Category category = new Category();
        category.setCategoryId(request.getCategory_id());

        // Ürün nesnesine Supplier ve Category nesnelerini atarız

        //product.setSupplier(supplier);
        product.setCategory(category);*/

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

        Product product = productRepository.findById(id);
        productRepository.deleteById(id);
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
            throw new BusinessException("Bu ürün ismi eklenmiş.Aynı ürün isminden 2 ürün bulunamaz lütfen değiştiriniz.");
        }

    }
}