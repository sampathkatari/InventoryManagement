package com.inventorymanagement.model.ui;

/**
 * Created by sampathkatari on 30/06/18.
 */
public class DashboardStatsDto {
    private long totalProducts;
    private long totalBrands;
    private long totalSuppliers;
    private long totalStockQuantity;
    private double totalStockPrice;
    public DashboardStatsDto() {

    }

    private DashboardStatsDto(Builder builder) {
        setTotalProducts(builder.totalProducts);
        setTotalBrands(builder.totalBrands);
        setTotalSuppliers(builder.totalSuppliers);
        setTotalStockQuantity(builder.totalStockQuantity);
        setTotalStockPrice(builder.totalStockPrice);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalBrands() {
        return totalBrands;
    }

    public void setTotalBrands(long totalBrands) {
        this.totalBrands = totalBrands;
    }

    public long getTotalSuppliers() {
        return totalSuppliers;
    }

    public void setTotalSuppliers(long totalSuppliers) {
        this.totalSuppliers = totalSuppliers;
    }

    public long getTotalStockQuantity() {
        return totalStockQuantity;
    }

    public void setTotalStockQuantity(long totalStockQuantity) {
        this.totalStockQuantity = totalStockQuantity;
    }

    public double getTotalStockPrice() {
        return totalStockPrice;
    }

    public void setTotalStockPrice(double totalStockPrice) {
        this.totalStockPrice = totalStockPrice;
    }

    public static final class Builder {
        private long totalProducts;
        private long totalBrands;
        private long totalSuppliers;
        private long totalStockQuantity;
        private double totalStockPrice;

        private Builder() {
        }

        public Builder totalProducts(long val) {
            totalProducts = val;
            return this;
        }

        public Builder totalBrands(long val) {
            totalBrands = val;
            return this;
        }

        public Builder totalSuppliers(long val) {
            totalSuppliers = val;
            return this;
        }

        public Builder totalStockQuantity(long val) {
            totalStockQuantity = val;
            return this;
        }

        public Builder totalStockPrice(double val) {
            totalStockPrice = val;
            return this;
        }

        public DashboardStatsDto build() {
            return new DashboardStatsDto(this);
        }
    }
}
