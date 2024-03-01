package com.baeldung.model;
// part of design pattern abstract factory
// aim is to load different objects from same table in DB

public class BookE1 extends BookE {
   private long id;
   private String url; 
   
   public long getId() {
	   return id;
   }
   public void setId(long id) {
       this.id=id;
   }
   
   public String getUrl() {
	   return url;
   }
   public void setUrl(String url) {
	   this.url=url;
   }
   
   public BookE1(long id, String url) {
	   this.id=id;
	   this.url=url;
   }
}