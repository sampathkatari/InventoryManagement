package com.inventorymanagement.model.ui;

/**
 * Created by sampathkatari on 28/06/18.
 */
public class ProductCheckoutDTO {
    private int selectedSupplierProductId;
    private int quantity;
    public ProductCheckoutDTO(){

    }

    private ProductCheckoutDTO(Builder builder) {
        setSelectedSupplierProductId(builder.selectedSupplierProductId);
        setQuantity(builder.quantity);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getSelectedSupplierProductId() {
        return selectedSupplierProductId;
    }

    public void setSelectedSupplierProductId(int selectedSupplierProductId) {
        this.selectedSupplierProductId = selectedSupplierProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static final class Builder {
        private int selectedSupplierProductId;
        private int quantity;

        private Builder() {
        }

        public Builder selectedSupplierProductId(int val) {
            selectedSupplierProductId = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public ProductCheckoutDTO build() {
            return new ProductCheckoutDTO(this);
        }
    }
}
