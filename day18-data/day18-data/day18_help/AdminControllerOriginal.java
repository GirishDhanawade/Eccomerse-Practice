package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Tutorial;
import com.app.service.ITopicService;
import com.app.service.ITutorialService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	// dep : topic service i/f
	@Autowired
	private ITopicService topicService;
	
	@Autowired
	private ITutorialService tutService;
	

	public AdminController() {
		System.out.println("in ctor of " + getClass());
	}

//add request handling method to forward admin to status page
	@GetMapping("/status")
	public String showAdminStatus(Model map) {
		System.out.println("in admin status ");
		map.addAttribute("topic_list", topicService.getAllTopics());
		return "/admin/status";
	}

	// add req handling method to show the form for adding new tut , under chosen
	// topic
	@GetMapping("/add_new_tut")
	public String addNewTutorialShowForm(@RequestParam long topicId, Model map, HttpSession session) {
		System.out.println("in add new tut show form " + topicId);
		map.addAttribute("new_tut", new Tutorial());// bind empty tut (model) to model map
		// add selected topic id under sesison scope
		session.setAttribute("topic_id", topicId);
		return "/admin/add_tutorial";// AVN /WEB-INF/views/admin/add_tutorial.jsp
	}

	// upon form submission :add req handling method : to process form
	@PostMapping("/add_new_tut")
	public String addNewTutorialProcessForm(@ModelAttribute("new_tut") Tutorial transientTut,HttpSession hs) {
		System.out.println("in add tut process form tut : " + transientTut);// except id n topic id --everything bound
																			// to user supplied values(View --> Model)
		System.out.println("tut's topic " + transientTut.getTopic());// null
		System.out.println("topic id "+hs.getAttribute("topic_id"));
		tutService.validateNAddTutotrial(transientTut,(long)hs.getAttribute("topic_id"));
		return "/admin/status";
	}
}
