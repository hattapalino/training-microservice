/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.service;

import com.btpn.shopping.domain.Product;
import com.btpn.shopping.dto.ResponseDto;
import java.util.List;

/**
 *
 * @author 18055244
 */
public interface ProductService {
    ResponseDto<List<Product>> getAll(String name);
    ResponseDto<Product> get(Long id);
    ResponseDto add(String name, Integer initialStock, Double price);
    ResponseDto update(Long id, String name, Integer stock, Double price);
}
