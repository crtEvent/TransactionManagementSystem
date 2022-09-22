package com.sshmanager.ssh.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sshmanager.ssh.user.dto.UserDTO;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 로그인된 상채가 아니면 로그인 modal창을 띄움
		HttpSession session = request.getSession(true);
		UserDTO userSession = (UserDTO) session.getAttribute("userSession");
		
		if (!userSession.getUser_idx().equals("1")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter printwriter = response.getWriter();
			printwriter.print("<script>alert('관리자 외 접근 불가.');</script>");
			printwriter.flush();
			printwriter.close();
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
