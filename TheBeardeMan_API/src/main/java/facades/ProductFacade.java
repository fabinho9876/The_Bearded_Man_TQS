/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    public List<Product> getProductsByCategory(String category) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.category = '" + category + "'");
        System.out.println(query.getResultList());
        return query.getResultList();
    }

    public List<Product> getProductsByName(String name) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE '%" + name + "%'");
        return query.getResultList();
    }

}
