package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProductDao {
	public Connection getConnection() {
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

	public int insertProduct(Product product) { // 게시글 입력
		Connection con = getConnection(); 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement
					("insert into product values (productseq.nextval,?,?,?,?,?,?,?,?,sysdate)");
			
		
			pstmt.setString(1, product.getName()); 	// 상품 이름
			pstmt.setInt(2, product.getPrice()); 	// 상품 가격
			pstmt.setInt(3, product.getStock()); 	// 재고
			pstmt.setString(4, product.getInfo());	// 상품 상세정보
			pstmt.setString(5, product.getImage());	// 상품사진
			pstmt.setInt(6, product.getProdgender());	// 성별정보(외래키Member)
			pstmt.setString(7, product.getProdans1());	// 설문정보1(외래키Answer)
			pstmt.setString(8, product.getProdans2());	// 설문정보2(외래키Answer)
			
			
			
			return pstmt.executeUpdate(); // dml 시 실행
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
}
