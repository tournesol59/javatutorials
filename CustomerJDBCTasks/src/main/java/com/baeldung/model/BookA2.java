package com.baeldung.model;
// part of design pattern abstract factory
// aim is to load different objects from same table in DB

public class BookA2 extends BookA {
   private long id;
   private String name; 
   private String journal;
   
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
      
   public String getJournal() {
	   return journal;
   }
   
   public void setJournal(String journal) {
	   this.journal=journal;
   }  
 
   public BookA2(long id, String name) {
	   this.id=id;
	   this.name=name;
   }
   
   public BookA2(long id, String name, String journal) {
	   this.id=id;
	   this.name=name;
	   this.journal=journal;
   }
}