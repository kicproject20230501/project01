package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Product;
import model.Survey;

@Repository
public class SurveyMybatis {

	@Autowired
	private SqlSessionTemplate sqlSession;

	private static final String NS = "mybatis.Survey.";

	public int insertSurvey(Survey survey) {
		return sqlSession.insert(NS + "insertSurvey", survey);
	}

	public Product ProductImage(int ansGender, String ans1, String ans2) {
		Map map = new HashMap();
		map.put("ansGender", ansGender);
		map.put("ans1", ans1);
		map.put("ans2", ans2);
		System.out.println(map);
		return sqlSession.selectOne(NS + "ProductImage", map);
	}

	public List<Survey> surveyList(String id) {

		Map map = new HashMap();
		map.put("id", id);

		return sqlSession.selectList(NS + "surveyList", map);
	}

	public List<Survey> AnswerDoughnut(int ansGender) {

		return sqlSession.selectList(NS + "AnswerDoughnut", ansGender);
	}

}// end