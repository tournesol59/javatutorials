package com.baeldung.model;

import java.sql.*;

import com.baeldung.model.Customer;
import com.baeldung.model.Login_Model;
import com.baeldung.demo.JdbcConnectionAccessor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class Login_Model implements Runnable {

    Connection connect;
    org.apache.logging.log4j.Logger logger;
	String login_status=null;
	
	public String getStatus() {
		return login_status;
	}
	
    @Autowired
    private static JdbcConnectionAccessor jdbcAcc;
	
    public Login_Model() {
    // initiate login connection
       logger= LogManager.getLogger(Login_Model.class);
       try {
    	 connect = jdbcAcc.getJdbcConnection();
       }
       catch (Exception e) {      
         logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: connection object error");	         
       }
    }

    public void do_login(String custname, String custfirstname) {
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
		  login_status="login success";
	  }
	  else {
		  login_status="login invalid";
	  }
      }
      catch (Exception e) {
  
         logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: connection request error");	      
         login_status="error";
      }
       
    }// end method

}
