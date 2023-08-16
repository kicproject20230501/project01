package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Product;
import model.Survey;
import service.SurveyMybatis;

@Controller
@RequestMapping("/survey/")
public class SurveyController {

	@Autowired
	SurveyMybatis surbd;

	Model m;
	HttpSession session;
	HttpServletRequest request;

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		session = request.getSession();
	}

	// 설문 1페이지 (성별)
	@RequestMapping("survey01")
	public String survey01() {
		return "survey/survey01";
	} // survey01 End
	
	// 설문 시작 페이지
	@RequestMapping("surveyStart")
	public String surveyStart(Survey s) {
		// 설문 시작화면에 통계표 구현
		// 각 성별 선호 향 도넛그래프로 표시

		List<Survey> MaleD = surbd.AnswerDoughnut(1);
		List<Survey> FemaleD = surbd.AnswerDoughnut(2);

		while (MaleD.size() < 3) {
			MaleD.add(s);
			if (MaleD.size() == 3)
				break;
		}
		while (FemaleD.size() < 3) {
			FemaleD.add(s);
			if (FemaleD.size() == 3)
				break;
		}

		m.addAttribute("MaleD", MaleD);
		m.addAttribute("FemaleD", FemaleD);

		return "survey/surveyStart";
	} // surveyStart End
	
	// 설문 답변에 따라 다르게 로딩되는 페이지 (설문 2, 설문 3, 설문 결과)
	@RequestMapping("RadioCheckedPro")
	public String RadioCheckedPro(@RequestParam("page") String page, @RequestParam("ck1gender") int ansGender,
			@RequestParam("ck2favorite") String ans1, Survey survey) {
		String msg = "";
		// value 확인
		System.out.println("page:" + page);
		System.out.println("성별: " + ansGender);
		System.out.println("선호향: " + ans1);
		System.out.println("과일?: " + request.getParameter("ck3fruit"));
		System.out.println("꽃?: " + request.getParameter("ck3flower"));
		System.out.println("나무?: " + request.getParameter("ck3wood"));

		switch (page) {
		
		case "surveyStart":

			return "survey/surveyStart";
		
		case "survey01":

			return "survey/survey01";

		case "survey02":
			m.addAttribute("ck1gender", ansGender);
			m.addAttribute("ck2favorite", ans1);
			return "survey/survey02";

		case "survey03fruit":
			m.addAttribute("ck1gender", ansGender);
			m.addAttribute("ck2favorite", ans1);
			return "survey/survey03fruit";

		case "survey03flower":
			m.addAttribute("ck1gender", ansGender);
			m.addAttribute("ck2favorite", ans1);
			return "survey/survey03flower";

		case "survey03wood":
			m.addAttribute("ck1gender", ansGender);
			m.addAttribute("ck2favorite", ans1);
			return "survey/survey03wood";

		case "surveyResult":
			m.addAttribute("ck1gender", ansGender);
			m.addAttribute("ck2favorite", ans1);
			m.addAttribute("ck3fruit", request.getParameter("ck3fruit"));
			m.addAttribute("ck3flower", request.getParameter("ck3flower"));
			m.addAttribute("ck3wood", request.getParameter("ck3wood"));

			String ans2 = "";
			if (request.getParameter("ck3fruit") != null && !request.getParameter("ck3fruit").equals("")) {
				ans2 = request.getParameter("ck3fruit");
			}
			if (request.getParameter("ck3flower") != null && !request.getParameter("ck3flower").equals("")) {
				ans2 = request.getParameter("ck3flower");
			}
			if (request.getParameter("ck3wood") != null && !request.getParameter("ck3wood").equals("")) {
				ans2 = request.getParameter("ck3wood");
			}

			// 설문 문항 따라서 보이는 제품사진//제품이름
			// product db에서 image와 name가져오기
			Product p = surbd.ProductImage(ansGender, ans1, ans2);
			System.out.println("resultpage출력" + ansGender + "," + ans1 + "," + ans2);

			String image = p.getImage();
			String pname = p.getName();

			System.out.println("상품명" + pname);
			System.out.println("상품사진" + image);

			m.addAttribute("pname", pname);
			m.addAttribute("image", image);

			// 이전 설문 결과 표시 창
			// Answer db에서 id가 가지고잇는 리스트 출력후 배열로 정렬
			String id = (String) session.getAttribute("id");
			// String id = "1048";
			survey.setId(id);
			List<Survey> anslist = surbd.surveyList(id);
			List<String> anslistImage = new ArrayList<>();
			List<String> anslistImageName = new ArrayList<>();
			List<Integer> anslistProdnum = new ArrayList<>();
			for (int i = 0; i < anslist.size(); i++) {
				ansGender = anslist.get(i).getAnsGender();
				ans1 = anslist.get(i).getAns1();
				ans2 = anslist.get(i).getAns2();
				p = surbd.ProductImage(ansGender, ans1, ans2);
				anslistImage.add(p.getImage());
				anslistImageName.add(p.getName());
				anslistProdnum.add(p.getProdnum());
			}
			System.out.println("anslistImage: " + anslistImage);
			System.out.println("prodname: " + anslistImageName);
			System.out.println("prodnum: " + anslistProdnum);

			if (anslistImage != null && anslistImage.size() != 0) {
				msg = "이전 결과";
			}
			m.addAttribute("msg", msg);
			m.addAttribute("anslistImage", anslistImage);
			m.addAttribute("anslistImageName", anslistImageName);
			m.addAttribute("anslistProdnum", anslistProdnum);
			return "survey/surveyResult";
		}

		return page;
	} // RadioCheckedPro End

	// 설문 결과 DB에 저장
	// 이미 설문 조사한 사람도 여러번 참여가능하며 같은아이디 다른 시퀀스 번호로 설문한 자료 저장됨
	@RequestMapping("surveyInsertPro")
	public String surveyInsertPro(@RequestParam("page") String page, @RequestParam("ck1gender") int ansGender,
			@RequestParam("ck2favorite") String ans1, Survey survey) {
		String url = "";
		System.out.println(page);

		m.addAttribute("ck1gender", ansGender);
		m.addAttribute("ck2favorite", ans1);
		m.addAttribute("ck3fruit", request.getParameter("ck3fruit"));
		m.addAttribute("ck3flower", request.getParameter("ck3flower"));
		m.addAttribute("ck3wood", request.getParameter("ck3wood"));

		String id = (String) session.getAttribute("id");
		// String id = "1048";
		String ans2 = " ";
		if (request.getParameter("ck3fruit") != null && !request.getParameter("ck3fruit").equals("")) {
			ans2 = request.getParameter("ck3fruit");
		}
		if (request.getParameter("ck3flower") != null && !request.getParameter("ck3flower").equals("")) {
			ans2 = request.getParameter("ck3flower");
		}
		if (request.getParameter("ck3wood") != null && !request.getParameter("ck3wood").equals("")) {
			ans2 = request.getParameter("ck3wood");
		}

		survey.setId(id);
		survey.setAnsGender(ansGender);
		survey.setAns1(ans1);
		survey.setAns2(ans2);
		// 설문 결과에 나온 상품 이름 불러오기
		Product p = surbd.ProductImage(ansGender, ans1, ans2);
		String prodname = p.getName();
		int prodnum = p.getProdnum();
		survey.setProdname(prodname);
		System.out.println(prodname + ":" + prodnum);

		String anslistProdnum = request.getParameter("pnum");
		System.out.println(anslistProdnum);

		switch (page) {
		// 홈페이지로~
		case "surveyStart":

			int num = surbd.insertSurvey(survey);
			if (num > 0) {
				url = "/home/index";
				System.out.println("저장성공");
			} else {
				System.out.println("저장실패");
			}
			m.addAttribute("url", url);
			return "survey/alert";

		// 설문결과 사진 누르면~
		case "product":

			num = surbd.insertSurvey(survey);
			if (num > 0) {
				System.out.println("저장성공");
				url = "product/productDetail?prodnum=" + prodnum;
			} else {
				System.out.println("저장실패");
			}
			m.addAttribute("url", url);
			return "survey/alert";

		case "product2":

			num = surbd.insertSurvey(survey);
			if (num > 0) {
				System.out.println("저장성공");
				url = "product/productDetail?prodnum=" + anslistProdnum;
			} else {
				System.out.println("저장실패");
			}
			m.addAttribute("url", url);
			return "survey/alert";
		}
		return null;
	} // surveyInsertPro End

} // end