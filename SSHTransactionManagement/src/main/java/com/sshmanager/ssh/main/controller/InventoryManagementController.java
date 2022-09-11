package com.sshmanager.ssh.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sshmanager.ssh.main.dto.CompanyDTO;
import com.sshmanager.ssh.main.dto.InventoryItemDTO;
import com.sshmanager.ssh.main.service.CompanyService;
import com.sshmanager.ssh.main.service.InventoryService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/inventory")
public class InventoryManagementController {
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	CompanyService companyService;
	
	/* 재고 관리 페이지 */
	@PostMapping("/inventory-management")
	public String getInventoryManagement() throws Exception {
		
		return "redirect:/inventory/inventory-management-result";
	}
	
	/* 재고 관리 페이지 - 결과 */
	@GetMapping("/inventory-management-result")
	public String getInventoryManagementResult() throws Exception {
		
		return "/main/inventory_management";
	}
	
	/**/
	@RequestMapping("/get-inventory-item")
	@ResponseBody
	public InventoryItemDTO getInventoryItem(String item_code) throws Exception {
		
		return inventoryService.getInventoryItemByCode(item_code);
	}
	
	
	/* 재고 리스트 불러오기 (jsGrid) - 매개변수(company_idx)가 없는 경우*/
	/* 업체별 재고 리스트 불러오기 (jsGrid) - 매개변수(company_idx)가 있는 경우 */
	@RequestMapping("/inventory-item-list")
	@ResponseBody
	public List<InventoryItemDTO> getInventoryItemList(String company_idx) throws Exception {
		
		if(company_idx.equals("") || company_idx.equals("undefined")) company_idx = null;
		
		return inventoryService.getInventoryList(company_idx);
	}
	
	/* 업체별 재고 리스트 보기 */
	@PostMapping("/inventory-list-by-company")
	public String getInventoryListByCompany(RedirectAttributes ridirectAttr, String company_idx) throws Exception {
		
		ridirectAttr.addAttribute("company_idx", company_idx);
		
		return "redirect:/inventory/inventory-list-by-company-result";
	}
	
	/* 업체별 재고 리스트 보기 - 결과 */
	@GetMapping("/inventory-list-by-company-result")
	public String getInventoryListByCompanyResult(Model model, String company_idx) throws Exception {
		
		model.addAttribute("inventoryList", inventoryService.getInventoryList(company_idx));
		
		return "/main/inventory_list";
	}
	
	/* 아이템 코드 중복 검사 */
	@RequestMapping("/check-code")
	@ResponseBody
	public boolean checkItemCode(String item_code) throws Exception {
		// item_code가 중복이면 false, 중복이 아니면 true
		return inventoryService.checkDupItemCode(item_code);
	}
	
	/* 업체명 불러오기 */
	@RequestMapping(value="/get-company-name", produces = "application/text; charset=utf8")
	@ResponseBody
	public String getCompanyName(String company_idx) throws Exception {
		
		CompanyDTO dto = companyService.getCompany(company_idx);
		
		if(dto == null) {
			return null;
		}
		
		return dto.getCompany_name();
	}
	
	/* 재고 품목 불러오기 */
	@RequestMapping("/get-inventory")
	@ResponseBody
	public List<Object> getIventory(String item_idx) throws Exception {
		
		return inventoryService.getInventory(item_idx);
	}
	
	/* 재고 품목 등록 */
	@RequestMapping("/insert-inventory-item")
	@ResponseBody
	public boolean insertInventory(InventoryItemDTO dto, MultipartHttpServletRequest multipartRequest) throws Exception {
		
		String item_idx = inventoryService.insertInventroyItem(dto);
		
		inventoryService.insertinventoryFiles(dto.getCompany_idx(), item_idx, multipartRequest);
		
		return true;
	}
	
	/* 재고 품목 수정 */
	@RequestMapping("/update-inventory-item")
	@ResponseBody
	public boolean updateInventory(InventoryItemDTO dto
			, MultipartHttpServletRequest multipartRequest
			, String existingFileJsonData) throws Exception {
		
		String oldCompanyIdx = inventoryService.updateInventroyItem(dto);
		inventoryService.updateinventoryFiles(oldCompanyIdx, dto.getCompany_idx()
				, dto.getItem_idx()
				, multipartRequest
				, JSONArray.fromObject(existingFileJsonData));
		
		return false;
	}
	
	/* 재고 품목 삭제 */
	@RequestMapping("/delete-inventory-item")
	@ResponseBody
	public void deleteinventory(String item_idx) throws Exception {
		inventoryService.deleteInventoryItem(item_idx);
	}
}
