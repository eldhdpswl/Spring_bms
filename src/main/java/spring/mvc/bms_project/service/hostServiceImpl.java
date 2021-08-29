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
	
	
	//å�߰� ó��������
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
			
			//1. ���� �ٱ���(DTO)�� �����.
			hostBookVO dto = new hostBookVO();
			
			
			//2. ȭ�����κ��� �Է¹��� ������ ���� �ٱ���(DTO)�� ��´�.
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
			
			//System.out.println("å�߰���� --> " + cnt);
			//req.setAttribute("cnt", cnt);
			model.addAttribute("cnt", cnt);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//å���
	@Override
	public void host_stockList(HttpServletRequest req, Model model) {
		
		int pageSize = 10;    //�� �������� ����� �� ����
		int pageBlock = 3;   //�� ���� ������ ����
		
		int cnt=0;			 //�� ����
		int start=0;		 //���� ������ �۽��۹�ȣ
		int end=0;			 //���� ������ �۸�������ȣ
		int number=0;		 //����� �۹�ȣ
		String pageNum = null;  //��������ȣ
		int currentPage = 0;    //����������
		
		int pageCount = 0;		//������ ����
		int startPage = 0; 		//����������
		int endPage=0;  		//������ ������	
		
		//dao ��ü����(�̱���, ������)
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		cnt = 0;
		cnt = dao.getArticleCnt();
		
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1"; //ù������ 1�������� ����
		}
		
		currentPage = (Integer.parseInt(pageNum));   //����������
		
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		
		start = (currentPage - 1) * pageSize +1;   //���������� ���۹�ȣ
		
		end = start + pageSize -1; //���������� ����ȣ
		
		
		
		if(end > cnt) end = cnt;
		
		number = cnt - (currentPage - 1) * pageSize; //����� �۹�ȣ... �ֽű�(ū ������)�� 1page 
		
		
		if(cnt > 0) {
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			ArrayList<hostBookVO> dtos = dao.getArticleList(map);
			
			//req.setAttribute("dtos", dtos);  //ū�ٱ��� : �Խñ۸�� cf)�����ٱ��� : �Խñ� 1��
			model.addAttribute("dtos", dtos);
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1; // (5/3) * 3 + 1 = 4
		if(currentPage % pageBlock == 0) startPage -= pageBlock;  //(5%3) == 0
		
		endPage = startPage + pageBlock -1 ;  // 4 + 3 - 1 = 6
		if(endPage > pageCount) endPage = pageCount;
		
		//req.setAttribute("cnt", cnt); //å��
		//req.setAttribute("number", number); //�۹�ȣ
		//req.setAttribute("pageNum", pageNum); //��������ȣ
		model.addAttribute("cnt", cnt);
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		
		if(cnt > 0) {
			//req.setAttribute("startPage", startPage);  //����������
			//req.setAttribute("endPage", endPage);//������������
			//req.setAttribute("pageBlock", pageBlock);//����� ������ ����
			//req.setAttribute("pageCount", pageCount);//������ ����
			//req.setAttribute("currentPage", currentPage);//���� ������
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
			
		}
		
	}
	
	//host å ��������
	@Override
	public void bookcontentForm(HttpServletRequest req, Model model) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum")); //���� �ѱ涧�� ������ Ű�� ���� �ѱ�� �޴´�.
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		//dao ����(�̱���, ������)
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		//�������� ��������..1��
		hostBookVO dto = dao.getArticle(bookNum);
		
		//jsp�� ���� �ѱ��. dto, pageNum, number
		//req.setAttribute("dto", dto);
		//req.setAttribute("pageNum", pageNum);
		//req.setAttribute("number", number); //db�۹�ȣ�� �̴� �ѹ����� �۹�ȣ
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("number", number);
		
	}
	/*
	//host å ���� ó��������
	@Override
	public void bookmodifyView(HttpServletRequest req, HttpServletResponse res) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum")); 
		int pageNum = Integer.parseInt(req.getParameter("pageNum")); 
		
		//dao��ü����(�̱���, ������ ����)
		hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		//�������� ��������..1��
		hostBookVO dto = dao.getArticle(bookNum);
		
		req.setAttribute("bookNum", bookNum);
		req.setAttribute("pageNum", pageNum);
		
	}
	
	//å ���� ó��������
	@Override
	public void bookmodifyPro(HttpServletRequest req, HttpServletResponse res) {
		
		MultipartRequest mr = null;
		int maxSize = 10 * 1024 * 1024; // ���ε��� ������ �ִ� ������(10MB)
		String saveDir = req.getRealPath("/host/images/");
		String readDir = "C:\\Dev\\workspace\\BMS_project\\WebContent\\host\\images\\";
		String encType = "UTF-8";
		
		try {
			int bookNum = Integer.parseInt(req.getParameter("bookNum"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			
			//�ٱ��� ����
			hostBookVO dto = new hostBookVO();
			
			//�ٱ��Ͽ� ȭ�鿡�� �Է¹��� ������ ��´�.
			dto.setBookNum(bookNum);
			dto.setAuthor(mr.getParameter("author"));
			dto.setPublisher(mr.getParameter("publisher"));
			dto.setContent(mr.getParameter("content"));
			dto.setPrice(Integer.parseInt(mr.getParameter("price")));
			dto.setBookforeign(mr.getParameter("bookforeign"));
			dto.setBookcount(Integer.parseInt(mr.getParameter("bookcount")));
			
			//dao��ü����(�̱���, ������ ����)
			hostBookDAO dao = hostBookDAOImpl.getInstance();
			
			//����ó��
			int cnt = dao.updateBook(dto);
			
			req.setAttribute("bookNum", bookNum);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("cnt", cnt);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//å���� ó��������
	@Override
	public void bookdeletePro(HttpServletRequest req, HttpServletResponse res) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		System.out.println("bookNum = " + bookNum);
		
		//dao��ü����(�̱���, ������ ����)
		hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		int deleteCnt = dao.deleteBook(bookNum);
		req.setAttribute("deleteCnt", deleteCnt);
		req.setAttribute("pageNum", pageNum);
		
		System.out.println("deleteCnt = " + deleteCnt);
		
		
	}
	
	//å ���� ��������
	@Override
	public void bookdeleteForm(HttpServletRequest req, HttpServletResponse res) {
		int bookNum = (Integer)req.getAttribute("bookNum"); //���� �ѱ涧�� ������ Ű�� ���� �ѱ�� �޴´�.
		int pageNum = (Integer)req.getAttribute("pageNum");
		//int number = Integer.parseInt(req.getParameter("number"));
		
		//dao ����(�̱���, ������)
		hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		//�������� ��������..1��
		hostBookVO dto = dao.getArticle(bookNum);
		
		//jsp�� ���� �ѱ��. dto, pageNum, number
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		
	}
	*/
	
	//�ֹ���û ó�� ������
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
	
	//host�� ����ϱ� Ŭ��������
	@Override
	public void delivery(HttpServletRequest req, Model model) {
		int payNum=Integer.parseInt(req.getParameter("payNum"));
		int bookNum=Integer.parseInt(req.getParameter("bookNum"));
		int count = Integer.parseInt(req.getParameter("count"));
		int price = Integer.parseInt(req.getParameter("price"));
		
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		
		// �������� �������� ã�ƿ���
		String bookforeign= dao.selectforeign(bookNum);
		
		// step 2���� ����
		dao.stepUpdate(payNum);
		
		// total ���̺� �� �߰�(����/���� å���ż���, �� �ݾ�)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookforeign", bookforeign);
		map.put("total", (price*count));
		map.put("count", count);
		
		if(bookforeign.equals("����")) {
			dao.total_in(map);
		}else {
			dao.total_out(map);
		}
		
		
		
		
	}
	
	//ȯ��
	@Override
	public void hostrefund(HttpServletRequest req, Model model) {
		int bookNum = Integer.parseInt(req.getParameter("bookNum"));
		int bookcount = Integer.parseInt(req.getParameter("bookcount"));
		int price = Integer.parseInt(req.getParameter("price"));
		int payNum = Integer.parseInt(req.getParameter("payNum"));
		
		// 1. ȯ�ҽ� ������ ������Ű��
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookNum", bookNum);
		map.put("bookcount", bookcount);
		//dao.hostrefundbook(bookNum, bookcount);
		dao.hostrefundbook(map);
		
		//----------------------------------------------------------------
		//2. �Ѱ�꿡�� ȯ���� ���ݸ�ŭ ���ֱ�
		dao.totalupdate(price);
		
		//3. step=>4�� �ٲٱ�
		dao.updatestep(payNum);
		
		//4. ȯ���ϴ� å�� �������� /�������� ��������
		String foreign = dao.foreigncheck(bookNum);
		
		//5. total���� foreign�� ������Ʈ ��Ű��
		//dao.totalforeign(foreign, bookcount);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("foreign", foreign);
		map1.put("bookcount", bookcount);
		
		if(foreign.equals("����")) {
			dao.totalforeign_in(map1);
		}else {
			dao.totalforeign_out(map1);
		}
		
		
	}
	
	
	//���
	@Override
	public void finaltotal(HttpServletRequest req, Model model) {
		//hostBookDAO dao = hostBookDAOImpl.getInstance();
		totalVO dto = dao.selecttotal();
		
		//req.setAttribute("dto", dto);
		model.addAttribute("dto", dto);
	}
	
}
