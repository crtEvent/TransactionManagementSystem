package com.sshmanager.ssh.sample.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sshmanager.ssh.sample.service.SampleService;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@Autowired(required = true)
	DriverManagerDataSource dataSource;

	@Autowired
	SampleService sampleService;

	// MySQL DB 연결 확인
	@RequestMapping(value = "/testDB")
	public String testDB(Model model) {

		System.out.println("MySQL 연결 테스트");

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NOW() AS NOW;");
			while (rs.next()) {
				model.addAttribute("servertime", rs.getString("now"));
				System.out.println("servertime: " + rs.getString("now"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "";
	}

	// MyBatis 테스트
	@RequestMapping(value = "/testMyBatis")
	public String test_mybatis(Model model) throws Exception {

		System.out.println("MyBatis 연결 테스트");
		System.out.println("getTime: " + sampleService.getTime());

		return "";
	}

}
