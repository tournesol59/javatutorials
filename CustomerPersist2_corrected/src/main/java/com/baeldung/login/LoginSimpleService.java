package com.baeldung.login;

import com.baeldung.model.Customer;
//import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


//@Component
public class LoginSimpleService implements LoginService {

    public Customer createCustomerLogin(Login login)   {
       try {
           Customer customer=new Customer();
	   Set<Login> loginset = new HashSet<Login>();
	   loginset.add(login);
           customer.setCustomerLogins(loginset);
	   return customer;
       }
       catch (Exception e) {
          e.printStackTrace();
	  return null;
       }
    }
}
