/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author 18055244
 */
public class ProductAddRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Stock is mandatory")
    private int stock;
    @NotBlank(message = "Price is mandatory")
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
