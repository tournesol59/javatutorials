package com.authorname.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Partition extends RecordObj {

	private int id;
	private String name;
	private int instr;
	private int auth;
	protected String comment;
	protected Date date;
	protected String datestring;

	// constructor(1)
        public Partition(int part_id, String part_name, int part_instr, int part_auth) {
            super();
	    this.id=part_id;
	    this.name=part_name;
	    this.instr=part_instr;
	    this.auth=part_auth;
       	}

	// constructor(2)
	public Partition(int part_id, String part_name, int part_instr, int part_auth, Date date, String datestring, String comment) {
            super();
	    this.id=part_id;
	    this.name=part_name;
	    this.instr=part_instr;
	    this.auth=part_auth;
	    this.date = date;
	    this.datestring = datestring;
	    this.comment = comment;	    
	}

	public int getId() {
            return id;
	}

	public void setId(int part_id) {
            this.id=part_id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String part_name) {
            this.name=part_name;
	}

	public int getInstr() {
	    return instr;
	}

	public void setInstr(int part_instr) {
            this.instr=part_instr;
	}

	public int getAuth() {
	    return auth;
	}

	public void setAuth(int part_auth) {
            this.auth=part_auth;
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

   public String getMessage() {
      return comment;
   }

   public void setMessage(String comment) {
      this.comment = comment;
   }

   public String toString() {
     String msg = "id: "+this.id+" name: "+this.name+" date saved: "+this.datestring+" msg: "+this.comment+"\n --------------------------------";
     return msg;
   }

}

