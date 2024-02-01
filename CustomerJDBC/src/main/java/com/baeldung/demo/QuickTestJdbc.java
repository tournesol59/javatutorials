// En utilisant jdbc
package com.baeldung.demo;

import com.baeldung.model.Customer;
import com.baeldung.demo.ClassJdbcDataAccessor;
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


public class QuickTestJdbc {
   // global variable (equiv to static) must be outside of main: important

   public org.apache.logging.log4j.Logger logger;

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
	  // get Connection:
    	 instquick.connection = ClassJdbcDataAccessor.getInstance().getJdbcConnection();
    	 instquick.logger.log(Level.DEBUG, "LoggerName :: "+instquick.logger.getName()+":: connection object created");
      }
    catch (Exception e) {
        // logger
        instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: connection failed");
       e.printStackTrace();
    }

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
// method list all customers at all and perform resultSet metadata verifications
   public List<Map<String,String>> getAllCustomers() {
     try {
    	 // ... //
	 Statement stmt = connection.createStatement();
         int customer_id=1;
    	 ResultSet result = stmt.executeQuery("select t1.customer_name as custname, t1.customer_contact as contact FROM tblcustomers as t1 "); // t1.customer_email as email, ... where t1.customer_id="+customer_id);	 

	// other way: preparedStatement
	// PreparedStatement pstmt = connection.prepareStatement("select t1.customer_name as custname, t1.customer_email as email, t1.customer_contact as contact FROM tblcustomers as t1 where t1.customer_id=?");
	//
	// pstmt.setInt(0,1);	
	//
	// ResultSet result = pstmt.executeQuery();

    	 logger.log(Level.DEBUG, "LoggerName :: "+ logger.getName()+":: request executed");

	 // inspect data types for each column
    	 ResultSetMetaData dma = result.getMetaData();
    	 int colcount= dma.getColumnCount();
 	 System.out.println(colcount + " columns");    	 
    	 //String[] names = new String[10];
	 String[] sqlcols = new String[10];
	 int[] sqltyp = new int[10];
    	 boolean[] sqlbool = new boolean[10];
    	 for (int i=0; i<colcount; i++) {
	     sqlcols[i]= dma.getColumnName(i);
	     sqltyp[i]= dma.getColumnType(i);
    	     sqlbool[i]= dma.isReadOnly(i);
    	     System.out.println("! loop "+i+", "+sqlcols[i]+", "+sqlbool[i]);
    	 }    	 
  	 // transfer results to map
    	 int count=0;
	 List<Map<String, String>> list_customers = new ArrayList<>();
    	 while (result.next()) {
	     Map<String, String> cust = new HashMap<String,String>();
      	     cust.put("customer_name", result.getString(1));
       	     cust.put("customer_contact", result.getString(2));           	             count++;
             list_customers.add(cust);
    		 
     	     logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: row "+count+", "+cust.get("customer_name")+", "+cust.get("customer_contact"));
    	 }
	 result.close();
         return list_customers;

     } catch (SQLException e) {
        // logger
        logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: extract from result customer query failed");
	return null;
     }
} // end method getAllCustomers


// method list all customers that are invited to an event
   public List<Map<String,String>> getAllCustomersToEvent(int event_id)  {
      try {
	 // a mettre en commentaires tant que la table n'est pas creee dans la BDD
	 List<Map<String, String>> list_participants = new ArrayList<>();
	 PreparedStatement pstmt = connection.prepareStatement("select t1.customer_name as custname, t1.customer_contact as custcontact t3.event_title FROM tblcustomers as t1 RIGHT OUTER JOIN (SELECT t2.event_title as event_title, t4.customer_event_customer, t2.event_id FROM tblevents as t2 INNER JOIN tblcustomer_event as t4 ON t4.customer_event_event = t2.event_id) as t3 ON t1.customer_id = t3.customer_event_customer WHERE t3.event_id ="+event_id);
// or	 pstmt.setParam(1,event_id);
    	 // play a little with this big query
    	 ResultSet result = pstmt.executeQuery();
    	 int count=0;
	 
    	 while (result.next()) {
	     Map<String, String> custpart = new HashMap<String,String>();
      	     custpart.put("customer_name", result.getString(1));
	     custpart.put("customer_contact", result.getString(2));
       	     custpart.put("event_title", result.getString(3));           	             count++;
             list_participants.add(custpart);
    		 
     	     logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: row "+count+", "+custpart.get("customer_name")+", "+custpart.get("customer_contact")+ ", "+custpart.get("event_title"));
    	 }
	 result.close();

         // TBC
	 return list_participants;
      } catch (Exception e) {
      	      logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: extract from result customer participants to event "+event_id+ " query failed");

         return null;
      }
   } // end method getAllCustomersToEvent

}
