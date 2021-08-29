package spring.mvc.bms_project.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms_project.vo.cartAddVO;
import spring.mvc.bms_project.vo.guestCartVO;


public interface guestCartDAO {
	
	//å���� ���ϱ�
	public int getArticleCnt();
	
	//å��� ��� ��ȸ
	public ArrayList<guestCartVO> getArticleList(Map<String, Integer> map);
	
	//��������
	public guestCartVO getArticle(int bookNum);
	
	//��ٱ��� cart���̺� �߰�
	public int insertCart(cartAddVO cartdto); 
	
	//��ٱ��� cart���̺� �ҷ�����
	public int selectcart(int bookNum);
	
	//��ٱ��Ͽ� �Ȱ��� å�� ������  ������Ʈ 
	public int updatecount(Map<String, Integer> map);
	
	//��ٱ��Ͽ� �Ȱ��� å�� ������  insert 
	public int insertcount(cartAddVO cartdto);
	
	
	//��ٱ��� å ���� ���ϱ�
	public int getCartArticleCnt();
	
	//��ٱ��� ��� ��ȸ
	//public ArrayList<guestCartVO> getCartArticleList(int cnt, String memId);
	public ArrayList<guestCartVO> getCartArticleList(Map<String, Object> map);
	
	
	//���Ŵ����� �ѱ涧 ��ٱ��� ��� ��ȸ�ϱ�
	public ArrayList<cartAddVO> getCartInfo(String[] cartNum);
	
	//insert
	public void iPay(cartAddVO dto);
	
/*	//���̳� �� ��������
	public int pay_seq();*/
	
	
	//�����ͼ� ��������
	public int payNum(String memid);
	
	//pay���̺� �߰�
	public String insertpay(ArrayList<cartAddVO> list);
	
	//cart���̺� �ִ� �������� delete
	public int deletecart(ArrayList<cartAddVO> delist);
	
	//pay�����ҷ�����
	//public ArrayList<guestCartVO> getPayList(String memId,int cnt);
	public ArrayList<guestCartVO> getPayList(Map map);
	
	public cartAddVO getCart(int num);
	//for������ update���ֱ�
	public void bookCountUpdate(ArrayList<cartAddVO> dtos);
	//update
	public void BCUpdate(cartAddVO dto);
	
	//cart���̺� �ִ������� delete�ϱ�
	public int Dcart(cartAddVO dto);
	
	/*
	//���Ÿ�� å ���� ���ϱ�
	public int getPayArticleCnt();
	
	//��ٱ��� ��� ��ȸ
	public ArrayList<guestCartVO> getPayArticleList(int cnt, String memId);
	*/
	/*//���Ÿ�Ͽ��� �����ϱ��Ҷ� pay���ִ� ��Ϻҷ�����
	public ArrayList<guestCartVO> getPaymentList();*/
	
	//���Ÿ�Ͽ��� �����ϱ��Ҷ� payNumüũ�ϱ�
	public int selectPayNum(guestCartVO dto);
	
	//��ٱ������̺� �����Ͱ� ������
	public int UPpay(guestCartVO dto);
	
	//���Ÿ�Ͽ��� �����ϱ��Ҷ� step update�� �κ�
	public int updatepay(guestCartVO paydto);
	
	//ȯ�ҿ�û������ update
	public int refund(int payNum);
	
	//�ٷα����ҋ� pay�� insert
	//public int insertdirectpay(int bookNum, int bookcount, String id);
	public int insertdirectpay(Map<String, Object> map);
	
	//�ٷα����ҋ� pay�� update
	public int directpayupdate(Map<String, Object> map);
	
	
	//public int selectBCount(int bookNum,int bookcount);
	public int selectBCount(Map<String, Object> map);
	
	
	//��������Ҷ� ���� ���� update
	public int paycancel(Map<String, Object> map);
	
	//��������ҋ� pay���� list����
	public int paylistdelete(int payNum);
}
