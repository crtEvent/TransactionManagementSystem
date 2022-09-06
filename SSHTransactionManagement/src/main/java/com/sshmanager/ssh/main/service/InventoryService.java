package com.sshmanager.ssh.main.service;

import java.util.List;

import com.sshmanager.ssh.main.dto.InventoryItemDTO;

public interface InventoryService {
	
	/** 품목코드(item_code) 중복 검사 
	 *  품목코드가 중복이면 false, 중복이 아니면 true */
	public boolean checkDupItemCode(String item_code) throws Exception;
	
	/** 재고 품목 리스트를 불러오는 메서드
	 *  매개변수로 넘어온 값(company_idx)이 있으면 where절로 검색 조건 추가(업체별 검색)
	 *  매개변수가 없으면 재고 품목 전체 검색 */
	public List<InventoryItemDTO> getInventoryList() throws Exception;
	public List<InventoryItemDTO> getInventoryList(String company_idx) throws Exception;
}
