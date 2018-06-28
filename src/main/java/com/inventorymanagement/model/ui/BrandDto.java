package com.inventorymanagement.model.ui;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sampathkatari on 26/06/18.
 */
public class BrandDto {
    private int id;
    private String name;
    private LocalDateTime createdOn;
    public BrandDto(){

    }

    private BrandDto(Builder builder) {
        setId(builder.id);
        setName(builder.name);
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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public static final class Builder {
        private int id;
        private String name;
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

        public Builder createdOn(LocalDateTime val) {
            createdOn = val;
            return this;
        }

        public BrandDto build() {
            return new BrandDto(this);
        }
    }
}
