package com.sshmanager.ssh.main.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			dto.setContent(obj.get("content").toString());
			
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
		
		// FileDTO - DB에 저장할 떄 사용
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
			
			String fileType = fileName.substring(0, 5);
			String path = null; // 파일이 저장될 경로
			
			// 파일이 저장될 경로 지정
			switch(fileType) {
			case "quote":
				middle_root = middle_root + File.separator + FileType.QUOTE.getFolder_name();
				path = file_root + File.separator + companyFolderName + File.separator + middle_root;
				fileDTO.setFile_type(FileType.QUOTE);
				break;
			case "order":
				middle_root = middle_root + File.separator + FileType.ORDER.getFolder_name();
				path = file_root + File.separator + companyFolderName + File.separator + middle_root;
				fileDTO.setFile_type(FileType.ORDER);
				break;
			case "image":
				middle_root = middle_root + File.separator + FileType.IMAGE.getFolder_name();
				path = file_root + File.separator + companyFolderName + File.separator + middle_root;
				fileDTO.setFile_type(FileType.IMAGE);
				break;
			case "other":
				middle_root = middle_root + File.separator + FileType.OTHER.getFolder_name();
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
				fileDTO.setFile_path(middle_root);
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
}
