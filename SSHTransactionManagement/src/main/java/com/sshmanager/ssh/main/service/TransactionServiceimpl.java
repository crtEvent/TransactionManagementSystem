package com.sshmanager.ssh.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshmanager.ssh.main.dao.TransactionDAO;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

@Service("transactionService")
public class TransactionServiceimpl implements TransactionService {
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	public List<TransactionDTO> getTransactionList(String company_name) throws Exception {
		return transactionDAO.selectTransactionList(company_name);
	}
	
	public TransactionDTO getTransaction(String transaction_idx) throws Exception {
		return transactionDAO.selectTransaction(transaction_idx);
	}
	
	public List<ItemDTO> getItemList(String transaction_idx) throws Exception {
		return transactionDAO.selectItemList(transaction_idx);
	}
	
	public List<FileDTO> getFileList(String transaction_idx) throws Exception {
		return transactionDAO.selectFileList(transaction_idx);
	}

}
