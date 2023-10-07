package com.authorname.controller;
// shall do the interface between the page viewallmusic.jsp and the db access imethod "read_musician_instrument" in dao/JoinedreadProc.java
// 
import com.authorname.dao.ClassJdbcDataAccessor;
import com.authorname.dao.JoinedreadProc;
import com.authorname.dao.InstrumentDAO;
import com.authorname.dao.InstrAllPrepService;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;

@Controller
public class UpdatePart_Controller {
   
   @RequestMapping(value="/viewallmusic", method=RequestMethod.GET)
   ModelAndView do_list_partitions(HttpSession session, HttpServletRequest request ) {
   
   ModelAndView mav = new ModelAndView("/updatepart");
;	   

     try {

      String username=session.getAttribute("username").toString();
       
 //     String musicianame = request.getParameter("musicname");
      String musicianame = (String) session.getAttribute("musicianame");

      // instanciate class JoinedreadProc (similar to musician instrument)
      JoinedreadProc joinrd = new JoinedreadProc();
      // call request method

      List<Map<String,String>> listparts = joinrd.read_partitions_musician(musicianame);

      mav.addObject("parts", listparts);
      return mav;

     } catch (Exception e) {
      return mav;
     }

  }

   @RequestMapping(value="/updatepart", method=RequestMethod.POST)
   ModelAndView do_update_partitions(HttpSession session, Model md, HttpServletRequest request ) {

     String folderSQL="/home/frederic/Documents/eclipse-workspace/MySQL_InstrDemo2/src/main/resources/";
      ModelAndView mav = new ModelAndView("/viewallmusic"); // temp solution for redirecting

     try {

         String username=session.getAttribute("username").toString();
	 String instrumentname=request.getParameter("instrumentname");
	 String musicianame=request.getParameter("musicianame");
         //String musicianame=session.getAttribute("username").toString();
         String partname=request.getParameter("partname");

	 
         ClassJdbcDataAccessor accessor = ClassJdbcDataAccessor.getInstance();

	 InstrAllPrepService prepsv=new InstrAllPrepService(folderSQL, accessor);
      // caution class DAO are singletons
         InstrumentDAO instrdao=new InstrumentDAO() ; 
         Map<String,String> prepargs=instrdao.createMapforAllPrep(instrumentname,musicianame,partname);

   	 // TO BE CONTINUED: call executeSQLFile method of prepsv, which itself calls executeUpdateArgs, which has to be finished in InstrAllPrepService
         
         Map<String,String> updargs = instrdao.createMapforAllPrep(instrumentname, musicianame, partname);

	 prepsv.executeSQLFile("updatepartition", updargs);
//        md.addAttribute("error_msg", message);
         return mav;
     } catch (Exception e) {
         return mav;
     }
   }
}

