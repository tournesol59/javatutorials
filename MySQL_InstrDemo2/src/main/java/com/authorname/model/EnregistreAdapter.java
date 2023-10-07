package com.authorname.model;

import java.util.List.*;
import java.util.HashMap.*;
import java.util.Date;
import com.authorname.model.RecordObj;

public class EnregistreAdapter implements IEnregistreClient {

//  public Map<String, String> getEnregistreData(RecordObj recordobj);
// todo

// recapitualation:
// EnregistreAdapter fournit une extraction de champs d'un objet qui peut etre de plusieurs types differents:
// inspire du patron adapter et de la surcharge de methode equals pour Hibernate
// a ceci pres qu il gere en fait une famille de 4 objets differents pour ne pas faire trop de fichiers source. En fait on fera appel dans le main en fonction de la requete du prgm a:
// IEnregistreClient cli = new EnregistreAdapter();
// cli.getEnregistreXy(RecordObj recordobj);
//
  public int getEnregistreId(RecordObj recordobj) {
	  return -1;
  }
  
  public String getEnregistreName(RecordObj recordobj) {
     if (recordobj instanceof com.authorname.model.Playrecord) {
       return getServicePlayrecordName((Playrecord) recordobj);
     }  
     else if (recordobj instanceof com.authorname.model.Musician) {
       return getServiceMusicianName((Musician) recordobj);
     }
     else if (recordobj instanceof com.authorname.model.Partition) {
       return getServicePartitionName((Partition) recordobj);
     }
     else if (recordobj instanceof com.authorname.model.Instrument) {
       return getServiceInstrumentName((Instrument) recordobj);
     }
     else return "error getEnregistreName object class not recognized";
  }
  public Date getEnregistreDate(RecordObj recordobj) {
	  return new Date();
  }
  public String getEnregistreMessage(RecordObj recordobj) {
	  return "todo";
  }

  // Service methods: call with a real class from Type Playrecod
  
  public String getServicePlayrecordName(Playrecord rec) {
      return rec.getName();
  }

  public String getServiceMusicianName(Musician rec) {
      return rec.getName();
  }

  public String getServicePartitionName(Partition rec) {
      return rec.getName();
  }

  public String getServiceInstrumentName(Instrument rec) {
      return rec.getName();
  }

}
