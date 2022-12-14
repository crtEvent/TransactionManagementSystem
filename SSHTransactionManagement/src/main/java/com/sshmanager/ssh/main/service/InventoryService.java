package com.sshmanager.ssh.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sshmanager.ssh.main.dto.InventoryItemDTO;
import com.sshmanager.ssh.main.dto.ItemHistoryDTO;

import net.sf.json.JSONArray;

public interface InventoryService {
	
	/** 품목코드(item_code) 중복 검사 
	 *  품목코드가 중복이면 false, 중복이 아니면 true */
	public boolean checkDupItemCode(String item_code) throws Exception;
	
	/* 품목코드(item_code)로 재고품목 정보 가져오기 */
	public InventoryItemDTO getInventoryItemByCode(String item_code) throws Exception;
	
	/** 재고 품목 내용 및 파일 리스트를 불러오는 메서드.
	 *  item_dx에 해당하는 재고 품목 내용 및 파일 리스트를 불러온다.
	 * */
	public List<Object> getInventory(String item_idx) throws Exception;
	
	/** 재고 품목 리스트를 불러오는 메서드
	 *  매개변수로 넘어온 값(company_idx)이 있으면 where절로 검색 조건 추가(업체별 검색)
	 *  매개변수가 없으면 재고 품목 전체 검색 */
	public List<InventoryItemDTO> getInventoryList() throws Exception;
	public List<InventoryItemDTO> getInventoryList(String company_idx) throws Exception;
	
	/* 재고 품목 거래 내역 불러오는 메서드 */
	public List<ItemHistoryDTO> getInventoryItemHistory(String item_idx) throws Exception;
	
	/* [INSERT] 재고 품목 등록 */
	public String insertInventroyItem(InventoryItemDTO dto) throws Exception;
	
	/* [INSERT] 재고 품목 파일 저장 */
	public void insertinventoryFiles(String company_idx, String item_idx
			, MultipartHttpServletRequest multipartRequest) throws Exception;
	
	/* [UPDATE] 재고 품목 수정 */
	public String updateInventroyItem(InventoryItemDTO dto) throws Exception;
	
	/* [INSERT/DELETE] 재고 품목 파일 수정 */
	public void updateinventoryFiles(String old_company_idx, String new_company_idx, String item_idx
			, MultipartHttpServletRequest multipartRequest, JSONArray existingFilejsonArray) throws Exception;
	
	/* 재고 품목 삭제 */
	public void deleteInventoryItem(String item_idx) throws Exception;
}
