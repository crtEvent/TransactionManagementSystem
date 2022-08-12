package com.sshmanager.ssh.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("searchCompanyDAO")
public class SearchCompanyDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "searchCompany.";
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> searchCompanyList(String company_name_keyword, int limit) throws Exception {
		Map map = new HashMap();
    	map.put("keyword", company_name_keyword);
    	map.put("limit", Integer.toString(limit));
		return session.selectList(namespace+"searchCompanyList", map);
	}
}
