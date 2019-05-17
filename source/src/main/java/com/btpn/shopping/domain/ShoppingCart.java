package com.btpn.shopping.domain;
// Generated May 7, 2019 9:56:28 AM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ShoppingCart generated by hbm2java
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements java.io.Serializable {

    private ShoppingCartId id;
    private Customer customer;
    private Product product;
    private int count;

    public ShoppingCart() {
    }

    public ShoppingCart(ShoppingCartId id, Customer customer, Product product, int count) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.count = count;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "idUser", column = @Column(name = "id_user", nullable = false)), 
        @AttributeOverride(name = "idProduct", column = @Column(name = "id_product", nullable = false))})
    public ShoppingCartId getId() {
        return this.id;
    }

    public void setId(ShoppingCartId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product", nullable = false, insertable = false, updatable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "count", nullable = false)
    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}