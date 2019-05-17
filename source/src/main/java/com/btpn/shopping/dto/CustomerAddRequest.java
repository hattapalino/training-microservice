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
public class CustomerAddRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "Address is mandatory")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
