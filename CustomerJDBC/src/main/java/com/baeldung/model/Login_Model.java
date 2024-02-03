package com.baeldung.model;

import java.sql.*;

import com.baeldung.model.Customer;
import com.baeldung.model.Login_Model;
import com.baeldung.demo.ClassJdbcDataAccessor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.Level;

public class Login_Model {

    Connection connect;
    org.apache.logging.log4j.Logger logger;

    public Login_Model() {
    // initiate login connection
       logger= LogManager.getLogger(Login_Model.class);
       try {
    	 connect = ClassJdbcDataAccessor.getInstance().getJdbcConnection();
       }
       catch (Exception e) {      
         logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: connection object error");	         
       }
    }

    public String do_login(String custname, String custfirstname) {
      int found=0;    
      try {
          Statement stmt = connect.createStatement();
       
          ResultSet result = stmt.executeQuery("SELECT * FROM tblcustomers WHERE customer_name="+custname);
	  
	  while (result.next()) {
             String dbcustfirstname = result.getString(2);
	     if (dbcustfirstname == custfirstname) {
                found=1;
		break;
	     }
	  }
	  result.close();
	  if (found==1) {
		  return "lofin success";
	  }
	  else {
		  return "login invalid";
	  }
      }
      catch (Exception e) {
  
         logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: connection request error");	      
         return "error";
      }
       
    }// end method

}
