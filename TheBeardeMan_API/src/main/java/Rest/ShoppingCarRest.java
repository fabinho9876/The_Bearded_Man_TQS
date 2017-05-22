package Rest;

import Facades.ClientFacade;
import Facades.ProductFacade;
import Entities.Client;
import Entities.Product;
import Entities.ShoppingCar;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("cart")
public class ShoppingCarRest extends AbstractFacade<ShoppingCar> {

    @PersistenceContext(unitName = "com.mycompany_TheBeardeMan_API_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ShoppingCarRest(Class<ShoppingCar> entityClass) {
        super(entityClass);
    }
    
    @EJB
    private ClientFacade cfacade = new ClientFacade();

    @EJB
    private ProductFacade pfacade = new ProductFacade();
    
   
    @POST
    @Path("addProduct/{client}/{product}")
    public Response addProd(@PathParam("product") long prod, @PathParam("client") String client) {

        Product desiredProduct = pfacade.find(prod);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        if (!cfacade.clientExists(client)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = cfacade.getClientByEmail(client);
        desiredClient.getCar().addProduct(desiredProduct);
        cfacade.edit(desiredClient);

        return Response.status(201).entity("{\"State\":\"OK\"}").build();
    }

    @DELETE
    @Path("removePoduct/{client}/{product}")
    public Response remProd(@PathParam("product") long prod, @PathParam("client") String client) {

        Product desiredProduct = pfacade.find(prod);
        if (desiredProduct == null) {
            return Response.status(204).entity("{\"State\":\"Product does not exist\"}").build();
        }
        if (!cfacade.clientExists(client)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = cfacade.getClientByEmail(client);
        desiredClient.getCar().removeProduct(desiredProduct);
        cfacade.edit(desiredClient);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @DELETE
    @Path("ClaerCart/empty/{client}")
    public Response empty(@PathParam("client") String client) {

        if (!cfacade.clientExists(client)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = cfacade.getClientByEmail(client);
        desiredClient.getCar().clearProducts();
        cfacade.edit(desiredClient);

        return Response.status(200).entity("{\"State\":\"OK\"}").build();
    }

    @GET
    @Path("find/{client}")
    public Response find(@PathParam("client") String client) {

        if (!cfacade.clientExists(client)) {
            return Response.status(204).entity("{\"State\":\"Client does not exist\"}").build();
        }
        Client desiredClient = cfacade.getClientByEmail(client);
        String output = desiredClient.getCar().toString();

        return Response.status(200).entity(output).build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
