/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabio
 */
@Entity
@XmlRootElement
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Provider> listProviders = new ArrayList<>();

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addProvider(Provider p) {
        listProviders.add(p);
    }

    public void rmeoveProvider(Provider p) {
        listProviders.remove(p);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    public List<Provider> getListProviders() {
        return listProviders;
    }

    public void setListProviders(List<Provider> listProviders) {
        this.listProviders = listProviders;
    }

    @Override
    public String toString() {
        return "{\"productId\":\"" + productId + "\", \"Name\":\"" + name + "\",\"Price\":\"" + price + "\","
                + "\"Description\":\"" + description + "\",\"imageURL\":\"" + imageUrl + "\"" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.productId);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.listProviders);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.listProviders, other.listProviders)) {
            return false;
        }
        return true;
    }

}
