package spring.mvc.bms_project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.bms_project.service.gBoardService;
import spring.mvc.bms_project.service.gBoardServiceImpl;
import spring.mvc.bms_project.service.guestService;
import spring.mvc.bms_project.service.hostService;
import spring.mvc.bms_project.service.hostServiceImpl;
import spring.mvc.bms_project.service.gMemberServiceImpl;
import spring.mvc.bms_project.service.guestServiceImpl;

@Controller
public class ProjectController {
	
	@Autowired  
	gMemberServiceImpl memberservice;
	
	@Autowired
	guestServiceImpl guestservice;
	
	@Autowired
	gBoardServiceImpl gboardservice;
	
	@Autowired
	hostServiceImpl hostservice;
	
	
	//첫 메인페이지
	@RequestMapping(value="main", method=RequestMethod.GET)
	public String main(HttpServletRequest req, Model model) {
		System.out.println("main()");
		
		guestservice.stockList(req, model);
		return "member/main"; 
	}
	
	//로그인 페이지
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(HttpServletRequest req, Model model) {
		System.out.println("login()");
		
		return "member/login";
	}
	
	//회원가입 페이지
	@RequestMapping(value="inputForm", method=RequestMethod.GET)
	public String inputForm(HttpServletRequest req, Model model) {
		System.out.println("inputForm()");
		
		return "member/inputForm";
	}
	
	//중복확인 페이지
	@RequestMapping(value="confirmId",  method= {RequestMethod.GET,RequestMethod.POST})
	public String confirmId(HttpServletRequest req, Model model) {
		System.out.println("confirmId()");
		
		memberservice.confirmId(req, model);
		
		return "member/confirmId";
	}
	
	//회원가입 처리 페이지
	@RequestMapping(value="inputPro", method=RequestMethod.POST)
	public String inputPro(HttpServletRequest req, Model model) {
		System.out.println("inputPro()");
		
		memberservice.inputPro(req, model);
		
		return "member/inputPro";
	}
	
	//회원가입 성공 : mainSuccess.mem
	@RequestMapping(value="mainSuccess", method=RequestMethod.GET)
	public String mainSuccess(HttpServletRequest req, Model model) {
		System.out.println("mainSuccess");
		
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		model.addAttribute("cnt", cnt);  //회원가입을 축하합니다.
		
		return "member/login";
	}
	
	//로그인 처리페이지 //화면에 직접입력받는 방식 get
	@RequestMapping(value="loginPro", method=RequestMethod.POST)
	public String loginPro(HttpServletRequest req, Model model) {
		System.out.println("loginPro()");
		
		memberservice.loginPro(req, model);
		
		if(req.getSession().getAttribute("memId").equals("host")) {
			return "host/hostMain";
		
		}else {
			
			guestservice.stockList(req, model);
			
			return "member/main";
			
		}
		
	}
	
