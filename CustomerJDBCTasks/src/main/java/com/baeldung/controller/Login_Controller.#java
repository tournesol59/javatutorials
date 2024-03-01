package com.baeldung.controller;

import com.baeldung.model.Customer;
import com.baeldung.model.Login_Model;
import com.baeldung.demo.ClassJdbcDataAccessor;
import com.baeldung.dao.CustomerDAO;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.Level;

@Controller
public class Login_Controller {
	
 //  @RequestMapping("/") 
 //   public String home(){
 //       return "index"; 
 //   }
   
  // fred: first a very basic class
   @RequestMapping(value="/login", method=RequestMethod.POST)
   public String do_login(HttpServletRequest request, Model md, HttpSession session) { // Session and Model after
    try {
      //
      String custname=request.getParameter("custname");
      String custfirstname=request.getParameter("custfirstname");

      System.out.println("Customer name and first name are : "+custname+" "+custfirstname);

      // just database opening
    	 Connection connection = ClassJdbcDataAccessor.getInstance().getJdbcConnection();
      org.apache.logging.log4j.Logger logger= LogManager.getLogger(Login_Controller.class);
  
      logger.log(Level.DEBUG, "LoggerName :: "+logger.getName()+":: connection object created");

      Login_Model lm = new Login_Model();
      String message = lm.do_login(custname, custfirstname);

      if (message.equals("login success"))
      {
        session.setAttribute("custname", custname);
	md.addAttribute("customer", custname);
	return "redirect:/myprofile";
      }
      else
      {
        md.addAttribute("error_msg", message);
      }
      return "login";
      }
    catch (Exception e)
    {
      return "login";
    }
  }
}// end class

