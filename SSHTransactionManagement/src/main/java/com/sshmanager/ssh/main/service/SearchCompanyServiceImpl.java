package com.sshmanager.ssh.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshmanager.ssh.main.dao.SearchCompanyDAO;

@Service("searchCompanyService")
public class SearchCompanyServiceImpl implements SearchCompanyService {
	
	@Autowired
	private SearchCompanyDAO searchCompanyDAO;
	
	public List<Map<String, Object>> searchCompanyList(String company_name_keyword, int limit) throws Exception {
		return searchCompanyDAO.searchCompanyList(company_name_keyword, limit);
	}
	
}
