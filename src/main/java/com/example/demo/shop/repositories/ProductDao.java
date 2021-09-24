package com.example.demo.shop.repositories;

import com.example.demo.shop.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {

    private final ProductJpaInterface productJpaInterface;

    public ProductDao(ProductJpaInterface productJpaInterface) {
        this.productJpaInterface = productJpaInterface;
    }

    public List<Product> all() {
        return productJpaInterface.findAllActiveProducts();
    }

    public List<Product> allActiveAndNotActive() {
        return productJpaInterface.findAll();
    }

    public void addNewProduct(Product product){
            product.setActive(true);
            productJpaInterface.save(product);
    }

    public Product findProductById(Long id) {
        return productJpaInterface.findById(id).get();
    }

    public void removeProductByName(Long id) {
       Product product = productJpaInterface.getById(id);
       if(product.getActive()==true){
           product.setActive(false);
       } else {
           product.setActive(true);
       }
        productJpaInterface.save(product);
    }

    public void editProduct(Product product){
        product.setActive(true);
        productJpaInterface.save(product);
    }

}
