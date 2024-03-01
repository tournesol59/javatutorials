package com.baeldung.dao;

import com.baeldung.model.Customer;
//import com.baeldung.model.Event;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public interface IEntityDAO<T> {

   public abstract T getEntity(int id);

   public abstract List<Map<String,String>> getAllEntity();

   public abstract int updateEntity(int id, T entityinst);
}
