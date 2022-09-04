package com.sshmanager.ssh.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sshmanager.ssh.main.domain.FileType;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.service.FileService;

@Controller
@RequestMapping("/view")
public class ViewFilesController {
	
	@Autowired
	FileService fileService;
	
	@PostMapping("/view-image")
	public String viewImageFile(RedirectAttributes ridirectAttr
			, String company_idx, String year) throws Exception {
		
		ridirectAttr.addAttribute("company_idx", company_idx);
		ridirectAttr.addAttribute("year", year);
		
		return "redirect:/view/view-image-result";
	}
	
	@GetMapping("/view-image-result")
	public String viewImageFileResult(Model model, String company_idx, String year) throws Exception {
		
		model.addAttribute("imageList", fileService.getFileByFileType(company_idx, FileType.IMAGE, year));
		
		return "/main/view_image_list";
	}
	
}
