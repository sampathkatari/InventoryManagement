package com.inventorymanagement.dao;


import com.inventorymanagement.model.db.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sampathkatari on 18/06/18.
 */
@Transactional(readOnly = true)
public interface SupplierDao extends JpaRepository<Supplier, Integer> {
    @Query("select p from Supplier p where p.name= ?1")
    Supplier findBySupplierName(final String supplierName);
}
