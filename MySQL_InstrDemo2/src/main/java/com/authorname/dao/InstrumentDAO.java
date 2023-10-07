package com.authorname.dao;

import com.authorname.dao.ClassJdbcDataAccessor;
 	
import com.authorname.model.Instrument;
import com.authorname.model.RecordObj;

import java.sql.*;
import java.util.Date;
import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class InstrumentDAO {
// a procedure that reads all intruments from Table instruments
  Connection connect;

  org.apache.logging.log4j.Logger logger;
  /* 
  public static InstrumentDAO INSTANCE;  // of this class was due to be a singleton

  public static InstrumentDAO getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new InstrumentDAO();
      }
      logger = LogManager.getLogger(InstrumentDAO.class);
      return INSTANCE;
  }
 */

  protected static int last_instr_id=10;
// constructor
  public InstrumentDAO() {
       logger = LogManager.getLogger(InstrumentDAO.class);
       last_instr_id=10;
  }

  public List<Instrument> read_all_instrument() throws SQLException {

   String requete = "SELECT * from instruments";

   ResultSet result;

   List<Instrument> listinstr = new ArrayList<Instrument>();
//   try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//   } catch (ClassNotFoundException e) {
//	   System.out.println("Impossible to load driver jdbc:odbc");
//   }

   try {
	   
      ClassJdbcDataAccessor accessor = ClassJdbcDataAccessor.getInstance();

      connect = accessor.accessJdbcDatabaseConnection();

      Statement stmt = connect.createStatement();

      result = stmt.executeQuery(requete);
      // logger
      logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: query from instrument success");

      // create objects for each occurrence found 
      System.out.println("first item "+result.getString(0));

   // in the result with access getString(int)

      while ( result.next()) { // verifier

      // we do static indexed properties,danger

         String instrname = result.getString(0);

         String fieldpos = result.getString(2);

         Instrument instrI = new Instrument(1, instrname,1, fieldpos);

         listinstr.add(instrI);

    //  result.next(); // non, direct dans la condition de la boucle while

       } 
      System.out.println("after recopying instrument to object list");
   // display 3 first elements
   // create objects for each occurrence found
   
      ResultSetMetaData rsmd;
  
      rsmd = result.getMetaData();
   
      int nbCol = rsmd.getColumnCount();
    
      System.out.println("number of instrument items: " + nbCol);

   // ListIterator<Instrument> listIter=listinstr.iterator(); pas verifie donc autre implementation:

       int i =0;

       List<Instrument> sousinstr=listinstr.subList(0,3);

       for (Instrument instr : sousinstr) {

         System.out.println( "instrument " + i + " " + instr.getName() + " " + instr.getPlace());

         i++ ;

       }

       result.close();  // frees object

   } catch (Exception e) {

      // logger
      logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: query from instrument failed");

   }

  return listinstr;
  } // end read function

// Ensuite: modifier la table enregistrement
//public static void insert_into_intrument(Connection connect, String instrument, String position, int playpart) {

  public void insert_into_instrument(String instrument, String position, int playpart) {

   String requete = "INSERT INTO instruments (instr_id, instr_name, play part, instr_place) VALUES ("+ last_instr_id +" , "+ instrument + " , " + playpart + " , " +  position + ");" ;
   last_instr_id++;
   ResultSet result;

   try {
     Statement stmt = connect.createStatement(); // ERROR must be included

     result = stmt.executeQuery(requete);
      // logger
     logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: insert instrument success");


   } catch (SQLException e) {

       // logger
      logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: Database error, insert operation");

   }

  } // end insert_into_instrument

/** the following search the ids of a triplet (instr_name, muse_name, playpart_name) in DB**/
  public Map<String,String> createMapforAllPrep(String instr_name, String muse_name, String playpart_name)  {
// this is a function for the pure sql executeUpdateArgs method in InstrAllPrepService
     Map<String,String> pargs=new HashMap<String,String>();
     // search for ids in all individual tables
     boolean reqfound=true;
     int instr_id=0;
     int muse_id=0;
     int playpart_id=0;
     try {
	   
        ClassJdbcDataAccessor accessor = ClassJdbcDataAccessor.getInstance();
        connect = accessor.accessJdbcDatabaseConnection();

        String requete="SELECT instr_id FROM intruments WHERE instruments.instr_name="+instr_name;
        Statement stmt = connect.createStatement();
        ResultSet results = stmt.executeQuery(requete);
        if (results.next()) {
           reqfound=true;
           instr_id=results.getInt(0);    
        }
        else {
           reqfound=false;
        }
        results.close();
     } catch (Exception e) {
        System.out.println("error InstrDAO createMap instr");
     }
     if (reqfound==true) {
        try {
	   
        ClassJdbcDataAccessor accessor = ClassJdbcDataAccessor.getInstance();
        connect = accessor.accessJdbcDatabaseConnection();

        String requete="SELECT muse_id FROM musicians WHERE musisians.muse_name="+muse_name;
        Statement stmt = connect.createStatement();
        ResultSet results = stmt.executeQuery(requete);
        if (results.next()) {
           reqfound=true;
           muse_id=results.getInt(0);    
        }
        else {
           reqfound=false;
        }
        results.close();
      } catch (Exception e) {
        System.out.println("error InstrDAO createMap musician");
      }
     }
     if (reqfound==true) {
        try {
	   
        ClassJdbcDataAccessor accessor = ClassJdbcDataAccessor.getInstance();
        connect = accessor.accessJdbcDatabaseConnection();

        String requete="SELECT playpart_id FROM partitionparts WHERE partitionparts.playpart_name="+playpart_name;
        Statement stmt = connect.createStatement();
        ResultSet results = stmt.executeQuery(requete);
        if (results.next()) {
           reqfound=true;
           playpart_id=results.getInt(0);    
        }
        else {
           reqfound=false;
        }
        results.close();
     } catch (Exception e) {
        System.out.println("error InstrDAO createMap partition");
     }
    }
    if (reqfound==true) {
// fills in the map with the requested id s found and the orig names
      pargs.put("id1", ""+instr_id);
      pargs.put("name1", instr_name);
      pargs.put("id2", ""+muse_id);
      pargs.put("name2", muse_name);
      pargs.put("id3", ""+playpart_id);
      pargs.put("name3", playpart_name);
    }
    else {
      System.out.println("createMapForAll: no id argument found");
    }
    return pargs;
  } // end method createMapForAll

}//end class

