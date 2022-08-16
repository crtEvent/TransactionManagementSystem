package com.sshmanager.ssh.main.service;

import java.util.List;
import java.util.Map;

import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

public interface TransactionService {
	
	/* 거래 목록 불러오기 */
	public List<TransactionDTO> getTransactionList(String company_name) throws Exception;
	
	/* 거래 정보 불러오기 */
	public TransactionDTO getTransaction(String transaction_idx) throws Exception;
	
	/* 아이템 목록 불러오기 */
	public List<ItemDTO> getItemList(String transaction_idx) throws Exception;
	
	/* 파일 목록 불러오기 */
	public List<FileDTO> getFileList(String transaction_idx) throws Exception;
	
}
