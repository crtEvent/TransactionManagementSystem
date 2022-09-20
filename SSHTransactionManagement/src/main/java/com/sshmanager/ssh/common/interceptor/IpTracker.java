package com.sshmanager.ssh.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sshmanager.ssh.main.dao.IpDAO;

public class IpTracker extends HandlerInterceptorAdapter {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IpDAO ipDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("===============          START         ===============");
			log.debug("[Request URI] \t: " + request.getRequestURI());

			List<String> list = ipDAO.selectIpList();
			String ip = request.getHeader("X-FORWARDED-FOR");

			// proxy 환경일 경우
			if (ip == null || ip.length() == 0) {
				ip = request.getHeader("Proxy-Client-IP");
			}

			// 웹로직 서버일 경우
			if (ip == null || ip.length() == 0) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}

			if (ip == null || ip.length() == 0) {
				ip = request.getRemoteAddr();
			}

			for (String s : list) {
				if (s.equals(ip)) {
					log.debug("[IP] \t: " + ip);
					return true;
				}
			}
			
			log.debug("[IP] \t: " + ip + " <- 허용되지 않은 ip");
			response.sendRedirect(request.getContextPath() + "/ban");
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("===============           END          ===============\n");
		}
	}
}
