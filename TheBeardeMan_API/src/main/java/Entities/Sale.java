/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabio
 */
@Entity
@XmlRootElement
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    @ManyToOne
    private Client client;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> listProduct;

    public Sale(Client c) {
        listProduct = new ArrayList<>();
        client = c;
    }

    public Sale() {
        listProduct = new ArrayList<>();
    }

    public Long getSaleId() {
        return saleId;
    }

    public Client getClient() {
        return client;
    }

    public double getTotalPayedAmount() {
        double amount = 0;
        for (Product prod : listProduct) {
            amount += prod.getPrice();
        }
        return amount;
    }

    public int getProductAmount(Product p) {
        int amount = 0;
        for (Product prod : listProduct) {
            if (prod == p) {
                amount++;
            }
        }
        return amount;
    }

    public int getTotalproductAmount() {
        return listProduct.size();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @XmlTransient
    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void addProduct(Product p) {
        listProduct.add(p);
    }

    public void removeProd(Product p) {
        listProduct.remove(p);
    }

    @Override
    public String toString() {
        return "{\"SaleId\":\"" + saleId + "\", \"ClientEmail\":\"" + client.getEmail() + "\", \"Products\":" + listProduct + "}";
    }
}
