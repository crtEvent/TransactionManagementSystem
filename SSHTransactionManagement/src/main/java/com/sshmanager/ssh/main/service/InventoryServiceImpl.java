package com.sshmanager.ssh.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshmanager.ssh.main.dao.InventoryDAO;
import com.sshmanager.ssh.main.dto.InventoryItemDTO;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryDAO inventoryDAO;
	
	public boolean checkDupItemCode(String item_code) throws Exception {
		if(inventoryDAO.countItemCode(item_code) > 0) {
			return false;
		}
		return true;
	}
	
	public List<InventoryItemDTO> getInventoryList() throws Exception {
		return inventoryDAO.selectInventoryList();
	}
	
	public List<InventoryItemDTO> getInventoryList(String company_idx) throws Exception {
		return inventoryDAO.selectInventoryList(company_idx);
	}
	
	public void insertInventroyItem(InventoryItemDTO dto) throws Exception {
		inventoryDAO.insertInventroyItem(dto);
	}
}
