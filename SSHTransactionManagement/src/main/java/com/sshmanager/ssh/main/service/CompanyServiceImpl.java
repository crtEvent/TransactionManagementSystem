package com.sshmanager.ssh.main.service;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshmanager.ssh.main.dao.CompanyDAO;
import com.sshmanager.ssh.main.dao.InventoryDAO;
import com.sshmanager.ssh.main.dao.InventoryFileDAO;
import com.sshmanager.ssh.main.dao.PathDAO;
import com.sshmanager.ssh.main.dao.TransactionDAO;
import com.sshmanager.ssh.main.dto.CompanyDTO;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.InventoryItemDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private InventoryDAO inventoryDAO;
	
	@Autowired
	private InventoryFileDAO inventoryFileDAO;
	
	@Autowired
	private PathDAO pathDAO;
	
	public CompanyDTO getCompany(String company_idx) throws Exception {
		return companyDAO.selectCompany(company_idx);
	}
	
	public boolean checkDupCompanyName(String company_name) throws Exception {
		
		if(companyDAO.checkDupCompanyName(company_name) != 0) {
			// 업체명이 중복이면 false
			return false;
		}
		// 업체명이 중복되지 않으면 true
		return true;
	}
	
	
	public void insertCompany(CompanyDTO dto) throws Exception {
		companyDAO.insertCompany(dto);
	}
	
	public boolean updateCompany(CompanyDTO dto) {
		
		try {
			
			if(dto.getCompany_name().equals("") || dto.getCompany_name() == null) {
				throw new Exception("company_name값이 null이거나 빈문자열입니다.");
			}
			
			// 이전 업체 정보 불러오기
			CompanyDTO oldDto = companyDAO.selectCompany(dto.getCompany_idx());
			String oldName = oldDto.getCompany_name();
			
			// 업체 정보 update
			companyDAO.updateCompany(dto);
			
			// 업체 폴더명 변경
			renameCompnanyFolder(dto.getCompany_idx(), oldName, dto.getCompany_name());

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
		return true;
	}
	
	public void renameCompnanyFolder(String company_idx, String oldName, String newName) throws Exception {
		
		String file_root = pathDAO.selectFileRootPath();
		
		if(!oldName.equals(newName)) {
			// 업체명이 변경된 경우에만 폴더명 변경
			File oldFile = new File(file_root+File.separator+oldName+"["+company_idx+"]");
			File newFile = new File(file_root+File.separator+newName+"["+company_idx+"]");
			
			if(!oldFile.exists()) {
				oldFile.mkdirs();
			}
			
			FileUtils.moveDirectory(oldFile, newFile);
		}
	}
	
	/* 업체 삭제 - 거래 내역(품목, 메모, 파일, 전용 품목)까지 모두 삭제 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteCompany(String company_idx) throws Exception {
		
		// company_idx에 해당하는 거래 list 삭제
		String transaction_idx;
		List<TransactionDTO> tList = transactionDAO.selectTransactionList(company_idx);
		 
		for(int i = 0; i < tList.size(); i++) {
			transaction_idx = tList.get(i).getTransaction_idx();
			transactionService.deleteTransaction(transaction_idx);

		}
		
		// company_idx에 해당하는 재고 품목 list 삭제
		String item_idx;
		List<InventoryItemDTO> iList = inventoryService.getInventoryList(company_idx);
		
		for(int i = 0; i < iList.size(); i++) {
			item_idx = iList.get(i).getItem_idx();
			inventoryService.deleteInventoryItem(item_idx);
		}
		
		// company 삭제
		companyDAO.deleteCompany(company_idx);
		
	}
}
