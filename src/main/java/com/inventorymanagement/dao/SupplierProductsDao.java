package com.inventorymanagement.dao;

import com.inventorymanagement.model.db.SupplierProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sampathkatari on 26/06/18.
 */
@Transactional(readOnly = true)
public interface SupplierProductsDao extends JpaRepository<SupplierProducts, Integer> {
    @Query("select p from SupplierProducts p where p.supplier.id= ?1")
    List<SupplierProducts> findBySupplierId(final int supplierId);
}
