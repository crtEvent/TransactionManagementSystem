package com.sshmanager.ssh.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshmanager.ssh.main.dao.TransactionDAO;
import com.sshmanager.ssh.main.domain.FileType;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
import com.sshmanager.ssh.main.dto.MemoDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	
	public List<MemoDTO> getMemoList(String transaction_idx) throws Exception {
		return transactionDAO.selectMemoList(transaction_idx);
	}
	
	public List<FileDTO> getQuoteFileList(String transaction_idx) throws Exception {
		return transactionDAO.selectFileList(transaction_idx, FileType.QUOTE);
	}
	
	public List<FileDTO> getOrderFileList(String transaction_idx) throws Exception {
		return transactionDAO.selectFileList(transaction_idx, FileType.ORDER);
	}
	
	public List<FileDTO> getImageFileList(String transaction_idx) throws Exception {
		return transactionDAO.selectFileList(transaction_idx, FileType.IMAGE);
	}
	
	public List<FileDTO> getOtherFileList(String transaction_idx) throws Exception {
		return transactionDAO.selectFileList(transaction_idx, FileType.OTHER);
	}
	
	public String insertTransaction(TransactionDTO dto) throws Exception {
		transactionDAO.insertTransaction(dto);
		// insert 후 transaction_idx 추출 (useGeneratedKeys="true" keyProperty="transaction_idx")
		return dto.getTransaction_idx();
	}
	
	public boolean insertItem(ItemDTO dto) {
		try {
			transactionDAO.insertItem(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean insertItemList(String transaction_idx, JSONArray jsonArray) {
		
		for(int i=0; i<jsonArray.size(); i++){
			//JSONArray 형태의 값을 가져와 JSONObject로 풀어준다.    
			JSONObject obj = (JSONObject)jsonArray.get(i);
			
			ItemDTO dto = new ItemDTO();
			dto.setTransaction_idx(transaction_idx);
			dto.setContent(obj.get("content").toString());
			dto.setAmount(obj.get("amount").toString().replaceAll("\\,", ""));
			dto.setUnit_price(obj.get("unit_price").toString().replaceAll("\\,", ""));
			dto.setSupply_price(obj.get("supply_price").toString().replaceAll("\\,", ""));
			dto.setTax_price(obj.get("tax_price").toString().replaceAll("\\,", ""));
			dto.setTotal_price(obj.get("total_price").toString().replaceAll("\\,", ""));
			
			if(!insertItem(dto)) return false;
		}
		
		return true;
	}
	
	public boolean insertMemo(MemoDTO dto) {
		try {
			transactionDAO.insertMemo(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean insertMemoList(String transaction_idx, JSONArray jsonArray) {
		
		for(int i=0; i<jsonArray.size(); i++){
			//JSONArray 형태의 값을 가져와 JSONObject로 풀어준다.    
			JSONObject obj = (JSONObject)jsonArray.get(i);
			
			MemoDTO dto = new MemoDTO();
			dto.setTransaction_idx(transaction_idx);
			dto.setContent(obj.get("content").toString());
			
			if(!insertMemo(dto)) return false;
		}
		return true;
	}
	
	public boolean insertQuoteFile(FileDTO dto) {
		
		dto.setFile_type(FileType.QUOTE);
		
		try {
			transactionDAO.insertFile(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean insertOrderFile(FileDTO dto) {
		
		dto.setFile_type(FileType.ORDER);
		
		try {
			transactionDAO.insertFile(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean insertImageFile(FileDTO dto) {
		
		dto.setFile_type(FileType.IMAGE);
		
		try {
			transactionDAO.insertFile(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean insertOtherFile(FileDTO dto) {
		
		dto.setFile_type(FileType.OTHER);
		
		try {
			transactionDAO.insertFile(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean insertTransactionDetails(String transaction_idx
			, JSONArray itemJsonArray, JSONArray memoJsonArray) {
		
		if(!insertItemList(transaction_idx, itemJsonArray)) return false;
		if(!insertMemoList(transaction_idx, memoJsonArray)) return false;
		
		return true;
	}

}
