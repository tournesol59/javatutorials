package com.baeldung.model;
// part of design pattern abstract factory
// aim is to load different objects from same table in DB

public abstract class BookE {
   private long id;
   private String url; 
   
   public abstract long getId();
   public abstract void setId(long id);
   
   public abstract String getUrl();
   public abstract void setUrl(String url);
}