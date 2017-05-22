/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Manager;
import Facades.ManagerFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author fabio
 */
@Stateless
@Path("manager")
public class ManagerFacadeREST extends AbstractFacade<Manager> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ManagerFacadeREST() {
        super(Manager.class);
    }

    @EJB
    private ManagerFacade facade = new ManagerFacade();

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Manager> findAll() {
        return super.findAll();
    }

    @GET
    @Path("findall")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findall() {

        List<Manager> managerList = facade.findAll();
        String output = managerList.toString();
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("add/{nome}/{email}/{adress}/{password}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response add(@PathParam("nome") String name, @PathParam("email") String email,
            @PathParam("password") String password, @PathParam("adress") String adress) {
        Manager manager = new Manager();
        manager.setName(name);
        manager.setEmail(email);
        manager.setPassword(password);
        manager.setAddress(adress);
        facade.create(manager);

        return Response.status(201).entity("{\"State\":\"OK\"}").build();
    }

    @GET
    @Path("findbyID/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {

        Manager desiredManager = facade.find(id);
        if (desiredManager == null) {
            return Response.status(204).entity("{\"State\":\"Manager does not exist\"}").build();
        }
        String output = desiredManager.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") long id) {

        Manager desiredManager = facade.find(id);
        if (desiredManager == null) {
            return Response.status(204).entity("{\"State\":\"Manager does not exist\"}").build();
        }
        facade.remove(desiredManager);
        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @POST
    @Path("edit/{id}/{nome}/{email}/{adress}/{password}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") long id, @PathParam("nome") String name,
            @PathParam("email") String email, @PathParam("password") String password,
            @PathParam("adress") String adress) {

        Manager desiredManager = facade.find(id);
        if (desiredManager == null) {
            return Response.status(204).entity("{\"State\":\"Manager does not exist\"}").build();
        }
        desiredManager.setName(name);
        desiredManager.setEmail(email);
        desiredManager.setPassword(password);
        desiredManager.setAddress(adress);
        facade.edit(desiredManager);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
