package com.sshmanager.ssh.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshmanager.ssh.main.dto.InventoryItemDTO;
import com.sshmanager.ssh.main.service.InventoryService;

@Controller
@RequestMapping("/inventory")
public class InventoryManagementController {
	
	@Autowired
	InventoryService inventoryService;
	
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
	
	/* 재고 리스트 불러오기 (jsGrid) */
	@RequestMapping("/inventory-item-list")
	@ResponseBody
	public List<InventoryItemDTO> getInventoryItemList() throws Exception {
		return inventoryService.getInventoryList();
	}
	
	
}
