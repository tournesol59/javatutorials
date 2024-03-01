package com.baeldung.base;

import com.baeldung.base.IDatabaseAccess;
import java.sql.*;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
// fred: ou version consultee sur le site de Oracle:
//import java.nio.file;

import java.lang.StringBuilder;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.beans.factory.annotation.Autowired;

//@Configuration
public class DatabaseHSQLDBAccess implements IDatabaseAccess {

   private String jdbcdriver = "org.hsqldb.jdbc.JDBCDriver";
   private String server = "localhost";
   private int port = 9001;
   private String databasename = "customerdbtest";
   private String dbusername="SA";
   private String dbpassword="";
   
   private static final String fproperties="com.javatpoint.model.eltechdbhsqldb.properties";
// si ca marche pas essayer le path direct et version de Oracle
// plus bas     
   private static final String filenameprops="C:\\Users\\Alain\\Documents\\Frederic\\eclipse\\eclipse-workspace\\CustomerJDBC\\src\\main\\resources\\customerdbtest.properties";

  // @Autowired     
   public DatabaseHSQLDBAccess() {
/*	   
      this.jdbcdriver = jdbcdriver;
      this.server = server;
      this.port = port;
      this.databasename = databasename;
      this.dbusername = dbusername;
      this.dbpassword = dbpassword;
      */
        try {
          
          BufferedReader rd = new BufferedReader( new FileReader(filenameprops));
          this.databasename = rd.readLine();
          this.dbusername= rd.readLine();
          this.dbpassword = rd.readLine();
          System.out.println("DB: "+this.databasename+" user: "+this.dbusername+" passwd: "+dbpassword);
          rd.close();
	}
	catch (IOException e) {
           System.out.println("DatabaseHSQLDBAccess constructor error");
	}
      
   }

/*
   public DatabaseHSQLDBAccess() {
	super();
        try {
	
           InputStreamReader is = new InputStreamReader( Class.class.getClassLoader().getResourceAsStream(fproperties));
          BufferedReader rd = new BufferedReader(is);
          this.databasename = rd.readLine();
          this.dbusername= rd.readLine();
          this.dbpassword = rd.readLine();
          rd.close();
	}
	catch (IOException e) {
           System.out.println("DatabaseHSQLDBAccess constructor error");
	}
    }
*/

   public String getServer() {  
       return server;
   }

   public void setServer(String server) {  
       this.server = server;
   }

   public int getPort() {
       return port;
   }

   public void setPort(int port) {
	   //9001
       this.port = port;
   }

   public String getClassDriver() {
       return jdbcdriver;
   }

//    public String getDriver() {
//       return jdbcdriver;
//    }

   public String getDatabaseName() {
//       return "customerdbtest";
       return databasename;
   }

   public void setDatabaseName(String databasename) {
       this.databasename = databasename;
   }


   public String getDBusername() {
	return dbusername;
   }

   public void setDBusername(String dbusername) {
	this.dbusername = dbusername;
   }

   public String getDBpassword() {
        return dbpassword;
   }  

   public void setDBpassword(String dbpassword) {
        this.dbpassword = dbpassword;
   }     
    public String getJdbcUrl() {
       return getUrl();
    }

    public String getUrl() {
       StringBuilder sb = new StringBuilder("localhost");       
       sb.append(":");
       sb.append("9001");
       sb.append("/");
       sb.append(this.getDatabaseName());
       return sb.toString();

   } 

}
