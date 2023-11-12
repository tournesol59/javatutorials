package com.javatpoint.models;

import java.util.ArrayList; 
import java.util.Map;
import java.util.HashMap;
import java.sql.*;

public interface Database_Connectivity {
/*
   protected String server;
   protected String port;
   protected String db;
   protected String url;
   protected String dbusername;
   protected String dbpassword;

   protected Connection connection;
   */
   //public Database_Connectivity(String dbuser, String dbpass) {}

   public void openConnection() throws SQLException ;

   public Map<String, String> getResults(String username, String password)  throws SQLException ;
}
