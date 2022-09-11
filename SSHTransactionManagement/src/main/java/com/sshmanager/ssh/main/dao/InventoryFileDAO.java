package com.sshmanager.ssh.main.dao;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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
	
	public List<FileDTO> selectInventoryFileListByCompany(String company_idx) throws Exception {
		return session.selectList(namespace+"selectInventoryFileListByCompany", company_idx);
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
		session.delete(namespace+"deleteInventoryFile", file_idx);
	}
	
	public void deleteInventroyFilesInRoot(String item_idx) throws Exception {
		File file, trash;
		String file_root = session.selectOne("path.selectPathByPathName", "FILE_ROOT").toString().replace("\\", "\\\\"); // 파일 저장소
		List<FileDTO> files = selectInventoryFiles(item_idx);
		
		// 실제 파일 삭제
		for(int i = 0; i < files.size(); i++) {
			file = FileUtils.getFile(files.get(i).getFile_path() + File.separator 
					+ files.get(i).getFile_name());
			trash = FileUtils.getFile(file_root + File.separator 
					+ "휴지통" + File.separator 
					+ "item(" + files.get(i).getFile_idx() + ")" + files.get(i).getFile_name());
			FileUtils.moveFile(file, trash); // 파일 이동 + 경로 자동 생성
			
			// 파일 DB에서 제거
			deleteInventroyFile(files.get(i).getFile_idx().toString());
		}
	}
}
