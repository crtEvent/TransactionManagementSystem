package com.sshmanager.ssh.main.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		model.addAttribute("searchCompanyList", searchCompanyService.searchCompanyList(null, 20));

		// 로그인된 상채가 아니면 로그인 modal창을 띄움
		HttpSession session = request.getSession(true);
		Object userSession = session.getAttribute("userSession");

		if (userSession == null) {
			//model.addAttribute("", "");
		}

		return "/main/main";
	}
}
