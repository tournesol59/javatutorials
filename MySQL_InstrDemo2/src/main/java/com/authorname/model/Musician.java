package com.authorname.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Musician extends RecordObj {

	private int id;
	private String name;
	private int instr;

        protected Date date;
	protected String datestring;
	protected String message;

	public Musician(int muse_id, String muse_name, int muse_instr) {
           super();
           this.id=muse_id;
	   this.name=muse_name;
	   this.instr=muse_instr;
	}
	public Musician(int muse_id, String muse_name, int muse_instr, Date date, String datestring, String message) {
           super();
           this.id=muse_id;
	   this.name=muse_name;
	   this.instr=muse_instr;   
	   this.date = date;
	   this.datestring = datestring;
	   this.message = message;
	}

	public int getId() {
            return id;
	}

	public void setId(int muse_id) {
            this.id=muse_id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String muse_name) {
            this.name=muse_name;
	}

	public int getInstr() {
	    return instr;
	}

	public void setInstr(int muse_instr) {
            this.instr=muse_instr;
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
      } catch (ParseException e) {
         System.out.println("User constructor method parseDate setters of Musician");
      }
   }

   public String getMessage() {
        return this.message;
   }

   public void setMessage(String message) {
       this.message = message;
   }

   public String toString() {
     String msg = "id: "+this.id+" name: "+this.name+" date birth: "+this.datestring+" msg: "+this.message+"\n --------------------------------";
     return msg;
   }

};
