package com.sshmanager.ssh.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.dto.InventoryItemDTO;

@Repository("inventoryDAO")
public class InventoryDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "inventory.";
	
	public int countItemCode(String item_code) throws Exception {
		return session.selectOne(namespace+"countItemCode", item_code);
	}
	
	public InventoryItemDTO selectInventoryItemByCode(String item_code) throws Exception {
		return session.selectOne(namespace+"selectInventoryItemByCode", item_code);
	}
	
	public InventoryItemDTO selectInventoryItem(String item_idx) throws Exception {
		return session.selectOne(namespace+"selectInventoryItem", item_idx);
	}
	
	public List<InventoryItemDTO> selectInventoryList() throws Exception {
		return session.selectList(namespace+"selectInventoryList", null);
	}
	
	public List<InventoryItemDTO> selectInventoryList(String company_idx) throws Exception {
		return session.selectList(namespace+"selectInventoryList", company_idx);
	}
	
	public void insertInventroyItem(InventoryItemDTO dto) throws Exception {
		session.insert(namespace+"insertInventroyItem", dto);
	}
	
	public void updateInventroyItem(InventoryItemDTO dto) throws Exception {
		session.update(namespace+"updateInventoryItem", dto);
	}
	
	public void deleteInventoryItem(String item_idx) throws Exception {
		session.delete(namespace+"deleteInventoryItem", item_idx);
	}
	
	public void deleteInventoryItemList(String company_idx) throws Exception {
		session.delete(namespace+"deleteInventoryItemList", company_idx);
	}
	
}
