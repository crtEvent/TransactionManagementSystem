package com.sshmanager.ssh.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
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
	
	public List<FileDTO> selectFileList(String transaction_idx) throws Exception {
		return session.selectList(namespace+"selectFileList", transaction_idx);
	}
	
}
