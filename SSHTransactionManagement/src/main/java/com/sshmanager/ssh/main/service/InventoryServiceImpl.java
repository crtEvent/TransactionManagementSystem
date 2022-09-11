package com.sshmanager.ssh.main.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	
	/* 품목코드(item_code) 중복 검사 */
	public boolean checkDupItemCode(String item_code) throws Exception {
		if(inventoryDAO.countItemCode(item_code) > 0) {
			return false;
		}
		return true;
	}
	
	/* 품목코드(item_code)로 재고품목 정보 가져오기 */
	public InventoryItemDTO getInventoryItemByCode(String item_code) throws Exception {
		return inventoryDAO.selectInventoryItemByCode(item_code);
	}
	
	/* 재고 품목 내용 및 파일 리스트를 불러오는 메서드 */
	public List<Object> getInventory(String item_idx) throws Exception {
		
		List<Object> list = new ArrayList<Object>();
		list.add(inventoryDAO.selectInventoryItem(item_idx));
		list.add(inventoryFileDAO.selectInventoryFiles(item_idx));
		
		return list;
	}
	
	/* 재고 품목 리스트를 불러오는 메서드 */
	public List<InventoryItemDTO> getInventoryList() throws Exception {
		return inventoryDAO.selectInventoryList();
	}
	/* 재고 품목 리스트를 불러오는 메서드 */
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
	
	/* [UPDATE] 재고 품목 수정 */
	/** [UPDATE] 재고 품목 수정
	 *  재고 품목의 내용을 수정 후 company_idx를 반환
	 *  company_idx가 변경된 경우 변경되기 전의 company_idx를 반환
	 * */
	public String updateInventroyItem(InventoryItemDTO dto) throws Exception {
		
		String oldCompanyIdx = inventoryDAO.selectInventoryItem(dto.getItem_idx()).getCompany_idx();
		
		inventoryDAO.updateInventroyItem(dto);
		
		return oldCompanyIdx;
	}
	
	/* [INSERT/DELETE] 재고 품목 파일 수정 */
	public void updateinventoryFiles(String old_company_idx, String new_company_idx, String item_idx
			, MultipartHttpServletRequest multipartRequest, JSONArray existingFilejsonArray) throws Exception {
		
		// 이전 업체 정보 조회
		CompanyDTO oldCompanyDTO = companyDAO.selectCompany(old_company_idx);
		String oldCompanyFolderName = oldCompanyDTO.getCompany_name()+"["+old_company_idx+"]";
		
		// 파일 저장소 경로
		String file_root = pathDAO.selectFileRootPath().replace("\\", "\\\\");
		
		File oldFile;
		File newFile;
		
		// 해당 거래에 등록된 파일 리스트 불러오기(DB에 저장된 파일 리스트)
		List<FileDTO> storedFileList = inventoryFileDAO.selectInventoryFiles(item_idx);
		
		boolean isFileExist= false; // 
		
		System.out.println("시작 전 - storedFileList - "+storedFileList.toString());
		System.out.println("시작 전 - existingFilejsonArray - "+existingFilejsonArray);
		
		for(int i = 0; i < storedFileList.size(); i++) {
			System.out.println("1. storedFileList.get(i).getFile_idx(): "+storedFileList.get(i).getFile_idx());
			isFileExist = false;
			
			for(int j = 0; j < existingFilejsonArray.size(); j++){
				//JSONArray 형태의 값을 가져와 JSONObject로 풀어준다.    
				JSONObject obj = (JSONObject)existingFilejsonArray.get(j);
				System.out.println(" 1-1. obj.get(\"file_idx\"): "+obj.get("file_idx"));
				
				if(storedFileList.get(i).getFile_idx().equals(obj.get("file_idx"))) {
					// 넘어온 file_idx 중에 기존에 존재하던 파일(DB에 저장된 값)이 있는 경우
					isFileExist = true;
					System.out.println("   넘어온 file_idx 중에 기존에 존재하던 파일(DB에 저장된 값)이 있는 경우 isFileExist: "+isFileExist);
					// 업체가 변경된 경우 파일 위치 변경
					if(!old_company_idx.equals(new_company_idx)) {System.out.println("    업체 변경됨 - 파일 위치 변경");
						oldFile = FileUtils.getFile(file_root + File.separator
								+ oldCompanyFolderName + File.separator
								+ "전용품목" + File.separator
								+ storedFileList.get(i).getFile_name());System.out.println("    업체 변경됨 - oldfile: "+file_root + File.separator
										+ oldCompanyFolderName + File.separator
										+ "전용품목" + File.separator
										+ storedFileList.get(i).getFile_name());
						newFile = FileUtils.getFile(storedFileList.get(i).getFile_path() + File.separator 
								+ storedFileList.get(i).getFile_name());System.out.println("    업체 변경됨 - newfile: "+storedFileList.get(i).getFile_path() + File.separator 
										+ storedFileList.get(i).getFile_name());
						FileUtils.moveFile(oldFile, newFile); // 파일 이동 + 경로 자동 생성
					}
					System.out.println("   그대로 내비둠");
				} // .if() - end
				
			} // .for - existingFilejsonArray.size() - end
			
			if(!isFileExist) {System.out.println("   기존에 존재하던 파일이 삭제된 경우 - DB와 실제 파일 삭제 isFileExist: "+isFileExist);
				// 기존에 존재하던 파일이 삭제된 경우 - DB와 실제 파일 삭제
				// 파일 휴지통으로 이동
				oldFile = FileUtils.getFile(file_root+File.separator
						+oldCompanyFolderName+File.separator
						+"전용품목"+File.separator
						+storedFileList.get(i).getFile_name());System.out.println("    휴지통 old - "+file_root+File.separator
								+oldCompanyFolderName+File.separator
								+"전용품목"+File.separator
								+storedFileList.get(i).getFile_name());
						newFile = FileUtils.getFile(file_root+File.separator+"휴지통"+File.separator+"("+storedFileList.get(i).getFile_idx()+")"+storedFileList.get(i).getFile_name());
						System.out.println("    휴지통 new - "+file_root+File.separator+"휴지통"+File.separator+"("+storedFileList.get(i).getFile_idx()+")"+storedFileList.get(i).getFile_name());
						FileUtils.moveFile(oldFile, newFile); // 파일 이동 + 경로 자동 생성
			
						System.out.println("휴지통에 버리고 DB에서도 삭제할 거임");
						// 파일 DB에서 제거
						System.out.println("버릴 idx값: "+storedFileList.get(i).getFile_idx());
						inventoryFileDAO.deleteInventroyFile(storedFileList.get(i).getFile_idx());
			} // .if(!isFileExist) - end
			
		} // .for - storedFileList.size() - end
		
		// [새로운 파일 추가]
		insertinventoryFiles(new_company_idx, item_idx, multipartRequest);
	}
	
	/* 재고 품목 삭제 */
	public void deleteInventoryItem(String item_idx) throws Exception {
		
		// 재고 품목 파일(실제 저장된 파일) 삭제
		inventoryFileDAO.deleteInventroyFilesInRoot(item_idx);
		
		// 품목 리스트 삭제
		inventoryDAO.deleteInventoryItem(item_idx);
	}
}
