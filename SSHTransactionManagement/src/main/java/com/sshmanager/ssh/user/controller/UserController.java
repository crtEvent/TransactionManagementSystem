package com.sshmanager.ssh.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshmanager.ssh.user.dto.UserDTO;
import com.sshmanager.ssh.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public String login(String user_id, String user_password, String remember_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserDTO user = userService.getUser(user_id, user_password);

		if (user == null) {
			return "";
		}

		// session 积己
		HttpSession session = request.getSession();
		session.setAttribute("userSession", user);

		if (remember_id == null) {
			// cookie 昏力
			Cookie cookie = new Cookie("remember_user", null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} else {
			// cookie 历厘
			Cookie cookie = new Cookie("remember_user", user.getUser_id());
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 365); // 1斥
			response.addCookie(cookie);
		}
		
		return user.getUser_id();
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session) throws Exception {
		session.removeAttribute("userSession");
	}

}
