package com.inventorymanagement.dao;

import com.inventorymanagement.model.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sampathkatari on 18/06/18.
 */
@Transactional(readOnly = true)
public interface ProductDao extends JpaRepository<Product, Integer> {
}
