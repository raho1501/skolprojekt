package beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-19T01:10:28")
@StaticMetamodel(Budget.class)
public class Budget_ { 

    public static volatile SingularAttribute<Budget, Date> dateTime;
    public static volatile SingularAttribute<Budget, Integer> amount;
    public static volatile SingularAttribute<Budget, Integer> budgetId;
    public static volatile SingularAttribute<Budget, String> info;

}