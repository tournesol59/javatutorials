package com.baeldung.dao;

import com.baeldung.demo.ClassJdbcDataAccessor;
import com.baeldung.model.*;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BookFactory1 implements IBookFactory {

   public Connection connection;

   public BookFactory1() {
     try {
	  // get Connection:
       this.connection = ClassJdbcDataAccessor.getInstance().getJdbcConnection();
	 } catch (Throwable e) {
       e.printStackTrace();
     }
   }
   
   public BookA getInstanceBookA(long id) {
	   try {
		   BookA bookA=null;
		   Statement stmt = connection.createStatement();
		   ResultSet result = stmt.executeQuery("select t1.book_name, t1.book_pub from tblbooks as t1 where t1.book_id="+id);
		   if (result.next()) {
			   bookA = new BookA1(id, result.getString(1), result.getString(2));
		   }
		   return bookA;
	   } catch (SQLException e) {
           e.printStackTrace();
           return null;
     }
   }
   
   public BookE getInstanceBookE(long id) {
	   try {
		   BookE bookE=null;
		   Statement stmt = connection.createStatement();
		   ResultSet result = stmt.executeQuery("select t1.site_url from tblsites as t1 where t1.site_id="+id);
		   if (result.next()) {
			   bookE = new BookE1(id, result.getString(1));
		   }
		   return bookE;		   
	   } catch (SQLException e) {
           e.printStackTrace();
           return null;
     }
   }
}