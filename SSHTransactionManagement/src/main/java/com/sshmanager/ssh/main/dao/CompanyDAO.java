package com.sshmanager.ssh.main.dao;


import java.io.File;

import org.apache.commons.io.FileUtils;
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
	
	public void updateCompany(CompanyDTO dto) throws Exception {
		session.update(namespace+"updateCompany", dto);
	}
	
	// 업체 삭제 - 업체 폴더까지 통째로 삭제
	public void deleteCompany(String company_idx) throws Exception {
		
		CompanyDTO comp = selectCompany(company_idx);
		
		File file, trash;
		String file_root = session.selectOne("path.selectPathByPathName", "FILE_ROOT").toString().replace("\\", "\\\\"); // 파일 저장소
		
		file = FileUtils.getFile(comp.getPath());
		trash = FileUtils.getFile(file_root + File.separator
				+ "휴지통" + File.separator
				+ comp.getCompany_name() + "[" + comp.getCompany_idx() + "]");
		
		if(file.exists()) {
			FileUtils.moveDirectory(file, trash);
		}
		
		session.delete(namespace+"deleteCompany", company_idx);
	}

}
