package com.authorname.model;

import java.sql.*;
import java.util.*;

public class JdbcPartModel
{
   private static final Integer port=3306;
   private String server;  //="localhost";
   private String db; //="instr_collection"
   private String login;  //="root"
   private String password;  //="lpf6lmsql";

   public JdbcPartModel(String server, String db, String login, String password) {
		super();
		// this.port = port;
		this.server = server;
		this.db = db;
		this.login = login;
		this.password = password;
	}

   public int getPort() {  // to demonstrate a very simple junit test
      return (int) port;
   }
   private Connection connection;
// procedure that connect to the DB
   public void connect() throws SQLException 
   {
     String url="jdbc:mysql://"+server+":"+port+"/"+db; // the database address
     connection = DriverManager.getConnection(url, login, password);
   }

// procedure that perform a stmt.executeQuery search for a particular name    
   public List<Partition> searchPart_name(String partname, String authorname)
   {
     List<Partition> partslist = new ArrayList<Partition>();
     String requete = "SELECT COUNT (*) playpart_name, playpart_auth, playpart_instr, musician_name, muse_id FROM partitionparts INNER JOIN musicians ON partitionparts.playpart_auth = musicians.muse_id  WHERE playpart_name = "+partname+" AND musician_name ="+authorname;
     ResultSet results=null;

     try
      {
	 Statement stmt = connection.createStatement();
	 results = stmt.executeQuery(requete);
         String message;
	 int count=0;
         while (results.next()) 
	 {
	    count = results.getInt(1);
	    String playpart_name = results.getString(1); // caution there is a risk here if requete is changed
	    int muse_id = results.getInt(3);
	    int instr_id = results.getInt(2);
	    String musician_name  = results.getString(2);
	    Partition part= new Partition(count, playpart_name, instr_id, muse_id);
	    partslist.add(part); // add Partition object to the list, wh shall be returned
            message="Partition  "+count+" : "+playpart_name+", musician: "+musician_name;
	 }
	 if (count == 1)
	 {
            System.out.println("part and anthor match at least one row");
	 }
	 else {
	    System.out.println("part and author does not match");
	 }
	 results.close();  // frees object
      }
      catch (Exception e)
      {
            System.out.println("Something went wrong .. Please try again!");
	    return null;
      }
      return partslist;
   }

   // TBC: for view in servlet
   public List<Map<String,String>> searchPartAll() throws SQLException 
   {
   //definition of HashMap that contains the list of the request result
      List<Map<String,String>> partslist = new ArrayList<Map<String,String>>();

      try {
         Statement stmt = connection.createStatement();
	 ResultSet results = stmt.executeQuery("SELECT * FROM partitionparts");
	 while (results.next()) {  // the model fills the list that will be passed to the view
	    Map<String,String> record = new HashMap<String,String>();
	    record.put("playpartname", results.getString(1)); // risky here
	    partslist.add(record);
	 }
	 results.close(); // frees object
      } catch (SQLException e) {
	      // treatment

      }
      return partslist;
   }
} // end class

