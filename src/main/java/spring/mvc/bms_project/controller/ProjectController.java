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
	
	
	//ù ����������
	@RequestMapping(value="main", method=RequestMethod.GET)
	public String main(HttpServletRequest req, Model model) {
		System.out.println("main()");
		
		guestservice.stockList(req, model);
		return "member/main"; 
	}
	
	//�α��� ������
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(HttpServletRequest req, Model model) {
		System.out.println("login()");
		
		return "member/login";
	}
	
	//ȸ������ ������
	@RequestMapping(value="inputForm", method=RequestMethod.GET)
	public String inputForm(HttpServletRequest req, Model model) {
		System.out.println("inputForm()");
		
		return "member/inputForm";
	}
	
	//�ߺ�Ȯ�� ������
	@RequestMapping(value="confirmId",  method= {RequestMethod.GET,RequestMethod.POST})
	public String confirmId(HttpServletRequest req, Model model) {
		System.out.println("confirmId()");
		
		memberservice.confirmId(req, model);
		
		return "member/confirmId";
	}
	
	//ȸ������ ó�� ������
	@RequestMapping(value="inputPro", method=RequestMethod.POST)
	public String inputPro(HttpServletRequest req, Model model) {
		System.out.println("inputPro()");
		
		memberservice.inputPro(req, model);
		
		return "member/inputPro";
	}
	
	//ȸ������ ���� : mainSuccess.mem
	@RequestMapping(value="mainSuccess", method=RequestMethod.GET)
	public String mainSuccess(HttpServletRequest req, Model model) {
		System.out.println("mainSuccess");
		
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		model.addAttribute("cnt", cnt);  //ȸ�������� �����մϴ�.
		
		return "member/login";
	}
	
	//�α��� ó�������� //ȭ�鿡 �����Է¹޴� ��� get
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
	
	//�α׾ƿ�
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest req, Model model) {
		System.out.println("logout()");
		
		req.getSession().setAttribute("memId", null);
		model.addAttribute("cnt", 2);
		
		guestservice.stockList(req, model);
		
		return "member/login";
	}
	
	//ȸ��Ż�� ��������
	@RequestMapping(value="deleteForm", method=RequestMethod.GET)
	public String deleteForm(HttpServletRequest req, Model model) {
		System.out.println("deleteForm()");
		
		return "member/deleteForm";
	}
	
	//ȸ��Ż��ó��������	
	@RequestMapping(value="deletePro", method=RequestMethod.POST)
	public String deletePro(HttpServletRequest req, Model model) {
		System.out.println("deletePro()");
		
		memberservice.deletePro(req,model);
		
		return "member/deletePro";
	}
	
	//ȸ������ ������������
	@RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public String modifyForm(HttpServletRequest req, Model model) {
		System.out.println("modifyForm()");
		
		return "member/modifyForm";
		
	}
	
	//ȸ���������� �� ������
	@RequestMapping(value="modifyView", method=RequestMethod.POST)
	public String modifyView(HttpServletRequest req, Model model) {
		System.out.println("modifyView()");
		
		memberservice.modifyView(req, model);
		
		return "member/modifyView";
	}
	
	//ȸ������ ���� ó��������
	@RequestMapping(value="modifyPro", method=RequestMethod.POST)
	public String modifyPro(HttpServletRequest req, Model model) {
		System.out.println("modifyPro()");
		
		memberservice.modifyPro(req, model);
		
		return "member/modifyPro";
	}
	
	/////////////��ٱ���, �ٷα��� start//////////////////////
	//cart å��Ϻҷ�����
	@RequestMapping(value="cart", method=RequestMethod.GET)
	public String cart(HttpServletRequest req, Model model) {
		System.out.println("cart()");
		
		guestservice.stockList(req, model);
		
		return "member/cart";
	}
	
	//cart å��Ͽ���  ��������
	@RequestMapping(value="cartContent", method=RequestMethod.GET)
	public String cartContent(HttpServletRequest req, Model model) {
		System.out.println("cartContent()");
		
		guestservice.stockcontent(req, model);
		
		return "member/cartContent";
	}
	
	//cart ��ٱ��Ͽ� å��� ó�� ������
	@RequestMapping(value="cartTakePro", method=RequestMethod.GET)
	public String cartTakePro(HttpServletRequest req, Model model) {
		System.out.println("cartTakePro()");
		
		guestservice.cartTakePro(req, model);
		
		return "member/cartTakePro";
	}
	
	//����ٱ��� ��� //�����ʿ�
	@RequestMapping(value="mycartlist", method=RequestMethod.GET)
	public String mycartlist(HttpServletRequest req, Model model) {
		System.out.println("mycartlist");
		
		guestservice.mycartlist(req, model);
		
		return "member/mycartlist";
	}
	
	//���Ŵܺκ�
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
	
	//���Ŵ� ��� �ҷ�����
	@RequestMapping(value="paylist", method=RequestMethod.GET)
	public String paylist(HttpServletRequest req, Model model) {
		System.out.println("paylist()");
		
		guestservice.paylist(req, model);
		
		return "member/pay";
		
	}
	
	//���Ŵܿ��� �����ҋ�
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
	
	//ȯ���ϱ�
	@RequestMapping(value="refund", method=RequestMethod.GET)
	public String refund(HttpServletRequest req, Model model) {
		System.out.println("refund()");
		
		guestservice.payNum(req, model);
		guestservice.paylist(req, model);
		
		return "member/pay";
	}
	
	//�ٷα���
	@RequestMapping(value="directpay", method=RequestMethod.GET)
	public String directpay(HttpServletRequest req, Model model) {
		
		guestservice.selectBCount(req, model);
		
		int BCnt=(Integer)req.getAttribute("BCnt");
		//BCnt�� 1�϶� ���� ������ ������ ������� �ʰ�
		if(BCnt==1) {
			
		}else {
			guestservice.directpay(req, model);//����
		}
		req.setAttribute("BCnt", BCnt);
		
		guestservice.stockcontent(req, model);	
		
		return "member/cartContent";
	}
	
	//�������
	@RequestMapping(value="paycancel", method=RequestMethod.GET)
	public String paycancel(HttpServletRequest req, Model model) {
		System.out.println("paycancel()");
		
		guestservice.paycancel(req, model);
		
		guestservice.paylist(req, model);
		
		return "member/pay";
	}
	/////////////��ٱ���, �ٷα��� end//////////////////////
	
	/////////////////board///////////////
	//�۸��
	@RequestMapping(value="boardList", method=RequestMethod.GET)
	public String boardList(HttpServletRequest req, Model model) {
		System.out.println("boardList()");
		
		gboardservice.boardList(req, model);
		
		return "board/boardList";
		
	}
	
	//��������
	@RequestMapping(value="b_contentForm", method=RequestMethod.GET)
	public String b_contentForm(HttpServletRequest req, Model model) {
		System.out.println("b_contentForm()");
		
		gboardservice.b_contentForm(req, model);
		
		return "board/b_contentForm";
	}
	
	//���������ȿ��� �ۼ��������� 
	//�ۼ����� ������
	@RequestMapping(value="b_modifyForm", method=RequestMethod.GET)
	public String b_modifyForm(HttpServletRequest req, Model model) {
		System.out.println("b_modifyForm()");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		
		return "board/b_modifyForm";
		
	}
	
	//�Խ���
	@RequestMapping(value="memberboardList", method=RequestMethod.GET)
	public String memberboardList(HttpServletRequest req, Model model) {
		System.out.println("memberboardList()");
		
		gboardservice.boardList(req, model);
		
		return "member/memberboardList";
		
	}
	
	//�ۼ��� ��������
	@RequestMapping(value="b_modifyView", method=RequestMethod.POST)
	public String b_modifyView(HttpServletRequest req, Model model) {
		System.out.println("b_modifyView()");
		
		gboardservice.b_modifyView(req, model);
		
		return "board/b_modifyView";
		
	}
	
	//�ۼ��� ó��������
	@RequestMapping(value="b_modifyPro", method=RequestMethod.POST)
	public String b_modifyPro(HttpServletRequest req, Model model) {
		System.out.println("b_modifyPro()");
		
		gboardservice.b_modifyPro(req, model);
		
		return "board/b_modifyPro";
	}
	
	//�۾����� ������(��۾���)
	@RequestMapping(value="b_writeForm", method=RequestMethod.GET)
	public String b_writeForm(HttpServletRequest req, Model model) {
		System.out.println("b_writeForm()");
		
		//�����(�亯���� �ƴ� ���)
		int num = 0;
		int ref = 1;	//�׷�ȭ ���̵�
		int ref_step = 0;  // �ۼ���(��)
		int ref_level = 0; // �۷���(�鿩����)
		
		//�亯��
		//contentForm.jsp���� get������� �ѱ� �� num, ref, ref_step, ref_level
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
	
	//�۾��� ó��������
	@RequestMapping(value="b_writePro", method=RequestMethod.POST)
	public String b_writePro(HttpServletRequest req, Model model) {
		System.out.println("b_writePro()");
		
		gboardservice.b_writePro(req, model);
		
		return "board/b_writePro";
	}
	
	//�ۻ����� ������
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
	
	//�ۻ��� ó��������
	@RequestMapping(value="b_deletePro", method=RequestMethod.POST)
	public String b_deletePro(HttpServletRequest req, Model model) {
		System.out.println("b_deletePro()");
		
		gboardservice.b_deletePro(req, model);
		
		return "board/b_deletePro";
		
	}
	/////////////////�Խ��� end////////////////
	
	
	///////////////host start////////////////
	
	//host ����������
	@RequestMapping(value="hostMain", method=RequestMethod.GET)
	public String hostMain(HttpServletRequest req, Model model) {
		System.out.println("hostMain()");
		
		
		return "host/hostMain";
	}
	
	//host ������
	@RequestMapping(value="host_stockMain", method=RequestMethod.GET)
	public String host_stockMain(HttpServletRequest req, Model model) {
		System.out.println("host_stockMain()");
		
		return "host/host_stockMain";
	}
	
	//host å ���
	@RequestMapping(value="host_stockList", method=RequestMethod.GET)
	public String host_stockList(HttpServletRequest req, Model model) {
		System.out.println("host_stockList()");
		
		hostservice.host_stockList(req, model);
		
		return "host/host_stockList";
		
	}
	
	//host å ��������
	@RequestMapping(value="host_stockcontent", method=RequestMethod.GET)
	public String host_stockcontent(HttpServletRequest req, Model model) {
		System.out.println("host_stockcontent()");
		
		hostservice.bookcontentForm(req, model);
		
		return "host/host_stockcontent";
		
	}
	
	//host �Խ���
	@RequestMapping(value="hostBoardList", method=RequestMethod.GET)
	public String hostBoardList(HttpServletRequest req, Model model) {
		System.out.println("hostBoardList()");
		
		gboardservice.boardList(req, model);
		
		return "host/hostBoardList";
	}
	
	//host �Խ��� ��������           /////////�����ϱ�////////////
	@RequestMapping(value="hostContentForm", method=RequestMethod.GET)
	public String hostContentForm(HttpServletRequest req, Model model) {
		System.out.println("hostContentForm()");
		
		gboardservice.boardList(req, model);
		
		return "host/hostContentForm";
		
	}
	
	//host å �߰�
	@RequestMapping(value="host_stockaddForm", method=RequestMethod.GET)
	public String host_stockaddForm(HttpServletRequest req, Model model) {
		System.out.println("host_stockaddForm()");
		
		return "host/host_stockaddForm";
	}
	
	//host å �߰� ó��������
	@RequestMapping(value="host_stockaddPro", method=RequestMethod.GET)
	public String host_stockaddPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("host_stockaddPro()");
		
		hostservice.host_stockaddPro(req, model);
		
		return "host/host_stockaddPro";
		
	}
	
	// ������ - �߰� - ó��    
    /*@RequestMapping(value="inventory_addPro", method=RequestMethod.POST)
    public String inventory_addPro(MultipartHttpServletRequest req, Model model) {
        System.out.println("inventory_addPro");
        
        service.inventory_add(req, model);
        service.inventory_list(req, model);
        
        return "host/inventory_add";
    }*/
	
	//�ֹ�����
	@RequestMapping(value="orderlist", method=RequestMethod.GET)
	public String orderlist(HttpServletRequest req, Model model) {
		System.out.println("orderlist()");
		
		hostservice.orderlist(req, model);
		
		return "host/orderlist";
		
	}
	
	//����ϱ�
	@RequestMapping(value="delivery", method=RequestMethod.GET)
	public String delivery(HttpServletRequest req, Model model) {
		System.out.println("delivery()");
		
		hostservice.delivery(req, model);
		hostservice.orderlist(req, model);
		
		return "host/orderlist";
		
	}
	
	//���
	@RequestMapping(value="finaltotal", method=RequestMethod.GET)
	public String finaltotal(HttpServletRequest req, Model model) {
		System.out.println("finaltotal()");
		
		hostservice.finaltotal(req, model);
		
		
		return "host/finaltotal";
		
	}
	
	//ȯ�Ұ��
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
		
		
		//host å �߰� ó��������
		else if(url.equals("/host_stockaddPro.host")) {
			System.out.println("/host_stockaddPro.host");
			
			hostService service = new hostServiceImpl();
			service.host_stockaddPro(req, res);
			
			viewPage = "/host/host_stockaddPro.jsp";
		}
		
		
		
		//host å ����
		else if(url.equals("/host_stockmodify.host")) {
			System.out.println("/host_stockmodify.host");
			
			int bookNum = Integer.parseInt(req.getParameter("bookNum"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			
			req.setAttribute("bookNum", bookNum);
			req.setAttribute("pageNum", pageNum);
			
			hostService service = new hostServiceImpl();
			
			viewPage = "/host/host_stockmodifyForm.jsp";
		}
		
		//host å ���� ��������
		else if(url.equals("/host_stockmodifyView.host")) {
			System.out.println("/host_stockmodifyView.host");
			
			hostService service = new hostServiceImpl();
			service.bookmodifyView(req, res);
			
			viewPage= "/host/host_stockmodifyView.jsp";
		}
		
		//host å ���� ó��������
		else if(url.equals("/host_stockmodifyPro.host")) {
			System.out.println("/host_stockmodifyPro.host");
			
			hostService service = new hostServiceImpl();
			service.bookmodifyPro(req, res);
			
			viewPage="/host/host_stockmodifyPro.jsp";
		}
		
		
		//host å ������ ������
		else if(url.equals("/host_stockdeleteForm.host")) {
			System.out.println("/host_stockdeleteForm.host");
			
			//contentForm.jsp���� ������ư Ŭ��������  deleteForm.jsp?num=${dto.num}&pageNum=${pageNum}
			
			int bookNum = Integer.parseInt(req.getParameter("bookNum"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			
			req.setAttribute("bookNum", bookNum);
			req.setAttribute("pageNum", pageNum);
			
			hostService service = new hostServiceImpl();
			service.bookdeleteForm(req, res);
			
			viewPage="/host/host_stockdeleteForm.jsp";
			
		}
		
		//host å���� ó��������
		else if(url.equals("/host_stockdeletePro.host")) {
			System.out.println("/host_stockdeletePro.host");
			
			hostService service = new hostServiceImpl();
			service.bookdeletePro(req, res);
			
			viewPage = "/host/host_stockdeletePro.jsp";
			
		}
		
	

*/
