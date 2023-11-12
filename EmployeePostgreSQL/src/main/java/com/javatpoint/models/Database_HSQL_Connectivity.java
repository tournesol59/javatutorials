package com.javatpoint.models;

import java.util.ArrayList; 
import java.util.Map;
import java.util.HashMap;
import java.sql.*;

public class Database_HSQL_Connectivity implements Database_Connectivity {

   protected String server="localhost";
   protected String port="5432";
   protected String db="eltechdb";

   protected String url; 
   protected String dbusername;
   protected String dbpassword;
   
   protected Connection connection;
   public Database_HSQL_Connectivity(String dbuser, String dbpass) {
      super();
      this.server="localhost";
      this.port="5432";
      this.db="eltechdb";
      this.dbusername = dbuser;
      this.dbpassword = dbpass;
   }

   public void openConnection()  throws SQLException 
   {
      url="jdbc:hsqldb:hsql://"+server+"/"+db;
      try {      
         Class.forName("org.hsqldb.jdbc.JDBCDriver"); 
         connection = DriverManager.getConnection(url, "SA", "");
      }
      catch (Exception e) {

      }
   }

   public Map<String, String> getResults(String username, String password)  throws SQLException 
{
       String query = "SELECT * FROM tblemployee WHERE tblemployee.first_name="+username;

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
