package com.authorname.dao;
// 
import com.authorname.dao.ClassJdbcDataAccessor;

import java.util.List.*;
import java.util.HashMap.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.authorname.model.*;
import com.authorname.base.*;

import java.sql.*;

public class EnregistreFactoryMusician implements IEnregistreFactory<Musician> {

    private String sqlread = "SELECT muse_id, muse_name, muse_birth, muse_best FROM musicians WHERE muse_id =";
    private ClassJdbcDataAccessor databaseaccess;

    public EnregistreFactoryMusician() {
          try {
             this.databaseaccess = ClassJdbcDataAccessor.getInstance();
	  }
	  catch (Exception e) {
             System.out.println("error");
	  }
    }

   
    @Override
    public String getEnregistreName(long id) {
       RecordObj recobj;
       try {
          recobj=find(id);
   	  return recobj.getName();

       } catch (Exception e) {
          System.out.println("error");
	  return null;
       }
    }

   
    @Override
    public String getEnregistreDate(long id) {
       RecordObj recobj;
       try {
          recobj=find(id);
   	  return recobj.getDate();

       } catch (Exception e) {
          System.out.println("error");
	  return null;
       }
    }

 
    @Override
    public String getEnregistreMessage(long id) {
       RecordObj recobj;
       try {
          recobj=find(id);
   	  return recobj.getMessage();

       } catch (Exception e) {
          System.out.println("error");
	  return null;
       }
    }

    @Override
    public Musician find(long id) {
	// returns a musician object
       Musician musician=null;
       try {
           Connection connection = databaseaccess.accessJdbcDatabaseConnection() ;

	   Statement stmt = connection.createStatement();

	   ResultSet result = stmt.executeQuery(sqlread+id); // append id as Parameter ("where muse_id=?"
	   if (result.next()) {
              int muse_id=result.getInt(0);
              String objname = result.getString(0);
	      String datestring = result.getString(1);
              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	      Date objdate = formatter.parse(datestring);
	      String objmessage = result.getString(2);
           //   Date adate=new Date(); //stub
	      musician = new Musician(muse_id, objname, 1, objdate, datestring, objmessage);
	   }
	   else {
              System.out.println("No record found with id: "+id);
	   }
	   result.close();
       }
       catch(Exception e) {

       }
       return musician;
    }

    @Override
    public void insert(RecordObj musician) {
	// returns a musician object
       int max_id=0;
       int success=0;
       try {
          Connection connection = databaseaccess.accessJdbcDatabaseConnection() ;

	  Statement stmt = connection.createStatement();
	  ResultSet result = stmt.executeQuery("SELECT muse_id, MAX(muse_id) FROM musicians");
          result.last();
	  max_id = result.getInt(0);
// il est plus recommande de mettre une valeur initiale a AUTO_INCREMENT (donc de meme enabled)
// lors de la creation de la table ALTER TABLE `nom_de__la_table` AUTO_INCREMENT=50;
	  System.out.println("ca marche: "+max_id);
       } 
       catch (Exception e) {
          System.out.println("error");
       }
       try {
          Connection connection = databaseaccess.accessJdbcDatabaseConnection() ;
	  max_id++;
          musician.setId(max_id);
	  String name = musician.getName();
	  String date = musician.getDate();
	  String message = musician.getMessage();
	  int musician_instr = 1; //stub here


	  PreparedStatement prestmt = connection.prepareStatement("INSERT INTO musicians VALUES(?,?,?,?);");
		  prestmt.setInt(0,max_id);
		  prestmt.setString(1,name);
		  prestmt.setString(2,date);
		  prestmt.setString(3,message);
		  prestmt.setInt(4,musician_instr);
	   int nb = prestmt.executeUpdate();

       }
       catch (Exception e) {
          System.out.println("error insert");
       }
       if (success==0) {
          System.out.println("success");
       }
    }
// recapitualation:

}

