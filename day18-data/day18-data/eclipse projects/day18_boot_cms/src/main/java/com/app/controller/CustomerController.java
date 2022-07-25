package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.ITutorialDao;
import com.app.service.ITopicService;
import com.app.service.ITutorialService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	//dep : service layer i/f
	@Autowired
	private ITopicService service;
	
	//tut service i/f
	@Autowired
	private ITutorialService tutService;
	
	
	public CustomerController() {
		System.out.println("in ctor of "+getClass());
	}
	//add req handling method : to forward clnt to topics.jsp
	@GetMapping("/topics")
	public String getAllTopics(Model map)
	{
		System.out.println("in get all topics");
		map.addAttribute("topics", service.getAllTopics());
		return "/customer/topics";//AVN : /WEB-INF/views/customer/topics.jsp
	}
	//add req handling method for getting tuts by selected topic
	@GetMapping("/tutorials")
	public String getTutorialsByTopic(Model map,@RequestParam long topicId)
	{
		System.out.println("in get tuts "+topicId);
		map.addAttribute("tut_names", tutService.getTutorialNamesByTopic(topicId));
		return "/customer/tutorials";//AVN : /WEB-INF/views/customer/tutorials.jsp
	}
	//add req handling method to get selected tut details
	@GetMapping("/tutorial_detail")
	public String getTutorialDetails(@RequestParam String tutName,Model map)
	{
		System.out.println("in get tut dtls "+tutName);
		//dao's method
		map.addAttribute("tut_details", tutService.getTutorialDetails(tutName));
		return "/customer/tutorial_details";
	}
	

}
