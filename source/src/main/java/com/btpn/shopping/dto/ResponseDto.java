/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.dto;

import com.btpn.shopping.util.ResponseCodeEnum;

/**
 *
 * @author 18055244
 */
public class ResponseDto<T> {
    
    public String code;
    public String description;
    public T data;

    public ResponseDto() {
    }

    public ResponseDto(ResponseCodeEnum respCode, T data) {
        this.code = respCode.getCode();
        this.description = respCode.getDescription();
        this.data = data;
    }
    
    public ResponseDto(ResponseCodeEnum respCode, String additionalInfo, T data) {
        this.code = respCode.getCode();
        this.description = respCode.getDescription() + " : " + additionalInfo;
        this.data = data;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
