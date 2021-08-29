package spring.mvc.bms_project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import spring.mvc.bms_project.persistence.guestCartDAO;
import spring.mvc.bms_project.persistence.guestCartDAOImpl;
import spring.mvc.bms_project.vo.guestCartVO;
import spring.mvc.bms_project.persistence.hostBookDAO;
import spring.mvc.bms_project.persistence.hostBookDAOImpl;
import spring.mvc.bms_project.vo.hostBookVO;
import spring.mvc.bms_project.vo.totalVO;

@Service
public class hostServiceImpl implements hostService{
	
	@Autowired
	hostBookDAO dao;
	
	
	//책추가 처리페이지
	@Override
	public void host_stockaddPro(MultipartHttpServletRequest req, Model model) {
		//MultipartRequest mr = null;
		MultipartFile file = req.getFile("kimg");
		
		
		String saveDir = req.getRealPath("/resources/images/");
		String readDir = "C:\\Dev\\workspace\\SPRING_BMS_project\\src\\main\\webapp\\resources\\images\\";
		
		 	
		try {
			//mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
			file.transferTo(new File(saveDir+file.getOriginalFilename()));
			
			FileInputStream fis = new FileInputStream(saveDir +  file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(readDir + file.getOriginalFilename());
			
			int data = 0; 
			
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
			
			
			fis.close();
			fos.close();
			
			//1. 작은 바구니(DTO)를 만든다.
			hostBookVO dto = new hostBookVO();
			
			
			//2. 화면으로부터 입력받은 내용을 작은 바구니(DTO)에 담는다.
			//dto.setKimg(mr.getFilesystemName("kimg"));
			//dto.setBookName(mr.getParameter("bookName"));
			//dto.setAuthor(mr.getParameter("author"));
			//dto.setPublisher(mr.getParameter("publisher"));
			//dto.setContent(mr.getParameter("content"));
			//dto.setPrice(Integer.parseInt(mr.getParameter("price")));
			//dto.setBookforeign(mr.getParameter("bookforeign"));
			//dto.setBookcount(Integer.parseInt(mr.getParameter("count")));
			//dto.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			dto.setKimg(file.getOriginalFilename());
			dto.setBookName(req.getParameter("bookName"));
			dto.setAuthor(req.getParameter("author"));
			dto.setPublisher(req.getParameter("publisher"));
			dto.setContent(req.getParameter("content"));
			//dto.setPrice(Integer.parseInt(mr.getParameter("price")));
			//dto.setBookforeign(mr.getParameter("bookforeign"));
			//dto.setBookcount(Integer.parseInt(mr.getParameter("count")));
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			dto.setBookforeign(req.getParameter("bookforeign"));
			dto.setBookcount(Integer.parseInt(req.getParameter("count")));
			
			dto.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			
			
			//hostBookDAO dao = hostBookDAOImpl.getInstance();
			
			int cnt = dao.insertBook(dto);
			
			//System.out.println("책추가등록 --> " + cnt);
			//req.setAttribute("cnt", cnt);
			model.addAttribute("cnt", cnt);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//책목록
	@Override
	public void host_stockList(HttpServletRequest req, Model model) {
		
		int pageSize = 10;    //한 페이지당 출력할 글 갯수
		int pageBlock = 3;   //한 블럭당 페이지 갯수
		
		int cnt=0;			 //글 갯수
		int start=0;		 //현재 페이지 글시작번호
		int end=0;			 //현재 페이지 글마지막번호
		int number=0;		 //출력할 글번호
		String pageNum = null;  //페이지번호
		int currentPage = 0;    //현재페이지
		
		int pageCount = 0;		//페이지 갯수
		int startPage = 0; 		//시작페이지
		int endPage=0;  		//마지막 페이지	
		
		//dao 객체생성(싱글톤, 다형성)
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		cnt = 0;
		cnt = dao.getArticleCnt();
		
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1"; //첫페이지 1페이지로 설정
		}
		
		currentPage = (Integer.parseInt(pageNum));   //현재페이지
		
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		
		start = (currentPage - 1) * pageSize +1;   //현재페이지 시작번호
		
		end = start + pageSize -1; //현재페이지 끝번호
		
		
		
		if(end > cnt) end = cnt;
		
		number = cnt - (currentPage - 1) * pageSize; //출력할 글번호... 최신글(큰 페이지)가 1page 
		
		
		if(cnt > 0) {
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			ArrayList<hostBookVO> dtos = dao.getArticleList(map);
			
			//req.setAttribute("dtos", dtos);  //큰바구니 : 게시글목록 cf)작은바구니 : 게시글 1건
			model.addAttribute("dtos", dtos);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1; // (5/3) * 3 + 1 = 4
		if(currentPage % pageBlock == 0) startPage -= pageBlock;  //(5%3) == 0
		
		endPage = startPage + pageBlock -1 ;  // 4 + 3 - 1 = 6
		if(endPage > pageCount) endPage = pageCount;
		
		//req.setAttribute("cnt", cnt); //책수
		//req.setAttribute("number", number); //글번호
		//req.setAttribute("pageNum", pageNum); //페이지번호
		model.addAttribute("cnt", cnt);
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		
		if(cnt > 0) {
			//req.setAttribute("startPage", startPage);  //시작페이지
			//req.setAttribute("endPage", endPage);//마지막페이지
			//req.setAttribute("pageBlock", pageBlock);//출력할 페이지 갯수
			//req.setAttribute("pageCount", pageCount);//페이지 갯수
			//req.setAttribute("currentPage", currentPage);//현재 페이지
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
			
		}
		
	}
	
	//host 책 상세페이지
	@Override
	public void bookcontentForm(HttpServletRequest req, Model model) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum")); //값을 넘길때는 무조건 키를 통해 넘기고 받는다.
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		//dao 생성(싱글톤, 다형성)
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		//상세페이지 가져오기..1건
		hostBookVO dto = dao.getArticle(bookNum);
		
		//jsp로 값을 넘긴다. dto, pageNum, number
		//req.setAttribute("dto", dto);
		//req.setAttribute("pageNum", pageNum);
		//req.setAttribute("number", number); //db글번호가 이닌 넘버링한 글번호
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("number", number);
		
	}
	/*
	//host 책 수정 처리페이지
	@Override
	public void bookmodifyView(HttpServletRequest req, HttpServletResponse res) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum")); 
		int pageNum = Integer.parseInt(req.getParameter("pageNum")); 
		
		//dao객체생성(싱글톤, 다형성 적용)
		hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		//상세페이지 가져오기..1건
		hostBookVO dto = dao.getArticle(bookNum);
		
		req.setAttribute("bookNum", bookNum);
		req.setAttribute("pageNum", pageNum);
		
	}
	
	//책 수정 처리페이지
	@Override
	public void bookmodifyPro(HttpServletRequest req, HttpServletResponse res) {
		
		MultipartRequest mr = null;
		int maxSize = 10 * 1024 * 1024; // 업로드할 파일의 최대 사이즈(10MB)
		String saveDir = req.getRealPath("/host/images/");
		String readDir = "C:\\Dev\\workspace\\BMS_project\\WebContent\\host\\images\\";
		String encType = "UTF-8";
		
		try {
			int bookNum = Integer.parseInt(req.getParameter("bookNum"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			
			//바구니 생성
			hostBookVO dto = new hostBookVO();
			
			//바구니에 화면에서 입력받은 값들을 담는다.
			dto.setBookNum(bookNum);
			dto.setAuthor(mr.getParameter("author"));
			dto.setPublisher(mr.getParameter("publisher"));
			dto.setContent(mr.getParameter("content"));
			dto.setPrice(Integer.parseInt(mr.getParameter("price")));
			dto.setBookforeign(mr.getParameter("bookforeign"));
			dto.setBookcount(Integer.parseInt(mr.getParameter("bookcount")));
			
			//dao객체생성(싱글톤, 다형성 적용)
			hostBookDAO dao = hostBookDAOImpl.getInstance();
			
			//수정처리
			int cnt = dao.updateBook(dto);
			
			req.setAttribute("bookNum", bookNum);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("cnt", cnt);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//책삭제 처리페이지
	@Override
	public void bookdeletePro(HttpServletRequest req, HttpServletResponse res) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		System.out.println("bookNum = " + bookNum);
		
		//dao객체생성(싱글톤, 다형성 적용)
		hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		int deleteCnt = dao.deleteBook(bookNum);
		req.setAttribute("deleteCnt", deleteCnt);
		req.setAttribute("pageNum", pageNum);
		
		System.out.println("deleteCnt = " + deleteCnt);
		
		
	}
	
	//책 삭제 폼페이지
	@Override
	public void bookdeleteForm(HttpServletRequest req, HttpServletResponse res) {
		int bookNum = (Integer)req.getAttribute("bookNum"); //값을 넘길때는 무조건 키를 통해 넘기고 받는다.
		int pageNum = (Integer)req.getAttribute("pageNum");
		//int number = Integer.parseInt(req.getParameter("number"));
		
		//dao 생성(싱글톤, 다형성)
		hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		//상세페이지 가져오기..1건
		hostBookVO dto = dao.getArticle(bookNum);
		
		//jsp로 값을 넘긴다. dto, pageNum, number
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		
	}
	*/
	
	//주문요청 처리 페이지
	@Override
	public void orderlist(HttpServletRequest req, Model model) {
		
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		int cnt = dao.payNum();
		
		ArrayList<guestCartVO> payselect = dao.getorderlist(cnt);

		//req.setAttribute("cnt", cnt);
		//req.setAttribute("dtos", payselect);
		model.addAttribute("cnt", cnt);
		model.addAttribute("dtos", payselect);
		
		
	}
	
	//host가 배송하기 클릭했을때
	@Override
	public void delivery(HttpServletRequest req, Model model) {
		int payNum=Integer.parseInt(req.getParameter("payNum"));
		int bookNum=Integer.parseInt(req.getParameter("bookNum"));
		int count = Integer.parseInt(req.getParameter("count"));
		int price = Integer.parseInt(req.getParameter("price"));
		
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		// 국내인지 국외인지 찾아오기
		String bookforeign= dao.selectforeign(bookNum);
		
		// step 2으로 변경
		dao.stepUpdate(payNum);
		
		// total 테이블에 값 추가(국내/국외 책구매수량, 총 금액)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookforeign", bookforeign);
		map.put("total", (price*count));
		map.put("count", count);
		
		if(bookforeign.equals("국내")) {
			dao.total_in(map);
		}else {
			dao.total_out(map);
		}
		
		
		
		
	}
	
	//환불
	@Override
	public void hostrefund(HttpServletRequest req, Model model) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		int bookcount = Integer.parseInt(req.getParameter("bookcount"));
		int price = Integer.parseInt(req.getParameter("price"));
		int payNum = Integer.parseInt(req.getParameter("payNum"));
		
		// 1. 환불시 재고수량 증가시키기
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookNum", bookNum);
		map.put("bookcount", bookcount);
		//dao.hostrefundbook(bookNum, bookcount);
		dao.hostrefundbook(map);
		
		//----------------------------------------------------------------
		//2. 총결산에서 환불한 가격만큼 빼주기
		dao.totalupdate(price);
		
		//3. step=>4로 바꾸기
		dao.updatestep(payNum);
		
		//4. 환불하는 책이 국내인지 /국외인지 가져오기
		String foreign = dao.foreigncheck(bookNum);
		
		//5. total에서 foreign을 업데이트 시키기
		//dao.totalforeign(foreign, bookcount);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("foreign", foreign);
		map1.put("bookcount", bookcount);
		
		if(foreign.equals("국내")) {
			dao.totalforeign_in(map1);
		}else {
			dao.totalforeign_out(map1);
		}
		
		
	}
	
	
	//결산
	@Override
	public void finaltotal(HttpServletRequest req, Model model) {
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		totalVO dto = dao.selecttotal();
		
		//req.setAttribute("dto", dto);
		model.addAttribute("dto", dto);
	}
	
}
