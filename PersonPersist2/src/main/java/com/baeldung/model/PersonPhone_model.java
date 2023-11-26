package com.baeldung.model;

import java.util.Map;
import java.util.HashMap;

public class PersonPhone_model {

	public String do_search_phone(String personame) {
	   DatabaseConnectivity dbc = new DatabaseConnectivity();
       String phonenumber="";
       
	   try {
		   dbc.do_db_connection();
		   Map<String, String> rowset=dbc.getResults(personame);
		   if (rowset != null) {
			   phonenumber=rowset.get("phone");
		   }
		   return phonenumber;
	   } catch (Exception e) {
		   System.out.println("error database search");
		   return "error";
	   }
	}
}
