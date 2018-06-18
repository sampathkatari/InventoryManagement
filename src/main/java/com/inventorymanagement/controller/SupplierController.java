package com.inventorymanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sampathkatari on 18/06/18.
 */
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> get() {

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create() {

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
