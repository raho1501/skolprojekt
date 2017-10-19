package beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-19T01:10:28")
@StaticMetamodel(Shop.class)
public class Shop_ { 

    public static volatile SingularAttribute<Shop, Integer> price;
    public static volatile SingularAttribute<Shop, String> imageUrl;
    public static volatile SingularAttribute<Shop, Integer> shopId;
    public static volatile SingularAttribute<Shop, Integer> customerIdFk;
    public static volatile SingularAttribute<Shop, String> title;
    public static volatile SingularAttribute<Shop, String> info;

}