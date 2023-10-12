package com.baeldung.service;

import com.baeldung.model.Customer;
import com.baeldung.login.Login;
import com.baeldung.login.LoginService;

import java.util.List;
import org.springframework.stereotype.Component;

public interface CustomerService {

 //   private Customer cust;  // error and however private is nonsense in an interface 

    public Customer createCustomer(Customer customer);
    
 //   public List<Customer> getAllCustomers();
 //   //fred

 //   @Autowired
 //   public void setCustomer();
 // //  int searchCustomer(CustomerSearchForm customerSearchForm);

     public Customer findCustomer(List<Customer> list, int id); // hide for the moment as we have only one instance 

  //  void updateCustomer(Customer customer);

}
