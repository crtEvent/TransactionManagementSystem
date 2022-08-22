package com.sshmanager.ssh.main.service;

import java.util.List;

import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
import com.sshmanager.ssh.main.dto.MemoDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

import net.sf.json.JSONArray;

public interface TransactionService {
	
	/* 거래 목록 불러오기 */
	public List<TransactionDTO> getTransactionList(String company_name) throws Exception;
	
	/* 거래 정보 불러오기 */
	public TransactionDTO getTransaction(String transaction_idx) throws Exception;
	
	/* 아이템 목록 불러오기 */
	public List<ItemDTO> getItemList(String transaction_idx) throws Exception;
	
	/* 메모 목록 불러오기 */
	public List<MemoDTO> getMemoList(String transaction_idx) throws Exception;
	
	/* 견적서 파일 목록 불러오기 */
	public List<FileDTO> getQuoteFileList(String transaction_idx) throws Exception;
	
	/* 지시서 파일 목록 불러오기 */
	public List<FileDTO> getOrderFileList(String transaction_idx) throws Exception;
	
	/* 이미지 파일 목록 불러오기 */
	public List<FileDTO> getImageFileList(String transaction_idx) throws Exception;
	
	/* 기타 파일 목록 불러오기 */
	public List<FileDTO> getOtherFileList(String transaction_idx) throws Exception;
	
	/* INSERT  - 거래 입력 */
	public String insertTransaction(TransactionDTO dto) throws Exception;
	
	/* INSERT  - 아이템 입력 */
	public boolean insertItem(ItemDTO dto);
	
	/* INSERT  - 아이템 리스트 입력 */
	public boolean insertItemList(String transaction_idx, JSONArray jsonArray);
	
	/* INSERT - 메모 입력 */
	public boolean insertMemo(MemoDTO dto);
	
	/* INSERT  - 메모 리스트 입력 */
	public boolean insertMemoList(String transaction_idx, JSONArray jsonArray);
	
	/* INSERT - 견적서 파일 입력 */
	public boolean insertQuoteFile(FileDTO dto);
	
	/* INSERT - 지시서 파일 입력 */
	public boolean insertOrderFile(FileDTO dto);
	
	/* INSERT - 이미지 파일 입력 */
	public boolean insertImageFile(FileDTO dto);
	
	/* INSERT - 기타파일 입력 */
	public boolean insertOtherFile(FileDTO dto);
	
	/* INSERT - 아이템+메모 입력 */
	public boolean insertTransactionDetails(String transaction_idx
			, JSONArray itemJsonArray, JSONArray memoJsonArray);
}
