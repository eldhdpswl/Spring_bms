package spring.mvc.bms_project.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.bms_project.persistence.gMemberDAO;
import spring.mvc.bms_project.persistence.gMemberDAOImpl;
import spring.mvc.bms_project.vo.gMemberVO;


@Service
public class gMemberServiceImpl implements gMemberService{
	
	@Autowired
	gMemberDAO dao;
	
	//�ߺ�Ȯ��ó��
	@Override //(1�ܰ�, 2�ܰ�� MFrontController)
	public void confirmId(HttpServletRequest req, Model model) {
		// 3�ܰ�. ȭ�����κ��� �Է¹��� ���� �޾ƿ´�.
		
		String strId = req.getParameter("id");
		
		//4�ܰ�. ������ ����, �̱��� ������� ��ü ���� 
		//gMemberDAO dao = gMemberDAOImpl.getInstance();
		
		//5�ܰ�. �ߺ��� id�� �ִ��� Ȯ��
		int cnt = dao.idCheck(strId);
		
		//6�ܰ�. request�� session�� ó�� ����� ����
		//req.setAttribute("cnt", cnt);
		//req.setAttribute("id", strId);
		model.addAttribute("cnt", cnt);
		model.addAttribute("id", strId);
		
	}
	
	//ȸ������ó��
	@Override
	public void inputPro(HttpServletRequest req, Model model) {
		//vo �ٱ��� ����
		gMemberVO vo = new gMemberVO();
		
		//2�ܰ�. dto �ٱ��Ͽ� ��´�(ȭ�鿡�� �Ѿ�� ����).
		vo.setId(req.getParameter("id"));
		vo.setPwd(req.getParameter("pwd"));
		vo.setName(req.getParameter("name"));
		vo.setJumin1(req.getParameter("jumin1"));
		vo.setJumin2(req.getParameter("jumin2"));
		
		//hp
		String hp="";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2"); 
		String hp3 = req.getParameter("hp3");
		
		//�ʼ��Է��׸��� �ƴϹǷ� null üũ���� ������ insert�ϸ� nullpointer Exception�߻�
		if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 + "-" + hp2 + "-" + hp3; 
			
		}
		vo.setHp(hp);  //�ʼ��Է� �׸��� �ϳ��Ƿ� null�̸� ���� set, �ƴϸ� ���� ����
		
		//email
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String email3 = req.getParameter("email3");
		
		String email = "";
		
		if(email3.equals("0")) {
			//�����Է��϶�
			email = email1 + "@" + email2; 
			
		}else {
			//�����Է��϶�
			email = email1 + "@" + email3;
		}
		
		vo.setEmail(email);
		
		//req_date
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		//3�ܰ�. ����������, �̱��� ������� ��ü����
		//gMemberDAO dao = gMemberDAOImpl.getInstance();
		
		//4�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�.
		//4�ܰ�. DAO���� vo �ٱ��ϸ� dao �Ķ���Ϳ� �Ѱܼ� �ش�SQL�� ȣ��
		int cnt = dao.insertMember(vo);
		
