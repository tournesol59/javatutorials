// En utilisant jdbc
package com.baeldung.demo;

import com.baeldung.model.Customer;
import com.baelding.Login_Model;
import com.baeldung.base.*;
import com.baeldung.demo.JdbcConnectionAccessor; // replaces ClassJdbcDataAccessor in previous version
import com.baeldung.dao.CustomerDAO;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.Level;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class QuickTestJdbc {
   // global variable (equiv to static) must be outside of main: important

   public org.apache.logging.log4j.Logger logger;
   
   @Bean
   public IDatabaseAccess baseAccess() {
       return new DatabaseMySQLAccess();
   }
   
   @Bean(name={"base"})
   public Runnable baseTask() {
	   return new JdbcConnectionAccessor();
   }

// une chose a la fois
//   @Bean(name={"login"})
//   public Runnable loginTask() {
//	   return new Login_Model();
//   }
   
   @Bean
   public BufferedReader readDataSupplier() {
      try {
     	   return new BufferedReader( InputStreamReader(new FileReader("C:\\Users\\Fr�d�ric\\Documents\\eclipse\\eclipse-newimport\\CustomerJDBCTasks\\src\\main\\resources\\customerdbtest.properties")));
      } catch (IOException e) {
    	 logger.log(Level.DEBUG, "LoggerName :: "+instquick.logger.getName()+":: connection object created");
      }
   }

   @Bean(name="file")
   public Runnable readDataTask {
        return new ReaderService();
   }

   public QuickTestJdbc() {
   	   logger = LogManager.getLogger(QuickTestJdbc.class);
	   custdao = new CustomerDAO();
   }

   public Connection connection;

   public List<Map<String, String>> list_customers_toevent=null;
   
   public CustomerDAO custdao;

   // main method:
   public static void main(String args[]) {

    QuickTestJdbc instquick = new QuickTestJdbc();
	try {
      // perform Bean assembly and run baseTask()
	    AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(QuickTestJdbc.class);
		JdbcConnectionAccessor jdbcAcc = appCtx.getBean("base");
		jdbcAcc.run();

      // get Connection:
		instquick.connection = jdbcAcc.getJdbcConnection();
    	instquick.logger.log(Level.DEBUG, "LoggerName :: "+instquick.logger.getName()+":: connection object created");

    } 
	catch (ClassNotFoundException e) {
    	instquick.logger.log(Level.DEBUG, "LoggerName :: "+instquick.logger.getName()+":: Bean used for Connection Factory not created"); 
	 }
        catch (Exception e) {
        // logger
        instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: connection failed");
        e.printStackTrace();
    }
    // now try login task
    /*
	try {
	    AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(QuickTestJdbc.class);
		Login_Model lm = appCtx.getBean("login");
		lm.run();
        instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: login result is "+lm.getStatus());		
	} catch (Exception e) {
        // logger
        instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: login operation failed");
        e.printStackTrace();
	}
*/
    // begin of a new method TBD: 
    instquick.list_customers_toevent = instquick.getAllCustomers();
  
    Map<String, String> custmap = instquick.list_customers_toevent.get(0);
    String cust_name = custmap.get("customer_name");
    String cust_contact = "new contact";
    Customer customer1 = new Customer(cust_name, cust_contact);
    if (instquick.custdao.updateEntity(1, customer1) != 0) {
	    System.out.println("update operation error");
    }
}

// *** end main *** //

}
