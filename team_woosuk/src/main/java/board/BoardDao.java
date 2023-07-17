package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDao {
	public Connection getConnection() { // db 연동
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic", "1111");
			System.out.println("db ok");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("db error");
		return null;
	}
	
	public int insertBoard(Board board) { // 게시글 입력
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement
					("insert into board values (boardseq.nextval,?,?,?,?,?,sysdate,0)");
			
			pstmt.setString(1, board.getBoardid()); // 게시판 종류
			pstmt.setString(2, board.getName()); // 작성자 -> 회원 아이디가 들어가게 바꾸기
			pstmt.setString(3, board.getSubject()); // 제목
			pstmt.setString(4, board.getContent()); // 내용
			pstmt.setString(5, board.getImage()); // 첨부된 사진
			
			return pstmt.executeUpdate(); // dml 시 실행
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
}
