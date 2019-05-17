package com.btpn.shopping.controller;

import com.btpn.shopping.domain.Product;
import com.btpn.shopping.dto.ProductAddRequest;
import com.btpn.shopping.dto.ProductUpdateRequest;
import com.btpn.shopping.dto.ResponseDto;
import com.btpn.shopping.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("product")
@Api(tags = "product")
public class ProductController extends BaseController {
 
    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation(value = "Lihat semua product yang terdaftar")
    public ResponseEntity<ResponseDto<List<Product>>> find(
            @ApiParam(value = "Nama Product", required = false) @RequestParam(required = false) String search) {
        return ResponseEntity.ok(productService.getAll(search));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Lihat detail product yang terdaftar")
    public ResponseEntity<ResponseDto<Product>> detail(
            @ApiParam(value = "ID Product", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.get(id));
    }
    
    @PostMapping
    @ApiOperation(value = "Tambah product yang akan didaftar")
    public ResponseEntity<ResponseDto> save(@Valid @RequestBody ProductAddRequest request) {
        return ResponseEntity.ok(productService.add(request.getName(), request.getStock(), request.getPrice()));
    }

    @PutMapping
    @ApiOperation(value = "Update informasi product yang terdaftar")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody ProductUpdateRequest org) {
        return ResponseEntity.ok(productService.update(org.getId(), org.getName(), org.getStock(), org.getPrice()));
    }
    
    @DeleteMapping("{id}")
    @ApiOperation(value = "Kosongkan stock product yang terdaftar")
    public ResponseEntity<ResponseDto> delete(
            @ApiParam(value = "ID Product", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.update(id, null, 0, null));
    }    

}
