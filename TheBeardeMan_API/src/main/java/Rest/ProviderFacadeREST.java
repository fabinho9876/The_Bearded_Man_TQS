/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Provider;
import Facades.ProviderFacade;
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
@Path("provider")
public class ProviderFacadeREST extends AbstractFacade<Provider> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ProviderFacadeREST() {
        super(Provider.class);
    }

    @EJB
    private ProviderFacade facade = new ProviderFacade();

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Provider> findAll() {
        return super.findAll();
    }

    @GET
    @Path("findall")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findall() {

        List<Provider> providerList = facade.findAll();
        String output = providerList.toString();
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("add/{nome}/{email}/{password}/{adress}/{ssn}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response add(@PathParam("nome") String name, @PathParam("email") String email, @PathParam("password") String password,
            @PathParam("adress") String adress, @PathParam("ssn") int ssn) {

        Provider provider = new Provider();
        provider.setName(name);
        provider.setEmail(email);
        provider.setPassword(password);
        provider.setAddress(adress);
        facade.create(provider);

        return Response.status(201).entity("{\"State\":\"OK\"}").build();
    }

    @GET
    @Path("find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {

        Provider desiredProvider = facade.find(id);
        if (desiredProvider == null) {
            return Response.status(204).entity("{\"State\":\"Provider does not exist\"}").build();
        }
        String output = desiredProvider.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") long id) {

        Provider desiredProvider = facade.find(id);
        if (desiredProvider == null) {
            return Response.status(204).entity("{\"State\":\"Provider does not exist\"}").build();
        }
        facade.remove(desiredProvider);
        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @POST
    @Path("edit/{id}/{nome}/{email}/{password}/{adress}/{ssn}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") long id, @PathParam("nome") String name, @PathParam("email") String email, @PathParam("password") String password,
            @PathParam("adress") String adress, @PathParam("ssn") int ssn) {

        Provider desiredProvider = facade.find(id);
        if (desiredProvider == null) {
            return Response.status(204).entity("{\"State\":\"Provider does not exist\"}").build();
        }
        desiredProvider.setName(name);
        desiredProvider.setEmail(email);
        desiredProvider.setPassword(password);
        desiredProvider.setAddress(adress);
        facade.edit(desiredProvider);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
