package com.inventorymanagement.model.ui;

import com.inventorymanagement.model.db.SupplierProducts;

/**
 * Created by sampathkatari on 26/06/18.
 */
public class SupplierProductDto {
    private int id;
    private int productId;
    private int supplierId;
    private int quantity;
    private String productName;
    public SupplierProductDto() {

    }

    private SupplierProductDto(Builder builder) {
        setId(builder.id);
        setProductId(builder.productId);
        setSupplierId(builder.supplierId);
        setQuantity(builder.quantity);
        setProductName(builder.productName);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public static final class Builder {
        private int id;
        private int productId;
        private int supplierId;
        private int quantity;
        private String productName;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder productId(int val) {
            productId = val;
            return this;
        }

        public Builder supplierId(int val) {
            supplierId = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder productName(String val) {
            productName = val;
            return this;
        }

        public SupplierProductDto build() {
            return new SupplierProductDto(this);
        }
    }
}
