/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.service;

import com.btpn.shopping.domain.Customer;
import com.btpn.shopping.dto.ResponseDto;
import java.util.List;

/**
 *
 * @author 18055244
 */
public interface CustomerService {
    ResponseDto<List<Customer>> getAll(String name, Integer status);
    ResponseDto<Customer> get(Long id);
    ResponseDto add(String name, String address);
    ResponseDto update(Long id, String name, String address);
    ResponseDto updateStatus(Long id, Integer status);
}
