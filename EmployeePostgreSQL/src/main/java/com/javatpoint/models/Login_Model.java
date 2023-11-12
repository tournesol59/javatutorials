package com.javatpoint.models;

import java.util.Map;
import java.sql.*;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  

public class Login_Model {

   private String msg;
   private String dbusername;
   private String dbpassword;   

   public Login_Model() {};

   public String do_login_process(String username, String password) throws SQLException 
 {
      // import database connection parameters from File "eltechdb.properties"
      try {
         FileReader f=new FileReader("C://MVN//EmployeePostgreSQL//src//main//java//com//javatpoint//models//eltechdb.properties");
         BufferedReader b=new BufferedReader(f);
         dbusername=(String) b.readLine();
         dbpassword=(String) b.readLine();
         b.close();
         f.close();
      } 
      catch (Exception e) {
	 System.out.println("Buffered reader from proprties error");
      }
      // pass them
      Database_Connectivity dc = new Database_PostgreSQL_Connectivity(dbusername, dbpassword); // recopy this class from the Udemy course
      dc.openConnection();
      Map<String,String> res=dc.getResults(username, password);
      if (res.get("password") == password) {
         msg="login success";
      }
      else {
         msg="login error";
      }
      return msg;
   }

}