		//5�ܰ�. request�� session�� ó������� �����ϸ� jsp���� �޴´�.
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
	}
	
	//�α��� ó��
	@Override
	public void loginPro(HttpServletRequest req, Model model) {
		// 1�ܰ�. ȭ�����κ��� id, pwd �޾ƿ´�.
		String strId = req.getParameter("id");
		String strPwd = req.getParameter("pwd");
		
		// 2-1�ܰ�. dao ��ü����(�̱���, ������ ����)
		//gMemberDAO dao = gMemberDAOImpl.getInstance();
		
		// 2-2�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�. 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		int cnt = dao.pwdCheck(map); //�ΰ��� ���� �Ѱܼ� select��
		
		if(cnt==1) {
			//memId��ҹ��� ����
			req.getSession().setAttribute("memId", strId);
		}
		
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
	}
	
	//Ż��ó�� ������
	@Override
	public void deletePro(HttpServletRequest req, Model model) {
		// 1�ܰ�. ȭ������ ���̵�, �н����� ���� �޾ƿ´�.
		String strId = (String)req.getSession().getAttribute("memId");
		String strPwd = req.getParameter("pwd");
		
		// 2-1�ܰ�. dao ��ü����(�̱���, ������ ����)
		//gMemberDAO dao = gMemberDAOImpl.getInstance();
		
		//2-2�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�. 
		/*
		 * ���̵�� �н����尡 ��ġ�ϸ� (selectCnt == 1) �Է��� ȸ�������� �о�´�.
		 * �н����尡 ��ġ���� ������ (selectCnt == -1) | ���̵� �������� ������(selectCnt == 0).
		 * */
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("strId", strId);
		 map.put("strPwd", strPwd);
		
		int selectCnt = dao.pwdCheck(map);
		int deleteCnt = 0;
		
		//���̵�� �н����尡 ��ġ�ϸ� ȸ��Ż��
		if(selectCnt == 1) {
			deleteCnt = dao.memberDelete(strId);
		}
		
		
		System.out.println("selectCnt -->" + selectCnt);
		System.out.println("deleteCnt -->" + deleteCnt);
		
		// 3�ܰ�. request�� session�� ó�� ����� �����ϸ� jsp���� �޴´�.
		//req.setAttribute("selectCnt", selectCnt);
		//req.setAttribute("deleteCnt", deleteCnt);
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("deleteCnt", deleteCnt);
	}
	
	//ȸ���������� ��������
	@Override
	public void modifyView(HttpServletRequest req, Model model) {
		// 1�ܰ�. ȭ�����κ��� id, pwd �޾ƿ´�.
		String strId = (String) req.getSession().getAttribute("memId");
		String strPwd = (String) req.getParameter("pwd");
		
		// 2-1�ܰ�. dao ��ü����(�̱���, ������ ����)
		//gMemberDAO dao = gMemberDAOImpl.getInstance();
		
		//2-2�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�. 
		//int selectCnt = dao.check(strId, strPwd);
		//Map<Ű, value(� Ÿ���� ���� �𸣴ϱ� Object�� ��)>
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		int selectCnt = dao.pwdCheck(map);
		
		//���̵�� �н����尡 ��ġ�ϸ�, �����ϱ� ���ؼ�, �Է��� ������ �о�´�.
		if(selectCnt == 1) {
			gMemberVO vo = dao.getMemberInfo(strId);
			//req.setAttribute("vo", vo);
			model.addAttribute("vo", vo);
		}
		
		// 3�ܰ�. request�� session�� ó�� ����� �����ϸ� jsp���� �޴´�.
		//req.setAttribute("selectCnt", selectCnt);
		model.addAttribute("selectCnt", selectCnt);
	}
	
	
	@Override
	public void modifyPro(HttpServletRequest req, Model model) {
		//1�ܰ�. ���� �̿��ؼ� ��û�� ����� �����Ѵ�.
		//vo �ٱ��� ����
		gMemberVO vo = new gMemberVO(); 		
				
		//2�ܰ�. dto �ٱ��Ͽ� ��´�(ȭ�鿡�� �Ѿ�� ����).
		//id
		String id = (String) req.getSession().getAttribute("memId");		
		vo.setId(id);
		vo.setPwd(req.getParameter("pwd"));
		
		//name
		String name = req.getParameter("name");
		vo.setName(name);
		
		//hp
		String hp ="";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		
		//�ʼ��Է��׸��� �ƴϹǷ� null üũ���� ������ insert�ϸ� nullpointer Exception�߻�
		if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 + "-" + hp2 + "-" + hp3;
			
		}
		vo.setHp(hp);
		
		//email
		String email="";		
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		email = email1 + "@" + email2;
		vo.setEmail(email);
		
		//3�ܰ�. ����������, �̱��� ������� ��ü����
		//gMemberDAO dao = gMemberDAOImpl.getInstance();
		
		//4�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�.
		//4�ܰ�. DAO���� vo �ٱ��ϸ� dao �Ķ���Ϳ� �Ѱܼ� �ش�SQL�� ȣ��		
		int cnt = dao.updateMember(vo);		
		
		//5�ܰ�. request�� session�� ó������� �����ϸ� jsp���� �޴´�.
		//req.setAttribute("cnt", cnt);
		model.addAttribute("cnt", cnt);
		
		System.out.println("cnt : " + cnt);

	}
	

}
