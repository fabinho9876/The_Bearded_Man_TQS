/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Product;
import Entities.Provider;
import Facades.ProductFacade;
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
@Path("product")
public class ProductFacadeREST extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ProductFacadeREST() {
        super(Product.class);
    }
    @EJB
    private ProductFacade facade = new ProductFacade();

    @EJB
    private ProviderFacade pfacade = new ProviderFacade();

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Product> findAll() {
        return super.findAll();
    }

    @GET
    @Path("findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findall() {
        List<Product> productList = facade.findAll();
        String output = productList.toString();
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("add/{nome}/{price}/{description}/{url}/{category}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response add(@PathParam("nome") String name, @PathParam("price") double price,
            @PathParam("description") String description, @PathParam("url") String url,
            @PathParam("category") String category) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(url);
        product.setCategory(category);
        facade.create(product);

        return Response.status(201).entity("{\"State\":\"OK\"}").build();
    }

    @GET
    @Path("findById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {

        Product desiredProduct = facade.find(id);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        String output = desiredProduct.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") long id) {

        Product desiredProduct = facade.find(id);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        facade.remove(desiredProduct);
        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @POST
    @Path("edit/{id}/{nome}/{price}/{description}/{url}/{category}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") long id, @PathParam("nome") String name, @PathParam("price") double price,
            @PathParam("description") String description, @PathParam("url") String url,
            @PathParam("category") String category) {

        Product desiredProduct = facade.find(id);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        desiredProduct.setName(name);
        desiredProduct.setDescription(description);
        desiredProduct.setPrice(price);
        desiredProduct.setImageUrl(url);
        desiredProduct.setCategory(category);
        facade.edit(desiredProduct);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @POST
    @Path("addProdToProvider/{prod}/{provider}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response newProvider(@PathParam("provider") long id, @PathParam("prod") long prodId) {

        Provider desiredProvider = pfacade.find(id);
        if (desiredProvider == null) {
            return Response.status(204).entity("{\"State\":\"Provider does not exist\"}").build();
        }
        Product desiredProduct = facade.find(prodId);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        if (desiredProduct.getListProviders().contains(desiredProvider)) {
            return Response.status(204).entity("{\"State\":\"Provider is already in the list\"}").build();
        }
        desiredProduct.getListProviders().add(desiredProvider);
        facade.edit(desiredProduct);

        return Response.status(201).entity("{\"State\":\"OK\"}").build();
    }

    @DELETE
    @Path("delete/{prod}/{provider}")
    public Response remProvider(@PathParam("provider") long id, @PathParam("prod") long prodId) {

        Provider desiredProvider = pfacade.find(id);
        if (desiredProvider == null) {
            return Response.status(204).entity("{\"State\":\"Provider does not exist\"}").build();
        }
        Product desiredProduct = facade.find(prodId);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        if (!desiredProduct.getListProviders().contains(desiredProvider)) {
            return Response.status(204).entity("{\"State\":\"Provider is not in the list\"}").build();
        }
        desiredProduct.getListProviders().remove(desiredProvider);
        facade.edit(desiredProduct);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @GET
    @Path("findProductToProvider/providers/{prod}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProvider(@PathParam("prod") long prodId) {

        Product desiredProduct = facade.find(prodId);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        String output = desiredProduct.getListProviders().toString();

        return Response.status(200).entity(output).build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
