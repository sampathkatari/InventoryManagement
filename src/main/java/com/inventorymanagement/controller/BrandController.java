package com.inventorymanagement.controller;

import com.inventorymanagement.dao.BrandDao;
import com.inventorymanagement.model.db.Brand;
import com.inventorymanagement.model.ui.BrandDto;
import com.inventorymanagement.model.ui.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sampathkatari on 18/06/18.
 */
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandDao brandDao;

    @RequestMapping(value = "", method =  RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<BrandDto> collect = brandDao.findAll()
                .stream()
                .map(brand -> BrandDto.newBuilder()
                        .id(brand.getId())
                        .name(brand.getName())
                        .createdOn(brand.getCreatedOn())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
    @RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("brandId") final String brandId) {
        Brand one = brandDao.findOne(Integer.parseInt(brandId));
        BrandDto build = BrandDto.newBuilder()
                .name(one.getName())
                .createdOn(one.getCreatedOn())
                .id(one.getId())
                .build();
        return ResponseEntity.ok().body(build);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Brand brand) {
        if(brandDao.findByName(brand.getName().toLowerCase()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        brand.setCreatedOn(LocalDateTime.now());
        return ResponseEntity.ok(brandDao.save(brand));
    }

    @RequestMapping(value = "/{brandId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("brandId") final int brandId, @RequestBody Brand brand) {
        Brand one = brandDao.getOne(brandId);
        one.setName(brand.getName());
        return ResponseEntity.ok(brandDao.save(one));
    }

    @RequestMapping(value = "/{brandId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("brandId") final int brandId) {
        brandDao.delete(brandDao.getOne(brandId));
        return ResponseEntity.ok().build();
    }
}
