/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.dto;

import com.btpn.shopping.domain.Product;

/**
 *
 * @author 18055244
 */
public class ProductDto {

    private Product product;
    private int unit;
    private double subTotal;

    public ProductDto(Product product, int unit) {
        this.product = product;
        this.unit = unit;
        this.subTotal = unit * product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

}
