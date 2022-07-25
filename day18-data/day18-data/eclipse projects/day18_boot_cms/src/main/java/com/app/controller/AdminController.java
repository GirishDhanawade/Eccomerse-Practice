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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String addNewTutorialShowForm(@RequestParam long topicId, Tutorial tut, HttpSession session) {
		System.out.println("in add new tut show form " + topicId);
		// map.addAttribute("tutorial", new Tutorial());// bind empty tut (model) to
		// model map
		// add selected topic id under sesison scope
		session.setAttribute("topic_id", topicId);
		return "/admin/add_tutorial";// AVN /WEB-INF/views/admin/add_tutorial.jsp
	}

	// upon form submission :add req handling method : to process form
	@PostMapping("/add_new_tut")
	public String addNewTutorialProcessForm(Tutorial transientTut, HttpSession hs, Model map,
			RedirectAttributes flashMap) {
		System.out.println("in add tut process form tut : " + transientTut);// except id n topic id --everything bound
																			// to user supplied values(View --> Model)
		System.out.println("tut's topic " + transientTut.getTopic());// null
		System.out.println("topic id " + hs.getAttribute("topic_id"));
		try {
			flashMap.addFlashAttribute("mesg",
					tutService.validateNAddTutotrial(transientTut, (long) hs.getAttribute("topic_id")));
		} catch (RuntimeException e) {
			// in case of any errs --forward clnt to the view layer(add tut .jsp) ,
			// hightlighted with errs!
			map.addAttribute("err_mesg", e.getMessage());
			return "/admin/add_tutorial";// AVN /WEB-INF/views/admin/add_tutorial.jsp
		}
		// in case of success --redirect the clnt to admin status page with a status
		// mesg !
		return "redirect:/admin/status";

	}
}
