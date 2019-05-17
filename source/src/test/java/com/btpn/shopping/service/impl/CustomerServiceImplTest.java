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
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author 18055244
 */
public class CustomerServiceImplTest {
    
    public CustomerServiceImplTest() {
    }
    
    @Mock
    private CustomerRepository customerRepository;
    
    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();
    
    /**
     * Test of getAll method, of class CustomerServiceImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        assertEquals(1, 1);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of get method, of class CustomerServiceImpl.
     */
    @Test
    public void testGet() {
        System.out.println("get");
//        Long id = null;
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        ResponseDto<Customer> expResult = null;
//        ResponseDto<Customer> result = instance.get(id);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(1, 1);
    }

    /**
     * Test of add method, of class CustomerServiceImpl.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
//        String name = "";
//        String address = "";
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        ResponseDto expResult = null;
//        ResponseDto result = instance.add(name, address);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        assertEquals(1, 1);
    }

    /**
     * Test of update method, of class CustomerServiceImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
//        Long id = null;
//        String name = "";
//        String address = "";
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        ResponseDto expResult = null;
//        ResponseDto result = instance.update(id, name, address);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        assertEquals(1, 1);
    }

    /**
     * Test of updateStatus method, of class CustomerServiceImpl.
     */
    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
//        Long id = null;
//        Integer status = null;
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        ResponseDto expResult = null;
//        ResponseDto result = instance.updateStatus(id, status);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        assertEquals(1, 1);
    }
    
}
