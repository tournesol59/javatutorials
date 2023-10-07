package com.authorname.controller;
//gere la vue qui suit le formulaire "/onepartition"
//
import com.authorname.model.RecordObj;
import com.authorname.model.Partition;
import com.authorname.dao.IEnregistreFactory;
import com.authorname.dao.EnregistreFactoryPartition;

import java.util.List;

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
public class ReadPart_Controller {

   @RequestMapping(value="/onepartition", method=RequestMethod.GET)
   public ModelAndView load_searchpart(HttpServletRequest request, HttpSession session) {
      // nous n utuliserons pas cette methode, sauf si il fallait valider le formulaire de la jsp
      // extraction parametre
      // int playpart_id = parseInt(request.getParameter("partitionid"));

      ModelAndView mav=new ModelAndView("/onepartition");

//      mav.setAttribute("partition") = new Partition(1,"AmericanParis", 1, 1, "1832-09-26", "quietly");

      return mav;
   }
 
   @RequestMapping(value="/onepartition", method=RequestMethod.POST)
   public String do_searchpart(HttpServletRequest request, Model md, HttpSession session) {
   // nous utilisons ce modele
      try {
	  // used
	  int playpart_id =  Integer.parseInt(request.getParameter("partitionid"));
	  // not used but it shall be done TBC
	  String playpart_name = request.getParameter("partitioname");
	  
	  // model logic process, DAO:
	  IEnregistreFactory regpart = new EnregistreFactoryPartition();
	  Partition partition = (Partition) regpart.find((long) playpart_id);
	     // the type cast is important since the factory implements a template method

//	  if (partition != null) {
//	      // md.addAttribute("part", partition);
//            // set attributes to model and session
//            // and redirect:/onepartitionview
//	  }
//	  else {
//	  ..  // redirect:/onepartition
//	  }

          return "redirect:/onepartitionview";
      }
      catch (Exception e) {
          return "redirect:/onepartition";
      }
   }

}//end class


