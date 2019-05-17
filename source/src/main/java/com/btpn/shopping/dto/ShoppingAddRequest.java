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
public class ShoppingAddRequest {

    @NotBlank(message = "unit is mandatory")
    private int unit;
    @NotBlank(message = "userId is mandatory")
    private Long userId;
    @NotBlank(message = "productId is mandatory")
    private Long productId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
