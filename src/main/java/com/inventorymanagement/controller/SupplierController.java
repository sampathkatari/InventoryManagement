package com.inventorymanagement.controller;

import com.inventorymanagement.dao.ProductDao;
import com.inventorymanagement.dao.ReportDao;
import com.inventorymanagement.dao.SupplierDao;
import com.inventorymanagement.dao.SupplierProductsDao;
import com.inventorymanagement.model.db.Report;
import com.inventorymanagement.model.db.Supplier;
import com.inventorymanagement.model.db.SupplierProducts;
import com.inventorymanagement.model.ui.*;
import com.inventorymanagement.services.MailService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sampathkatari on 18/06/18.
 */
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SupplierProductsDao supplierProductsDao;

    @Autowired
    private MailService mailService;

    @Autowired
    private ReportDao reportDao;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @RequestMapping(value = "", method =  RequestMethod.GET)
    public ResponseEntity<?> getAll(final HttpServletRequest request) {
        List<SupplierDto> collect = supplierDao.findAll()
                .stream()
                .filter(supplier -> supplier.isActive())
                .map(supplier -> SupplierDto.newBuilder()
                        .id(supplier.getId())
                        .name(supplier.getName())
                        .address(supplier.getAddress())
                        .email(supplier.getEmail())
                        .createdOn(supplier.getCreatedOn())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @RequestMapping(value = "/{supplierId}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("supplierId") final String supplierId) {
        Supplier one = supplierDao.findOne(Integer.parseInt(supplierId));
        return ResponseEntity.ok(supplierDbToDto(one));
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public ResponseEntity<?> verifyEmail(@RequestParam("id") final String id){
        Supplier one = supplierDao.findOne(Integer.parseInt(id));
        if(one == null) {
            return ResponseEntity.ok().body("Unable to verify you email.");
        }
        one.setActive(true);
        supplierDao.save(one);
        return ResponseEntity.ok("Your account has been verify. Your products will be listed from now.");
    }

    public static SupplierDto supplierDbToDto(final Supplier supplier) {
        return SupplierDto.newBuilder()
                .id(supplier.getId())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .email(supplier.getEmail())
                .createdOn(supplier.getCreatedOn())
                .build();
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody final SupplierDto supplierDto) {
        if(supplierDao.findBySupplierName(supplierDto.getName().toLowerCase()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setCreatedOn(LocalDateTime.now());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setEmail(supplierDto.getEmail());
        Supplier save = supplierDao.save(supplier);
        mailService.sendVerificationEmail(supplierDto.getEmail(), save.getId());
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

    @RequestMapping(value = "/{supplierId}/products", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody final SupplierProductDto supplierProductDto) {
        SupplierProducts supplierProducts = new SupplierProducts();
        supplierProducts.setProduct(productDao.getOne(supplierProductDto.getProductId()));
        supplierProducts.setSupplier(supplierDao.getOne(supplierProductDto.getSupplierId()));
        supplierProducts.setQuantity(supplierProductDto.getQuantity());
        supplierProducts.setPrice(supplierProductDto.getPrice());
        supplierProductsDao.save(supplierProducts);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{supplierId}/products", method = RequestMethod.GET)
    public ResponseEntity<?> getProducts(@PathVariable("supplierId") final String supplierId) {
        List<SupplierProducts> bySupplierId = supplierProductsDao.findBySupplierId(Integer.parseInt(supplierId));
        List<SupplierProductResponseDto> list = bySupplierId.stream().map(supplierProducts -> supplierProductDbtoDto(supplierProducts))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    public static SupplierProductResponseDto supplierProductDbtoDto(final SupplierProducts supplierProducts){
        return SupplierProductResponseDto.newBuilder()
                .id(supplierProducts.getId())
                .product(ProductController.productDbToDto(supplierProducts.getProduct()))
                .quantity(supplierProducts.getQuantity())
                .price(supplierProducts.getPrice())
                .build();
    }

    @RequestMapping(value = "/{supplierId}/products/{supplierProductId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuantity(@PathVariable("supplierProductId") final String supplierProductId,
                                            @RequestBody final SupplierProductDto supplierProductDto) {
        SupplierProducts supplierProducts = supplierProductsDao.findOne(Integer.parseInt(supplierProductId));
        supplierProducts.setQuantity(supplierProductDto.getQuantity());
        supplierProductsDao.save(supplierProducts);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{supplierId}/products/{supplierProductId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSupplierProduct(@PathVariable("supplierProductId") final String supplierProductId) {
        SupplierProducts supplierProducts = supplierProductsDao.findOne(Integer.parseInt(supplierProductId));
        SupplierProductResponseDto supplierProductResponseDto = supplierProductDbtoDto(supplierProducts);
        return ResponseEntity.ok(supplierProductResponseDto);
    }

    @RequestMapping(value = "/{supplierId}/products/checkout", method = RequestMethod.POST)
    public ResponseEntity<?> checkout(@PathVariable("supplierId") final String supplierId,
                                      @RequestBody final ProductCheckoutDTO productCheckoutDTO) {
        final SupplierProducts one = supplierProductsDao.findOne(productCheckoutDTO.getSelectedSupplierProductId());
        if(productCheckoutDTO.getQuantity() > one.getQuantity()) {
            return ResponseEntity.badRequest().build();
        }
        int updatedQuantity = one.getQuantity()-productCheckoutDTO.getQuantity();
        if(updatedQuantity < 25) {
            Supplier supplier = supplierDao.findOne(Integer.parseInt(supplierId));
            mailService.sendEmail(supplier.getEmail(), one.getProduct().getName());
            Report report = new Report();
            report.setProductName(one.getProduct().getName());
            report.setSupplierEmail(supplier.getEmail());
            report.setQuantity(updatedQuantity);
            report.setLocalDateTime(LocalDateTime.now());
            reportDao.save(report);
        }
        one.setQuantity(updatedQuantity);
        supplierProductsDao.save(one);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<?> report(@RequestParam("startDate") final String startDate,
                                    @RequestParam("endDate") final String endDate) {


        LocalDateTime start = LocalDateTime.of(LocalDate.parse(startDate, formatter), LocalTime.of(0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.parse(endDate, formatter), LocalTime.of(23, 59));
        final List<Report> reportList = reportDao.findAll().stream().filter(report -> {
            return (report.getLocalDateTime().compareTo(start) > 0 && report.getLocalDateTime().compareTo(end) < 0);
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(reportList);
    }
}
