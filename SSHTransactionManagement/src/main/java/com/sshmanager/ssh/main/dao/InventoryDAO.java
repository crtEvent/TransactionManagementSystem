package com.sshmanager.ssh.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<InventoryItemDTO> selectInventoryList() throws Exception {
		return session.selectList(namespace+"selectInventoryList", null);
	}
	
	public List<InventoryItemDTO> selectInventoryList(String company_idx) throws Exception {
		return session.selectList(namespace+"selectInventoryList", company_idx);
	}
	
	public void insertInventroyItem(InventoryItemDTO dto) throws Exception {
		session.update(namespace+"insertInventroyItem", dto);
	}
	
}
