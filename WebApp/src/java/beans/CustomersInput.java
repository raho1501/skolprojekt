/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author markus
 */

@Named(value = "customersInput")
@RequestScoped
public class CustomersInput
{

	private int age;
	private String name;
	@Inject
	CustomerManagedBean customerManagedBean;
	
	/**
	 * Creates a new instance of CustomersInput
	 */

	public CustomersInput()
	{
	}
	
	public void setCustomerManagedBean(CustomerManagedBean customerManagedBean)
	{
		this.customerManagedBean = customerManagedBean;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String submit()
	{
		Customers cust = new Customers();
		cust.setAge((short)age);
		cust.setName(name);
		int id = 0;
		
		List<Customers> customers = customerManagedBean.getCustomers();
		if(customers.size() > 0)
		{
			int last = customers.size() - 1;
			id = customers.get(last).getId() + 1;
		}
		cust.setId(id);
		
		customerManagedBean.addCustomer(cust);
		
		return "index";
	}
}
