package Entities;

import Entities.Client;
import Entities.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-22T10:42:24")
@StaticMetamodel(Sale.class)
public class Sale_ { 

    public static volatile SingularAttribute<Sale, Long> saleId;
    public static volatile ListAttribute<Sale, Product> listProduct;
    public static volatile SingularAttribute<Sale, Client> client;

}