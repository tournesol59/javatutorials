package com.authorname.base;

import com.authorname.base.IDatabaseAccess;

import java.lang.StringBuilder;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.beans.factory.annotation.Autowired;

//:if expand("%") == ""|browse confirm w|else|confirm w|endif
//@Configuration
public class DatabaseMySQLAccess implements IDatabaseAccess{ // extends interface in the future
 
  // private final String classdriver ="com.mysql.jdbc.Driver";
   private String jdbcdriver="jdbc:mysql://";
   private String server="localhost";
   private int port="3306";
   private String databasename="customerdbtest";
   private String dbusername="root";
   private String dbpassword="lpf6lmsql";

   public String getClassDriver() {
       return "com.mysql.cj.jdbc.Driver";
   }

   public String getJdbcDriver() {
//       return "jdbc:mysql://";
       return jdbcdriver;
   }

   public void setJdbcDriver(String jdbcdriver) {
       this.jdbcdriver = jdbcdriver;
   }

   public String getDatabaseName() {
//       return "customerdbtest";
       return databasename;
   }

   public void setDatabaseName(String databasename) {
       this.databasename = databasename;
   }

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
       this.port = port;
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

   public String getUrl() {
       StringBuilder sb = new StringBuilder("localhost");       
       sb.append(":");
       sb.append("3306");
       sb.append("/");
       sb.append(this.getDatabaseName());
       return sb.toString();

   }

   public String getJdbcUrl() {
       StringBuilder sb = new StringBuilder("");
       sb.append(this.getJdbcDriver());
       sb.append("localhost");
       sb.append(":");
       sb.append("3306");
       sb.append("/");
       sb.append(this.getDatabaseName());
       return sb.toString();
   }

}
