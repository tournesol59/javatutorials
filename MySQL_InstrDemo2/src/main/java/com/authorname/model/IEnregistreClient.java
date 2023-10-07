package com.authorname.model;

import java.util.List;
import java.util.HashMap.*;
import java.util.HashMap;
import java.util.Date;

// but: implementer un "patron adaptateur" qui prend en argument une liste d'objet generique (les objets pouvant etre au choix une 'partition' ou un 'enregistrement' ou encore un 'musicien' et copie leur champ dans un HashMap generique: (String id, nom, date, message, spare) et ignore les autres
public interface IEnregistreClient {

 // public Map<String, String> getEnregistreData(RecordObj recordobj);

  public int getEnregistreId(RecordObj recordobj);

  public String getEnregistreName(RecordObj recordobj);

  public Date getEnregistreDate(RecordObj recordobj);

  public String getEnregistreMessage(RecordObj recordobj);

}
