package com.btpn.shopping.controller;

import com.btpn.shopping.domain.Customer;
import com.btpn.shopping.dto.CustomerAddRequest;
import com.btpn.shopping.dto.CustomerUpdateRequest;
import com.btpn.shopping.dto.ResponseDto;
import com.btpn.shopping.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customer")
@Api(tags = "customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @ApiOperation(value = "Lihat semua customer yang terdaftar")
    public ResponseEntity<ResponseDto<List<Customer>>> find(
            @ApiParam(value = "Nama Customer", required = false) @RequestParam(required = false) String search,
            @ApiParam(value = "Status Customer {0 atau 1}", required = false) @RequestParam(required = false) Integer status) {
        return ResponseEntity.ok(customerService.getAll(search, status));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Lihat detail customer yang terdaftar")
    public ResponseEntity<ResponseDto<Customer>> detail(
            @ApiParam(value = "ID Customer", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.get(id));
    }

    @PostMapping
    @ApiOperation(value = "Menambah / mendaftarkan customer")
    public ResponseEntity<ResponseDto> save(@Valid @RequestBody CustomerAddRequest request) {
        return ResponseEntity.ok(customerService.add(request.getName(), request.getAddress()));
    }

    @PutMapping
    @ApiOperation(value = "Update data customer yang telah terdaftar")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody CustomerUpdateRequest org) {
        return ResponseEntity.ok(customerService.update(org.getId(), org.getName(), org.getAddress()));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Menonaktifkan customer yang telah terdaftar")
    public ResponseEntity<ResponseDto> delete(
            @ApiParam(value = "ID Customer", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.updateStatus(id, 0));
    }

}
