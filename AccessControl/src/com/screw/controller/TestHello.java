package com.screw.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestHello {
	@RequestMapping(value = "/login" ,method=RequestMethod.GET)
	public String login() {
		
		return "login";
	}
	@RequestMapping(value = "/login" ,method=RequestMethod.POST)
	public String login(String username,String passwd,HttpSession session) {
		if(username.equals("admin") && passwd.equals("123456")) {
			session.setAttribute("role", "admin");
		}else if (username.equals("user") && passwd.equals("123456")) {
			session.setAttribute("role", "user");
		}else {
			return "login";
		}
		return "index";
	}
	@RequestMapping("/admin/index")
	public String admin() {
		
		return "admin";
	}
	
	@RequestMapping("/user/add")
	public String add() {
		
		return "add";
	}
		
}
