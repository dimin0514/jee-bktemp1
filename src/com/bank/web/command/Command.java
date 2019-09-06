package com.bank.web.command;
import javax.servlet.http.HttpServletRequest;
import com.bank.web.pool.Constants;
import lombok.Data;
@Data
public class Command implements Order{
	//equest is wrapped under an object as command
	protected HttpServletRequest request;
	protected String action, domain, page,view;

	@Override
	public void execute() {
		setDomain();
		setPage();
		System.out.println("리쿼스트가 가야할곳"+String.format(Constants.VIEW_PATH, domain,page));
		this.view =  String.format(Constants.VIEW_PATH,domain,page);     
		/* request.getRequestDispatcher
		(String.format(Constants.VIEW_PATH,
				"customer",
				request.getParameter("dest")))
		.forward(request, response); */
		
	}
	
	public void setDomain() {
		String path = request.getServletPath();
		System.out.println("서블릿 패스가 뭘가?"+path);
		
		
		domain = path.replace(".do",""); // .do 를 없애라
		domain = domain.substring(1);  //1부터 끝까지 숫자  //도메인 추출
		
		/*
		path = path.replace(".do", "");
		domain=path;
		*/
		
	}
	public void setPage() {
		 page = request.getParameter("page");
	}
}
