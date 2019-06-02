package com.dub.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Value("${server.port}")
	int port;
	
	@Value("${server.servlet.context-path}")
	String path;
	
	@RequestMapping({"/", "backHome"})   
	public String index(ModelMap model) {
		
		String baseUrl = "http://localhost:" + port + path;
		System.out.println("Fucking url " + baseUrl);
		
		//model.addAttribute("message", "SATOR");
		//model.addAttribute("baseUrl", "http://localhost:8080/convex-hull");
		
		model.addAttribute("baseUrl", baseUrl);
		//model.addAttribute("scanStepUrl", "http://localhost:8080/convex-hull/scanStep");
		//model.addAttribute("initPointsUrl", "http://localhost:8080/convex-hull/initPoints");
	      
		return "convexHull/convexHull";
	    
	}
}
