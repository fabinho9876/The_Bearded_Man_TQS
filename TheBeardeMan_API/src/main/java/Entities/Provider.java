/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabio
 */
@Entity
@XmlRootElement
public class Provider extends StoreUser {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> prods;

    public Provider() {
        super();
    }

    public Provider(String email, String password) {
        super(email, password);

        prods = new ArrayList<>();
    }

    public void addProduct(Product p) {
        prods.add(p);
    }

    public void removeProduct(Product p) {
        prods.remove(p);
    }

    @Override
    public String toString() {
        return "{\"ProviderId\":\"" + super.getUserId() + "\", \"Name\":\"" + super.getName() + "\", \"Address\":\""
                + super.getAddress() + "\"}";
    }
}
