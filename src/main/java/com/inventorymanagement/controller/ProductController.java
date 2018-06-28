package com.inventorymanagement.controller;

import com.inventorymanagement.dao.BrandDao;
import com.inventorymanagement.dao.ProductDao;
import com.inventorymanagement.model.db.Product;
import com.inventorymanagement.model.ui.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by sampathkatari on 18/06/18.
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private BrandDao brandDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "", method =  RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<ProductDto> collect = productDao.findAll()
                .stream()
                .map(product -> ProductDto.newBuilder()
                        .id(product.getId())
                        .name(product.getName())
                        .brandId(product.getBrand().getId())
                        .brandName(product.getBrand().getName())
                        .createdOn(product.getCreatedOn())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("productId")final String productId) {
        Product one = productDao.findOne(Integer.parseInt(productId));
        if(one == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ProductDto productDto = productDbToDto(one);
        return ResponseEntity.ok(productDto);
    }

    public static ProductDto productDbToDto(final Product product) {
        return ProductDto.newBuilder()
                .brandId(product.getBrand().getId())
                .createdOn(product.getCreatedOn())
                .id(product.getId())
                .name(product.getName())
                .build();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(brandDao.findOne(productDto.getBrandId()));
        product.setCreatedOn(LocalDateTime.now());
        productDao.save(product);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> update() {

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete() {

        return ResponseEntity.ok().build();
    }
}
