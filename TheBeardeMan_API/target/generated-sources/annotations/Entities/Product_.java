package Entities;

import Entities.Provider;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-22T10:42:24")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile ListAttribute<Product, Provider> listProviders;
    public static volatile SingularAttribute<Product, Long> productId;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, String> imageUrl;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, String> category;

}