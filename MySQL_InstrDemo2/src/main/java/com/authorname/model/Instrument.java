package com.authorname.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Instrument extends RecordObj {

	private int id;
	private String name;
	private int playpart;
	private String place;  // correspons to "message" field: duplicate getters
	protected Date date;   // why not, at least a spare to match inheritance from RecordObj
	protected String datestring;

	// constructor(1)
	public Instrument(int instr_id, String instr_name, int instr_playpart, String instr_place) {
            super();
	    this.id=instr_id;
	    this.name=instr_name;
	    this.playpart=instr_playpart;
	    this.place=instr_place;
	}
        // double constructor with dates fields not needed

	public int getId() {
            return this.id;
	}

	public void setId(int instr_id) {
            this.id=instr_id;
	}

	public String getName() {
	    return this.name;
	}

	public void setName(String instr_name) {
            this.name=instr_name;
	}
/*
	public int getPlayPart() {
	    return this.playpart;
	}

	public void setPlayPart(int instr_playpart) {
            this.playpart=instr_playpart;
	}
*/
	public String getPlace() {
	    return this.place;
	}

	public void setPlace(String instr_place) {
            this.place=instr_place;
	}

	public String getMessage() {
	    return this.place;
	}

	public void setMessage(String message) {
            this.place=message;
	}


   public String getDate() { // renounce to getter/setter compatible with dependency injection
     try {
      //     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       String formatteddate = formatter.format(this.date);

       return formatteddate;
     } catch (Exception e) {
       System.out.println("User constructor method parseDate setters of Musician");
       return "error";
     }
   }
   
   public void setDate(String datestring) {
      try {
	 this.datestring = datestring;
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 this.date = formatter.parse(this.datestring);
      } catch (Exception e) {
         System.out.println("User constructor method parseDate setters of Musician");
      }
   }

   public String toString() {
     String msg = "id: "+this.id+" name: "+this.name+" date : "+this.datestring+" place: "+this.place+"\n --------------------------------";
     return msg;
   }

};

