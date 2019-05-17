/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.service.impl;

import com.btpn.shopping.domain.Product;
import com.btpn.shopping.dto.ResponseDto;
import com.btpn.shopping.repository.ProductRepository;
import com.btpn.shopping.service.ProductService;
import com.btpn.shopping.util.ResponseCodeEnum;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author 18055244
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public ResponseDto<List<Product>> getAll(String name) {
        
        List<Product> findAll = productRepository.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if(name != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), name.toUpperCase()));
                }
                return predicate;
            }
        });
        
        return new ResponseDto<>(ResponseCodeEnum.SUCCESS, findAll);
    }

    @Override
    public ResponseDto<Product> get(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, product);
        else return new ResponseDto<>(ResponseCodeEnum.SUCCESS, product);
    }

    @Override
    public ResponseDto add(String name, Integer initialStock, Double price) {
        List<Product> products = productRepository.findAllByName(name);
        if(products != null && !products.isEmpty()) return new ResponseDto<>(ResponseCodeEnum.USER_ALREADY_EXIST, name, null); 
        
        Product product = new Product(name, initialStock, price);
        product = productRepository.save(product);
        if(product == null) return new ResponseDto<>(ResponseCodeEnum.GENERAL_ERROR, null);
        else return new ResponseDto<>(ResponseCodeEnum.SUCCESS, product);
    }

    @Override
    public ResponseDto update(Long id, String name, Integer stock, Double price) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "product", null);
    
        if(name != null) product.setName(name);
        if(price != null) {
            if(price <= 0.0) return new ResponseDto<>(ResponseCodeEnum.PARAMETER_NOT_ALLOWED, "price <= 0", null);
            product.setPrice(price);
        }
        if(stock != null) {
            if(stock < 0) return new ResponseDto<>(ResponseCodeEnum.PARAMETER_NOT_ALLOWED, "stock < 0", null);
            product.setStock(stock);
        }
         
        product = productRepository.save(product);

        if(product == null) return new ResponseDto<>(ResponseCodeEnum.GENERAL_ERROR, null);
        else return new ResponseDto<>(ResponseCodeEnum.SUCCESS, product);
    }
}
