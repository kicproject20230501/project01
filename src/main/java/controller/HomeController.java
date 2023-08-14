package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/home/")
public class HomeController {
	
	Model m;
	HttpSession session;
	HttpServletRequest request;
	
	// 초기화 작업을 한다, 객체 초기화 시에 사용한다.
	@ModelAttribute
	void init (HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		session = request.getSession();
	}
	
	@RequestMapping("index") // /home/index
	public String index() {
		
		return "home/index";
	}
	
	@RequestMapping("about") // /home/index
	public String about() {
		
		return "home/about";
	}
	
	@RequestMapping("adminAlert") // admin 확인
	public String adminAlert(String id) {
		if (id.equals("admin")) {
			m.addAttribute("msg", "접근 권한이 없습니다.");
		}
		m.addAttribute("url", "home/index");
		
		return "alert";
	}
	
	//로그인한 유저만 가능
	@RequestMapping("loginAlert") // 로그인 확인
	public String alert(String id){
		if(id.equals("login")) {
			m.addAttribute("msg", "로그인을 해주세요.");
		} 	
		m.addAttribute("url", "member/loginForm");	
		
		return "alert";
	}
	
}
