package com.baeldung.model;
// part of design pattern abstract factory
// aim is to load different objects from same table in DB

public abstract class BookA {
   private long id;
   private String name; 
   
   public abstract long getId();
   public abstract void setId(long id);
   
   public abstract String getName();
   public abstract void setName(String name);
   
   //public abstract BookA(long id, String name);
}