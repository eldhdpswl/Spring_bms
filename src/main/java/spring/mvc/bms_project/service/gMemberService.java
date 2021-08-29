package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface gMemberService {
	//중복확인
	public void confirmId(HttpServletRequest req, Model model);
	
	//회원가입처리
	public void inputPro(HttpServletRequest req, Model model);
	
	//로그인 처리
	public void loginPro(HttpServletRequest req, Model model);
	
	//회원탈퇴처리
	public void deletePro(HttpServletRequest req, Model model);
	
	//회원정보수정 수정 상세 페이지
	public void modifyView(HttpServletRequest req, Model model);
	
	//회원정보 수정
	public void modifyPro(HttpServletRequest req, Model model);
	
}
