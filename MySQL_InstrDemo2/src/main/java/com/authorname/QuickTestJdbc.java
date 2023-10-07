// Teste InstrumentDAO:read_all_instruments puis
// EnregistreFatoryMusician
package com.authorname;

import com.authorname.dao.ClassJdbcDataAccessor;
//import com.authorname.dao.InstrAllPrepService;
import com.authorname.model.RecordObj;
import com.authorname.model.Instrument;
import com.authorname.model.Musician;
import com.authorname.model.Partition;
import com.authorname.dao.InstrumentDAO;
import com.authorname.dao.IEnregistreFactory;
import com.authorname.dao.EnregistreFactoryPartition;
import com.authorname.dao.EnregistreFactoryMusician;

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


public class QuickTestJdbc {
   // global variable (equiv to static) must be outside of main: important
   //static int port=3306;
   //static String server="localhost";
   //static String db="instr_collection";
   //static String login="root";
	  
   public org.apache.logging.log4j.Logger logger;
   
   public List<Instrument> liste_instruments;
	//
   public List<Map<String, String>> liste_maps;

   public RecordObj record;
      // dao for Partition
   public EnregistreFactoryMusician enregFactMusician;

   Musician musician_one;
 
   public QuickTestJdbc() {
   	   logger = LogManager.getLogger(QuickTestJdbc.class);
   }

   public Instrument aninstrument;
   
   // main method:
   public static void main(String args[]) {

   	QuickTestJdbc instquick = new QuickTestJdbc();
      // generic class for derivating instrument, musician etc:
    try {
	   // JdbcDataAcessor is an "Adapter" for the type of database and reads url,user,password properties from a Bean definition file in resources/
        System.out.println("MAIN PROGRAM STARTED");
//        ClassJdbcDataAccessor databaseaccess = ClassJdbcDataAccessor.getInstance();  // this line levels an error: to be investigated

        // configure Log4j2
      //  Logger logger = LogManager.getRootLogger();
      //  logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile") );
        
	instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: connection object created");
    /// first part: instrument, simple test request
        InstrumentDAO instr_dao = new InstrumentDAO();
        
        instquick.liste_instruments = instr_dao.read_all_instrument();

	instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: read all instruments completed");
    /// first part: instrument, simple test request
	for (Instrument instrument1 : instquick.liste_instruments) {
          instquick.aninstrument = instrument1; // get first instance of the List
    // convert into a map
       // logger
         instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: extract from result query instrument success with name "+instquick.aninstrument.getName());
	}
    /// **** second part: musician/partition abstract factory, test find method
       instquick.enregFactMusician = new EnregistreFactoryMusician();
       
       int muse_id =1;
       instquick.musician_one = instquick.enregFactMusician.find(muse_id);

       instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: dao design pattern musician successfully executed");
 

    }
    catch (Exception e) {
        // logger
        instquick.logger.log(Level.ERROR, "LoggerName :: "+instquick.logger.getName()+":: extract from result query instrument failed");
       // System.out.println("main An (SQL) error has occurred in listing all instrument");
    }

  }
// *** end main *** //


/***
 * Fred: le code suivant n etait pas une bonne implentation du design pattern Abstract Factory
  list generic object
  public List<Map<String, String>> list_item_asmap(RecordObj record)  {}
*
*/
  
} // end class