	//로그아웃
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest req, Model model) {
		System.out.println("logout()");
		
		req.getSession().setAttribute("memId", null);
		model.addAttribute("cnt", 2);
		
		guestservice.stockList(req, model);
		
		return "member/login";
	}
	
	//회원탈퇴 폼페이지
	@RequestMapping(value="deleteForm", method=RequestMethod.GET)
	public String deleteForm(HttpServletRequest req, Model model) {
		System.out.println("deleteForm()");
		
		return "member/deleteForm";
	}
	
	//회원탈퇴처리페이지	
	@RequestMapping(value="deletePro", method=RequestMethod.POST)
	public String deletePro(HttpServletRequest req, Model model) {
		System.out.println("deletePro()");
		
		memberservice.deletePro(req,model);
		
		return "member/deletePro";
	}
	
	//회원정보 수정폼페이지
	@RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public String modifyForm(HttpServletRequest req, Model model) {
		System.out.println("modifyForm()");
		
		return "member/modifyForm";
		
	}
	
	//회원정보수정 상세 페이지
	@RequestMapping(value="modifyView", method=RequestMethod.POST)
	public String modifyView(HttpServletRequest req, Model model) {
		System.out.println("modifyView()");
		
		memberservice.modifyView(req, model);
		
		return "member/modifyView";
	}
	
	//회원정보 수정 처리페이지
	@RequestMapping(value="modifyPro", method=RequestMethod.POST)
	public String modifyPro(HttpServletRequest req, Model model) {
		System.out.println("modifyPro()");
		
		memberservice.modifyPro(req, model);
		
		return "member/modifyPro";
	}
	
	/////////////장바구니, 바로구매 start//////////////////////
	//cart 책목록불러오기
	@RequestMapping(value="cart", method=RequestMethod.GET)
	public String cart(HttpServletRequest req, Model model) {
		System.out.println("cart()");
		
		guestservice.stockList(req, model);
		
		return "member/cart";
	}
	
	//cart 책목록에서  상세페이지
	@RequestMapping(value="cartContent", method=RequestMethod.GET)
	public String cartContent(HttpServletRequest req, Model model) {
		System.out.println("cartContent()");
		
		guestservice.stockcontent(req, model);
		
		return "member/cartContent";
	}
	
	//cart 장바구니에 책담기 처리 페이지
	@RequestMapping(value="cartTakePro", method=RequestMethod.GET)
	public String cartTakePro(HttpServletRequest req, Model model) {
		System.out.println("cartTakePro()");
		
		guestservice.cartTakePro(req, model);
		
		return "member/cartTakePro";
	}
	
	//내장바구니 목록 //수정필요
	@RequestMapping(value="mycartlist", method=RequestMethod.GET)
	public String mycartlist(HttpServletRequest req, Model model) {
		System.out.println("mycartlist");
		
		guestservice.mycartlist(req, model);
		
		return "member/mycartlist";
	}
	
	//구매단부분
	@RequestMapping(value="pay", method=RequestMethod.GET)
	public String pay(HttpServletRequest req, Model model) {
		System.out.println("pay()");
		String[] cartNum = req.getParameterValues("cartNum");
		
		if(cartNum==null) {
			req.setAttribute("error", 1);
			
			guestservice.mycartlist(req, model);
			
			return "member/mycartlist";
		}else {
			guestservice.pay(req, model);
			
			return "member/pay";
		}
		
	}
	
	//구매단 목록 불러오기
	@RequestMapping(value="paylist", method=RequestMethod.GET)
	public String paylist(HttpServletRequest req, Model model) {
		System.out.println("paylist()");
		
		guestservice.paylist(req, model);
		
		return "member/pay";
		
	}
	
	//구매단에서 결재할떄
	@RequestMapping(value="payPro", method=RequestMethod.GET)
	public String payPro(HttpServletRequest req, Model model) {
		System.out.println("payPro()");
		
		/*String id = req.getParameter("id");
		System.out.println("id " + id);
		int payNum = Integer.parseInt(req.getParameter("payNum"));
		System.out.println("payNum " + payNum);
		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		System.out.println("bookNum " + bookNum);
		int bookcount = Integer.parseInt(req.getParameter("bookcount"));
		System.out.println("bookcount " + bookcount);
		
		req.setAttribute("payNum", payNum);
		req.setAttribute("bookNum", bookNum);
		req.setAttribute("bookcount", bookcount);*/
		
		guestservice.payPro(req, model);
		
		return "member/payPro";
		
		
	}
	
	//환불하기
	@RequestMapping(value="refund", method=RequestMethod.GET)
	public String refund(HttpServletRequest req, Model model) {
		System.out.println("refund()");
		
		guestservice.payNum(req, model);
		guestservice.paylist(req, model);
		
		return "member/pay";
	}
	
	//바로구매
	@RequestMapping(value="directpay", method=RequestMethod.GET)
	public String directpay(HttpServletRequest req, Model model) {
		
		guestservice.selectBCount(req, model);
		
		int BCnt=(Integer)req.getAttribute("BCnt");
		//BCnt가 1일때 내가 구입한 수량이 재고량보다 초과
		if(BCnt==1) {
			
		}else {
			guestservice.directpay(req, model);//구매
		}
		req.setAttribute("BCnt", BCnt);
		
		guestservice.stockcontent(req, model);	
		
		return "member/cartContent";
	}
	
	//구매취소
	@RequestMapping(value="paycancel", method=RequestMethod.GET)
	public String paycancel(HttpServletRequest req, Model model) {
		System.out.println("paycancel()");
		
		guestservice.paycancel(req, model);
		
		guestservice.paylist(req, model);
		
		return "member/pay";
	}
	/////////////장바구니, 바로구매 end//////////////////////
	
	/////////////////board///////////////
	//글목록
	@RequestMapping(value="boardList", method=RequestMethod.GET)
	public String boardList(HttpServletRequest req, Model model) {
		System.out.println("boardList()");
		
		gboardservice.boardList(req, model);
		
		return "board/boardList";
		
	}
	
	//상세페이지
	@RequestMapping(value="b_contentForm", method=RequestMethod.GET)
	public String b_contentForm(HttpServletRequest req, Model model) {
		System.out.println("b_contentForm()");
		
		gboardservice.b_contentForm(req, model);
		
		return "board/b_contentForm";
	}
	
	//상세페이지안에서 글수정페이지 
	//글수정폼 페이지
	@RequestMapping(value="b_modifyForm", method=RequestMethod.GET)
	public String b_modifyForm(HttpServletRequest req, Model model) {
		System.out.println("b_modifyForm()");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		
		return "board/b_modifyForm";
		
	}
	
	//게시판
	@RequestMapping(value="memberboardList", method=RequestMethod.GET)
	public String memberboardList(HttpServletRequest req, Model model) {
		System.out.println("memberboardList()");
		
		gboardservice.boardList(req, model);
		
		return "member/memberboardList";
		
	}
	
	//글수정 상세페이지
	@RequestMapping(value="b_modifyView", method=RequestMethod.POST)
	public String b_modifyView(HttpServletRequest req, Model model) {
		System.out.println("b_modifyView()");
		
		gboardservice.b_modifyView(req, model);
		
		return "board/b_modifyView";
		
	}
	
	//글수정 처리페이지
	@RequestMapping(value="b_modifyPro", method=RequestMethod.POST)
	public String b_modifyPro(HttpServletRequest req, Model model) {
		System.out.println("b_modifyPro()");
		
		gboardservice.b_modifyPro(req, model);
		
		return "board/b_modifyPro";
	}
	
	//글쓰기폼 페이지(답글쓰기)
	@RequestMapping(value="b_writeForm", method=RequestMethod.GET)
	public String b_writeForm(HttpServletRequest req, Model model) {
		System.out.println("b_writeForm()");
		
		//제목글(답변글이 아닌 경우)
		int num = 0;
		int ref = 1;	//그룹화 아이디
		int ref_step = 0;  // 글순서(행)
		int ref_level = 0; // 글레벨(들여쓰기)
		
		//답변글
		//contentForm.jsp에서 get방식으로 넘긴 값 num, ref, ref_step, ref_level
		if(req.getParameter("num") != null) {
			num = Integer.parseInt(req.getParameter("num"));
			ref = Integer.parseInt(req.getParameter("ref"));
			ref_step = Integer.parseInt(req.getParameter("ref_step"));
			ref_level = Integer.parseInt(req.getParameter("ref_level"));
		}
		//req.setAttribute("num", num);
		//req.setAttribute("ref", ref);
		//req.setAttribute("ref_step", ref_step);
		//req.setAttribute("ref_level", ref_level);
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("ref_step", ref_step);
		model.addAttribute("ref_level", ref_level);
		
		return "board/b_writeForm";
		
	}
	
	//글쓰기 처리페이지
	@RequestMapping(value="b_writePro", method=RequestMethod.POST)
	public String b_writePro(HttpServletRequest req, Model model) {
		System.out.println("b_writePro()");
		
		gboardservice.b_writePro(req, model);
		
		return "board/b_writePro";
	}
	
	//글삭제폼 페이지
	@RequestMapping(value="b_deleteForm", method=RequestMethod.GET)
	public String b_deleteForm(HttpServletRequest req, Model model) {
		System.out.println("b_deleteForm()");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		//req.setAttribute("num", num);
		//req.setAttribute("pageNum", pageNum);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		
		return "board/b_deleteForm";
		
	}
	
	//글삭제 처리페이지
	@RequestMapping(value="b_deletePro", method=RequestMethod.POST)
	public String b_deletePro(HttpServletRequest req, Model model) {
		System.out.println("b_deletePro()");
		
		gboardservice.b_deletePro(req, model);
		
		return "board/b_deletePro";
		
	}
	/////////////////게시판 end////////////////
	
	
	///////////////host start////////////////
	
	//host 메인페이지
	@RequestMapping(value="hostMain", method=RequestMethod.GET)
	public String hostMain(HttpServletRequest req, Model model) {
		System.out.println("hostMain()");
		
		
		return "host/hostMain";
	}
	
	//host 재고관리
	@RequestMapping(value="host_stockMain", method=RequestMethod.GET)
	public String host_stockMain(HttpServletRequest req, Model model) {
		System.out.println("host_stockMain()");
		
		return "host/host_stockMain";
	}
	
	//host 책 목록
	@RequestMapping(value="host_stockList", method=RequestMethod.GET)
	public String host_stockList(HttpServletRequest req, Model model) {
		System.out.println("host_stockList()");
		
		hostservice.host_stockList(req, model);
		
		return "host/host_stockList";
		
	}
	
	//host 책 상세페이지
	@RequestMapping(value="host_stockcontent", method=RequestMethod.GET)
	public String host_stockcontent(HttpServletRequest req, Model model) {
		System.out.println("host_stockcontent()");
		
		hostservice.bookcontentForm(req, model);
		
		return "host/host_stockcontent";
		
	}
	
	//host 게시판
	@RequestMapping(value="hostBoardList", method=RequestMethod.GET)
	public String hostBoardList(HttpServletRequest req, Model model) {
		System.out.println("hostBoardList()");
		
		gboardservice.boardList(req, model);
		
		return "host/hostBoardList";
	}
	
	//host 게시판 상세페이지           /////////수정하기////////////
	@RequestMapping(value="hostContentForm", method=RequestMethod.GET)
	public String hostContentForm(HttpServletRequest req, Model model) {
		System.out.println("hostContentForm()");
		
		gboardservice.boardList(req, model);
		
		return "host/hostContentForm";
		
	}
	
	//host 책 추가
	@RequestMapping(value="host_stockaddForm", method=RequestMethod.GET)
	public String host_stockaddForm(HttpServletRequest req, Model model) {
		System.out.println("host_stockaddForm()");
		
		return "host/host_stockaddForm";
	}
	
	//host 책 추가 처리페이지
	@RequestMapping(value="host_stockaddPro", method=RequestMethod.GET)
	public String host_stockaddPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("host_stockaddPro()");
		
		hostservice.host_stockaddPro(req, model);
		
		return "host/host_stockaddPro";
		
	}
	
	// 재고관리 - 추가 - 처리    
    /*@RequestMapping(value="inventory_addPro", method=RequestMethod.POST)
    public String inventory_addPro(MultipartHttpServletRequest req, Model model) {
        System.out.println("inventory_addPro");
        
        service.inventory_add(req, model);
        service.inventory_list(req, model);
        
        return "host/inventory_add";
    }*/
	
	//주문관리
	@RequestMapping(value="orderlist", method=RequestMethod.GET)
	public String orderlist(HttpServletRequest req, Model model) {
		System.out.println("orderlist()");
		
		hostservice.orderlist(req, model);
		
		return "host/orderlist";
		
	}
	
	//배송하기
	@RequestMapping(value="delivery", method=RequestMethod.GET)
	public String delivery(HttpServletRequest req, Model model) {
		System.out.println("delivery()");
		
		hostservice.delivery(req, model);
		hostservice.orderlist(req, model);
		
		return "host/orderlist";
		
	}
	
	//결산
	@RequestMapping(value="finaltotal", method=RequestMethod.GET)
	public String finaltotal(HttpServletRequest req, Model model) {
		System.out.println("finaltotal()");
		
		hostservice.finaltotal(req, model);
		
		
		return "host/finaltotal";
		
	}
	
	//환불결산
	@RequestMapping(value="hostrefund", method=RequestMethod.GET)
	public String hostrefund(HttpServletRequest req, Model model) {
		System.out.println("hostrefund()");
		
		hostservice.hostrefund(req, model);
		hostservice.orderlist(req, model);
		
		return "host/orderlist";
		
		
	}
	
	
}

