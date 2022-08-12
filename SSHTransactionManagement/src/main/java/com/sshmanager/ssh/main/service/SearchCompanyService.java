package com.sshmanager.ssh.main.service;

import java.util.List;
import java.util.Map;

public interface SearchCompanyService {
	
	// 업체 리스트 select
	public List<Map<String, Object>> searchCompanyList(String company_name_keyword, int limit) throws Exception;
	
}
