/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Client;
import Entities.Product;
import Entities.Sale;
import Facades.ClientFacade;
import Facades.ProductFacade;
import Facades.SaleFacade;
import java.util.ArrayList;
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
@Path("sale")
public class SaleFacadeREST extends AbstractFacade<Sale> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public SaleFacadeREST() {
        super(Sale.class);
    }
    @EJB
    private SaleFacade facade = new SaleFacade();

    @EJB
    private ClientFacade cfacade = new ClientFacade();

    @EJB
    private ProductFacade pfacade = new ProductFacade();

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sale> findAll() {
        return super.findAll();
    }

    @GET
    @Path("findall")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findall() {

        List<Sale> providerList = facade.findAll();
        String output = providerList.toString();
        return Response.status(200).entity(output).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("newSale/{client}")
    public Response newSale(@PathParam("client") String email) {

        if (!cfacade.clientExists(email)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = cfacade.getClientByEmail(email);
        Sale sale = new Sale();
        sale.setClient(desiredClient);
        sale.setListProduct(new ArrayList<Product>());
        facade.create(sale);

        return Response.status(201).entity("{\"State\":\"OK\"}").build();
    }

    @POST
    @Path("salaProduct/{sale}/{product}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response addProd(@PathParam("product") long prod, @PathParam("sale") long sale) {

        Product desiredProduct = pfacade.find(prod);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        Sale desiredSale = facade.find(sale);
        if (desiredSale == null) {
            return Response.status(204).entity("{\"State\":\"Sale does not exist\"}").build();
        }
        desiredSale.addProduct(desiredProduct);
        facade.edit(desiredSale);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @DELETE
    @Path("deleteSaleProduct/{sale}/{product}")
    public Response remProd(@PathParam("product") long prod, @PathParam("sale") long sale) {

        Product desiredProduct = pfacade.find(prod);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        Sale desiredSale = facade.find(sale);
        if (desiredSale == null) {
            return Response.status(204).entity("{\"State\":\"Sale does not exist\"}").build();
        }
        desiredSale.removeProd(desiredProduct);
        facade.edit(desiredSale);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @GET
    @Path("findById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {

        Sale desiredSale = facade.find(id);
        if (desiredSale == null) {
            return Response.status(204).entity("{\"State\":\"Sale does not exist\"}").build();
        }
        String output = desiredSale.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("deleteById/{id}")
    public Response delete(@PathParam("id") long id) {

        Sale desiredSale = facade.find(id);
        if (desiredSale == null) {
            return Response.status(204).entity("{\"State\":\"Sale does not exist\"}").build();
        }
        facade.remove(desiredSale);
        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @POST
    @Path("update/{id}/{client}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") long id, @PathParam("client") String email) {

        if (!cfacade.clientExists(email)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = cfacade.getClientByEmail(email);
        Sale desiredSale = facade.find(id);
        if (desiredSale == null) {
            return Response.status(204).entity("{\"State\":\"Sale does not exist\"}").build();
        }
        desiredSale.setClient(desiredClient);
        facade.edit(desiredSale);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
