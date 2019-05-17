/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.service;

import com.btpn.shopping.dto.ResponseDto;
import com.btpn.shopping.dto.ShoppingDto;
import java.util.List;

/**
 *
 * @author 18055244
 */
public interface ShoppingCartService {
    ResponseDto<List<ShoppingDto>> getAll(Long userId);
    ResponseDto addChart(Long userId, Long productId, int unit);
    ResponseDto removeChart(Long userId, Long productId, int unit);
    ResponseDto deleteChart(Long userId, Long productId);
}
