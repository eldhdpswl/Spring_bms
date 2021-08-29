package spring.mvc.bms_project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.bms_project.vo.gMemberVO;


@Repository
public class gMemberDAOImpl implements gMemberDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//�ߺ�Ȯ��üũ
	@Override
	public int idCheck(String strId) {
		int cnt = 0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt = dao.idCheck(strId);
		
		return cnt;
	}
	
	//ȸ������
	@Override
	public int insertMember(gMemberVO vo) {
		int cnt = 0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt = dao.insertMember(vo);
		
		return cnt;
	}
	
	//üũ --  �α���, ��������, ��������
	@Override
	public int pwdCheck(Map<String, Object> map) {
		int cnt = 0;
		
		//�α���ȭ�鿡�� �Է¹��� id�� ��ġ�� �����Ͱ� �ִ��� Ȯ��
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		int idChkCnt = dao.idCheck((String)map.get("strId"));
		
		//�α����� id�� �ش��ϴ� �����Ͱ� �ְ�
		if(idChkCnt==1) {
			int pwdChkCnt = dao.pwdCheck(map);
			
			if(pwdChkCnt==1) {
				//�н����尡 ��ġ�ϸ� cnt==1
				cnt=1;
			}else {
				//�н����尡 ��ġ���� ������ cnt= -1
				cnt=-1;
			}
		}
		
		return cnt;
		
	}
	
	
	//ȸ��Ż��ó��
	@Override
	public int memberDelete(String strId) {
		int cnt=0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt=dao.memberDelete(strId);
		
		return cnt;
	}
	
	
	//ȸ������ �������� - ���̵�� ��ġ�� ȸ������ ��������(��������ȭ�� ����)
	@Override
	public gMemberVO getMemberInfo(String strId) {
		//1. �ٱ��� ����
		gMemberVO vo = new gMemberVO();
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		vo=dao.getMemberInfo(strId);
		
		return vo;
	}
	
	//ȸ����������
	@Override
	public int updateMember(gMemberVO vo) {
		int cnt=0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt = dao.updateMember(vo);
		
		return cnt;
		
	}
	
}
