/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btpn.shopping.repository;

import com.btpn.shopping.domain.ShoppingCart;
import com.btpn.shopping.domain.ShoppingCartId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author 18055244
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId>, JpaSpecificationExecutor<ShoppingCart> {
    List<ShoppingCart> findAllByIdIdUser(Long idUser);
}
