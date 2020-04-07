package com.elfz.liquibasepsql;

import com.elfz.liquibasepsql.domain.Image;
import com.elfz.liquibasepsql.domain.Product;
import com.elfz.liquibasepsql.domain.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional // tx for each test rolls back by default
public class ValidationTest {

    @Autowired
    ProductRepository productRepository;


    @Test
    public void testApplicationPersistence() {

        Product product = buildProduct();
        Product savedProduct = productRepository.save(product);

        Assertions.assertAll(
                () -> assertNotNull(savedProduct.getId()),
                () -> assertNotNull(savedProduct.getImages().stream().findFirst().get().getId()),
                () -> assertNotNull(savedProduct.getSubProducts().stream().findFirst().get().getId()),
                () -> assertEquals("Default image", savedProduct.getImages().stream().findFirst().get().getType()),
                () -> assertEquals("Child Product", savedProduct.getSubProducts().stream().findFirst().get().getName()));
    }

    private Image buildImage() {
        Image im = new Image();
        im.setType("Default image");
        return im;
    }

    private Product buildChildProduct() {
        Product parent = new Product();
        parent.setName("Child Product");
        parent.setDescription("This is a child product");
        return parent;
    }

    private Product buildProduct() {
        Product parent = new Product();
        parent.setName("Parent Product");
        parent.setDescription("This is a parent product");

        parent.addImage(buildImage());
        parent.addSubProduct(buildChildProduct());

        return parent;
    }


}