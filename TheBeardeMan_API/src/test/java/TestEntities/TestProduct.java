/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestEntities;

import Entities.Product;
import Entities.Provider;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matos
 */
public class TestProduct {
    private Product prod;
    private List<Provider> provList;
    private Provider provider;
    public TestProduct() {
        prod = new Product();
        provider = new Provider();
        provList = new ArrayList<>();
    }
    
    
    @Before
    public void setUp() {
        provider.setName("a");
        provList.add(provider);
        provider = new Provider();
        provider.setName("b");
        provList.add(provider);
        prod.setListProviders(provList);
    }
    
    @Test
    public void testGetProviders() {
        assertEquals(provList, prod.getListProviders());
    }
    
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
