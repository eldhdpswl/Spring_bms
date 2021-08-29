package spring.mvc.bms_project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.bms_project.vo.cartAddVO;
import spring.mvc.bms_project.vo.guestCartVO;
import spring.mvc.bms_project.persistence.hostBookDAOImpl;
import spring.mvc.bms_project.vo.hostBookVO;

@Repository
public class guestCartDAOImpl implements guestCartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//å���� ���ϱ�
	@Override
	public int getArticleCnt() {
		
		int cnt = 0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.getArticleCnt();
		
		return cnt;
	}
	
	//å��� ��� ��ȸ
	@Override
	public ArrayList<guestCartVO> getArticleList(Map<String, Integer> map) {
		
		ArrayList<guestCartVO> dtos = null;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		dtos = dao.getArticleList(map);
		
		return dtos;
	}
	
	//��������
	@Override
	public guestCartVO getArticle(int bookNum) {
		
		guestCartVO dto = null;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		dto = dao.getArticle(bookNum);
		
		return dto;
	}
	@Override
	public int selectcart(int bookNum) {
		int cnt=0;
		System.out.println("-------------------------------");
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt=dao.selectcart(bookNum);
		
		return cnt;
	}
	//��ٱ��Ͽ� �Ȱ��� å�� ������  ������Ʈ 
	@Override
	public int updatecount(Map<String, Integer> map) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt=dao.updatecount(map);
		
		return cnt;
	}
	
	//��ٱ��Ͽ� �Ȱ��� å�� ������  insert 
	@Override
	public int insertcount(cartAddVO cartdto) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.insertcount(cartdto);
		
		return cnt;
	}
	
	
	
	//��ٱ��� cart���̺� �߰� //////////////////�����ؾߵ� �κ�
	@Override
	public int insertCart(cartAddVO cartdto) {
		int cnt = 0;
		int cartcount=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		System.out.println("BookNum:"+cartdto.getBookNum());
		try {
		cartcount=dao.selectcart(cartdto.getBookNum());
		}catch(Exception e) {
			
		}
		System.out.println("cartcount: "+cartcount);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("bookNum", cartdto.getBookNum());
		map.put("cartcount", cartcount+cartdto.getCartcount());
		
		if(cartcount>0) {
			//������Ʈ
			cnt = dao.updatecount(map);
		}else {
			//insert
			cnt = dao.insertcount(cartdto);
		}
		//cnt����
		
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			sql = "SELECT * FROM cart WHE
			RE bookNum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cartdto.getBookNum());
			
			rs = pstmt.executeQuery();
			
			//��ٱ������̺� �����Ͱ� ������
			if(rs.next()) {
				int cartcount = rs.getInt("cartcount");
				pstmt.close();
				sql= "UPDATE cart SET cartcount = ? "
						+"WHERE bookNum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cartdto.getCartcount()+cartcount);
				pstmt.setInt(2, cartdto.getBookNum());
				
				pstmt.executeUpdate();
				
				cnt = pstmt.executeUpdate();
			
			//��ٱ������̺� �����Ͱ� ������
			}else {
				pstmt.close();
				sql = "INSERT INTO cart(cartNum, id, bookNum, cartcount) "
						+"VALUES ( cart_seq.NEXTVAL, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, cartdto.getId());
				pstmt.setInt(2, cartdto.getBookNum());
				pstmt.setInt(3, cartdto.getCartcount());
				
				cnt = pstmt.executeUpdate();
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		return cnt;
	}
	
	//��ٱ��� å ���� ���ϱ�
	@Override
	public int getCartArticleCnt() {
		
		int cnt = 0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.getCartArticleCnt();
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) FROM cart";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		return cnt;
	
	}
	
	//��ٱ��� ��� ��ȸ
	@Override
	public ArrayList<guestCartVO> getCartArticleList(Map<String, Object> map) {
		ArrayList<guestCartVO> dtos = null;
		
		//guestCartVO dto = new guestCartVO();
		//dto.setTotal(dto.getBookcount(), dto.getPrice());
		
		
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		//dto.setTotal(dto.getBookcount(), dto.getPrice());
		dtos= dao.getCartArticleList(map);
		
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT " + 
						 "c.cartNum AS cartNum, " +
						 "c.bookNum AS bookNum, " + 
						 "b.kimg AS kimg, " + 
						 "b.bookname AS bookname, " + 
						 "b.price AS price, " + 
						 "c.cartcount AS cartcount " + 
						 "FROM cart c JOIN book b " + 
						 "ON c.bookNum=b.bookNum "+
						 "WHERE id=?";
						 //3. �Ѱܹ��� start���� end������ rowNum�� ��ȸ�Ѵ�. 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dtos = new ArrayList<guestCartVO>(cnt);
				
				do {
					//2. �����ٱ��� ����
					guestCartVO dto = new guestCartVO();
					
					//3. �Խñ� 1���� �о rs�� �����ٱ���(dto)�� ��ƶ�
					dto.setCartNum(rs.getInt("cartNum"));
					dto.setBookNum(rs.getInt("bookNum"));
					dto.setKimg(rs.getString("kimg"));
					dto.setBookName(rs.getString("bookname"));
					dto.setPrice(rs.getInt("price"));
					dto.setBookcount(rs.getInt("cartcount"));
					
					dto.setTotal(rs.getInt("cartcount"), rs.getInt("price"));
					
					dtos.add(dto);
					
				}while(rs.next());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		return dtos;
	}
	//����κ��� cartNum�� VO�� �޾ƿ��ºκ��̰�
	@Override
	public cartAddVO getCart(int num) {
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cartAddVO dto=dao.getCart(num);
		return dto;
	}
	
	//����κ��� �޾ƿ� cartNum�� dtos�� �ִ´ٴ°���???
	//���Ŵ����� �ѱ涧 ��ٱ��� ��� ��ȸ�ϱ�
	@SuppressWarnings("null")
	@Override
	public ArrayList<cartAddVO> getCartInfo(String[] cartNum) {
		
		ArrayList<cartAddVO> dtos =new ArrayList<cartAddVO>();
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		
		//cartNum�� ����ִ� ����ŭ �ݺ�
		for(int i=0;i<cartNum.length;i++) {
			//ù��° cartNum���� dto���� �����ͼ� dtos�� ����
			cartAddVO dto=dao.getCart(Integer.parseInt(cartNum[i]));
			dtos.add(dto);
		}
		System.out.println("-----------1234");
		/*dtos = dao.getCartInfo(cartNum);
		*/
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		guestCartVO dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="SELECT * FROM cart WHERE cartNum=?";
			pstmt = conn.prepareStatement(sql);
			dtos= new ArrayList<guestCartVO>(cartNum.length);
			
			for(int i=0; i<cartNum.length; i++) { //for�� ��������� �迭�� Ǯ�����ؼ� ���
				int cart1 = Integer.parseInt(cartNum[i]);
				pstmt.setInt(1, cart1);	
				System.out.println("cart1" + cart1);
				rs = pstmt.executeQuery();
			
				if(rs.next()) {
					dto = new guestCartVO();
					
					dto.setBookcount(rs.getInt("cartcount"));
					System.out.println(rs.getInt("cartcount"));
					
					dto.setCartNum(rs.getInt("cartNum"));
					System.out.println(rs.getInt("cartNum"));
					
					dto.setId(rs.getString("id"));
					dto.setBookNum(rs.getInt("bookNum"));
					System.out.println(rs.getInt("bookNum"));
					
					dtos.add(dto);
				}
				
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return dtos;
	}
	

/*	//pay_seq.NEXTVAL ���ڰ�������
	@Override
	public int pay_seq() {
		int pay_seq=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql="SELECT pay_seq.NEXTVAL as pay_num FROM dual";
			pstmt = conn.prepareStatement(sql);	
				rs = pstmt.executeQuery();
				if(rs.next()) {
					rs.getInt("pay_num");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return pay_seq;
	}*/
	
	@Override
	public void iPay(cartAddVO dto) {
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		dao.iPay(dto);
	}
	
	//pay���̺� �߰�
	@Override
	public String insertpay(ArrayList<cartAddVO> list) { //serviceImpl���� insertpay(dtos)���� dtos�� ���⼭ insertpay(ArrayList<guestCartVO> list)���� list�� ����(������ �����̱⶧���� �̸��� �ٸ��� �����Ҽ� �ִ�.)
		String id="";
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		System.out.println("list:"+list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println("@@@@@@bookNum="+list.get(i).getBookNum());
			System.out.println("@@@@@@cartcount="+list.get(i).getCartcount());
			System.out.println("@@@@@@id="+list.get(i).getId());
			//list.get(i) 0��°���� listǮ� �ֱ�
			dao.iPay(list.get(i));
			System.out.println();
			id=list.get(i).getId();
		}
		//Connection conn = null;				
		//PreparedStatement pstmt = null;
		//ResultSet rs = null;
		//String id="";
		/*
		try {
			conn = datasource.getConnection();
			String sql="INSERT INTO pay(payNum, bookNum, bookcount, id, step) VALUES(pay_seq.nextval,?,?,?,1)";
			pstmt = conn.prepareStatement(sql);
			
			
			for(int i=0; i<list.size(); i++) { //for�� ��������� �迭�� Ǯ�����ؼ� ���
				
				id = list.get(i).getId();
				int bookcount = list.get(i).getBookcount();
				int bookNum = list.get(i).getBookNum();
				
				System.out.println("id" + id + "bookcount" + bookcount + "bookNum" + bookNum);
				
				pstmt.setInt(1, bookNum);
				pstmt.setInt(2, bookcount);
				pstmt.setString(3, id);
				
				pstmt.executeUpdate();
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		return id;
	}
	
	//cart���̺� �ִ������� delete�ϱ�
	public int Dcart(cartAddVO dto) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.Dcart(dto);
		
		return cnt;
		
	}

	//cart���̺� �ִ� �������� delete
	@Override
	public int deletecart(ArrayList<cartAddVO> delist) {
		
		int deleteCnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		
		for(int i=0; i<delist.size(); i++) {
			deleteCnt= dao.Dcart(delist.get(i));
		}
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = datasource.getConnection();
			String sql="DELETE FROM cart WHERE cartNum=?";
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<delist.size(); i++) { //for�� ��������� �迭�� Ǯ�����ؼ� ���
				
				int cartNum = delist.get(i).getCartNum();
				
				pstmt.setInt(1, cartNum);
				
				deleteCnt = pstmt.executeUpdate();
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return deleteCnt;
	}
	
	//pay�����ҷ�����
	@Override
	public ArrayList<guestCartVO> getPayList(Map map) {
		//String memId,int cnt
		ArrayList<guestCartVO> dtos = null;
		System.out.println("memId:"+map.get("memId"));
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		dtos = dao.getPayList(map);
		
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		guestCartVO dto = null;
		
		try {
			conn = datasource.getConnection();
			
			String sql = "SELECT " + 
						 "p.payNum AS payNum, "
						 +"p.bookNum AS bookNum,p.step as step, "
						 + "p.id as id, " + 
						 "b.kimg AS img, " + 
						 "b.bookName AS bookName, " + 
						 "b.price AS price, " + 
						 "p.bookcount AS bookcount " + 
						 "FROM book b JOIN pay p " + 
						 "ON b.bookNum = p.bookNum"
						 + " WHERE p.id=?";
			pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, memId);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dtos=new ArrayList<guestCartVO>(cnt);
					do {
					dto=new guestCartVO();
					dto.setBookNum(rs.getInt("bookNum"));
					System.out.println(rs.getInt("bookNum"));
					dto.setKimg(rs.getString("img"));
					dto.setBookName(rs.getString("bookName"));
					System.out.println(rs.getString("bookName"));
					dto.setPrice(rs.getInt("price"));
					dto.setStep(rs.getInt("step"));
					dto.setBookcount(rs.getInt("bookcount"));
					dto.setTotal(rs.getInt("price"), rs.getInt("bookcount"));
					dto.setPayNum(rs.getInt("payNum"));
					
					dtos.add(dto);
					}while(rs.next());
				}
				
			System.out.println("---------------======");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return dtos;
	}
	
	//�����ͼ� ��������
	@Override
	public int payNum(String memid) {

		int cnt = 0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.payNum(memid);
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(paynum) as count FROM pay WHERE id=? and step!=4";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("count");
				System.out.println("================="+cnt);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return cnt;
	}
	/*
	//���Ÿ�� å ���� ���ϱ�
	@Override
	public int getPayArticleCnt() {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) FROM pay";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	//��ٱ��� ��� ��ȸ
	@Override
	public ArrayList<guestCartVO> getPayArticleList(int cnt, String memId) {
		
		ArrayList<guestCartVO> dtos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT " + 
						 "c.cartNum AS cartNum, " +
						 "c.bookNum AS bookNum, " + 
						 "b.kimg AS kimg, " + 
						 "b.bookname AS bookname, " + 
						 "b.price AS price, " + 
						 "c.cartcount AS cartcount " + 
						 "FROM cart c JOIN book b " + 
						 "ON c.bookNum=b.bookNum "+
						 "WHERE id=?";
						 //3. �Ѱܹ��� start���� end������ rowNum�� ��ȸ�Ѵ�. 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dtos = new ArrayList<guestCartVO>(cnt);
				
				do {
					//2. �����ٱ��� ����
					guestCartVO dto = new guestCartVO();
					
					//3. �Խñ� 1���� �о rs�� �����ٱ���(dto)�� ��ƶ�
					dto.setCartNum(rs.getInt("cartNum"));
					dto.setBookNum(rs.getInt("bookNum"));
					dto.setKimg(rs.getString("kimg"));
					dto.setBookName(rs.getString("bookname"));
					dto.setPrice(rs.getInt("price"));
					dto.setBookcount(rs.getInt("cartcount"));
					
					dto.setTotal(rs.getInt("cartcount"), rs.getInt("price"));
					
					dtos.add(dto);
					
				}while(rs.next());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	*/
	
	//���Ÿ�Ͽ��� �����ϱ��Ҷ� payNumüũ�ϱ� //ù����
	@Override
	public int selectPayNum(guestCartVO dto) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.selectPayNum(dto);
		
		return cnt;
	}
	
	//��ٱ������̺� �����Ͱ� ������  //�ι�°
	@Override
	public int UPpay(guestCartVO dto) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.UPpay(dto);
		
		return cnt;
	}
	
	
	//���Ÿ�Ͽ��� �����ϱ��Ҷ� step update�� �κ�
	@Override
	public int updatepay(guestCartVO paydto) {
		int cnt = 0;
		int checkpay=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		checkpay= dao.selectPayNum(paydto);
		
		if(checkpay>0) {
			cnt = dao.UPpay(paydto);
		}
		
		
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			sql = "SELECT * FROM pay WHERE payNum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, paydto.getPayNum());
			System.out.println("PayNum:"+paydto.getPayNum());
			rs = pstmt.executeQuery();
			
			//��ٱ������̺� �����Ͱ� ������
			if(rs.next()) {
				pstmt.close();
				sql= "UPDATE pay SET step = 2 WHERE payNum=?";
						
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, paydto.getPayNum());
				
				pstmt.executeUpdate();
				
				cnt = pstmt.executeUpdate();
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		
		return cnt;
	}

	@Override
	public void BCUpdate(cartAddVO dto) {
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		dao.BCUpdate(dto);
	}
	
	//for������ update���ֱ�
	@Override
	public void bookCountUpdate(ArrayList<cartAddVO> list) {
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		
		for(int i=0; i<list.size(); i++) {
			dao.BCUpdate(list.get(i));
		}
		
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			for(int i=0;i<dtos.size();i++) {
				int num=dtos.get(i).getBookNum();
				
				int count=dtos.get(i).getBookcount();
				
				conn = datasource.getConnection();
				String sql = "UPDATE book SET bookcount = bookcount-? WHERE booknum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, count);
				pstmt.setInt(2, num);
				pstmt.executeUpdate();
				pstmt.close();
			}
		
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		
		
	}
	//ȯ�ҿ�û������ update
	@Override
	public int refund(int payNum) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.refund(payNum);
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE pay SET step=3 WHERE payNum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, payNum);
			cnt=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		return cnt;
	}

	//�ٷα����ҋ� pay�� insert
	@Override
	public int insertdirectpay(Map<String, Object> map) { // ���⼭ insertpay(ArrayList<guestCartVO> list)���� list�� ����(������ �����̱⶧���� �̸��� �ٸ��� �����Ҽ� �ִ�.)
		
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.insertdirectpay(map);		
		/*
		Connection conn = null;							
		PreparedStatement pstmt = null;
		
		
		
		try {
			conn = datasource.getConnection();
			String sql="INSERT INTO pay(payNum, bookNum, bookcount, id, step) VALUES(pay_seq.nextval,?,?,?,1)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookNum);
			pstmt.setInt(2, bookcount);
			pstmt.setString(3, id);
			
			cnt = pstmt.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		return cnt;
		
	}
	//�ٷα����ҋ� pay�� update
	@Override
	public int directpayupdate(Map<String, Object> map) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.directpayupdate(map);		
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE book SET bookcount=bookcount-? WHERE bookNum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookcount);
			pstmt.setInt(2, bookNum);
			cnt=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return cnt;
	}
	
	@Override
	public int selectBCount(Map<String, Object> map) {
		int cnt = 0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.selectBCount(map);	
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM book where bookNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("bookcount");
				System.out.println("---------------"+bookcount);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return cnt;
	}
	
	
	//�������
	@Override
	public int paycancel(Map<String, Object> map) {
		int cnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		cnt = dao.paycancel(map);
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE book SET bookcount=bookcount+? WHERE bookNum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookcount);
			pstmt.setInt(2, bookNum);
			cnt=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return cnt;
	}
	
	//��������ҋ� pay���� list����
	@Override
	public int paylistdelete(int payNum) {
		int deleteCnt=0;
		
		guestCartDAO dao = sqlSession.getMapper(guestCartDAO.class);
		deleteCnt = dao.paylistdelete(payNum);
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = datasource.getConnection();
			String sql="DELETE FROM pay WHERE payNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, payNum);
			deleteCnt = pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		
		return deleteCnt;
	}
	
	
	
	
	
	//���Ÿ�Ͽ��� �����ϱ��Ҷ� pay���ִ� ��Ϻҷ�����
	/*@Override
	public ArrayList<guestCartVO> getPaymentList() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			sql = "SELECT * FROM pay";
			pstmt = conn.prepareStatement(sql);
			
			
			
			rs = pstmt.executeQuery();
			
			//��ٱ������̺� �����Ͱ� ������
			if(rs.next()) {
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return ;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}
