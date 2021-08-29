package spring.mvc.bms_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface hostService {
	
	//å�߰� ó��������
	public void host_stockaddPro(MultipartHttpServletRequest req, Model model);
	
	//å���
	public void host_stockList(HttpServletRequest req, Model model);
	
	//��������
	public void bookcontentForm(HttpServletRequest req, Model model);
	/*
	//å���� ��������
	public void bookmodifyView(HttpServletRequest req, HttpServletResponse res);
	
	//å ���� ó��������
	public void bookmodifyPro(HttpServletRequest req, HttpServletResponse res);
	
	//å ���� ��������
	public void bookdeleteForm(HttpServletRequest req, HttpServletResponse res);
	
	//å ���� ó��������
	public void bookdeletePro(HttpServletRequest req, HttpServletResponse res);
	*/
	
	//�ֹ���û ó�� ������
	public void orderlist(HttpServletRequest req, Model model);
	
	//host�� ����ϱ� Ŭ��������
	public void delivery(HttpServletRequest req, Model model);
	
	//ȯ��
	public void hostrefund(HttpServletRequest req, Model model);
	
	//���
	public void finaltotal(HttpServletRequest req, Model model);
	
	
}
