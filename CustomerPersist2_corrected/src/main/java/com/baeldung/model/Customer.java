package com.baeldung.model;

import java.util.List;
import java.util.Set;
import com.baeldung.login.Login;
import com.baeldung.model.Event;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;

@Component
@Entity
@Table( name="tblcustomers" )
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @GenericGenerator(name="increment", strategy="increment")
    private int customerId;
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
 /*   private Login customerLogin; // see after
    public Login getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(Login customerLogin) {
        this.customerLogin = customerLogin;
    }
*/
    @Column(name = "customer_name" )    
    private String customerName;
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "customer_contact" )  
    private String customerContact;
    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }
    @Column(name = "customer_email" )      
    private String customerEmail;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public Customer() {
        super();
    }

    public Customer(int id, Set<Login> logins, String name, String contact, String email) {
        super();
	this.customerId = id;
	this.customerLogins = logins;
	this.customerName = name;
	this.customerContact = contact;
	this.customerEmail = email;
    }

    public void test() {
        System.out.println(customerName);
    }

    public List<Customer> getAllCustomers() {
        System.out.println("Customer.getAllCustomers called");
        return null;
    }    
/*
    public Set<Login> getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(Set<Login> logins) {
        this.customerEvents = logins;
    }
*/
    // fred : this below solution is not performancegood but shouldwork


    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Login> customerLogins; // but has unique
     
    public Set<Login> getCustomerLogins() {
	    return customerLogins;
    }
    
    public void setCustomerLogins(Set<Login> logins) {
        this.customerLogins=logins;
    }

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Event> customerEvents;
    
    public Set<Event> getCustomerEvents() {
        return customerEvents;
    }

    public void setCustomerEvents(Set<Event> events) {
        this.customerEvents = events;
    }

}

