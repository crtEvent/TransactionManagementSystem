package com.sshmanager.ssh.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sshmanager.ssh.main.dto.CompanyDTO;
import com.sshmanager.ssh.main.service.CompanyService;
import com.sshmanager.ssh.main.service.TransactionService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	TransactionService transactionService;
	
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
	
	/* 업체 세부 내역 보기 */
	@PostMapping(value = "/company-details")
	public String getCompanyDetails(Model model
								, RedirectAttributes ridirectAttr
								, String company_idx) throws Exception {
		
		ridirectAttr.addAttribute("company_idx", company_idx);
		
		return "redirect:/company/company-details-result";
	}
	
	/* 업체 세부 내역 결과 반환 */
	@GetMapping(value = "/company-details-result")
	public String companyDetailsResult(Model model
										, String company_idx) throws Exception {
		
		model.addAttribute("companyDTO"
							, companyService.getCompany(company_idx));
		
		return "/main/main_details";
	}
}
