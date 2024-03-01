package com.baeldung.model;
// part of design pattern abstract factory
// aim is to load different objects from same table in DB

public class BookA1 extends BookA {
   private long id;
   private String name; 
   private String publisher;
   
   public long getId() {
	   return id;
   }
   public void setId(long id) {
	   this.id=id;
   }
   
   public String getName() {
	   return name;
   }
   
   public void setName(String name) {
	   this.name=name;
   }
      
   public String getPublisher() {
	   return publisher;
   }
   
   public void setPublisher(String publisher) {
	   this.publisher=publisher;
   }  
   
   public BookA1(long id, String name) {
	   this.id=id;
	   this.name=name;
   }   
   
   public BookA1(long id, String name, String publisher) {
	   this.id=id;
	   this.name=name;
	   this.publisher=publisher;
   }   
}