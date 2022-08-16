package com.sshmanager.ssh.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sshmanager.ssh.main.service.SearchCompanyService;

@Controller
@RequestMapping("/main")
public class SearchCompanyController {
	
	@Autowired
	SearchCompanyService searchCompanyService;
	
	@PostMapping(value = "/search_co")
	public String searchCompany(Model model
								, RedirectAttributes ridirectAttr
								, String company_name_keyword) throws Exception {
		
		ridirectAttr.addAttribute("company_name_keyword", company_name_keyword);
		
		return "redirect:/main/search_co_result";
	}
	
	@GetMapping(value = "/search_co_result")
	public String searchCompanyResult(Model model
										, String company_name_keyword) throws Exception {
		
		model.addAttribute("searchCompanyList"
				, searchCompanyService.searchCompanyList(company_name_keyword, 20));
		
		return "/main/search_company_result";
	}
	
}
