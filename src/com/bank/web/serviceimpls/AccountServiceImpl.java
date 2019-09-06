package com.bank.web.serviceimpls;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.bank.web.domains.AccountBean;
import com.bank.web.services.AccountService;

public class AccountServiceImpl implements AccountService {
	
	private List<AccountBean> accounts;
	Random ran;
	SimpleDateFormat sdf;
	Date date;
	
	public AccountServiceImpl() {
		accounts = new ArrayList<>();
	}
	
	

	@Override
	public void createAccount(String money) {
		AccountBean ac = new AccountBean();
		ac.setAccuntNum(createAccountNum());
		ac.setMoney(money);
		ac.setToday(findDate());
		System.out.println(ac.toString());
		accounts.add(ac);
	}

	@Override
	public String createAccountNum() {
		String accountNum="";
		Random ran = new Random();
		
		for(int i=0;i<9;i++){
			accountNum +=(i==4)?"-":ran.nextInt(10);
		}
		return accountNum;
	}

	@Override
	public List<AccountBean> findAll() {
		return accounts;
	}

	@Override
	public AccountBean findByAccountNum(String accountNum) {
		AccountBean temp= new AccountBean();
		for( AccountBean c : accounts) {
			if(accountNum.equals(c.getAccuntNum())) {
				temp = c;
			}
		}
		return temp;
		
	}

	@Override
	public int countAccounts() {
		return accounts.size();
	}

	@Override
	public boolean existAccountNum(String accountNum) {
		AccountBean m = findByAccountNum(accountNum);
		return accounts.contains(m) ;
	}

	@Override
	public String findDate() {
		sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		date = new Date();
		String now = sdf.format(date);
		return now;
	}

	@Override
	public void depositMoney(AccountBean param) {
		AccountBean acc = new AccountBean();
		for(AccountBean bb : accounts) {
			if(param.getAccuntNum().equals(bb.getAccuntNum())) {
				acc = bb;
				int sum = Integer.parseInt(acc.getMoney())+Integer.parseInt(param.getMoney());
				acc.setMoney(sum + "");
			}
		}
	}

	@Override
	public void withdrawMoney(AccountBean param) {
		AccountBean acc = new AccountBean();
		for(AccountBean bb : accounts) {
			if(param.getAccuntNum().equals(bb.getAccuntNum())) {
				acc = bb;
				int sum = Integer.parseInt(param.getMoney())-Integer.parseInt(acc.getMoney());
				acc.setMoney(sum + "");
			}
		}
	}

	@Override
	public void deleteAccountNum(String accountNum) {
		
		for(AccountBean bb : accounts) {
			if(accountNum.equals(bb.getAccuntNum())) {
				accounts.remove(bb);
			}
		}
	}
}
