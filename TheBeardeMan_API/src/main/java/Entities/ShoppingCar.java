/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author fabio
 */
public class ShoppingCar implements Serializable {

    private List<Product> prods;
    private static final long serialVersionUID = 746972771702940782L;

    public ShoppingCar() {
        prods = new ArrayList<>();
    }

    public void addProductAmount(Product p, int amount) {
        for (int i = 0; i < amount; i++) {
            prods.add(p);
        }
    }

    public void addProduct(Product p) {
        prods.add(p);
    }

    public void removeProduct(Product p) {
        prods.remove(p);
    }

    public void clearProducts() {
        prods.clear();
    }

    public Product getProduct(Product p) {
        return prods.get(prods.indexOf(p));
    }

    public Product getProduct(long proId) {
        Product searchProduct = null;
        for (Product p : prods) {
            if (p.getProductId() == proId) {
                searchProduct = p;
            }
        }
        return searchProduct;
    }

    public List<Product> getProducts() {
        return cloneList(prods);
    }

    private List<Product> cloneList(List<Product> list) {
        List<Product> clone = new ArrayList<>();
        for (Iterator<Product> it = list.iterator(); it.hasNext();) {
            clone.add(it.next());
        }
        return clone;
    }

    public int getProductAmount(Product p) {
        int amount = 0;
        for (Product prod : prods) {
            if (prod == p) {
                amount++;
            }
        }
        return amount;
    }

    @Override
    public String toString() {
        return prods.toString();
    }
}
