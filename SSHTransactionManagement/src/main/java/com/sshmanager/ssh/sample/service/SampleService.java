package com.sshmanager.ssh.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshmanager.ssh.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleService {
	
	@Autowired
	private SampleDAO sampleDAO;
	
	public String getTime() throws Exception {
		return sampleDAO.getTime();
	}
	
}
