/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.dto;

import com.btpn.shopping.domain.Customer;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 18055244
 */
public class ShoppingDto implements Serializable {

    private Customer customer;
    private List<ProductDto> chart;
    private double total = 0.0;

    public ShoppingDto(Customer customer, List<ProductDto> chart, double total) {
        this.customer = customer;
        this.chart = chart;
        this.total = total;
    }

    public ShoppingDto(Customer customer, List<ProductDto> chart) {
        this.customer = customer;
        this.chart = chart;
        for (ProductDto shoppingDto : chart) this.total += shoppingDto.getSubTotal();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductDto> getChart() {
        return chart;
    }

    public void setChart(List<ProductDto> chart) {
        this.chart = chart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
