/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Client;
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
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    public boolean validEmail(String email) {

        Query query = em.createQuery("SELECT c FROM Client c WHERE c.email = '" + email + "'");
        List<Client> c = query.getResultList();
        return c.size() < 1;
    }

    public boolean clientExists(String email) {

        Query query = em.createQuery("SELECT c FROM Client c WHERE c.email = '" + email + "'");
        List<Client> c = query.getResultList();
        return !c.isEmpty();
    }

    public Client getClientByEmail(String email) {

        Query query = em.createQuery("SELECT c FROM Client c WHERE c.email = '" + email + "'");
        Client c = (Client) query.getSingleResult();
        return c;
    }

    public boolean validLogin(String user, String password) {

        if (clientExists(user)) {
            Query query = em.createQuery("SELECT c FROM Client c WHERE c.email = '" + user + "' "
                    + "AND c.password = '" + password + "'");
            Client c = (Client) query.getSingleResult();

            if (password.equals(c.getPass())) {
                System.out.println("logged");
                return true;
            }
            return false;
        }

        return false;
    }

}
