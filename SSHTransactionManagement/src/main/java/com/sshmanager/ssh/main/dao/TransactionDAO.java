package com.sshmanager.ssh.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    	map.put("file_type", file_type.getFile_type());
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
}
