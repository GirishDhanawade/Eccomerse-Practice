package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	// dependency : service layer i/f
	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in ctor of " + getClass());
	}

	// add req handling method to show login form
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/user/login";// AVN : /WEB-INF/views/user/login.jsp
	}

	// add req handling method for processing login form
	// entry in Handler Mapping bean
	// key : /user/login + method=POST +.....
	// value : com.app.controller.UserController.processLoginForm
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email, @RequestParam("pass") String pwd, 
			Model map,HttpSession session,RedirectAttributes flashMap)
	// reco : MATCH req param names to method arg names for easy binding
	// SC : String pwd=request.getParameter("pass");
	{
		System.out.println("in process login form " + email + " " + pwd + " " + map+" "+flashMap);
		try {
			// invoke service layer method
			User user = userService.authenticateUser(email, pwd);
			flashMap.addFlashAttribute("mesg",
					"Login Successful, Hello , " + user.getName() + " , logged in under " + user.getRole() + " role");
			//save user details : under session scope
			session.setAttribute("user_dtls", user);
			// => valid login --proceed to authorization
			if (user.getRole() == Role.CUSTOMER)
				return "redirect:/customer/topics";
			//SC : response.sendRedirect(response.encodeRedirectURL("/customer/topics");
			//WC :temp resp pkt : SC 302 | location : /customer/topics ... | body : empty
			//Clnt browser send a new req : method = GET , http://host:port/day17_boot/customer/topics
			// => admin login success
			return "redirect:/admin/status";
		} catch (RuntimeException e) {
			System.out.println("err in " + getClass() + " exc " + e);
			map.addAttribute("mesg", "Invalid Login , Please retry !!!!!!!!!!!!!");
			return "/user/login";// AVN : /WEB-INF/views/user/login.jsp
		}
		
	}
	//add req handling method for user's logout
	@GetMapping("/logout")
	public String userLogout(HttpSession session,Model map,HttpServletResponse resp,HttpServletRequest request)
	{
		System.out.println("in log out");
		map.addAttribute("user_dtls", session.getAttribute("user_dtls"));
		//invalidate session
		session.invalidate();
		//How to auto redirect the clnt to the  home page , after some dly ?
		//Http Resp : header  : refresh : dly in secs , url
		//API of HttpServletResponse : public void setHeader(String name,String val)
		System.out.println("ctx path "+request.getContextPath());
		resp.setHeader("refresh", "5;url="+request.getContextPath());
		return "/user/logout";//AVN : /WEB-INF/views/user/logout.jsp
	}

}
