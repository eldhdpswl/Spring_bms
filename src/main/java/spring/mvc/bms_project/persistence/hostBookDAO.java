package spring.mvc.bms_project.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms_project.vo.guestCartVO;
import spring.mvc.bms_project.vo.hostBookVO;
import spring.mvc.bms_project.vo.totalVO;



public interface hostBookDAO {
	
	//게시글 작성, 책이미지 추가
	public int insertBook(hostBookVO dto);
	
	//책갯수 구하기
	public int getArticleCnt();
	
	//책목록 목록 조회
	public ArrayList<hostBookVO> getArticleList(Map<String, Integer> map);
	
	//상세페이지
	public hostBookVO getArticle(int bookNum);
	/*
	//비밀번호 확인(책 수정, 책 삭제)
	public int pwdCheck(int bookNum, String strPwd);
	
	//책수정 수정
	public int updateBook(hostBookVO dto);
	
	//책 삭제
	public int deleteBook(int bookNum);
	*/
	//주문목록
	public ArrayList<guestCartVO> getorderlist(int cnt);
	
	//주문요청목록에 올라온 수량 갯수
	public int payNum();
	
	//국내/국외 구분
	public String selectforeign(int bookNum);
	
	//주문완료했을댸 step=2
	public void stepUpdate(int payNum);
	
	//public void total(Map<String, Object> map); //bookforeign, price, count
	public void total_in(Map<String, Object> map); //bookforeign, price, count
	
	public void total_out(Map<String, Object> map); //bookforeign, price, count
	
	//환불하면 다시 book테이블에 재고량을 더함
	//public void hostrefundbook(int bookNum, int bookcount);
	public void hostrefundbook(Map<String, Object> map);
	
	//환불할떄 결산금액에서 환불한 금액을 차감
	public void totalupdate(int price);
	
	//환불할때 step=4로 바꾸기
	public void updatestep(int payNum);
	
	//환불하는 책이 국내인지 /국외인지 가져오기
	public String foreigncheck(int bookNum);
	
	
	//public void totalforeign_in(String foreign, int bookcount);
	public void totalforeign_in(Map<String, Object> map1);
	
	//public void totalforeign_out(String foreign, int bookcount);
	public void totalforeign_out(Map<String, Object> map1);
	
	//public void totalforeign(String foreign, int bookcount);
	
	
	public totalVO selecttotal();
	
}
