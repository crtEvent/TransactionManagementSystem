package com.sshmanager.ssh.main.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
import com.sshmanager.ssh.main.dto.MemoDTO;
import com.sshmanager.ssh.main.dto.TotalPriceDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

import net.sf.json.JSONArray;

public interface TransactionService {
	
	/* 거래 목록 불러오기 */
	public List<TransactionDTO> getTransactionList(String company_name) throws Exception;
	
	/* 거래 정보 불러오기 */
	public TransactionDTO getTransaction(String transaction_idx) throws Exception;
	
	/* 거래 내역 - 총 가격 정보 불러오기 */
	public TotalPriceDTO getTotalPrice(String transaction_idx) throws Exception;
	
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
	
	/* INSERT - 파일업로드 */
	public void insesrtTransactionFiles(String date, String company_idx, String transaction_idx
			, MultipartHttpServletRequest multipartRequest) throws Exception;
	
	/* 중복된 파일의 이름을 변경*/
	public String replaceDuplicateFileName(String path, String storedFileName);
	
	/* 파일명 앞에 날짜 붙여주는 메서드 */
	public String prependDateToFileName(String fileName, String date);
	
	/* UPDATE - 거래 수정 */
	public void updateTransaction(TransactionDTO transactionDTO) throws Exception;
	
	/* UPDATE - 아이템+메모 수정 */
	public boolean updateTransactionDetails(String transaction_idx
			, JSONArray itemJsonArray, JSONArray memoJsonArray);
	
	/* UPDATE - 파일 수정 */
	public void updateTransactionFiles(String date, String company_idx, String transaction_idx
			, MultipartHttpServletRequest multipartRequest, JSONArray existingFilejsonArray) throws Exception;
	
	/* 거래 내역 삭제 - transaction_idx에 해당하는 item, memo, file 모두 삭제 */
	public void deleteTransaction(String transaction_idx) throws Exception;
}
