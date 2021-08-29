package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface hostService {
	
	//책추가 처리페이지
	public void host_stockaddPro(MultipartHttpServletRequest req, Model model);
	
	//책목록
	public void host_stockList(HttpServletRequest req, Model model);
	
	//상세페이지
	public void bookcontentForm(HttpServletRequest req, Model model);
	/*
	//책수정 상세페이지
	public void bookmodifyView(HttpServletRequest req, HttpServletResponse res);
	
	//책 수정 처리페이지
	public void bookmodifyPro(HttpServletRequest req, HttpServletResponse res);
	
	//책 삭제 폼페이지
	public void bookdeleteForm(HttpServletRequest req, HttpServletResponse res);
	
	//책 삭제 처리페이지
	public void bookdeletePro(HttpServletRequest req, HttpServletResponse res);
	*/
	
	//주문요청 처리 페이지
	public void orderlist(HttpServletRequest req, Model model);
	
	//host가 배송하기 클릭했을때
	public void delivery(HttpServletRequest req, Model model);
	
	//환불
	public void hostrefund(HttpServletRequest req, Model model);
	
	//결산
	public void finaltotal(HttpServletRequest req, Model model);
	
	
}
