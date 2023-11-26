package com.baeldung.controller;

//import com.baeldung.model.Person;
//import com.baeldung.model.Phone;
import com.baeldung.model.PersonPhone_model;

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

@Controller
public class PersonController {
	
	@RequestMapping(value="/person", method=RequestMethod.POST)
	public String do_phone_annuary(HttpServletRequest request, Model md, HttpSession session) {
		
		try {
			String personame = request.getParameter("personame");
			
			System.out.println("person :"+personame);
			
			PersonPhone_model model = new PersonPhone_model();
			String phonenumber = model.do_search_phone(personame);
			
			if (phonenumber != "error") {
				return "redirect:/myprofile";
			}
			else {
				md.addAttribute("error_msg", "error");
				return "search";
			}
		} catch (Exception e) {
			return "search";
		}
	}
}
