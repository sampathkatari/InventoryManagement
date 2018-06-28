package com.inventorymanagement.model.ui;

import java.time.LocalDateTime;

/**
 * Created by sampathkatari on 26/06/18.
 */
public class ProductDto {
    private int id;
    private String name;
    private int brandId;
    private String brandName;
    private LocalDateTime createdOn;
    public ProductDto() {

    }

    private ProductDto(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setBrandId(builder.brandId);
        setBrandName(builder.brandName);
        setCreatedOn(builder.createdOn);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public static final class Builder {
        private int id;
        private String name;
        private int brandId;
        private String brandName;
        private LocalDateTime createdOn;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder brandId(int val) {
            brandId = val;
            return this;
        }

        public Builder brandName(String val) {
            brandName = val;
            return this;
        }

        public Builder createdOn(LocalDateTime val) {
            createdOn = val;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }
}
