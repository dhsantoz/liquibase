package com.elfz.liquibaseh2;

import com.elfz.liquibaseh2.domain.Image;
import com.elfz.liquibaseh2.domain.Product;
import com.elfz.liquibaseh2.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
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
