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
 
       Connection conn = ClassJdbcDataAccessor.getInstance().getJdbcConnection();
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

