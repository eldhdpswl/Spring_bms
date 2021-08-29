package spring.mvc.bms_project.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms_project.vo.gBoardVO;


public interface gBoardDAO {
	
	//글갯수 구하기
	public int getArticleCnt();
	
	//게시글 목록 조회
	public ArrayList<gBoardVO> getArticleList(Map<String, Integer> map);
	
	//상세페이지
	public gBoardVO getArticle(int num);
	
	//조회수 증가
	public void addReadCnt(int num);
	
	//비밀번호 확인(게시글 수정, 게시글 삭제)
	public int pwdCheck(Map<String, Object> map);
	
	//게시글 수정
	public int updateBoard(gBoardVO dto);
	
	//게시글작성  start
	//답변글이 아닌 경우(제목글인 경우)-신규
	public int getMaxNum();
	
	//답변글인 경우	- 신규
	public void updateReply(gBoardVO dto);
	
	//게시글 작성
	public int insertBoard(gBoardVO dto);
	//게시글작성-end
	
	//게시글 삭제 start
	//답글이 존재하는지 여부(제목글, 답글이 없는경우) - 신규
	public int checkReply(gBoardVO dto);
	//답글이 없는 경우
	public void updateRef_step(gBoardVO dto);
	
	//게시글 삭제
	public int deleteBoard(int num);
	//게시글 삭제 end
	
	
	
}
