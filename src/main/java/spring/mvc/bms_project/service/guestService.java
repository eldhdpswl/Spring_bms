package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface guestService {
	
	//å���/
	public void stockList(HttpServletRequest req, Model model);
	
	//��������/
	public void stockcontent(HttpServletRequest req, Model model);
	
	//��ٱ��� å��� ó��������
	public void cartTakePro(HttpServletRequest req, Model model);
	
	//�� ��ٱ��� ���
	public void mycartlist(HttpServletRequest req, Model model);
	
	//checkbox�� üũ�Ҷ� ���Ŵ� 
	public void pay(HttpServletRequest req, Model model);
	
	//���Ÿ�� ����Ʈ �ҷ�����
	public void paylist(HttpServletRequest req, Model model);
	
	//���Ÿ�Ͽ��� �����ϱ� ��������
	public void payPro(HttpServletRequest req,  Model model);
	
	//ȯ���ϱ�
	public void payNum(HttpServletRequest req, Model model);
	
	public void directpay(HttpServletRequest req,  Model model);
	
	public void selectBCount(HttpServletRequest req,  Model model);
	
	//�������
	public void paycancel(HttpServletRequest req,  Model model);
	
	
	
}
