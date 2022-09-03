package com.sshmanager.ssh.main.service;

import com.sshmanager.ssh.main.dto.CompanyDTO;

public interface CompanyService {
	
	/* 업체 정보 불러오기 */
	public CompanyDTO getCompany(String company_idx) throws Exception;
	
	/* 업체명 중복 체크 */
	public boolean checkDupCompanyName(String company_name) throws Exception;
	
	/* 업체 등록 */
	public void insertCompany(CompanyDTO dto) throws Exception;
	
	/* 업체 정보 수정 */
	public boolean updateCompany(CompanyDTO dto) throws Exception;
}
