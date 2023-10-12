package com.baeldung.controller;

import com.baeldung.controller.HibernateUtilTBCC;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


import com.baeldung.login.Login;
import com.baeldung.model.Event;
import com.baeldung.model.Customer;
import com.baeldung.login.Login;
import com.baeldung.service.CustomerService;
import com.baeldung.service.CustomerSimpleService;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;


import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class CustomerManager {

   private HibernateUtilTBCC hibernateUtil=null;

   private static final Logger LOGGER = LogManager.getLogger(CustomerManager.class);

   public static void main(String[] args) {

      CustomerManager mgr = new CustomerManager();     
      mgr.hibernateUtil = new HibernateUtilTBCC();

      String varg="list";
      if (varg.equals("store")) {   
    	  // create a new entity
	     mgr.createAndStoreCustomer("My Event", new Date());
      }
      else if (varg.equals("list")) {
    	  // get all event entities
         List<Event> events = mgr.listEvents();
         for (int i=0; i < events.size(); i++) {
             Event theEvent = (Event) events.get(i);
	         System.out.println("Event: "+ theEvent.getTitle() + 
			     " Time: " + theEvent.getDate());
	 }
      }
      else if (varg.equals("logins")) {
	    // List<Customer> customers = new ArrayList<Customer>();
	     Customer theCustomer = mgr.listCustomersByLogin(1);
 
	     System.out.println("Customer: "+theCustomer.getCustomerName() + " email: "+theCustomer.getCustomerEmail());
      }
      SessionFactory sessionFactory = mgr.hibernateUtil.getSessionFactory();
      sessionFactory.close();
   }

   private void createAndStoreCustomer(String title, Date theDate) {
//      Configuration config = new Configuration();  // prefer wrapper HibernateUtilTBCC
      Session session = hibernateUtil.getSessionFactory().openSession();

      session.beginTransaction();

      Login login1 = new Login(3,"liberty");
      Customer customer1 = new Customer();

      customer1.setCustomerName("Granger");
      customer1.setCustomerContact("lezard");
      customer1.setCustomerEmail("liz.granger@gmail.com");

      Set<Login> loginset = new HashSet<Login>();

      loginset.add(login1);

      customer1.setCustomerLogins(loginset);

      session.save(customer1);

      session.getTransaction().commit();

      LOGGER.info("The query of data from Database has succeded\n");
   }

   private List<Event> listEvents() {
      Session session = hibernateUtil.getSessionFactory().openSession();
 
      session.beginTransaction();

      List<Event> result = session.createQuery("from Event as event").list();

      session.getTransaction().commit();

      LOGGER.info("The query of data from Database has succeded\n");

      return result;
   }

    private Customer  listCustomersByLogin(int cust_id) {
      Session session = hibernateUtil.getSessionFactory().openSession();

      session.beginTransaction();

      List<Customer> listCustomer = session.createQuery("select cust from Customer cust where cust.is=:cid").setParameter("cid", cust_id).list();
     // Query query = session.createQuery("select cust from Customer cust left join fetch cust.customerLogin where cust.id = :cid").setParameter("cid", cust_id); // TODO after implementation of Login one-to-one
     // Query query = session.createQuery("from Customer as cust where cust.id = :cid").setParameter("cid", cust_id);

      Customer aCustomer = (Customer) listCustomer.get(0);

      session.getTransaction().commit();

      LOGGER.info("The query of data from Database has succeded\n");

      return aCustomer;      
    }

}

