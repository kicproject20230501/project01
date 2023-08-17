package service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.ProdReview;


@Repository
public class ProdReviewMybatis {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	private static final String NS = "mybatis.prodreview.";
	
	public int insertProdReview(ProdReview prodReview) {
		return sqlSession.insert(NS + "insertProdReview", prodReview);
	}
	
}
