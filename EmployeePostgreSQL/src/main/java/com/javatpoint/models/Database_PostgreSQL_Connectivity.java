package com.javatpoint.models;

import java.util.ArrayList; 
import java.util.Map;
import java.util.HashMap;
import java.sql.*;

public class Database_PostgreSQL_Connectivity implements Database_Connectivity {

   protected String server;
   protected String port;
   protected String db;
   protected String url; 
   protected String dbusername;
   protected String dbpassword;
   
   protected Connection connection;
   public Database_PostgreSQL_Connectivity(String dbuser, String dbpass) {
      super();
      this.server="localhost";
      this.port="5432";
      this.db="eltechdb";
      this.dbusername = dbuser;
      this.dbpassword = dbpass;
   }

   public void openConnection() throws SQLException 
   {
      url="jdbc:postgresql://"+server+":"+port+"/"+db;

      try {
	Class.forName("org.postgresql.Driver");      
        connection = DriverManager.getConnection(url, dbusername, dbpassword);
      }
      catch (Exception e) { // important here: not a SQLException but more general

      }
   }

   public Map<String, String> getResults(String username, String password)  throws SQLException 
{
       String query = "SELECT * FROM tblemployees WHERE tblemployees.first_name="+username;

       ResultSet result;
       Map<String, String> rowset = new HashMap<String, String>();

       try {
//          PreparedStatement prestmt = connection.preparedStatement(query);
//          prestmt.setParameter("name", username);
          Statement stmt = connection.createStatement();
	  result = stmt.executeQuery(query);
          if (result.next()) { // at east one row found that match the couple (name,password)
             rowset.put("name",username);
	     rowset.put("password",password);
	  }
	  result.close();
	  return rowset;
       }
       catch (SQLException e) {
          System.out.println("Error in Database_Connectivity getResults");
	  return rowset;
       }
   }

}
