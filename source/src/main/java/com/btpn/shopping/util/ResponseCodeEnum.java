/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.util;

/**
 *
 * @author 18055244
 */
public enum ResponseCodeEnum {
    SUCCESS("00","Succes"),
    NOT_FOUND("01", "User Not Found"),
    USER_ALREADY_EXIST("02", "User Already Exist"),
    PARAMETER_NOT_ALLOWED("03", "Parameter Not Allowed"),
    STOCK_NOT_AVAILABLE("04", "Stock Not Available"),
    GENERAL_ERROR("05","General Error"),
    TIME_OUT("89", "Time Out"),
    LINK_DOWN("91", "Link Down");
    
    private String code;
    private String description;

    private ResponseCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
