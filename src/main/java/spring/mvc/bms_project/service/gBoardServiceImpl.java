package spring.mvc.bms_project.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.bms_project.persistence.gBoardDAO;
import spring.mvc.bms_project.persistence.gBoardDAOImpl;
import spring.mvc.bms_project.vo.gBoardVO;


@Service
public class gBoardServiceImpl implements gBoardService{
	
	@Autowired
	gBoardDAO dao;
	
	
	//�۸��
	@Override
	public void boardList(HttpServletRequest req,  Model model) {
		
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
		//gBoardDAO dao = gBoardDAOImpl.getInstance();
		
		//�۰��� ���ϱ�
		cnt = 0;
		cnt = dao.getArticleCnt();
		
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
		
		System.out.println("cnt : " + cnt);
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";  //ù������ 1�������� ����
		}
		
		//����������
		currentPage = (Integer.parseInt(pageNum));  
		
		//������ ����
		//����������+������  //������ ������ ������ ���������� �� ��ߵȴ�.
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		
		//�������������� -1�� �ϴ� ������ 0���ͽ����ϱ⶧��
		start = (currentPage -1) * pageSize +1;
		
		//���������� ����ȣ
		end = start + pageSize -1;
		
		if(end > cnt) end=cnt;
		
		number = cnt - (currentPage - 1) * pageSize; //����� �۹�ȣ... �ֽű�(ū ������)�� 1page 
		
		System.out.println("number : " + number);
		System.out.println("cnt : " + cnt);
		System.out.println("currentPage : " + currentPage);
		System.out.println("pageSize : " + pageSize);
		
		if(cnt > 0) {
			//ArrayList<gBoardVO> dtos = dao.getArticleList(start, end);
			//req.setAttribute("dtos", dtos);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			ArrayList<gBoardVO> dtos = dao.getArticleList(map);
			
			model.addAttribute("dtos", dtos);
			
		}
		
		startPage = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		
		endPage = startPage + pageBlock -1; 
		if(endPage > pageCount) endPage = pageCount;
		
		//req.setAttribute("cnt", cnt); //�۰���
		//req.setAttribute("number", number); //�۹�ȣ
		//req.setAttribute("pageNum", pageNum); //��������ȣ
		model.addAttribute("cnt", cnt);
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		
		System.out.println("cnt--------"+cnt);
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
	
	//��������
	@Override
	public void b_contentForm(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		//dao ����(�̱���, ������)
		//gBoardDAO dao = gBoardDAOImpl.getInstance();
		
		//�������� ��������..1��
		gBoardVO dto = dao.getArticle(num);
		
		//��ȸ�� ����
		dao.addReadCnt(num);
		
		//jsp�� ���� �ѱ��. dto, pageNum, number
		//req.setAttribute("dto", dto);
		//req.setAttribute("pageNum", pageNum);
		//req.setAttribute("number", number);
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("number", number);
		
	}
	
	//�ۼ��� ��������
	@Override
	public void b_modifyView(HttpServletRequest req, Model model) {
		//�Ѱܹ��� �� �����´�.
		int num = Integer.parseInt(req.getParameter("num")); 
		int pageNum = Integer.parseInt(req.getParameter("pageNum")); 
		String strPwd = req.getParameter("pwd");
		
		//dao��ü����(�̱���, ������ ����)
		//gBoardDAO dao = gBoardDAOImpl.getInstance();
		
		//�н����尡 ��ġ�ϸ� selectCnt == 1, ����ġ�� selectCnt == 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("strPwd", strPwd);
		
		int selectCnt = dao.pwdCheck(map);
		
		//�н����尡 ��ġ�ϸ� num�� ��ġ�ϴ� �Խñ� 1���� �о�´�.
		if(selectCnt == 1) {
			gBoardVO dto = dao.getArticle(num);
			
			//req.setAttribute("dto", dto);
			model.addAttribute("dto", dto);
		}
		
		//jsp�� ������ �ѱ��.
		//req.setAttribute("selectCnt", selectCnt);
		//req.setAttribute("num", num);
		//req.setAttribute("pageNum", pageNum);
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		
	}
	
	//�ۼ��� ó��������
	@Override
	public void b_modifyPro(HttpServletRequest req, Model model) {
		
		//ȭ�����κ��� �Ѱܹ��� ������ �޴´�.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		//�ٱ��� ����
		gBoardVO dto = new gBoardVO();
				
		//�ٱ��Ͽ� ȭ�鿡�� �Է¹��� ������ ��´�.
		dto.setNum(num);
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setPwd(req.getParameter("pwd"));		
		
		//dao ��ü����(�̱���, ������)
		//gBoardDAO dao = gBoardDAOImpl.getInstance();
		
		//����ó��
		int cnt = dao.updateBoard(dto);
		//jsp�� ������ �ѱ��.
		//req.setAttribute("num", num);
		//req.setAttribute("pageNum", pageNum);
		//req.setAttribute("cnt", cnt);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("cnt", cnt);		
				
	}
	
	//���ۼ� ó��������
	@Override
	public void b_writePro(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int ref = Integer.parseInt(req.getParameter("ref"));
		int ref_step = Integer.parseInt(req.getParameter("ref_step"));
		int ref_level = Integer.parseInt(req.getParameter("ref_level"));
		
		//1. ���� �ٱ���(DTO)�� �����.
		gBoardVO dto = new gBoardVO();
		
		//2. ȭ�����κ��� �Է¹��� ������ ���� �ٱ���(DTO)�� ��´�.
		dto.setWriter(req.getParameter("writer"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		
		//3. hidden���κ��� �Ѱܹ��� ����(DTO)�� ��´�.
		
		dto.setNum(num);
		dto.setRef(ref);
		dto.setRef_step(ref_step);
		dto.setRef_level(ref_level);
		
		dto.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		//4. dao ��ü����(�̱���, ������)
		//gBoardDAO dao = gBoardDAOImpl.getInstance();
		
		//5. insertBoard()
		int cnt = dao.insertBoard(dto);
		System.out.println("cnt --> " + cnt);
		//6. jsp�� ������ �ѱ��.
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
	}
	
	//�ۻ��� ó��������
	@Override
	public void b_deletePro(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String strPwd = req.getParameter("pwd");
		
		//dao ��ü����(�̱���, ������)
		//gBoardDAO dao = gBoardDAOImpl.getInstance();
	
		//num�� ��ġ�� ��� ��й�ȣ ��ġ�ϴ��� Ȯ��
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("strPwd", strPwd);
		
		int selectCnt = dao.pwdCheck(map);
		
		
		/*
		 * deleteCnt = -1 : ����� �ִ� ��� ���� ����
		 * deleteCnt = 0 : ����� ���� ��� ���� ����
		 * deleteCnt = 1: ����� ���� ��� ���� ����
		 * */
		 
		
		if(selectCnt == 1) {
			int deleteCnt = dao.deleteBoard(num);
			//req.setAttribute("deleteCnt", deleteCnt);
			model.addAttribute("deleteCnt", deleteCnt);
			System.out.println("deleteCnt-->"+deleteCnt);
		}
		
		//req.setAttribute("selectCnt", selectCnt);
		//req.setAttribute("pageNum", pageNum);
		model.addAttribute("selectCnt", selectCnt);
		System.out.println("selectCnt-->"+selectCnt);
		model.addAttribute("pageNum", pageNum);
		
		
	}
	
	

}
