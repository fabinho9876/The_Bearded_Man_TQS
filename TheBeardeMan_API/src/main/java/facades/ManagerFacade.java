/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Manager;
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
public class ManagerFacade extends AbstractFacade<Manager> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagerFacade() {
        super(Manager.class);
    }

    public boolean managerExists(String email) {

        Query query = em.createQuery("SELECT c FROM Manager c WHERE c.email = '" + email + "'");
        List<Manager> c = query.getResultList();
        return !c.isEmpty();
    }

    public boolean validLogin(String user, String password) {

        if (managerExists(user)) {
            Query query = em.createQuery("SELECT c FROM Manager c WHERE c.email = '" + user + "' "
                    + "AND c.password = '" + password + "'");
            Manager c = (Manager) query.getSingleResult();

            if (password.equals(c.getPass())) {
                System.out.println("logged as manager");
                return true;
            }
            return false;
        }

        return false;
    }
}
