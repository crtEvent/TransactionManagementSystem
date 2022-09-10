package com.sshmanager.ssh.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.dto.FileDTO;

@Repository("inventoryFileDAO")
public class InventoryFileDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "inventoryFile.";
	
	public FileDTO selectInventoryFile(String file_idx) throws Exception {
		return session.selectOne(namespace+"selectInventoryFile", file_idx);
	}
	
	public List<FileDTO> selectInventoryFiles(String item_idx) throws Exception {
		return session.selectList(namespace+"selectInventoryFiles", item_idx);
	}
	
	public void insertInventroyFile(FileDTO dto) throws Exception {
		session.insert(namespace+"insertInventoryFile", dto);
	}
	
	public void updateInventoryFile(String file_idx, String file_name) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putIfAbsent("file_idx", file_idx);
		map.putIfAbsent("file_name", file_name);
		session.update(namespace+"updateInventoryFile", map);
	}
	
	public void deleteInventroyFile(String file_idx) throws Exception {
		System.out.println("dao에 들어온 idx: "+file_idx);
		session.delete(namespace+"deleteInventoryFile", file_idx);
	}
}
