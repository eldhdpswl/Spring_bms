package spring.mvc.bms_project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.bms_project.vo.gBoardVO;
import spring.mvc.bms_project.persistence.gBoardDAO;
import spring.mvc.bms_project.vo.gBoardVO;



@Repository
public class gBoardDAOImpl implements gBoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	//�۰��� ���ϱ�
	@Override
	public int getArticleCnt() {
		int cnt = 0;
		
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		cnt = dao.getArticleCnt();
		
		return cnt;
	}
	
	//�Խñ� ��� ��ȸ
	@Override
	public ArrayList<gBoardVO> getArticleList(Map<String, Integer> map) {
		ArrayList<gBoardVO> dtos = null;  //ū�ٱ���
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		dtos = dao.getArticleList(map);
			
		return dtos;
		
	}
	
	//��������
	@Override
	public gBoardVO getArticle(int num) {
		
		gBoardVO dto = null;
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		
		dto= dao.getArticle(num);
		
		return dto;
	}
	
	//��ȸ�� ����
	@Override
	public void addReadCnt(int num) {
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		dao.addReadCnt(num);
		
	}
	
	//��й�ȣ Ȯ��(�Խñ� ����, �Խñ� ����)
	@Override
	public int pwdCheck(Map<String, Object> map) {
		int selectCnt = 0;
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		selectCnt = dao.pwdCheck(map);
		System.out.println("selectCnt-->" + selectCnt);
		return selectCnt;
		
	}
	
	//�Խñ� ����
	@Override
	public int updateBoard(gBoardVO dto) {
		int cnt = 0;
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		cnt = dao.updateBoard(dto);
		
		return cnt;
	}
	
	//�亯���� �ƴ� ���(������� ���)
	@Override
	public int getMaxNum() {
		//sql = "SELECT MAX(num) FROM mvc_board";  //�ֽűۺ��� �����´�.(�ֽűۺ��� �ѷ��ֹǷ� �Խñ��ۼ��� �۹�ȣ�� �ֽű۹�ȣ�̾�� �Ѵ�.)
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		int maxNum = dao.getMaxNum();
		return maxNum;
	}
	
	//�亯���� ���	
	@Override
	public void updateReply(gBoardVO dto) {
		//������ �ۺ��� �Ʒ��� �۵��� update
		//sql = "UPDATE mvc_board SET ref_step = ref_step+1 WHERE ref=? AND ref_step > ?";
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		dao.updateReply(dto);
	}
	
	
	//�Խñ� �ۼ�
	@Override
	public int insertBoard(gBoardVO dto) {
		int cnt = 0;
		
		int num = dto.getNum();
		int ref = dto.getRef();
		int ref_step = dto.getRef_step();
		int ref_level = dto.getRef_level();
		
		if(num == 0) {
			cnt = getArticleCnt();
				
				
			//ù���� �ƴ� ���
			if(cnt > 0) {
				
				ref = getMaxNum() + 1;
				
			//ù���� ���	
			}else {
				ref = 1;
			}
			
			ref_step = 0;
			ref_level = 0;
			
		//�亯���� ���	
		}else {
			
			updateReply(dto);
			
			ref_step++;
			ref_level++;
		}
			
		//���ǻ���
		dto.setRef(ref);
		dto.setRef_step(ref_step);
		dto.setRef_level(ref_level);
		
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		cnt=dao.insertBoard(dto);
		
		return cnt;
	}
	
	//����� �����ϴ��� ����
	@Override
	public int checkReply(gBoardVO dto){
		int cnt = 0; 
		gBoardDAO dao=sqlSession.getMapper(gBoardDAO.class);
		cnt=dao.checkReply(dto);
		return cnt;
	}
	
	@Override
	// ����� ���� ��� ����(�����, ��۾��� ���)
	public void updateRef_step(gBoardVO dto) {
		//1)�����ۺ��� �Ʒ��� �ִ� �۵��� step-1�� �ؼ� 1�پ� �ø���.
		//���� ref, ���� level�� ���� ����� ����� ���� ��� ����
		gBoardDAO dao=sqlSession.getMapper(gBoardDAO.class);
		dao.updateRef_step(dto);
	}
	
	//�Խñ� ����
	@Override
	public int deleteBoard(int num) {
		int cnt = 0;
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		//����ȸ
		gBoardVO dto = dao.getArticle(num);
		// ����� �����ϴ��� ����
		int chkReply = checkReply(dto);
		// ����� �ִ� ��� �������� �ʰڴ�.
		if(chkReply != 0) {
			cnt = -1;
		// ����� ���� ��� ����(�����, ��۾��� ���)
		} else {
			updateRef_step(dto);
			//2)���� ----> �Ϸ�
			cnt = dao.deleteBoard(num);
		}
		System.out.println("deleteBoard->" + cnt);
		return cnt;
	}
	

	
	
}
