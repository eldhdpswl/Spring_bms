package spring.mvc.bms_project.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms_project.vo.gBoardVO;


public interface gBoardDAO {
	
	//�۰��� ���ϱ�
	public int getArticleCnt();
	
	//�Խñ� ��� ��ȸ
	public ArrayList<gBoardVO> getArticleList(Map<String, Integer> map);
	
	//��������
	public gBoardVO getArticle(int num);
	
	//��ȸ�� ����
	public void addReadCnt(int num);
	
	//��й�ȣ Ȯ��(�Խñ� ����, �Խñ� ����)
	public int pwdCheck(Map<String, Object> map);
	
	//�Խñ� ����
	public int updateBoard(gBoardVO dto);
	
	//�Խñ��ۼ�  start
	//�亯���� �ƴ� ���(������� ���)-�ű�
	public int getMaxNum();
	
	//�亯���� ���	- �ű�
	public void updateReply(gBoardVO dto);
	
	//�Խñ� �ۼ�
	public int insertBoard(gBoardVO dto);
	//�Խñ��ۼ�-end
	
	//�Խñ� ���� start
	//����� �����ϴ��� ����(�����, ����� ���°��) - �ű�
	public int checkReply(gBoardVO dto);
	//����� ���� ���
	public void updateRef_step(gBoardVO dto);
	
	//�Խñ� ����
	public int deleteBoard(int num);
	//�Խñ� ���� end
	
	
	
}
