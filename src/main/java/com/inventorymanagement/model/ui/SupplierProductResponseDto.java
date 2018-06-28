package com.inventorymanagement.model.ui;

/**
 * Created by sampathkatari on 26/06/18.
 */
public class SupplierProductResponseDto {
    private int id;
    private ProductDto product;
    private int quantity;
    public SupplierProductResponseDto()
    {

    }

    private SupplierProductResponseDto(Builder builder) {
        setId(builder.id);
        setProduct(builder.product);
        setQuantity(builder.quantity);
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

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static final class Builder {
        private int id;
        private ProductDto product;
        private int quantity;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder product(ProductDto val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public SupplierProductResponseDto build() {
            return new SupplierProductResponseDto(this);
        }
    }
}
