package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface gBoardService {
	
	//글목록
	public void boardList(HttpServletRequest req,  Model model);
	
	//상세페이지
	public void b_contentForm(HttpServletRequest req, Model model);
	
	//글수정 상세페이지
	public void b_modifyView(HttpServletRequest req, Model model);
	
	//글수정 처리페이지
	public void b_modifyPro(HttpServletRequest req, Model model);
	
	//글작성 처리페이지
	public void b_writePro(HttpServletRequest req, Model model);
	
	//글삭제 처리페이지
	public void b_deletePro(HttpServletRequest req, Model model);
	
	
}
