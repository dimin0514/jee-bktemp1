package com.bank.web.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.web.command.Sender;
import com.bank.web.command.Command;
import com.bank.web.command.MoveCommand;
import com.bank.web.command.Order;
import com.bank.web.command.Receiver;
import com.bank.web.domains.CustomerBean;
import com.bank.web.pool.Constants;
import com.bank.web.serviceimpls.MemberServiceImpl;
import com.bank.web.services.MemberService;

@WebServlet("/customer.do")   //서블릿 패스
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* String action=request.getParameter("action");
		 * syso("액션":+actoin);
		 * String dest =request.getParameter("dest");
		 * System.out.println("데스트"+dest);
		 */
	
		CustomerBean param = new CustomerBean();
		MemberService service = new MemberServiceImpl();
		Receiver.init(request);
		Receiver.cmd.execute();
		
		if(Receiver.cmd.getAction()==null)  {
	          Receiver.cmd.setPage();
	      }
		
			switch(Receiver.cmd.getAction()) {
			
			case"join":
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String ssn = request.getParameter("ssn");
				String credit = request.getParameter("credit");
				param.setCredit(credit);
				param.setId(id);
				param.setName(name);
				param.setPw(pw);
				param.setSsn(ssn);
				//System.out.println("회원정보: "+ param.toString());
				service.join(param);
				Receiver.cmd.setPage("login");
				
				
				//request.getRequestDispatcher(String.format(Constants.VIEW_PATH,"customer",request.getParameter("dest"))).forward(request, response);
				break;
			case "login": 
				id = request.getParameter("id");
				pw = request.getParameter("pw");
				param.setId(id);
				param.setPw(pw);
				param.setName("김유신");
				CustomerBean cust = service.login(param);
				
				
				if(cust!=null) {
					Receiver.cmd.setPage("login");
					//System.out.println("customeraa"+cust);
					//request.setAttribute("customer", cust);
					//System.out.printf("로그인 서비스 진입 후 아이디:%s 비번:%s",id,pw);
					//request.getRequestDispatcher(String.format(Constants.VIEW_PATH,"customer",request.getParameter("dest"))).forward(request, response);
					
				}else {
					
					Receiver.cmd.setPage("mypage");
					//request.getRequestDispatcher("index.jsp").forward(request, response);
					
					
				}
				 request.setAttribute("customer",cust);
		             
		            break;
		         case "existId":
		             break;
			
			}
			Sender.forward(request,  response);
		
		/*
		String action =request.getParameter("action");
		RequestDispatcher rd= null;
		switch(action) {
		case"login": 
			 rd= request.getRequestDispatcher("WEB-INF/views/customer/login.jsp");
			break;
		case"join":
			rd = request.getRequestDispatcher("WEB-INF/views/customer/join.jsp");
			break;
		}
		rd.forward(request, response);
		/*
		 * String jspName="";
		 * case에 
		 * 1이면 jspName= "join"; 정의됨
		 * 2에 jspName="login"; 정의됨
		 * 스위치 끝나면 
		 * 케이스에 따라 아래 정의된 값이 rd 가 됨
		 * 	rd = request.getRequestDispatcher(String.format("WEB-INF/views/customer/%s.jsp",jspName);
		 *  rd.forward(request, response);
		 *  
		 *  더 줄이면?
		 *  request.getRequestDispatcher(String.format("WEB-INF/views/customer/%s.jsp",request.getParameter("action"))).forward(request, response);
		 *  이게 됨..!!
		 */
		
		
		/*
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String ssn = request.getParameter("ssn");
		String credit = request.getParameter("credit");
		CustomerBean param = new CustomerBean();
		param.setCredit(credit);
		param.setId(id);
		param.setName(name);
		param.setPw(pw);
		param.setSsn(ssn);
		System.out.println("회원정보: "+ param.toString());
		MemberService service = new MemberServiceImpl();
		service.join(param);
		
		request.getRequestDispatcher(String.format("WEB-INF/views/customer/%s.jsp",request.getParameter("action"))).forward(request, response);
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
