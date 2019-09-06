package com.bank.web.daoImpls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.bank.web.pool.Constants;
import com.bank.web.daos.MemberDAO;
import com.bank.web.domains.CustomerBean;
import com.bank.web.domains.EmployeeBean;

public class MemberDAOImpl implements MemberDAO{



	@Override
	public void insertCustomer(CustomerBean param) {
		try {
			File file = new File(Constants.FILE_PATH+"customers0905.txt");
			@SuppressWarnings("resource")
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write(param.getId()+","+param.getPw()+","+param.getName()+","+param.getSsn()+","+param.getCredit());
			writer.newLine();      
			writer.flush();
			
		
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertEmplyoee(EmployeeBean param) {
		try{
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		
		
	}

	@Override
	public CustomerBean login(CustomerBean param) {
		return null;
	}
	

}
