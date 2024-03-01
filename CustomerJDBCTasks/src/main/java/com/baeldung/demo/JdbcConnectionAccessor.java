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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class JdbcConnectionAccessor implements Runnable {
	// Unique instance of this semf contained class that acts as a singleton

//    private static String jdbcdriver="org.h2.Driver";
//    private static String server="localhost";
//    private static int port = 8082;
////    private static String url = "jdbc:mysql://localhost:3306/customerdb";
//    private static String databasename="customerdbtest";
//    private static String dbusername="sa";
//    private static String dbpassword="";

// we replace the above attributes by a DI by attribute (static for having a singleton) 
    @Autowired
    private static IDatabaseAccess baseAccess;

    //private static JdbcConnectionAccessor INSTANCE;
    public static Connection connect;
    private org.apache.logging.log4j.Logger logger;
	/**
	* fred: now that this is used as a Bean in main prgm, even if singleton, methods must be non static
    public static JdbcConnectionAccessor getInstance() {
        // do not forget to configure logger level for TRACE for class
	// non static variable logger cannot be used referenced in static context..
        if (INSTANCE == null) {
	         INSTANCE = new JdbcConnectionAccessor();
    //********** HERE THE LINE TO CHANGE IN CASE OF WE DO NOT IMPLEMENT DI IN FUTURE:
             //baseAccess = (DatabaseH2Access) new DatabaseH2Access(jdbcdriver, server, port, databasename, dbusername, dbpassword);
			 // since we implement DI (see @Autowired) we do not need it (for the moment)
    //************
	    
	    logger = LogManager.getLogger(JdbcConnectionAccessor.class);
        }
        return INSTANCE;
    }
*/

    public JdbcConnectionAccessor() { // no argument constructor
	}
	
    public Connection getJdbcConnection() throws Exception {
		return connect;
	}
	
	@Override
	public void run() {
        try {   
        if ((connect==null) && (baseAccess != null)) {		
	   // and now use the methods of the created bean to pass url, name, passwd
           //Class.forName("com.mysql.cj.jdbc.Driver");	//
	       //Class.forName("org.h2.Driver");
           Class.forName("org.hsqldb.jdbc.JDBCDriver");
	   
           connect = DriverManager.getConnection( baseAccess.getJdbcUrl(), baseAccess.getDBusername(),  baseAccess.getDBpassword() );
		}
	   return connect;
      }
      catch (Exception e) {
		   logger.log(Level.ERROR, "LoggerName :: "+logger.getName()+":: Connection to DB failed ");
           e.printStackTrace();
		   return null;
	   return null;
      }
   
   }// end method

}

