package spring.mvc.bms_project.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms_project.vo.guestCartVO;
import spring.mvc.bms_project.vo.hostBookVO;
import spring.mvc.bms_project.vo.totalVO;



public interface hostBookDAO {
	
	//�Խñ� �ۼ�, å�̹��� �߰�
	public int insertBook(hostBookVO dto);
	
	//å���� ���ϱ�
	public int getArticleCnt();
	
	//å��� ��� ��ȸ
	public ArrayList<hostBookVO> getArticleList(Map<String, Integer> map);
	
	//��������
	public hostBookVO getArticle(int bookNum);
	/*
	//��й�ȣ Ȯ��(å ����, å ����)
	public int pwdCheck(int bookNum, String strPwd);
	
	//å���� ����
	public int updateBook(hostBookVO dto);
	
	//å ����
	public int deleteBook(int bookNum);
	*/
	//�ֹ����
	public ArrayList<guestCartVO> getorderlist(int cnt);
	
	//�ֹ���û��Ͽ� �ö�� ���� ����
	public int payNum();
	
	//����/���� ����
	public String selectforeign(int bookNum);
	
	//�ֹ��Ϸ������� step=2
	public void stepUpdate(int payNum);
	
	//public void total(Map<String, Object> map); //bookforeign, price, count
	public void total_in(Map<String, Object> map); //bookforeign, price, count
	
	public void total_out(Map<String, Object> map); //bookforeign, price, count
	
	//ȯ���ϸ� �ٽ� book���̺� ����� ����
	//public void hostrefundbook(int bookNum, int bookcount);
	public void hostrefundbook(Map<String, Object> map);
	
	//ȯ���ҋ� ���ݾ׿��� ȯ���� �ݾ��� ����
	public void totalupdate(int price);
	
	//ȯ���Ҷ� step=4�� �ٲٱ�
	public void updatestep(int payNum);
	
	//ȯ���ϴ� å�� �������� /�������� ��������
	public String foreigncheck(int bookNum);
	
	
	//public void totalforeign_in(String foreign, int bookcount);
	public void totalforeign_in(Map<String, Object> map1);
	
	//public void totalforeign_out(String foreign, int bookcount);
	public void totalforeign_out(Map<String, Object> map1);
	
	//public void totalforeign(String foreign, int bookcount);
	
	
	public totalVO selecttotal();
	
}
