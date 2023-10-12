package com.baeldung.login;

import com.baeldung.model.Customer;
import com.baeldung.login.Login;
//import org.springframework.stereotype.Component;

//@Component
public interface LoginService {

    public Customer createCustomerLogin(Login login);
}
