package com.sshmanager.ssh.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshmanager.ssh.main.dto.CompanyDTO;
import com.sshmanager.ssh.main.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	/* 업체 등록 */
	@PostMapping("/insert")
	public String insert(CompanyDTO dto, HttpServletRequest request) throws Exception {
		
		companyService.insertCompany(dto);
		
		// 이전 페이지 URL
		String referer = request.getHeader("Referer");
		
		return "redirect:"+referer;
	}
	
	/* 업체명 중복체크 */
	@RequestMapping("/check-company-name")
	@ResponseBody
	public boolean checkCompanyName(String company_name) throws Exception {
		
		// 업체명이 중복이면 false, 업체명이 중복되지 않으면 true
		return companyService.checkDupCompanyName(company_name)? true:false;
	}
}
