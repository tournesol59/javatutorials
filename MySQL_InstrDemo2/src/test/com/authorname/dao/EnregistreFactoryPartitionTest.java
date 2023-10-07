package com.authorname.dao;

import com.authorname.model.*;
import com.authorname.dao.*;
import java.sql.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.extensions.TestSetup;

public class EnregistreFactoryPartitionTest extends TestCase {

   private RecordObj record;

   private EnregistreFactoryPartition enregFactPartition;

   public EnregistreFactoryPartitionTest() {
       super();
   }

   public void setUp throws Exception {
        super.setUp();
   }

   public void tearDown() throws Exception {
        super.tearDown();

   public RecordObj testfind {
        enregFactPartition = new EnregistreFactoryPartition();
        assertNotNull("L instance nest pas creee", enregFactPartition);

        try {
           record=(Partition) enregFactPartition.find(1);
	}
	catch (Exception e) {
           System.out.println("la connection a echoue");
	   e.printStackTrace();
	}
    }

    public static Test suite() {
    TestSetup setup = new TestSetup(new TestSuite(EnregistreFactoryPartitionTest.class)) {
           protected void setUp() throws Exception {
		   // code execute une seule fois avant l execution des cas de tests
		  System.out.println("Appel de la methode setUp() de la classe de tests");
	   }

	   protected void tearDown() throws Exception {
                  // code execute une seule fois apres lexecution des cas de tests
		  System.out.println("Appel de la methode tearDown");
	   }
    }

}
