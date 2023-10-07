package com.authorname.dao;

import java.util.List;
import java.util.HashMap.*;
import java.util.HashMap;
import java.util.Date;
import com.authorname.model.*;

// but: implementer un "patron Abstract Factory mais template en meme temps => on a un DAO, qui sappelle "Enregistre" mais qui est bien fonctionnel pour read,insert,update 
public interface IEnregistreFactory<T> {

 // getters for the inline - generic object recordobj:

 // public int getEnregistreId();

  public abstract String getEnregistreName(long id);

  public abstract String getEnregistreDate(long id); // get rid of a Date format

  public abstract String getEnregistreMessage(long id);

  public abstract T find(long id);

  public abstract void insert(RecordObj record);
  // then create(T), update, delete
  
  // then a constructor (not exposed here) with dependency <RecordObj true type>
}
