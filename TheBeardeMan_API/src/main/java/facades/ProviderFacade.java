/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Provider;
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
public class ProviderFacade extends AbstractFacade<Provider> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProviderFacade() {
        super(Provider.class);
    }

    public boolean providerExists(String email) {
        Query query = em.createQuery("SELECT c FROM Provider c WHERE c.email = '" + email + "'");
        List<Provider> c = query.getResultList();
        return !c.isEmpty();
    }

    public boolean validLogin(String user, String password) {

        if (providerExists(user)) {
            Query query = em.createQuery("SELECT c FROM Provider c WHERE c.email = '" + user + "' "
                    + "AND c.password = '" + password + "'");
            Provider c = (Provider) query.getSingleResult();

            if (password.equals(c.getPass())) {
                System.out.println("logged as provider");
                return true;
            }
            return false;
        }

        return false;
    }

    public Provider getProviderByEmail(String userName) {
        Query query = em.createQuery("SELECT c FROM Provider c WHERE c.email = '" + userName + "'");
        Provider c = (Provider) query.getSingleResult();
        return c;
    }
}
