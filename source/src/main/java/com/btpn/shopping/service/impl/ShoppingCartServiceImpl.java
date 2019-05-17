/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.service.impl;

import com.btpn.shopping.domain.Customer;
import com.btpn.shopping.domain.Product;
import com.btpn.shopping.domain.ShoppingCart;
import com.btpn.shopping.domain.ShoppingCartId;
import com.btpn.shopping.dto.ProductDto;
import com.btpn.shopping.dto.ResponseDto;
import com.btpn.shopping.dto.ShoppingDto;
import com.btpn.shopping.repository.CustomerRepository;
import com.btpn.shopping.repository.ProductRepository;
import com.btpn.shopping.repository.ShoppingCartRepository;
import com.btpn.shopping.service.ShoppingCartService;
import com.btpn.shopping.util.ResponseCodeEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author 18055244
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CustomerRepository customerRepository;    
    
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    
    @Override
    @Transactional
    public ResponseDto addChart(Long userId, Long productId, int unit) {
        Customer customer = customerRepository.findById(userId).orElse(null);
        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Customer Not Found", null); 
        
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Product Not Found", null); 
        
        if(product.getStock() < unit) return new ResponseDto<>(ResponseCodeEnum.STOCK_NOT_AVAILABLE, "Max Unit Add " + product.getStock(), null); 
        
        ShoppingCartId id = new ShoppingCartId(userId, productId);
        ShoppingCart chart = shoppingCartRepository.findById(id).orElse(null);
        if(chart != null) chart.setCount(chart.getCount() + unit);
        else chart = new ShoppingCart(id, customer, product, unit);
        
        product.setStock(product.getStock() - unit);
        product = productRepository.save(product);
        chart = shoppingCartRepository.save(chart);
        
        return new ResponseDto<>(ResponseCodeEnum.SUCCESS, chart); 
    }

    @Override
    @Transactional
    public ResponseDto removeChart(Long userId, Long productId, int unit) {
        Customer customer = customerRepository.findById(userId).orElse(null);
        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Customer Not Found", null); 
        
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Product Not Found", null);
        
        ShoppingCartId id = new ShoppingCartId(userId, productId);
        ShoppingCart chart = shoppingCartRepository.findById(id).orElse(null);
        if(chart != null) chart.setCount(chart.getCount() - unit);
        else return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Chart Not Found", null);
        
        if(chart.getCount() <= 0) return new ResponseDto<>(ResponseCodeEnum.STOCK_NOT_AVAILABLE, "Max Unit Remove " +chart.getCount(), null); 
        
        
        
        product.setStock(product.getStock() + unit);
        productRepository.save(product);
        
        if(chart.getCount() == 0) {
            shoppingCartRepository.deleteById(id);
            chart = null;
        }
        else chart = shoppingCartRepository.save(chart);
        
        return new ResponseDto<>(ResponseCodeEnum.SUCCESS, chart); 
    }

    @Override
    public ResponseDto<List<ShoppingDto>> getAll(Long userId) {
        List<ShoppingCart> findAll = shoppingCartRepository.findAll(new Specification<ShoppingCart>() {
            @Override
            public Predicate toPredicate(Root<ShoppingCart> root, CriteriaQuery<?> cq, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if(userId != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id").get("userId"), userId));
                }
                return predicate;
            }
        });
        
        Map<Customer, List<ProductDto>> mapChart = new HashMap<>();
        for (ShoppingCart shoppingCart : findAll) {
            List<ProductDto> productDtos = mapChart.get(shoppingCart.getCustomer());
            if(productDtos == null) productDtos = new ArrayList<>();
            
            productDtos.add(new ProductDto(shoppingCart.getProduct(), shoppingCart.getCount()));
            mapChart.put(shoppingCart.getCustomer(), productDtos);
        }
        findAll.clear();
        
        List<ShoppingDto> dtos = new ArrayList<>();
        for (Customer customer : mapChart.keySet()) {
            dtos.add(new ShoppingDto(customer, mapChart.get(customer)));
        }
        mapChart.clear();
        
        return new ResponseDto<>(ResponseCodeEnum.SUCCESS, dtos);
    }

    @Override
    public ResponseDto deleteChart(Long userId, Long productId) {
        Customer customer = customerRepository.findById(userId).orElse(null);
        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Customer Not Found", null); 
        
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        
        if(productId == null) {
            shoppingCarts = shoppingCartRepository.findAllByIdIdUser(userId);
        } else {
            Product product = productRepository.findById(productId).orElse(null);
            if(product == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Product Not Found", null);
        
            ShoppingCartId id = new ShoppingCartId(userId, productId);
            ShoppingCart chart = shoppingCartRepository.findById(id).orElse(null);
            if(chart == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, "Chart Not Found", null);
            
            shoppingCarts.add(chart);
        }
        
        for (ShoppingCart shoppingCart : shoppingCarts) {
            deleteProductChartByShopping(shoppingCart);
        }
        return new ResponseDto<>(ResponseCodeEnum.SUCCESS, null); 
    }
    
    private void deleteProductChartByShopping(ShoppingCart shoppingCart) {
        Product product = shoppingCart.getProduct();
        product.setStock(product.getStock() + shoppingCart.getCount());
        productRepository.save(product);
        
        shoppingCartRepository.delete(shoppingCart);
    }
    
}
