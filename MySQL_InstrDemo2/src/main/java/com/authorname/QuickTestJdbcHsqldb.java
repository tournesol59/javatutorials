// En utilisant jdbc
package com.authorname;

//import com.authorname.dao.ClassJdbcDataAccessor;
//import com.authorname.dao.InstrAllPrepService;
import com.authorname.model.Instrument;
//import com.authorname.dao.InstrumentDAO;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
/*
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
*/
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.Level;


public class QuickTestJdbcHsqldb {
   // global variable (equiv to static) must be outside of main: important
   //static int port=3306;
   //static String server="localhost";
   //static String db="instr_collection";
   //static String login="root";
   public QuickTestJdbcHsqldb() {
   	   logger = LogManager.getLogger(QuickTestJdbcHsqldb.class);
   }
	  
   public org.apache.logging.log4j.Logger logger;
   
//   public List<Instrument> liste_instruments;
	//
   public List<Map<String, String>> liste_maps;

   Instrument aninstrument;
   
   public static void main(String args[]) {
   	QuickTestJdbcHsqldb instquick = new QuickTestJdbcHsqldb();  
    try {
        // configure Log4j2
        //Logger logger = LogManager.getRootLogger();
        //logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile") );
        Connection connect = DriverManager.getConnection("jdbc:hsqldb:file:/opt/db/instr_collection", "SA", "");	
        
    // simplest test request
        Statement stmt = connect.createStatement();
	ResultSet result = stmt.executeQuery("SELECT * FROM instruments");
        
	//loop to store the results
	
	while (result.next()) {
           Map<String,String> mpinstr = new HashMap<String,String>();
	   String keyinstr = "instr";
	   String instr_name = result.getString(0);

	   mpinstr.put(keyinstr, instr_name);
	   instquick.liste_maps.add(mpinstr);
	}

       Map<String,String> first_map=instquick.liste_maps.get(0);
       
       String first_instr = first_map.get("instr");
       instquick.logger.log(Level.DEBUG, "LoggerName :: "+instquick.logger.getName()+":: QuickTstJdbcHsqldb: instrument: "+first_instr);

    }

    catch (Exception e) {
        // logger
        instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: extract from Hsqldb instr_collection failed");
    }

  }
// *** end main *** //
} // end class
