package com.authorname.controller;
// shall do the interface between the page viewallmusic.jsp and the db access imethod "read_musician_instrument" in dao/JoinedreadProc.java

import com.authorname.dao.JoinedreadProc;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

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

@Controller
public class ReadAll_Controller {

   @RequestMapping( value="/formallmusic", method=RequestMethod.POST )
   public ModelAndView do_readall_musicinstrument(HttpSession session, HttpServletRequest request) {
   try {
      ModelAndView mav = new ModelAndView("/viewallmusic");
 
      String username=session.getAttribute("username").toString();

      String instrument=request.getParameter("instrument");
// create appropriate dao object "joinrd" from dao/JoinedreadProc

      JoinedreadProc joinrd = new JoinedreadProc();

      List<Map<String, String>> listmusicinstr=joinrd.read_musicians_instrument(instrument);
     // fred: normally you can redirect with mav
     mav.addObject("listmusic", listmusicinstr);

      return mav;
      
     } catch (Exception e) {
         System.out.println("database read failure");
	 ModelAndView mav = new ModelAndView("/formallmusic", "msg", "error"); 

         return mav;
     }
   }
}
