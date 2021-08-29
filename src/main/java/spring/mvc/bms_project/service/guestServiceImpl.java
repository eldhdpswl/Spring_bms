package spring.mvc.bms_project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.bms_project.persistence.gMemberDAO;
import spring.mvc.bms_project.persistence.guestCartDAO;
import spring.mvc.bms_project.persistence.guestCartDAOImpl;
import spring.mvc.bms_project.vo.cartAddVO;
import spring.mvc.bms_project.vo.guestCartVO;
import spring.mvc.bms_project.persistence.hostBookDAO;
import spring.mvc.bms_project.persistence.hostBookDAOImpl;
import spring.mvc.bms_project.vo.hostBookVO;

@Service
public class guestServiceImpl implements guestService {
	
	@Autowired
	guestCartDAO dao;
	
	// å���
	@Override
	public void stockList(HttpServletRequest req, Model model) {

		int pageSize = 9; // �� �������� ����� �� ����
		int pageBlock = 3; // �� ���� ������ ����

		int cnt = 0; // �� ����
		int start = 0; // ���� ������ �۽��۹�ȣ
		int end = 0; // ���� ������ �۸�������ȣ
		int number = 0; // ����� �۹�ȣ
		String pageNum = null; // ��������ȣ
		int currentPage = 0; // ����������

		int pageCount = 0; // ������ ����
		int startPage = 0; // ����������
		int endPage = 0; // ������ ������

		//guestCartDAO dao = guestCartDAOImpl.getInstance();

		cnt = 0;
		cnt = dao.getArticleCnt();

		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
		
		//System.out.println("cnt : " + cnt);

		pageNum = req.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1"; // ù������ 1�������� ����
		}

