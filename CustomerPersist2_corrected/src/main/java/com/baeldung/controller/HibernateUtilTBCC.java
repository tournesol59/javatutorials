package com.baeldung.controller;

import org.hibernate.*;
import org.hibernate.cfg.*;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class HibernateUtilTBCC {
   
   public static final SessionFactory sessionFactory=null;

   static {
      try {
	      // creation de la sessionFactory accessible global a partir de hibernate.cfg.xml    	  
	 Configuration configuration = new Configuration().configure();

	 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

	 SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//	 session = sessionFactory.openSession();

      } catch (Throwable ex) {
	 // Make sure you log the exception
	System.err.println("Initial SessionFactory creation failed." + ex);
	throw new ExceptionInInitializerError(ex);
      }
   }

   public static final ThreadLocal session = new ThreadLocal();

   public static SessionFactory getSessionFactory() {
       return sessionFactory;
   }


}