/*
		else if(url.equals("/hostrefund.host")) {
			System.out.println("/hostrefund.host");
			
			hostService service = new hostServiceImpl();
			service.hostrefund(req, res);
			
			
			service.orderlist(req, res);
			viewPage = "/host/orderlist.jsp";
		}
		
		
		//host 책 추가 처리페이지
		else if(url.equals("/host_stockaddPro.host")) {
			System.out.println("/host_stockaddPro.host");
			
			hostService service = new hostServiceImpl();
			service.host_stockaddPro(req, res);
			
			viewPage = "/host/host_stockaddPro.jsp";
		}
		
		
		
		//host 책 수정
		else if(url.equals("/host_stockmodify.host")) {
			System.out.println("/host_stockmodify.host");
			
			int bookNum = Integer.parseInt(req.getParameter("bookNum"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			
			req.setAttribute("bookNum", bookNum);
			req.setAttribute("pageNum", pageNum);
			
			hostService service = new hostServiceImpl();
			
			viewPage = "/host/host_stockmodifyForm.jsp";
		}
		
		//host 책 수정 상세페이지
		else if(url.equals("/host_stockmodifyView.host")) {
			System.out.println("/host_stockmodifyView.host");
			
			hostService service = new hostServiceImpl();
			service.bookmodifyView(req, res);
			
			viewPage= "/host/host_stockmodifyView.jsp";
		}
		
		//host 책 수정 처리페이지
		else if(url.equals("/host_stockmodifyPro.host")) {
			System.out.println("/host_stockmodifyPro.host");
			
			hostService service = new hostServiceImpl();
			service.bookmodifyPro(req, res);
			
			viewPage="/host/host_stockmodifyPro.jsp";
		}
		
		
		//host 책 삭제폼 페이지
		else if(url.equals("/host_stockdeleteForm.host")) {
			System.out.println("/host_stockdeleteForm.host");
			
			//contentForm.jsp에서 삭제버튼 클릭했을때  deleteForm.jsp?num=${dto.num}&pageNum=${pageNum}
			
			int bookNum = Integer.parseInt(req.getParameter("bookNum"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			
			req.setAttribute("bookNum", bookNum);
			req.setAttribute("pageNum", pageNum);
			
			hostService service = new hostServiceImpl();
			service.bookdeleteForm(req, res);
			
			viewPage="/host/host_stockdeleteForm.jsp";
			
		}
		
		//host 책삭제 처리페이지
		else if(url.equals("/host_stockdeletePro.host")) {
			System.out.println("/host_stockdeletePro.host");
			
			hostService service = new hostServiceImpl();
			service.bookdeletePro(req, res);
			
			viewPage = "/host/host_stockdeletePro.jsp";
			
		}
		
	

*/
