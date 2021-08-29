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
	
	//글갯수 구하기
	@Override
	public int getArticleCnt() {
		int cnt = 0;
		
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		cnt = dao.getArticleCnt();
		
		return cnt;
	}
	
	//게시글 목록 조회
	@Override
	public ArrayList<gBoardVO> getArticleList(Map<String, Integer> map) {
		ArrayList<gBoardVO> dtos = null;  //큰바구니
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		dtos = dao.getArticleList(map);
			
		return dtos;
		
	}
	
	//상세페이지
	@Override
	public gBoardVO getArticle(int num) {
		
		gBoardVO dto = null;
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		
		dto= dao.getArticle(num);
		
		return dto;
	}
	
	//조회수 증가
	@Override
	public void addReadCnt(int num) {
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		dao.addReadCnt(num);
		
	}
	
	//비밀번호 확인(게시글 수정, 게시글 삭제)
	@Override
	public int pwdCheck(Map<String, Object> map) {
		int selectCnt = 0;
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		selectCnt = dao.pwdCheck(map);
		System.out.println("selectCnt-->" + selectCnt);
		return selectCnt;
		
	}
	
	//게시글 수정
	@Override
	public int updateBoard(gBoardVO dto) {
		int cnt = 0;
		
		gBoardDAO dao= sqlSession.getMapper(gBoardDAO.class);
		cnt = dao.updateBoard(dto);
		
		return cnt;
	}
	
	//답변글이 아닌 경우(제목글인 경우)
	@Override
	public int getMaxNum() {
		//sql = "SELECT MAX(num) FROM mvc_board";  //최신글부터 가져온다.(최신글부터 뿌려주므로 게시글작성시 글번호는 최신글번호이어야 한다.)
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		int maxNum = dao.getMaxNum();
		return maxNum;
	}
	
	//답변글인 경우	
	@Override
	public void updateReply(gBoardVO dto) {
		//삽입할 글보다 아래쪽 글들을 update
		//sql = "UPDATE mvc_board SET ref_step = ref_step+1 WHERE ref=? AND ref_step > ?";
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		dao.updateReply(dto);
	}
	
	
	//게시글 작성
	@Override
	public int insertBoard(gBoardVO dto) {
		int cnt = 0;
		
		int num = dto.getNum();
		int ref = dto.getRef();
		int ref_step = dto.getRef_step();
		int ref_level = dto.getRef_level();
		
		if(num == 0) {
			cnt = getArticleCnt();
				
				
			//첫글이 아닌 경우
			if(cnt > 0) {
				
				ref = getMaxNum() + 1;
				
			//첫글인 경우	
			}else {
				ref = 1;
			}
			
			ref_step = 0;
			ref_level = 0;
			
		//답변글인 경우	
		}else {
			
			updateReply(dto);
			
			ref_step++;
			ref_level++;
		}
			
		//주의사항
		dto.setRef(ref);
		dto.setRef_step(ref_step);
		dto.setRef_level(ref_level);
		
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		cnt=dao.insertBoard(dto);
		
		return cnt;
	}
	
	//답글이 존재하는지 여부
	@Override
	public int checkReply(gBoardVO dto){
		int cnt = 0; 
		gBoardDAO dao=sqlSession.getMapper(gBoardDAO.class);
		cnt=dao.checkReply(dto);
		return cnt;
	}
	
	@Override
	// 답글이 없는 경우 삭제(제목글, 답글없는 답글)
	public void updateRef_step(gBoardVO dto) {
		//1)삭제글보다 아래에 있는 글들을 step-1을 해서 1줄씩 올린다.
		//같은 ref, 같은 level을 가진 답글중 답글이 없는 경우 삭제
		gBoardDAO dao=sqlSession.getMapper(gBoardDAO.class);
		dao.updateRef_step(dto);
	}
	
	//게시글 삭제
	@Override
	public int deleteBoard(int num) {
		int cnt = 0;
		gBoardDAO dao = sqlSession.getMapper(gBoardDAO.class);
		//상세조회
		gBoardVO dto = dao.getArticle(num);
		// 답글이 존재하는지 여부
		int chkReply = checkReply(dto);
		// 답글이 있는 경우 삭제하지 않겠다.
		if(chkReply != 0) {
			cnt = -1;
		// 답글이 없는 경우 삭제(제목글, 답글없는 답글)
		} else {
			updateRef_step(dto);
			//2)삭제 ----> 완료
			cnt = dao.deleteBoard(num);
		}
		System.out.println("deleteBoard->" + cnt);
		return cnt;
	}
	

	
	
}
