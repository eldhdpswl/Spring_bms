package spring.mvc.bms_project.vo;

public class totalVO {
    /*num number(5) primary key,
    bookforeignIn number(20),--���ż���[����]
    bookforeignOut number(20),--���ż���[����]
    total number(25)--�� �Ǹ� ����*/
	private int num;
	private int bookforeignIn;
	private int bookforeignOut;
	private int total;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getBookforeignIn() {
		return bookforeignIn;
	}
	public void setBookforeignIn(int bookforeignIn) {
		this.bookforeignIn = bookforeignIn;
	}
	public int getBookforeignOut() {
		return bookforeignOut;
	}
	public void setBookforeignOut(int bookforeignOut) {
		this.bookforeignOut = bookforeignOut;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
