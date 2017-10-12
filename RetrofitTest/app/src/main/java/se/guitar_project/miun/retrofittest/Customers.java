package se.guitar_project.miun.retrofittest;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2017-10-11.
 */
@Root(name = "customers")
public class Customers {
    @ElementList(name = "customer", inline=true)
    private List<Customer> CustomerList = new ArrayList<>();
    public int size(){
        return CustomerList.size();
    }
    public Customer getCustomer(int index)
    {
        return CustomerList.get(index);
    }

}
