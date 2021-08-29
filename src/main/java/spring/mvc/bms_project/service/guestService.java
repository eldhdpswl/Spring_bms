package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface guestService {
	
	//책목록/
	public void stockList(HttpServletRequest req, Model model);
	
	//상세페이지/
	public void stockcontent(HttpServletRequest req, Model model);
	
	//장바구니 책담기 처리페이지
	public void cartTakePro(HttpServletRequest req, Model model);
	
	//내 장바구니 목록
	public void mycartlist(HttpServletRequest req, Model model);
	
	//checkbox로 체크할때 구매단 
	public void pay(HttpServletRequest req, Model model);
	
	//구매목록 리스트 불러오기
	public void paylist(HttpServletRequest req, Model model);
	
	//구매목록에서 결재하기 눌렀을때
	public void payPro(HttpServletRequest req,  Model model);
	
	//환불하기
	public void payNum(HttpServletRequest req, Model model);
	
	public void directpay(HttpServletRequest req,  Model model);
	
	public void selectBCount(HttpServletRequest req,  Model model);
	
	//구매취소
	public void paycancel(HttpServletRequest req,  Model model);
	
	
	
}
