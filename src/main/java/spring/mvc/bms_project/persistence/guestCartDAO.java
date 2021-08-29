package spring.mvc.bms_project.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms_project.vo.cartAddVO;
import spring.mvc.bms_project.vo.guestCartVO;


public interface guestCartDAO {
	
	//책갯수 구하기
	public int getArticleCnt();
	
	//책목록 목록 조회
	public ArrayList<guestCartVO> getArticleList(Map<String, Integer> map);
	
	//상세페이지
	public guestCartVO getArticle(int bookNum);
	
	//장바구니 cart테이블에 추가
	public int insertCart(cartAddVO cartdto); 
	
	//장바구니 cart테이블 불러오기
	public int selectcart(int bookNum);
	
	//장바구니에 똑같은 책이 있을때  업데이트 
	public int updatecount(Map<String, Integer> map);
	
	//장바구니에 똑같은 책이 없을떄  insert 
	public int insertcount(cartAddVO cartdto);
	
	
	//장바구니 책 갯수 구하기
	public int getCartArticleCnt();
	
	//장바구니 목록 조회
	//public ArrayList<guestCartVO> getCartArticleList(int cnt, String memId);
	public ArrayList<guestCartVO> getCartArticleList(Map<String, Object> map);
	
	
	//구매단으로 넘길때 장바구니 목록 조회하기
	public ArrayList<cartAddVO> getCartInfo(String[] cartNum);
	
	//insert
	public void iPay(cartAddVO dto);
	
/*	//페이넘 수 가져오기
	public int pay_seq();*/
	
	
	//데이터수 가져오기
	public int payNum(String memid);
	
	//pay테이블에 추가
	public String insertpay(ArrayList<cartAddVO> list);
	
	//cart테이블에 있는 정보들을 delete
	public int deletecart(ArrayList<cartAddVO> delist);
	
	//pay정보불러오기
	//public ArrayList<guestCartVO> getPayList(String memId,int cnt);
	public ArrayList<guestCartVO> getPayList(Map map);
	
	public cartAddVO getCart(int num);
	//for문으로 update값넣기
	public void bookCountUpdate(ArrayList<cartAddVO> dtos);
	//update
	public void BCUpdate(cartAddVO dto);
	
	//cart테이블에 있는정보들 delete하기
	public int Dcart(cartAddVO dto);
	
	/*
	//구매목록 책 갯수 구하기
	public int getPayArticleCnt();
	
	//장바구니 목록 조회
	public ArrayList<guestCartVO> getPayArticleList(int cnt, String memId);
	*/
	/*//구매목록에서 결재하기할때 pay에있는 목록불러오기
	public ArrayList<guestCartVO> getPaymentList();*/
	
	//구매목록에서 결재하기할때 payNum체크하기
	public int selectPayNum(guestCartVO dto);
	
	//장바구니테이블에 데이터가 있을때
	public int UPpay(guestCartVO dto);
	
	//구매목록에서 결재하기할때 step update할 부분
	public int updatepay(guestCartVO paydto);
	
	//환불요청했을떄 update
	public int refund(int payNum);
	
	//바로구매할떄 pay에 insert
	//public int insertdirectpay(int bookNum, int bookcount, String id);
	public int insertdirectpay(Map<String, Object> map);
	
	//바로구매할떄 pay에 update
	public int directpayupdate(Map<String, Object> map);
	
	
	//public int selectBCount(int bookNum,int bookcount);
	public int selectBCount(Map<String, Object> map);
	
	
	//구매취소할때 도서 수량 update
	public int paycancel(Map<String, Object> map);
	
	//구매취소할떄 pay에서 list삭제
	public int paylistdelete(int payNum);
}
