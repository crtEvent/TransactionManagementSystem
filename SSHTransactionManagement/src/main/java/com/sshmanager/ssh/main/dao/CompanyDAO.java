package com.sshmanager.ssh.main.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.dto.CompanyDTO;

@Repository("companyDAO")
public class CompanyDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "company.";
	
	public CompanyDTO selectCompany(String company_idx) throws Exception {
		return session.selectOne(namespace+"selectCompany", company_idx);
	}
	
	public int checkDupCompanyName(String company_name) throws Exception {
		return session.selectOne(namespace+"checkDupCompanyName", company_name);
	}
	
	public void insertCompany(CompanyDTO dto) throws Exception {
		session.insert(namespace+"insertCompany", dto);
	}

}
