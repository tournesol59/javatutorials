package com.authorname.controller;
// shall do the interface between the page login.jsp and the db access imethod "read_musician_instrument" in dao/JoinedreadProc.java

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

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginMuse_Controller {
   
   @RequestMapping(value="/loginmusician", method=RequestMethod.POST)
   ModelAndView do_loginmuse(HttpSession session, HttpServletRequest request ) {

     try {
       ModelAndView mav;

       String username = request.getParameter("login");
       String musicianame = request.getParameter("musicname");
       String instrument = request.getParameter("instrname");
       session.setAttribute("username", username);
       session.setAttribute("musicianame", musicianame);
       //session.setAttribute("instrument", instrument);// redondant
   
       String valid=valid_instrmuse(instrument, musicianame);
       if (valid=="loginsuccess") {

          JoinedreadProc joinrd = new JoinedreadProc();

          List<Map<String, String>> listmusicinstr=joinrd.read_musicians_instrument(instrument);
	       
         return mav = new ModelAndView("/viewallmusic", "listmusic", listmusicinstr); // and /viewallinstruments ?
       }  
       else {
         return mav = new ModelAndView("/loginmusician", "msg", "error");
       }
     } catch (Exception e) {
         System.out.println("login verification failure");
         ModelAndView mav;
         return mav = new ModelAndView("/loginmusician", "msg", "error"); 
     }
    
   }

   String valid_instrmuse(String instrument, String musician) {

     try {
/*
  // fred: apres
        JoinedreadProc joinrd = new JoinreadProc();

        List<Map<String,String>> listinstrmuse = joinrd.read_musicians_instrument(instrument);
        int count=0;
        String musename="";
        Iterator iter=listinstrmuse.listIterator();
        while (iter.hasNext()) {
          count++;
          musename=iter.next().get("musician");
        } 
        if (count & (musename==musician)) {
          return "loginsuccess";
        } 
        else {
          return "loginfailed";
        }   
*/
         return "loginsuccess";
     } 
     catch (Exception e) {
        System.out.println("login error to database access");
        return "loginfailed";
     } 
   }

} //end class

