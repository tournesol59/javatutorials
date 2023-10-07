package com.authorname.model;

import java.util.Date;

public abstract class RecordObj {
//   protected int id;

//   protected String name;

//   protected Date date;
 
//   protected String message;
   public abstract int getId();
   public abstract void setId(int id);
   
   public abstract String getName();
   public abstract void setName(String name);

   public abstract String getDate(); 
   public abstract void setDate(String datestring);

   public abstract String getMessage();
   public abstract void setMessage(String message);

   public abstract String toString();
 //  public RecordObj(String name, Date adate, String message); 

   /*

   public Date getDate() {
      return date;
   }

   public void setDate(Date adate) {
     this.date = adate;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public RecordObj(Date adate, String message)  {
      super();
      this.Date = adate;
      this.message = message;
   }
   */
}

