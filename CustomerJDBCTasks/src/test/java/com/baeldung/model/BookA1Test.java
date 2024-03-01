package com.baeldung.model;

import com.baeldung.model.*; //BookA1;

import java.sql.*;
import java.util.Date;
import java.util.*;
import junit.framework.TestCase;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class BookA1Test {  // extends TestCase (may not any more add this) 
  private BookA bookA;

  @Before
  public void setUp() throws Exception { // not protected
	//super.setUp();
	bookA = new BookA1(1,"Le Seigneur des Anneaux, tome1");
  }
  @After
  public void tearDown() throws Exception {  // not protected
	//super.tearDown();
    bookA=null;
  }
  @Test
  public void bookA1() {
     assertNotNull("The object class was not created", bookA);
  }
  @Test
  public void getName() {
	  if (bookA.getId()==1) {  // teste le premier element en BD
	     assertEquals("the book name with id:1 is incorrect, should be The Lord of the Rings, T.I", "Le Seigneur des Anneaux, tome1", bookA.getName());
      }
  }
  @Test
  public void setName() {
	  bookA.setName("Le Seigneur des Anneaux, tome2");
      assertEquals("the set method to bookA has not expected result", "Le Seigneur des Anneaux, tome2", bookA.getName());
  }
  
  public static void main(String[] args) {
	  org.junit.runner.JUnitCore.main("com.baeldung.model.BookA1Test");
  }
}