package com.baeldung.dao;
// abstract factory design pattern to distinguish 
// paper or e-book and journal articles
import com.baeldung.model.*;
import java.sql.*;

public interface IBookFactory {

   //public Connection connection;
   
   public abstract BookA getInstanceBookA(long id);
   
   public abstract BookE getInstanceBookE(long id);
}