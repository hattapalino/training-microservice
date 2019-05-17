/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.service.impl;

import com.btpn.shopping.domain.Customer;
import com.btpn.shopping.dto.ResponseDto;
import com.btpn.shopping.repository.CustomerRepository;
import com.btpn.shopping.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public ResponseDto<List<Customer>> getAll(String name, Integer status) {
        
        List<Customer> findAll = customerRepository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if(name != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), name.toUpperCase()));
                }
                if(status != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
                }
                
                return predicate;
            }
        });
        
        return new ResponseDto<>(ResponseCodeEnum.SUCCESS, findAll);
    }

    @Override
    public ResponseDto<Customer> get(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, customer);
        else return new ResponseDto<>(ResponseCodeEnum.SUCCESS, customer);
    }

    @Override
    public ResponseDto add(String name, String address) {
        List<Customer> customers = customerRepository.findAllByName(name);
        if(customers != null && !customers.isEmpty()) return new ResponseDto<>(ResponseCodeEnum.USER_ALREADY_EXIST, name, null); 
        
        Customer customer = new Customer(name, address, 1);
        customer = customerRepository.save(customer);
        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.GENERAL_ERROR, null);
        else return new ResponseDto<>(ResponseCodeEnum.SUCCESS, customer);
    }

    @Override
    public ResponseDto update(Long id, String name, String address) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, null); 

        if(name != null)    customer.setName(name);
        if(address != null) customer.setAddress(address);

        customer = customerRepository.save(customer);

        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.GENERAL_ERROR, null);
        else return new ResponseDto<>(ResponseCodeEnum.SUCCESS, customer);
    }

    @Override
    public ResponseDto updateStatus(Long id, Integer status) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.NOT_FOUND, null); 

        customer.setStatus(status);
        customer = customerRepository.save(customer);

        if(customer == null) return new ResponseDto<>(ResponseCodeEnum.GENERAL_ERROR, null);
        else return new ResponseDto<>(ResponseCodeEnum.SUCCESS, customer);
    }
    
}
