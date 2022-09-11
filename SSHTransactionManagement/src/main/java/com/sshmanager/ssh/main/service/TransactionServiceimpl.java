package com.sshmanager.ssh.main.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sshmanager.ssh.main.dao.CompanyDAO;
import com.sshmanager.ssh.main.dao.PathDAO;
import com.sshmanager.ssh.main.dao.TransactionDAO;
import com.sshmanager.ssh.main.domain.FileType;
import com.sshmanager.ssh.main.dto.CompanyDTO;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.ItemDTO;
import com.sshmanager.ssh.main.dto.MemoDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("transactionService")
public class TransactionServiceimpl implements TransactionService {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private PathDAO pathDAO;
	
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
			
			if(obj.get("content").toString().equals("")) {
				dto.setContent(null);
			} else {
				dto.setContent(obj.get("content").toString());
			}
			if(obj.get("inventory_item_idx").toString().equals("")) {
				dto.setInventory_item_idx(null);
			} else {
				dto.setInventory_item_idx(obj.get("inventory_item_idx").toString());
			}
			if(obj.get("amount").toString().equals("")) {
				dto.setAmount(null);
			} else {
				dto.setAmount(obj.get("amount").toString().replaceAll("\\,", ""));
			}
			if(obj.get("unit_price").toString().equals("")) {
				dto.setUnit_price(null);
			} else {
				dto.setUnit_price(obj.get("unit_price").toString().replaceAll("\\,", ""));
			}
			if(obj.get("supply_price").toString().equals("")) {
				dto.setSupply_price(null);
			} else {
				dto.setSupply_price(obj.get("supply_price").toString().replaceAll("\\,", ""));
			}
			if(obj.get("tax_price").toString().equals("")) {
				dto.setTax_price(null);
			} else {
				dto.setTax_price(obj.get("tax_price").toString().replaceAll("\\,", ""));
			}
			if(obj.get("total_price").toString().equals("")) {
				dto.setTotal_price(null);
			} else {
				dto.setTotal_price(obj.get("total_price").toString().replaceAll("\\,", ""));
			}
			
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
	
