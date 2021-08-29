package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface gMemberService {
	//�ߺ�Ȯ��
	public void confirmId(HttpServletRequest req, Model model);
	
	//ȸ������ó��
	public void inputPro(HttpServletRequest req, Model model);
	
	//�α��� ó��
	public void loginPro(HttpServletRequest req, Model model);
	
	//ȸ��Ż��ó��
	public void deletePro(HttpServletRequest req, Model model);
	
	//ȸ���������� ���� �� ������
	public void modifyView(HttpServletRequest req, Model model);
	
	//ȸ������ ����
	public void modifyPro(HttpServletRequest req, Model model);
	
}
