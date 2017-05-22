/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Client;
import Entities.ShoppingCar;
import Facades.ClientFacade;
import Facades.StoreUserFacade;
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
@Path("client")
public class ClientFacadeREST extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ClientFacadeREST() {
        super(Client.class);
    }
    @EJB
    private ClientFacade facade = new ClientFacade();

    @EJB
    private StoreUserFacade facadeU = new StoreUserFacade();

    @GET
    @Override
    public List<Client> findAll() {
        List<Client> clientList = facade.findAll();
        return clientList;
    }

    @POST
    @Path("add/{nome}/{email}/{adress}/{password}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response add(@PathParam("nome") String name, @PathParam("email") String email,
            @PathParam("password") String password, @PathParam("adress") String adress) {
        if (!facade.validEmail(email)) {
            return Response.status(204).entity("{\"State\":\"Duplicated User\"}").build();
        }
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        client.setAddress(adress);
        client.setCar(new ShoppingCar());
        facade.create(client);

        return Response.status(201).entity("{\"State\":\"OK\"}").build();
    }

    @GET
    @Path("find/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("email") String email) {

        if (!facade.clientExists(email)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = facade.getClientByEmail(email);
        String output = desiredClient.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("delete/{email}")
    public Response delete(@PathParam("email") String email) {

        if (!facade.clientExists(email)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = facade.getClientByEmail(email);
        facade.remove(desiredClient);
        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @POST
    @Path("edit/{id}/{nome}/{email}/{adress}/{password}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") String emailId, @PathParam("nome") String name,
            @PathParam("email") String email, @PathParam("password") String password,
            @PathParam("adress") String adress) {

        if (!facade.clientExists(emailId)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = facade.getClientByEmail(emailId);
        desiredClient.setName(name);
        desiredClient.setEmail(email);
        desiredClient.setPassword(password);
        desiredClient.setAddress(adress);

        facade.edit(desiredClient);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
