package com.ecommerce.dto;


public class OrderRequest {
    private String shippingAddress;

    public OrderRequest() {}

    public OrderRequest(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public static OrderRequestBuilder builder() {
        return new OrderRequestBuilder();
    }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public static class OrderRequestBuilder {
        private String shippingAddress;

        public OrderRequestBuilder shippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; return this; }

        public OrderRequest build() {
            return new OrderRequest(shippingAddress);
        }
    }
}