		currentPage = (Integer.parseInt(pageNum)); // ����������

		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);

		start = (currentPage - 1) * pageSize + 1; // ���������� ���۹�ȣ

		end = start + pageSize - 1; // ���������� ����ȣ

		//System.out.println("start : " + start);
		//System.out.println("end : " + end);

		if (end > cnt)
			end = cnt;

		number = cnt - (currentPage - 1) * pageSize; // ����� �۹�ȣ... �ֽű�(ū ������)�� 1page

		//System.out.println("number : " + number);
		//System.out.println("cnt : " + cnt);
		//System.out.println("currentPage : " + currentPage);
		//System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			// �Խñ� ��� ��ȸ
			//ArrayList<guestCartVO> dtos = dao.getArticleList(start, end);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			ArrayList<guestCartVO> dtos = dao.getArticleList(map);
			//req.setAttribute("dtos", dtos); // ū�ٱ��� : �Խñ۸�� cf)�����ٱ��� : �Խñ� 1��
			model.addAttribute("dtos", dtos);	
			
		}

		startPage = (currentPage / pageBlock) * pageBlock + 1; // (5/3) * 3 + 1 = 4
		if (currentPage % pageBlock == 0)
			startPage -= pageBlock; // (5%3) == 0

		endPage = startPage + pageBlock - 1; // 4 + 3 - 1 = 6
		if (endPage > pageCount)
			endPage = pageCount;

		//req.setAttribute("cnt", cnt); // å��
		//req.setAttribute("number", number); // �۹�ȣ
		//req.setAttribute("pageNum", pageNum); // ��������ȣ
		model.addAttribute("cnt", cnt);
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		
		if (cnt > 0) {
			//req.setAttribute("startPage", startPage); // ����������
			//req.setAttribute("endPage", endPage);// ������������
			//req.setAttribute("pageBlock", pageBlock);// ����� ������ ����
			//req.setAttribute("pageCount", pageCount);// ������ ����
			//req.setAttribute("currentPage", currentPage);// ���� ������
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}

	}
	
	// ��������
	@Override
	public void stockcontent(HttpServletRequest req,  Model model) {

		int bookNum = Integer.parseInt(req.getParameter("bookNum")); // ���� �ѱ涧�� ������ Ű�� ���� �ѱ�� �޴´�.


		// dao ����(�̱���, ������)
		//guestCartDAO dao = guestCartDAOImpl.getInstance();

		// �������� ��������..1��
		guestCartVO dto = dao.getArticle(bookNum);

		// jsp�� ���� �ѱ��. dto, pageNum, number

		//req.setAttribute("dto", dto);
		model.addAttribute("dto", dto);
		// db�۹�ȣ�� �̴� �ѹ����� �۹�ȣ

	}
	
	// ��ٱ��� å��� ó��������
	@Override
	public void cartTakePro(HttpServletRequest req, Model model) {

		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		String id = req.getParameter("id");
		int cartcount = Integer.parseInt(req.getParameter("cartcount"));
		System.out.println("bookNum:"+bookNum);
		System.out.println("id:"+id);
		System.out.println("cartcount:"+cartcount);
		
		cartAddVO cartdto = new cartAddVO();

		cartdto.setId(id);
		cartdto.setBookNum(bookNum);
		cartdto.setCartcount(cartcount);
		
		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		System.out.println("111");
		
		int cnt = dao.insertCart(cartdto);
		System.out.println("222");
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
	}
	
	// �� ��ٱ��� ���
	@Override
	public void mycartlist(HttpServletRequest req, Model model) {
		
		String memId = (String) req.getSession().getAttribute("memId");

		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		
		//guestCartVO dto = new guestCartVO();
		//dto.setTotal(dto.getBookcount(), dto.getPrice());
		
		int cnt = dao.getCartArticleCnt();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		map.put("memId", memId);
		
		ArrayList<guestCartVO> dtos = dao.getCartArticleList(map);
		
		//req.setAttribute("cnt", cnt);
		//req.setAttribute("dtos", dtos);
		model.addAttribute("cnt", cnt);
		model.addAttribute("dtos", dtos);
	}
	
	
	// checkbox�� üũ�Ҷ� ���Ŵ� ///////
	@Override
	public void pay(HttpServletRequest req, Model model) {
		
		
		String[] cartNum = req.getParameterValues("cartNum");
		
		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		ArrayList<cartAddVO> dtos = dao.getCartInfo(cartNum);
		System.out.println(dtos.get(0).getBookNum());
		String memId= dao.insertpay(dtos);
		System.out.println("1");
		//����� memid�ε�
		// ��� ������Ʈ
		dao.bookCountUpdate(dtos);
		System.out.println("2");
		//���̿� �ִ� �ش� ���̵��� ������ ���� ������?? ����Ʈ�� �Ѹ� ��̸���Ʈ ������� ����
		int cnt=dao.payNum(memId);
		System.out.println("3");
			int deleteCnt = dao.deletecart(dtos);
			System.out.println("4");
			if(deleteCnt != 0) {
				Map map = new HashMap();
				map.put("memId", memId);
				map.put("cnt", cnt);
				//ArrayList<guestCartVO> payselect = dao.getPayList(memid,cnt);	
				ArrayList<guestCartVO> payselect = dao.getPayList(map);	
				System.out.println("5");
				//req.setAttribute("dtos", payselect);
				model.addAttribute("dtos", payselect);
			}
		
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
		
	}
	
	//���Ÿ�� ����Ʈ �ҷ�����
	@Override
	public void paylist(HttpServletRequest req, Model model) {
		
		String memId = (String) req.getSession().getAttribute("memId");

		//guestCartDAO dao = guestCartDAOImpl.getInstance();

		int cnt = dao.payNum(memId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("cnt", cnt);
		//ArrayList<guestCartVO> payselect = dao.getPayList(memId,cnt);
		ArrayList<guestCartVO> payselect = dao.getPayList(map);
		
		//req.setAttribute("cnt", cnt);
		//req.setAttribute("dtos", payselect);
		model.addAttribute("cnt", cnt);
		model.addAttribute("dtos", payselect);
		
	}

	//���Ÿ�Ͽ��� �����ϱ� ��������
	@Override
	public void payPro(HttpServletRequest req, Model model) {
		int payNum = (Integer)req.getAttribute("payNum");
		int bookNum = (Integer)req.getAttribute("bookNum");
		int bookcount = (Integer)req.getAttribute("bookcount");
		
		guestCartVO paydto = new guestCartVO();
		
		paydto.setPayNum(payNum);
		paydto.setBookNum(bookNum);
		paydto.setBookcount(bookcount);
		
		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		
		int cnt = dao.updatepay(paydto);
		
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
		
	}
	
	//ȯ���ϱ�
	@Override
	public void payNum(HttpServletRequest req, Model model) {
		int payNum = Integer.parseInt(req.getParameter("payNum"));
		
		
		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		int cnt = dao.refund(payNum);
		
		//req.setAttribute("refundCnt", cnt);
		model.addAttribute("refundCnt", cnt);
		
	}
	
	@Override
	public void directpay(HttpServletRequest req, Model model) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		int bookcount = Integer.parseInt(req.getParameter("bookcount"));
		String id = (String)req.getSession().getAttribute("memId");
		
		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		//1. pay�� �����ֱ�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookNum", bookNum);
		map.put("bookcount", bookcount);
		map.put("id", id);
		//int cnt = dao.insertdirectpay(bookNum, bookcount, id);
		int cnt = dao.insertdirectpay(map);
		
		
		//2. book���� �����
		//int Cnt1 = dao.directpayupdate(bookNum, bookcount);
		int Cnt1 = dao.directpayupdate(map);
		
		int directbookCnt=0;
		if(cnt!=0&&Cnt1!=0) {
			directbookCnt=1;
		}
		//req.setAttribute("directbookCnt",directbookCnt);
		model.addAttribute("directbookCnt", directbookCnt);
	}

	@Override
	public void selectBCount(HttpServletRequest req, Model model) {
		int bookNum=Integer.parseInt(req.getParameter("bookNum"));
		int bookcount=Integer.parseInt(req.getParameter("bookcount"));
		int BCnt=0;
		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		System.out.println("-----bookcount:"+bookcount);
		//å ���� ���ϱ�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookNum", bookNum);
		map.put("bookcount", bookcount);
		//int BCountCheck=dao.selectBCount(bookNum,bookcount);
		int BCountCheck=dao.selectBCount(map);
		System.out.println("BCountCheck:"+BCountCheck);
		//������ å ������ ���� ������� Ŭ��
		if(bookcount>BCountCheck) {
			BCnt=1;
		}
		System.out.println("BCnt----------"+BCnt);
		req.setAttribute("BCnt", BCnt);
		/*model.addAttribute("BCnt", BCnt);*/
	}
	
	//�������
	@Override
	public void paycancel(HttpServletRequest req, Model model) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		int bookcount = Integer.parseInt(req.getParameter("bookcount"));
		int payNum = Integer.parseInt(req.getParameter("payNum"));
		
		System.out.println("bookNum : "+ bookNum);
		System.out.println("bookcount : " + bookcount);
		
		//guestCartDAO dao = guestCartDAOImpl.getInstance();
		//1.��������ҋ� ������Ʈ
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookNum", bookNum);
		map.put("bookcount", bookcount);
		//int paycancelcnt = dao.paycancel(bookNum, bookcount);
		int paycancelcnt = dao.paycancel(map);
		
		//2. ��������ҋ� pay�κ� delete
		int paydeletecnt= dao.paylistdelete(payNum);
		
		int Dcnt=0;
		
		
		if(paycancelcnt !=0 &&paydeletecnt !=0) {
			Dcnt=1;
		}
		
		//req.setAttribute("Dcnt", Dcnt);
		model.addAttribute("Dcnt", Dcnt);
	}
	
	

}
