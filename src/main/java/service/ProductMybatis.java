package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Product;

@Repository
public class ProductMybatis {

	@Autowired
	private SqlSessionTemplate sqlSession;

	private static final String NS = "mybatis.product.";

	public int insertProduct(Product product) {
		return sqlSession.insert(NS + "insertProduct", product);
	}

	public int productCount() {
		return sqlSession.selectOne(NS + "productCount");
	}

	public List<Product> productList(int pageInt, int limit) {
		Map map = new HashMap();
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", pageInt * limit);
		return sqlSession.selectList(NS + "productList", map);
	}

	public Product productOne(int prodnum) {
		return sqlSession.selectOne(NS + "productOne", prodnum);
	}

	public List<Product> productManagement(int pageInt, int limit) {
		Map map = new HashMap();
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", pageInt * limit);
		return sqlSession.selectList(NS + "productManagement", map);
	}

	public int productUpdate(Product product) {
		return sqlSession.insert(NS + "productUpdate", product);
	}

	public int productDelete(int prodnum) {
		return sqlSession.delete(NS + "productDelete", prodnum);
	}

	public List<Product> orderProductList(int prodnum) { // 주문서에서 찍어낼 product list
		return sqlSession.selectList(NS + "orderProductList", prodnum);
	}

	public int stockUpdate(Product product) {
		return sqlSession.insert(NS + "stockUpdate", product);

	}

}