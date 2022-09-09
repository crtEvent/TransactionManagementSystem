package com.sshmanager.ssh.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.InventoryItemDTO;

@Repository("inventoryFileDAO")
public class InventoryFileDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "inventoryFile.";
	
	public void insertInventroyFile(FileDTO dto) throws Exception {
		session.update(namespace+"insertInventoryFile", dto);
	}
	
}
