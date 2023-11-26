package com.baeldung.model;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class DatabaseConnectivity {
// Jdbc:h2:mem:testdb
	private static String url="jdbc:h2://";
	private static String Server="localhost"; // but not used
	private static String port="8082";
	private static String driver="com.h2.jdbc.Driver";
    private static String dbname="persondb";
    private static String dbusername="sa";
    private static String dbpassword="";

    private static String dburl="jdbc:h2:mem:persondb";
	private Connection connection;
	   
    public Statement do_db_connection() {

    	try {
    	   Class.forName(driver);
           connection = DriverManager.getConnection(dburl, dbusername, dbpassword);
    	 
    	   Statement stmt = connection.createStatement();
    	   
    	   return stmt;
    	}
    	catch (Exception e) {
    	   System.out.println("error connect");
    	   return null;
    	}
    }

    
    public Map<String, String> getResults(String personame) throws SQLException 
    {
    	String query = "\r\n" + 
    			"SELECT person_name as name, tblphones.phone_number as number FROM (SELECT person_name, tblperson_phones.phone_id as pid "
    			+ "  FROM tblpersons LEFT JOIN tblperson_phones ON tblpersons.person_id=tblperson_phones.person_id) AS PERSONPHONE LEFT JOIN "
    			+ "tblphones ON PERSONPHONE.pid = tblphones.phone_id WHERE name=?";
    	ResultSet result;
    	Map<String,String> rowset = new HashMap<String, String>();
    	
    	try {
    		PreparedStatement prestmt = connection.prepareStatement(query);
    		prestmt.setString(0, personame);
    		result = prestmt.executeQuery();
    		
    		if (result.next()) {
    			// recopy the first entry only, ifit exists
    			String rsname=result.getString(0);
    			String rsphone=result.getString(1);
    			
    			rowset.put("name", rsname);
    			rowset.put("phone", rsphone);
    		}
    		return rowset;
    	}
    	catch (SQLException e) {
    		System.out.println("error statement");
    		return rowset;
    	}
    	
    	
    }
}
