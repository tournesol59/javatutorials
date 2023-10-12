package com.baeldung.model;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;

@Component
@Entity
@Table( name="tblevents" )
public class Event {

   @Id
   @GeneratedValue( strategy=GenerationType.AUTO )
   @GenericGenerator( name="increment", strategy="increment")
   private int eventId;
   
   public int getEventId() {
	   return eventId;
   }

   public void setEventId(int id) {
	   this.eventId = id;
   }

   @Column(name = "event_date" )  
   private String date;
   public String getDate() {
           return date;
   }

   public void setDate(String date) {
	   this.date = date;
   }

   @Column(name = "event_title" )  
   private String title;

   public String getTitle() {
           return title;
   }

   public void setTitle(String title) {
	   this.title = title;
   }

   public Event(int id, String date, String title) {
           super();
	   this.eventId = id;
	   this.date = date;
	   this.title = title;
   }

}
