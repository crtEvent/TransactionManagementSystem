package com.sshmanager.ssh.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sshmanager.ssh.main.service.SearchCompanyService;

@Controller
public class MainController {
	
	@Autowired
	SearchCompanyService searchCompanyService;
	
	
	@RequestMapping("/")
	public String main(Model model) throws Exception {
		
		model.addAttribute("searchCompanyList"
				, searchCompanyService.searchCompanyList(null, 20));
		
		return "/main/main";
	}
}
