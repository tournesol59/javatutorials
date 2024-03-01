package com.baeldung.base;
// fred utilise a certains endroits la DI Spring (pourqoui pas) donc spring-core=> POM
//import com.baeldung.base.IDatabaseAccess;

import java.lang.StringBuilder;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.beans.factory.annotation.Autowired;

//@Configuration
public class DatabaseH2Access implements IDatabaseAccess { // extends interface in the future
 
  // private final String classdriver ="com.h2.jdbc.Driver";
   private String jdbcdriver;
   private String server;
   private int port;
   private String databasename;
   private String dbusername;
   private String dbpassword;

   //@Autowired     
   public DatabaseH2Access(String jdbcdriver, String server, int port, String databasename, String dbusername, String dbpassword) {
      this.jdbcdriver = jdbcdriver;
      this.server = server;
      this.port = port;
      this.databasename = databasename;
      this.dbusername = dbusername;
      this.dbpassword = dbpassword;
   }

   public String getClassDriver() {
       return "com.h2.jdbc.Driver";
   }

   public String getJdbcDriver() {
//       return "jdbc:h2://";
       return jdbcdriver;
   }

   public void setJdbcDriver(String jdbcdriver) {
       this.jdbcdriver = jdbcdriver;
   }

   public String getDatabaseName() {
//       return "instr_collection";
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
	   // 8082
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
       sb.append("8082");
       sb.append("/");
       sb.append(this.getDatabaseName());
       return sb.toString();

   }

   public String getJdbcUrl() {
// jdbc:h2:~/instr_collection
//or if use in memory db: jdbc:h2:mem:instr_collection"
       StringBuilder sb = new StringBuilder("");
//       sb.append(this.getJdbcDriver());
       sb.append("jdbc:h2:~/");
//       sb.append("jdbc:h2:mem:");
       sb.append(":~/localhost/");
//       sb.append(":");
//       sb.append("8082");
//       sb.append("/");
       sb.append(this.getDatabaseName());
       return sb.toString();
   }

}
