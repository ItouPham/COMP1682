package com.final_project.chriscosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String viewHomePage() {
		return "index";
	}

}