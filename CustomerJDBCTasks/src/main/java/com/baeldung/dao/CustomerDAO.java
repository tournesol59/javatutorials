package com.baeldung.dao;
// fred bien penser a auto increment dans la BD sans quoi complications inutile
// gerer le log aussi
//
import com.baeldung.model.Customer;
//
import com.baeldung.demo.ClassJdbcDataAccessor;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class CustomerDAO implements IEntityDAO<Customer> {
   org.apache.logging.log4j.Logger logger;
   Connection connect;
   public CustomerDAO() {}

   public Customer getEntity(int id)  {
     ResultSet result;
     try {
 
       connect = ClassJdbcDataAccessor.getInstance().getJdbcConnection();
     } catch (Exception e) {
        logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: failed to establish a connection");
     }
     try {
      Statement stmt = connect.createStatement();

      result = stmt.executeQuery("SELECT * FROM tblcustomers");

     // just load the fields and call a new Customer(..) instance
     //   customer_id
     //   customer_name
     //   customer_firstname
     //   customer_contact
     //   customer_email
       Customer customer = new Customer("Delphine", "Guadeloupe");
       return customer;
     } catch (SQLException e) {
        logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: failed to load a Customer row with id: "+id);
        return null;
     }
   }
   
   public List<Map<String,String>> getAllEntity() {
	   List<Map<String, String>> listresult = new ArrayList<>();
     // same thing as above but perform a while result.next
           try {
//		   preparedStatement pstmt
		   return listresult;
	   }
	   catch (Exception e) {
                   System.out.println("error Customer DAO getAllEntity");
		   return null;
	   }
   }

// same method result as getAllEntity but checks validity of column names
// method list all customers at all and perform resultSet metadata verifications
   public List<Map<String,String>> getAllCustomers() {
	  List<Map<String, String>> list_customers = new ArrayList<>();
     try {
    	 // ... //
         Statement stmt = connect.createStatement();
         int customer_id=1;
    	 ResultSet result = stmt.executeQuery("select t1.customer_name as custname, t1.customer_contact as contact FROM tblcustomers as t1 "); // t1.customer_email as email, ... where t1.customer_id="+customer_id);	 

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
	 PreparedStatement pstmt = connect.prepareStatement("select t1.customer_name as custname, t1.customer_contact as custcontact t3.event_title FROM tblcustomers as t1 RIGHT OUTER JOIN (SELECT t2.event_title as event_title, t4.customer_event_customer, t2.event_id FROM tblevents as t2 INNER JOIN tblcustomer_event as t4 ON t4.customer_event_event = t2.event_id) as t3 ON t1.customer_id = t3.customer_event_customer WHERE t3.event_id ="+event_id);
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


   public int updateEntity(int id, Customer entityinst) {
     int res;
     try {
 
       Connection conn = ClassJdbcDataAccessor.getInstance().getJdbcConnection();
     } catch (Exception e) {
        logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: failed to establish a connection");
     }
     try {
       PreparedStatement stmt = connect.prepareStatement("UPDATE tblcustomers VALUES ("+id+",?,?,?);");
//      stmt.setInt(1,instr_id); // not used if auto-increment
      stmt.setString(1,entityinst.getCustomerName());
      stmt.setString(2,entityinst.getCustomerFirstName());
      stmt.setString(3,entityinst.getCustomerContact());

      res=0; //stmt.executeUpdate();
      
   }  catch (Exception e) {
      // logger
     logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: update customer failed");
     res = -1;
   }
   return res;
}

}

