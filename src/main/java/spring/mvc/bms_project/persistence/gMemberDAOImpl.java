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
	
	
	//중복확인체크
	@Override
	public int idCheck(String strId) {
		int cnt = 0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt = dao.idCheck(strId);
		
		return cnt;
	}
	
	//회원가입
	@Override
	public int insertMember(gMemberVO vo) {
		int cnt = 0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt = dao.insertMember(vo);
		
		return cnt;
	}
	
	//체크 --  로그인, 정보수정, 정보삭제
	@Override
	public int pwdCheck(Map<String, Object> map) {
		int cnt = 0;
		
		//로그인화면에서 입력받은 id와 일치한 데이터가 있는지 확인
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		int idChkCnt = dao.idCheck((String)map.get("strId"));
		
		//로그인한 id에 해당하는 데이터가 있고
		if(idChkCnt==1) {
			int pwdChkCnt = dao.pwdCheck(map);
			
			if(pwdChkCnt==1) {
				//패스워드가 일치하면 cnt==1
				cnt=1;
			}else {
				//패스워드가 일치하지 않으면 cnt= -1
				cnt=-1;
			}
		}
		
		return cnt;
		
	}
	
	
	//회원탈퇴처리
	@Override
	public int memberDelete(String strId) {
		int cnt=0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt=dao.memberDelete(strId);
		
		return cnt;
	}
	
	
	//회원정보 상세페이지 - 아이디와 일치한 회원정보 가져오기(정보수정화면 제공)
	@Override
	public gMemberVO getMemberInfo(String strId) {
		//1. 바구니 생성
		gMemberVO vo = new gMemberVO();
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		vo=dao.getMemberInfo(strId);
		
		return vo;
	}
	
	//회원정보수정
	@Override
	public int updateMember(gMemberVO vo) {
		int cnt=0;
		
		gMemberDAO dao = sqlSession.getMapper(gMemberDAO.class);
		cnt = dao.updateMember(vo);
		
		return cnt;
		
	}
	
}
