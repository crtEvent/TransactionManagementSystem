package com.sshmanager.ssh.main.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sshmanager.ssh.main.dao.CompanyDAO;
import com.sshmanager.ssh.main.dao.InventoryDAO;
import com.sshmanager.ssh.main.dao.InventoryFileDAO;
import com.sshmanager.ssh.main.dao.PathDAO;
import com.sshmanager.ssh.main.dto.CompanyDTO;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.InventoryItemDTO;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryDAO inventoryDAO;
	
	@Autowired
	private InventoryFileDAO inventoryFileDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private PathDAO pathDAO;
	
	public boolean checkDupItemCode(String item_code) throws Exception {
		if(inventoryDAO.countItemCode(item_code) > 0) {
			return false;
		}
		return true;
	}
	
	public List<InventoryItemDTO> getInventoryList() throws Exception {
		return inventoryDAO.selectInventoryList();
	}
	
	public List<InventoryItemDTO> getInventoryList(String company_idx) throws Exception {
		return inventoryDAO.selectInventoryList(company_idx);
	}
	
	/* [INSERT] 재고 품목 등록 */
	public String insertInventroyItem(InventoryItemDTO dto) throws Exception {
		inventoryDAO.insertInventroyItem(dto);
		// insert 후 item_idx 추출 (useGeneratedKeys="true" keyProperty="item_idx")
		return dto.getItem_idx();
	}
	
	/* [INSERT] 재고 품목 파일 저장 */
	public void insertinventoryFiles(String company_idx, String item_idx
			, MultipartHttpServletRequest multipartRequest) throws Exception {
		
		// 업체 정보 조회
		CompanyDTO companyDTO = companyDAO.selectCompany(company_idx);
		String companyFolderName = companyDTO.getCompany_name()+"["+company_idx+"]";
		
		// 파일 저장소 경로
		String file_root = pathDAO.selectFileRootPath().replace("\\", "\\\\");
		
		// FileDTO - DB에 저장할 때 사용
		FileDTO fileDTO = new FileDTO();
		fileDTO.setItem_idx(item_idx);
		
		// UTF-8 인코딩
		multipartRequest.setCharacterEncoding("utf-8");
		
		// 첨부된 파일들의 이름을 가져옴
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		// 파일 전송(저장)
		while(fileNames.hasNext()){
			
			String fileName = fileNames.next(); // front단에서 받아온 input[type=file]의 name속성 값
			MultipartFile multiFile = multipartRequest.getFile(fileName); // 업로드 된 파일 하나를 MultipartFile 객체에 임시 저장
			
			// 저장될 파일명 지정
			String storedFileName = multiFile.getOriginalFilename();
			String path = file_root + File.separator
						+ companyFolderName + File.separator 
						+ "전용품목"; // 파일이 저장될 경로
			
			if(multiFile.getSize()!=0) { // file이 null이 아닌 경우
				// 중복된 파일 이름 변경
				storedFileName = replaceDuplicateFileName(path, storedFileName);
				
				//경로에 해당하는 디렉토리들을 생성
				File file = new File(path+File.separator+storedFileName);
				file.getParentFile().mkdirs();
				
				// 임시로 저장된 multipartFile을 실제 파일로 전송
				multiFile.transferTo(file);
				
				// DB에 저장
				fileDTO.setFile_name(storedFileName);
				inventoryFileDAO.insertInventroyFile(fileDTO);
			} // .if() - end
			
		} // .while() - end
		
	} // .insertinventoryFiles() - end
	
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
}
