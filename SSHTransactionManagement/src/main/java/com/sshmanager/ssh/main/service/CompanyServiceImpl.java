package com.sshmanager.ssh.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshmanager.ssh.main.dao.CompanyDAO;
import com.sshmanager.ssh.main.dto.CompanyDTO;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyDAO companyDAO;
	
	public boolean checkDupCompanyName(String company_name) throws Exception {
		System.out.println(companyDAO.checkDupCompanyName(company_name));
		if(companyDAO.checkDupCompanyName(company_name) != 0) {
			// 업체명이 중복이면 false
			System.out.println("false");
			return false;
		}
		// 업체명이 중복되지 않으면 true
		System.out.println("true");
		return true;
	}
	
	
	public void insertCompany(CompanyDTO dto) throws Exception {
		companyDAO.insertCompany(dto);
	}
}
