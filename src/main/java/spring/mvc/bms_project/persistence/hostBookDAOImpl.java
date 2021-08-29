package spring.mvc.bms_project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.bms_project.vo.guestCartVO;
import spring.mvc.bms_project.vo.hostBookVO;
import spring.mvc.bms_project.vo.totalVO;

@Repository
public class hostBookDAOImpl implements hostBookDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	//�Խñ� �ۼ�
	@Override
	public int insertBook(hostBookVO dto) {
		int cnt = 0;
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		cnt = dao.insertBook(dto);
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			sql = "INSERT INTO book(bookNum, kimg, bookName, author, publisher, content, price, bookforeign, bookcount, reg_date) "
					+"VALUES ( book_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getKimg());
			pstmt.setString(2, dto.getBookName());
			pstmt.setString(3, dto.getAuthor());
			pstmt.setString(4, dto.getPublisher());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, dto.getPrice());
			pstmt.setString(7, dto.getBookforeign());
			pstmt.setInt(8, dto.getBookcount());
			pstmt.setTimestamp(9, dto.getReg_date());
			
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
	
	
	//å���� ���ϱ�
	@Override
	public int getArticleCnt() {
		int cnt = 0;
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		cnt = dao.getArticleCnt();
		
		return cnt;
	}
	
	//å��� ��� ��ȸ
	@Override
	public ArrayList<hostBookVO> getArticleList(Map<String, Integer> map) {
		ArrayList<hostBookVO> dtos = null;
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dtos = dao.getArticleList(map);
		
		return dtos;
	}

	//��������	
	@Override
	public hostBookVO getArticle(int bookNum) {
		
		hostBookVO dto = null;
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dto = dao.getArticle(bookNum);
		
		return dto;
	}
	/*
	//��й�ȣ Ȯ��(�Խñ� ����, �Խñ� ����)
	@Override
	public int pwdCheck(int bookNum, String strPwd) {
		
		return 0;
	}
	
	//å���� ����(�ؾߵɺκ�)
	@Override
	public int updateBook(hostBookVO dto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql= "UPDATE book set book=? , content=?, pwd=? WHERE num=?";
			
			//pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, dto.getSubject());
			//pstmt.setString(2, dto.getContent());
			//pstmt.setString(3, dto.getPwd());
			//pstmt.setInt(4, dto.getNum());
			
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
		
		return cnt;
	}
	
	//å ����ó��
	@Override
	public int deleteBook(int bookNum) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			sql= "SELECT * FROM book WHERE bookNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pstmt.close();
				sql="DELETE FROM book WHERE bookNum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookNum);
				
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
		}
		
		return cnt;
	}
	*/
	
	//�ֹ����
	@Override
	public ArrayList<guestCartVO> getorderlist(int cnt) {
		
		ArrayList<guestCartVO> dtos = null;
		
		//guestCartVO dto = null;
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dtos= dao.getorderlist(cnt);
		
		return dtos;
	}
	
	//�ֹ���û��Ͽ� �ö�� ���� ����
	@Override
	public int payNum() {
		int cnt = 0;
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		cnt = dao.payNum();
		
		return cnt;
	}
	
	//����/���� ����
	@Override
	public String selectforeign(int bookNum) {
		String foreign = null;
		
		/*
		try {
			conn = datasource.getConnection();
			sql= "SELECT * FROM book WHERE bookname=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foreign = rs.getString("bookforeign");
				
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
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		foreign = dao.selectforeign(bookNum);
		
		return foreign;
	}
	
	//�ֹ��Ϸ������� step=2
	@Override
	public void stepUpdate(int payNum) {
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql= "UPDATE pay set step=2 WHERE payNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, payNum);
			
			pstmt.executeUpdate();
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
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.stepUpdate(payNum);
		
		
	}
	//�����϶�
	@Override
	public void total_in(Map<String, Object> map) {
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			if(bookforeign.equals("����")) {
				conn = datasource.getConnection();
				String sql= "UPDATE total set bookforeignIn=bookforeignIn+? ,total=total+? WHERE num=1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, count);
				pstmt.setInt(2, price);
			}else {
				conn = datasource.getConnection();
				String sql= "UPDATE total set bookforeignOut=bookforeignOut+? ,total=total+? WHERE num=1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, count);
				pstmt.setInt(2, price);
			}
			
			
			pstmt.executeUpdate();
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
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.total_in(map);
		
		
	}
	//�����϶�
	@Override
	public void total_out(Map<String, Object> map) {
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.total_out(map);
	}
	
	
	//ȯ���ϸ� �ٽ� book���̺� ����� ����
	@Override
	public void hostrefundbook(Map<String, Object> map) {
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.hostrefundbook(map);
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql= "UPDATE book set bookcount = bookcount + ? WHERE bookNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookcount);
			pstmt.setInt(2, bookNum);
			
			pstmt.executeUpdate();
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
	}
	//ȯ���ҋ� ���ݾ׿��� ȯ���� �ݾ��� ����
	@Override
	public void totalupdate(int price) {
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.totalupdate(price);
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql= "UPDATE total set total = total - ? WHERE num=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			
			pstmt.executeUpdate();
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
	}
	//ȯ���Ҷ� step=4�� �ٲٱ�
	@Override
	public void updatestep(int payNum) {
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.updatestep(payNum);
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql= "UPDATE pay set step = 4 WHERE payNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, payNum);
			
			pstmt.executeUpdate();
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
	}
	
	//ȯ���ϴ� å�� �������� /�������� ��������
	@Override
	public String foreigncheck(int bookNum) {
		
		String foreign = null;
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		foreign=dao.foreigncheck(bookNum);
		
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			sql= "SELECT * FROM book WHERE bookNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foreign = rs.getString("bookforeign");
				
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
		return foreign;
		
	}
	
	@Override
	public void totalforeign_in(Map<String, Object> map1) {
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.totalforeign_in(map1);
		
		
	}
	
	@Override
	public void totalforeign_out(Map<String, Object> map1) {
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dao.totalforeign_out(map1);
	}
	/*
	@Override
	public void totalforeign(String foreign, int bookcount) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			if(foreign.equals("����")) {
				conn = datasource.getConnection();
				String sql= "UPDATE total set bookforeignIn = bookforeignIn - ? WHERE num=1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookcount);
				
			}else {
				conn = datasource.getConnection();
				String sql= "UPDATE total set bookforeignOut = bookforeignOut - ? WHERE num=1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookcount);
			}
			
			pstmt.executeUpdate();
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
		
	}
	*/
	
	@Override
	public totalVO selecttotal() {
		/*
		try {
			conn = datasource.getConnection();
			sql= "SELECT * FROM total WHERE num=1";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setBookforeignIn(rs.getInt("bookforeignIn"));
				dto.setBookforeignOut(rs.getInt("bookforeignOut"));
				dto.setTotal(rs.getInt("total"));
				
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
		totalVO dto= new totalVO();
		
		hostBookDAO dao = sqlSession.getMapper(hostBookDAO.class);
		dto = dao.selecttotal();
		return dto;
		
	}
	
	

}
