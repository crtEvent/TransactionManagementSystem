package com.sshmanager.ssh.main.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sshmanager.ssh.main.dto.TransactionDTO;
import com.sshmanager.ssh.main.service.CompanyService;
import com.sshmanager.ssh.main.service.TransactionService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	TransactionService transactionService;
	
	/* 거래 리스트 (jsGrid) */
	@RequestMapping("/transaction-list")
	@ResponseBody
	public List<TransactionDTO> getTransactionList(String company_idx) throws Exception {
		
		List<TransactionDTO> list = transactionService.getTransactionList(company_idx);
		
		return list;
	}
	
	/* 거래 세부 내역 보기 */
	@PostMapping("/transaction-details")
	public String getTransactionDetails(Model model
								, RedirectAttributes ridirectAttr
								, String transaction_idx) throws Exception {
		
		ridirectAttr.addAttribute("transaction_idx", transaction_idx);
		
		return "redirect:/transaction/transaction-details-result";
	}
	
	/* 거래 세부 내역 결과 */
	@GetMapping("/transaction-details-result")
	public String transactionDetailsResult(Model model ,String transaction_idx) throws Exception {
	
		model.addAttribute("transactionDTO", transactionService.getTransaction(transaction_idx));
		model.addAttribute("itemList", transactionService.getItemList(transaction_idx));
		model.addAttribute("memoList", transactionService.getMemoList(transaction_idx));
		model.addAttribute("quoteFileList", transactionService.getQuoteFileList(transaction_idx));
		model.addAttribute("orderFileList", transactionService.getOrderFileList(transaction_idx));
		model.addAttribute("imageFileList", transactionService.getImageFileList(transaction_idx));
		model.addAttribute("otherFileList", transactionService.getOtherFileList(transaction_idx));
		
		return "/main/transaction_details";
	}
	
	/* 거래 업데이트 모달 보기 */
	@PostMapping("/transaction-update-modal")
	public String getTransactionUpdateModal(Model model
								, RedirectAttributes ridirectAttr
								, String transaction_idx) throws Exception {
		
		ridirectAttr.addAttribute("transaction_idx", transaction_idx);
		
		return "redirect:/transaction/transaction-update-modal-result";
	}
	
	/* 거래 업데이트 모달 보기 결과 */
	@GetMapping("/transaction-update-modal-result")
	public String getTransactionUpdateModalResult(Model model ,String transaction_idx) throws Exception {
	
		model.addAttribute("transactionDTO", transactionService.getTransaction(transaction_idx));
		model.addAttribute("itemList", transactionService.getItemList(transaction_idx));
		model.addAttribute("memoList", transactionService.getMemoList(transaction_idx));
		model.addAttribute("quoteFileList", transactionService.getQuoteFileList(transaction_idx));
		model.addAttribute("orderFileList", transactionService.getOrderFileList(transaction_idx));
		model.addAttribute("imageFileList", transactionService.getImageFileList(transaction_idx));
		model.addAttribute("otherFileList", transactionService.getOtherFileList(transaction_idx));
		
		return "/modal/modal_update_transaction_body";
	}
	
	/* 거래 내역 입력 - item, memo file */
	@RequestMapping("/insert-transaction")
	@ResponseBody
	public ResponseEntity<?> insertTransaction(MultipartHttpServletRequest multipartRequest
			, TransactionDTO transactionDTO
			, String itemJsonData
			, String memoJsonData) {
			
		try {
			String transaction_idx = transactionService.insertTransaction(transactionDTO);
			
			transactionService.insertTransactionDetails(transaction_idx
					, JSONArray.fromObject(itemJsonData)
					, JSONArray.fromObject(memoJsonData));
		
			transactionService.insesrtTransactionFiles(transactionDTO.getDate()
					, transactionDTO.getCompany_idx()
					, transaction_idx
					, multipartRequest);
			
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}
	
	/* 거래 내역 수정 - item, memo file */
	@RequestMapping("/update-transaction")
	@ResponseBody
	public ResponseEntity<?> updateTransaction(MultipartHttpServletRequest multipartRequest
			, TransactionDTO transactionDTO
			, String itemJsonData
			, String memoJsonData
			, String existingFileJsonData) {
			
		try {
			transactionService.updateTransaction(transactionDTO);
			
			transactionService.updateTransactionDetails(transactionDTO.getTransaction_idx()
					, JSONArray.fromObject(itemJsonData)
					, JSONArray.fromObject(memoJsonData));
		
			transactionService.updateTransactionFiles(transactionDTO.getDate()
					, transactionDTO.getCompany_idx()
					, transactionDTO.getTransaction_idx()
					, multipartRequest
					, JSONArray.fromObject(existingFileJsonData));
			
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}
	
	@RequestMapping("/delete-transaction")
	@ResponseBody
	public void deleteTransaction(String transaction_idx) throws Exception {
		
		transactionService.deleteTransaction(transaction_idx);
		
	}
	
}
