// the goal of this class isto be instanciated one time
package com.baeldung.demo;

import com.baeldung.base.*;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

public final class ClassJdbcDataAccessor {
	// Unique instance of this semf contained class that acts as a singleton

    private static String jdbcdriver="org.h2.Driver";
    private static String server="localhost";
    private static int port = 8082;
//    private static String url = "jdbc:mysql://localhost:3306/customerdb";
    private static String databasename="customerdbtest";
    private static String dbusername="sa";
    private static String dbpassword="";

    private static IDatabaseAccess baseAccess;

    private static ClassJdbcDataAccessor INSTANCE;
    public Connection connect;
    private static org.apache.logging.log4j.Logger logger;
    public static ClassJdbcDataAccessor getInstance() {
        // do not forget to configure logger level for TRACE for class
	    
	// non static variable logger cannot be used referenced in static context..
        if (INSTANCE == null) {
	    INSTANCE = new ClassJdbcDataAccessor();
    //********** HERE THE LINE TO CHANGE IN CASE OF DATABASE CHANGE:
             baseAccess = (DatabaseH2Access) new DatabaseH2Access(jdbcdriver, server, port, databasename, dbusername, dbpassword);
    //************
	    
	    logger = LogManager.getLogger(ClassJdbcDataAccessor.class);
        }
        return INSTANCE;
    }

    public Connection getJdbcConnection() throws Exception {

        try {      

	   // and now use the methods of the created bean to pass url, name, passwd
           //Class.forName("com.mysql.cj.jdbc.Driver");	//
	   Class.forName("org.h2.Driver");
           //Class.forName("org.hsqldb.jdbc.JDBCDriver");
	   
           connect = DriverManager.getConnection( baseAccess.getJdbcUrl(), baseAccess.getDBusername(),  baseAccess.getDBpassword() );

	   return connect;
      }
      catch (Exception e) {
	   logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: Connection to DB failed ");

           e.printStackTrace();
	   return null;
      }
   
   }// end method

}

