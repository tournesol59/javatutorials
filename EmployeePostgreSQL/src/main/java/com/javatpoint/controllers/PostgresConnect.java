package com.javatpoint.controllers;

import com.javatpoint.models.*;
import java.sql.*;

public class PostgresConnect {

  public static void main(String[] args) {
	  
     String username = "Martin";
     String password = "pass";
     String msg;

     try {
         Login_Model lm = new Login_Model();
	 msg = lm.do_login_process(username, password);
	 if (msg != "login success") {
	    System.out.println("Correct access to eltechdb but no matching entries found");
	 }
     } catch (SQLException e) {
         System.out.println("Error in eltechdb access");
     }
  }
}