	public boolean insertFile(FileDTO dto) {
		try {
			transactionDAO.insertFile(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	
	/** 거래 내역에 등록된 파일을 저장하는 메서드
	 * 매개변수로 넘어온 파일을 견적서(quote), 지시서(order), 이미지(image), 기타(other)파일로 분류
	 * 파일 이름 앞에 날짜를 붙여서 지정된 경로에 저장한 후 DB에 insert
	 */
	public void insesrtTransactionFiles(String date, String company_idx, String transaction_idx
			, MultipartHttpServletRequest multipartRequest) throws Exception {
		
		CompanyDTO companyDTO = companyDAO.selectCompany(company_idx);
		String companyFolderName = companyDTO.getCompany_name()+"["+company_idx+"]";
		
		// 파일이 저장될 경로
		String file_root = pathDAO.selectFileRootPath().replace("\\", "\\\\"); // 파일 저장소
		
		// FileDTO - DB에 저장할 때 사용
		FileDTO fileDTO = new FileDTO();
		fileDTO.setTransaction_idx(transaction_idx);
		
		// UTF-8 인코딩
		multipartRequest.setCharacterEncoding("utf-8");
		
		// 첨부된 파일들의 이름을 가져옴
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		// 파일 전송(저장)
		while(fileNames.hasNext()){
			String fileName = fileNames.next(); // front단에서 받아온 input[type=file]의 name속성 값
			MultipartFile multiFile = multipartRequest.getFile(fileName); // 업로드 된 파일 하나를 MultipartFile 객체에 임시 저장
			
			// 저장될 파일명 지정
			String storedFileName = prependDateToFileName(multiFile.getOriginalFilename(), date);
			String middle_root = date.substring(0, 4); // yyyy -> 후에 yyyy/fileType으로 변경됨
			
			String fileType = fileName.substring(0, 5); // quote, order, image, other
			String path = null; // 파일이 저장될 경로
			
			// 파일이 저장될 경로 지정
			switch(fileType) {
			case "quote":
				middle_root = middle_root + File.separator + FileType.QUOTE.getFolder_name(); // yyyy/fileType 형태
				path = file_root + File.separator + companyFolderName + File.separator + middle_root;
				fileDTO.setFile_type(FileType.QUOTE);
				break;
			case "order":
				middle_root = middle_root + File.separator + FileType.ORDER.getFolder_name(); // yyyy/fileType 형태
				path = file_root + File.separator + companyFolderName + File.separator + middle_root;
				fileDTO.setFile_type(FileType.ORDER);
				break;
			case "image":
				middle_root = middle_root + File.separator + FileType.IMAGE.getFolder_name(); // yyyy/fileType 형태
				path = file_root + File.separator + companyFolderName + File.separator + middle_root;
				fileDTO.setFile_type(FileType.IMAGE);
				break;
			case "other":
				middle_root = middle_root + File.separator + FileType.OTHER.getFolder_name(); // yyyy/fileType 형태
				path = file_root + File.separator + companyFolderName + File.separator + middle_root;
				fileDTO.setFile_type(FileType.OTHER);
				break;
			default :
				break;
			}
			
			// fileType이 quote, order, image, other 가 아닌 경우 파일을 저장하지 않는다.
			if(path == null) {
				continue;
			}
	
			if(multiFile.getSize()!=0) { // file이 null이 아닌 경우
				
				// 중복된 파일 이름 변경
				storedFileName = replaceDuplicateFileName(path, storedFileName);
				
				//경로에 해당하는 디렉토리들을 생성
				File file = new File(path+File.separator+storedFileName);
				file.getParentFile().mkdirs();
				
				// 임시로 저장된 multipartFile을 실제 파일로 전송
				multiFile.transferTo(file);
				
				// fileDTO set
				fileDTO.setFile_name(storedFileName);
				
				// DB에 저장
				transactionDAO.insertFile(fileDTO);
				
			} // .if
		} // .while
		
	}

	
	/** 중복된 파일의 이름을 변경하는 메서드
	* 해당 경로(path)에 동일한 파일명(fileName)의 파일이 존재하는지 확인하여
	* 존재한다면 파일명 뒤에 "-복사본"을 붙인후 변경된 이름(fileName)을 반환
	* 변경된 이름을 다시 체크하여 동일한 파일명이 존재하지 않을 때 까지 반복한다.
	*/
	public String replaceDuplicateFileName(String path, String fileName) {
		
		File file = new File(path+File.separator+fileName);
		
		if(!file.exists()){ // 경로와 파일이 존재하지 않는 경우
			return fileName;
		}
		
		// 경로와 파일이 존재하는 경우
		// 중복된 이름 변경
		int pos = fileName.lastIndexOf(".");
		String body = fileName.substring(0,pos)+"-복사본";
		String ext = fileName.substring(pos+1);
		fileName = body+"."+ext;
		
		return replaceDuplicateFileName(path, fileName);
		
	}
	
	/** 파일명 앞에 날짜(yyyy-Mm-dd)를 붙이는 메서드
	 *  파일명(fileName)앞에 날짜가 없으면 매개변수로 입력된 날짜(date)를 붙인다
	 *  파일명(fileName)앞에 이미 날짜가 붙어 있으면 날짜 제거 후 매개변수로 입력된 날짜(date)를 붙인다
	 */
	public String prependDateToFileName(String fileName, String date) {
		
		String[] splitFileName = new String[2];
		splitFileName = fileName.split(" ", 2);
		
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Matcher matcher = pattern.matcher(splitFileName[0]);
		
		if(matcher.matches()) {
			// 파일명(fileName)앞에 이미 날짜가 붙어 있으면 날짜 제거 후 매개변수로 입력된 날짜(date)를 붙인다
			return date+" "+splitFileName[1];
		}
		
		// 파일명(fileName)앞에 날짜가 없으면 매개변수로 입력된 날짜(date)를 붙인다
		return date+" "+fileName;
	}
	
	public boolean updateItem(ItemDTO dto) {
		try {
			transactionDAO.updateItem(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void updateTransaction(TransactionDTO transactionDTO) throws Exception {
		transactionDAO.updateTransaction(transactionDTO);
	}
	
	public boolean updateItemList(String transaction_idx, JSONArray jsonArray) {
		
		try {
			// 기존에 저장된 item 삭제
			transactionDAO.deleteItemList(transaction_idx);
			
			// item 목록 새로 입력
			if(insertItemList(transaction_idx, jsonArray)) return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateMemoList(String transaction_idx, JSONArray jsonArray) {
		
		try {
			// 기존에 저장된 memo 삭제
			transactionDAO.deleteMemoList(transaction_idx);
			
			// memo 목록 새로 입력
			if(insertMemoList(transaction_idx, jsonArray)) return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean updateTransactionDetails(String transaction_idx
			, JSONArray itemJsonArray, JSONArray memoJsonArray) {
		
		if(!updateItemList(transaction_idx, itemJsonArray)) return false;
		if(!updateMemoList(transaction_idx, memoJsonArray)) return false;
		
		return true;
	}
	
	public void updateTransactionFiles(String date, String company_idx, String transaction_idx
			, MultipartHttpServletRequest multipartRequest, JSONArray existingFilejsonArray) throws Exception {
		
		// [기존에 저장된 파일 검사]
		// 업체 정보
		CompanyDTO companyDTO = companyDAO.selectCompany(company_idx);
		String companyFolderName = companyDTO.getCompany_name()+"["+company_idx+"]";
		
		// 파일이 저장될 경로
		String file_root = pathDAO.selectFileRootPath().replace("\\", "\\\\"); // 파일 저장소
		
		File oldFile;
		File newFile;
		
		// 해당 거래에 등록된 파일 리스트 불러오기(DB에 저장된 파일 리스트)
		List<FileDTO> storedFileList = transactionDAO.selectFileList(transaction_idx, FileType.NULL);
		
		boolean isFileExist; // 
		
		for(int i = 0; i < storedFileList.size(); i++) {
			
			isFileExist = false;
			
			for(int j = 0; j < existingFilejsonArray.size(); j++){
				//JSONArray 형태의 값을 가져와 JSONObject로 풀어준다.    
				JSONObject obj = (JSONObject)existingFilejsonArray.get(j);
				
				if(storedFileList.get(i).getFile_idx().equals(obj.get("file_idx"))) {
					// 넘어온 file_idx 중에 기존에 존재하던 파일(DB에 저장된 값)이 있는 경우
					isFileExist = true;
					
					// 이름 변경
					String newName = prependDateToFileName(storedFileList.get(i).getFile_name(), date);
					
					if(!newName.equals(storedFileList.get(i).getFile_name())) {
						// 이름이 변경된 경우에만 변경
						oldFile = FileUtils.getFile(file_root+File.separator
								+companyFolderName+File.separator
								+storedFileList.get(i).getFile_name().substring(0, 4)+File.separator
								+storedFileList.get(i).getFile_type().getFolder_name()+File.separator
								+storedFileList.get(i).getFile_name());
						newFile = FileUtils.getFile(storedFileList.get(i).getFile_path()+File.separator+newName);
						FileUtils.moveFile(oldFile, newFile); // 파일 이동 + 경로 자동 생성
						
						// DB에 저장된 이름 변경
						transactionDAO.updateFileName(storedFileList.get(i).getFile_idx(), newName);
						
					}
					
				}
				
			} // /.for - existingFilejsonArray.size()
			
			if(!isFileExist) {
				// 기존에 존재하던 파일이 삭제된 경우 - DB와 실제 파일 삭제
				// 파일 휴지통으로 이동
				oldFile = FileUtils.getFile(file_root+File.separator
						+companyFolderName+File.separator
						+storedFileList.get(i).getFile_name().substring(0, 4)+File.separator
						+storedFileList.get(i).getFile_type().getFolder_name()+File.separator
						+storedFileList.get(i).getFile_name());
				newFile = FileUtils.getFile(file_root+File.separator+"휴지통"+File.separator+"("+storedFileList.get(i).getFile_idx()+")"+storedFileList.get(i).getFile_name());
				FileUtils.moveFile(oldFile, newFile); // 파일 이동 + 경로 자동 생성
				
				// 파일 DB에서 제거
				transactionDAO.deleteFile(storedFileList.get(i).getFile_idx());
			}
			
		} // /.for - storedFileList.size()
		
		// [새로운 파일 추가]
		insesrtTransactionFiles(date, company_idx, transaction_idx
				, multipartRequest);

	}
	
} // .class
