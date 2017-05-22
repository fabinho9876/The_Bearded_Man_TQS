/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabio
 */
@Entity
@XmlRootElement
public class Client extends StoreUser {

    private ShoppingCar car;

    public Client() {
        super();
        car = new ShoppingCar();
    }

    public Client(String email, String password) {
        super(email, password);
        car = new ShoppingCar();
    }

    public ShoppingCar getCar() {
        return car;
    }

    public void setCar(ShoppingCar car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "{\"ClientId\":\"" + super.getUserId() + "\", \"Name\":\"" + super.getName() + "\", \"Email\":\"" + super.getEmail() + "\","
                + " \"Address\":\"" + super.getAddress() + "}";
    }
}
