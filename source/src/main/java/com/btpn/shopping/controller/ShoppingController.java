package com.btpn.shopping.controller;

import com.btpn.shopping.dto.ResponseDto;
import com.btpn.shopping.dto.ShoppingDeleteRequest;
import com.btpn.shopping.dto.ShoppingDto;
import com.btpn.shopping.dto.ShoppingAddRequest;
import com.btpn.shopping.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("shopping")
@Api(tags = "shopping")
public class ShoppingController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    @ApiOperation(value = "Lihat semua daftar keranjang")
    public ResponseEntity<ResponseDto<List<ShoppingDto>>> find(
            @ApiParam(value = "ID User", required = false) @RequestParam(required = false) Long search) {
        return ResponseEntity.ok(shoppingCartService.getAll(search));
    }

    @PostMapping
    @ApiOperation(value = "Menambah keranjang")
    public ResponseEntity<ResponseDto> addShopping(@Valid @RequestBody ShoppingAddRequest request) {
        return ResponseEntity.ok(shoppingCartService.addChart(request.getUserId(), request.getProductId(), request.getUnit()));
    }

    @PutMapping
    @ApiOperation(value = "Mengurangi keranjang (bisa secara unit)")
    public ResponseEntity<ResponseDto> removeShopping(@Valid @RequestBody ShoppingAddRequest request) {
        return ResponseEntity.ok(shoppingCartService.removeChart(request.getUserId(), request.getProductId(), request.getUnit()));
    }
    
    @DeleteMapping
    @ApiOperation(value = "Menghapus keranjang per user per product")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody ShoppingDeleteRequest request) {
        return ResponseEntity.ok(shoppingCartService.deleteChart(request.getUserId(), request.getProductId()));
    }    

}
