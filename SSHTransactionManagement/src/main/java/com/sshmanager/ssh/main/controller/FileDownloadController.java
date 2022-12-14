package com.sshmanager.ssh.main.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sshmanager.ssh.main.dao.InventoryFileDAO;
import com.sshmanager.ssh.main.dao.PathDAO;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.service.FileService;

@Controller
@RequestMapping("/file")
public class FileDownloadController {

	ResourceLoader resourceLoader;

	@Autowired
	public FileDownloadController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Autowired
	FileService fileService;
	
	@Autowired
	InventoryFileDAO inventoryFileDAO;

	@RequestMapping("/download")
	public ResponseEntity<Resource> downloadFile(String file_idx, String db) {

		try {
			
			FileDTO fileDTO = new FileDTO();
			
			if(db == null || db.equals("")) {
				fileDTO = fileService.getFile(file_idx);
			} else if(db.equals("inventory")) {
				fileDTO = inventoryFileDAO.selectInventoryFile(file_idx);
			}
			
			String file_name = fileDTO.getFile_name();
			String ext = file_name.substring(file_name.lastIndexOf(".")+1).toLowerCase();
			String disposition;
			MediaType mediaType;
			String filePath = fileDTO.getFile_path()+"/"+file_name;
			
			switch(ext) {
			case "jpeg":
				disposition = "inline";
				mediaType = MediaType.IMAGE_JPEG;
				break;
			case "jpg":
				disposition = "inline";
				mediaType = MediaType.IMAGE_JPEG;
				break;
			case "png":
				disposition = "inline";
				mediaType = MediaType.IMAGE_PNG;
				break;
			case "gif":
				disposition = "inline";
				mediaType = MediaType.IMAGE_GIF;
				break;
			case "pdf":
				disposition = "inline";
				mediaType = MediaType.APPLICATION_PDF;
				break;
			default:
				disposition = "attachment";
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}
			

			Resource resource = resourceLoader.getResource("file:/"+filePath);
			File file = resource.getFile();
			String fileName = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");;

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, disposition+"; filename=\"" + fileName + "\"")
					.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
					.header(HttpHeaders.CONTENT_TYPE, mediaType.toString()).body(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
