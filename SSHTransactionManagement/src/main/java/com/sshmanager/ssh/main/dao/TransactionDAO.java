package com.sshmanager.ssh.main.dao;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.domain.FileType;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
import com.sshmanager.ssh.main.dto.MemoDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

@Repository("transactionDAO")
public class TransactionDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "transaction.";
	
	public List<TransactionDTO> selectTransactionList(String company_idx) throws Exception {
		return session.selectList(namespace+"selectTransactionList", company_idx);
	}
	
	public TransactionDTO selectTransaction(String transaction_idx) throws Exception {
		return session.selectOne(namespace+"selectTransaction", transaction_idx);
	}
	
	public List<ItemDTO> selectItemList(String transaction_idx) throws Exception {
		return session.selectList(namespace+"selectItemList", transaction_idx);
	}
	
	public List<MemoDTO> selectMemoList(String transaction_idx) throws Exception {
		return session.selectList(namespace+"selectMemoList", transaction_idx);
	}
	
	public List<FileDTO> selectFileList(String transaction_idx, FileType file_type) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
    	map.put("transaction_idx", transaction_idx);
    	map.put("file_type", file_type == null? null : file_type.getFile_type());
		return session.selectList(namespace+"selectFileList", map);
	}
	
	public void insertTransaction(TransactionDTO dto) throws Exception {
		session.insert(namespace+"insertTransaction", dto);
	}
	
	public void insertItem(ItemDTO dto) throws Exception {
		session.insert(namespace+"insertItem", dto);
	}
	
	public void insertMemo(MemoDTO dto) throws Exception {
		session.insert(namespace+"insertMemo", dto);
	}
	
	public void insertFile(FileDTO dto) throws Exception {
		session.insert(namespace+"insertFile", dto);
	}
	
	public void updateTransaction(TransactionDTO dto) throws Exception {
		session.update(namespace+"updateTransaction", dto);
	}
	
	public void updateItem(ItemDTO dto) throws Exception {
		session.update(namespace+"updateItem", dto);
	}
	
	public void updateMemo(MemoDTO dto) throws Exception {
		session.update(namespace+"updateMemo", dto);
	}
	
	public void updateFile(FileDTO dto) throws Exception {
		session.update(namespace+"updateFile", dto);
	}
	
	public void updateFileName(String file_idx, String file_name) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("file_idx", file_idx);
		map.put("file_name", file_name);
		session.update(namespace+"updateFileName", map);
	}
	
	public void deleteTransaction(String transaction_idx) throws Exception {
		session.delete(namespace+"deleteTransaction", transaction_idx);
	}
	
	public void deleteTransactionList(String company_idx) throws Exception {
		session.delete(namespace+"deleteTransactionList", company_idx);
	}
	
	public void deleteItem(String item_idx) throws Exception {
		session.delete(namespace+"deleteItem", item_idx);
	}
	
	public void deleteMemo(String memo_idx) throws Exception {
		session.delete(namespace+"deleteMemo", memo_idx);
	}
	
	public void deleteFile(String file_idx) throws Exception {
		session.delete(namespace+"deleteFile", file_idx);
	}
	
	public void deleteItemList(String transaction_idx) throws Exception {
		session.delete(namespace+"deleteItemList", transaction_idx);
	}
	
	public void deleteMemoList(String transaction_idx) throws Exception {
		session.delete(namespace+"deleteMemoList", transaction_idx);
	}
	
	public void deleteFileList(String transaction_idx) throws Exception {
		session.delete(namespace+"deleteFileList", transaction_idx);
	}
	
	public void deleteFilesInRoot(String transaction_idx) throws Exception {
		
		File file, trash;
		String file_root = session.selectOne("path.selectPathByPathName", "FILE_ROOT").toString().replace("\\", "\\\\"); // 파일 저장소
		List<FileDTO> files = selectFileList(transaction_idx, null);
		
		// 실제 파일 삭제
		for(int i = 0; i < files.size(); i++) {
			file = FileUtils.getFile(files.get(i).getFile_path() + File.separator 
							+ files.get(i).getFile_name());
			trash = FileUtils.getFile(file_root + File.separator 
							+ "휴지통" + File.separator 
							+ "(" + files.get(i).getFile_idx() + ")" + files.get(i).getFile_name());
			FileUtils.moveFile(file, trash); // 파일 이동 + 경로 자동 생성
			
			// 파일 DB에서 제거
			deleteFile(files.get(i).getFile_idx());
		}
	}
}
