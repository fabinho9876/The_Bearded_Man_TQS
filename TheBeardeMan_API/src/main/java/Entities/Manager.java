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
public class Manager extends StoreUser {

    public Manager() {
        super();
    }

    public Manager(int SSN, String email, String password) {
        super(email, password);
    }

    @Override
    public String toString() {
        return "{\"ManagerId\":\"" + super.getUserId() + "\", \"Name\":\"" + super.getName() + "\", \"Email\":\"" + super.getEmail() + "\","
                + " \"Address\":\"" + super.getAddress() + "\"}";
    }
}
