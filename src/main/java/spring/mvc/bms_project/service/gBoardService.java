package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface gBoardService {
	
	//�۸��
	public void boardList(HttpServletRequest req,  Model model);
	
	//��������
	public void b_contentForm(HttpServletRequest req, Model model);
	
	//�ۼ��� ��������
	public void b_modifyView(HttpServletRequest req, Model model);
	
	//�ۼ��� ó��������
	public void b_modifyPro(HttpServletRequest req, Model model);
	
	//���ۼ� ó��������
	public void b_writePro(HttpServletRequest req, Model model);
	
	//�ۻ��� ó��������
	public void b_deletePro(HttpServletRequest req, Model model);
	
	
}
