/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestEntities;

import Entities.Client;
import Entities.Product;
import Entities.Sale;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matos
 */
public class TestSale {
    private List<Product> prodList;
    private Client cli;
    private Sale sale;
    private Product p;
    public TestSale() {
        prodList = new LinkedList<>();
        cli = new Client();
        sale = new Sale();
        
    }
    
    @Before
    public void setUp() {
        p = new Product();
        p.setName("a");
        p.setPrice(1);
        prodList.add(p);
        p = new Product();
        p.setName("b");
        p.setPrice(1);
        prodList.add(p);
        cli.setName("manel");
        sale.setListProduct(prodList);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetAmountToPay(){
        assertEquals(2.0, sale.getTotalPayedAmount(), 0);
    }
    
    @Test
    public void testTotalProductAmount(){
        assertEquals(2, sale.getTotalproductAmount());
    }
    
    @Test
    public void testProductAmount(){
        assertEquals(1, sale.getProductAmount(p));
    }

    @Test
    public void testGetProducts(){
        assertEquals(prodList, sale.getListProduct());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
