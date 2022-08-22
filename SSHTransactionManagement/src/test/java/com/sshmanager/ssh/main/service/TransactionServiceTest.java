package com.sshmanager.ssh.main.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sshmanager.ssh.main.service.TransactionService;
import com.sshmanager.ssh.main.dao.TransactionDAO;
import com.sshmanager.ssh.main.dto.FileDTO;
import com.sshmanager.ssh.main.dto.TransactionDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TransactionServiceTest {
	
	@Autowired
	TransactionDAO transactionDAO;
	
	@Autowired
	TransactionService transactionService;
	
	@Test
	public void testSelectTransaction() throws Exception {
		
		String transaction_idx = "ee1d";
		
		TransactionDTO dto;
		dto = transactionDAO.selectTransaction(transaction_idx);
		
		System.out.println("dto.toString(): "+dto.toString());
		
		assertTrue(dto != null);
	}
	
	@Test
	public void testInsertFile() {
		
		FileDTO fileDTO = new FileDTO();
		
		transactionService.insertOrderFile(fileDTO);
		
	}
	
	
	
}
