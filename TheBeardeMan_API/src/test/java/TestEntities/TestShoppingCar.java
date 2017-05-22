/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestEntities;

import Entities.Product;
import Entities.ShoppingCar;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author matos
 */
public class TestShoppingCar {
    private ShoppingCar car;
    public TestShoppingCar() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    @Before
    public void setUp() {
        car = new ShoppingCar();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testAddProduct(){
        Product p = new Product(); 
        car.addProduct(p);
        assertEquals(p, car.getProduct(p));
    }
    
    @Test
    public void getProductAmount(){
        Product p1 = new Product();
        car.addProduct(p1);
        car.addProduct(p1);
        assertEquals(2, car.getProductAmount(p1));
        assertEquals(0, car.getProductAmount(new Product()));
    }
    
    @Test
    public void testGetProducts(){
        Product p1 = new Product();
        Product p2 = new Product();
        car.addProduct(p1);
        car.addProduct(p2);
        List<Product> prodsExpected = new ArrayList<>();
        prodsExpected.add(p1);
        prodsExpected.add(p2);
        assertEquals(prodsExpected, car.getProducts());
    }
    
    @Test
    public void testClear(){
        ShoppingCar carToClear = new ShoppingCar();
        Product p1 = new Product();
        carToClear.addProduct(p1);
        carToClear.clearProducts();
        assertEquals(0, carToClear.getProducts().size());
    }
    
    @Test
    public void testAddAmountAnswerCorrectAmount(){
        Product someProduct = new Product();
        car.addProductAmount(someProduct, 3);
        assertEquals(3, car.getProductAmount(someProduct));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
