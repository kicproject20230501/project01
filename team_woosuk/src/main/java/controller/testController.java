package controller;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;

@WebServlet(urlPatterns= {"/test/*"},  
initParams= {@WebInitParam(name="view",value="/view/test/"),//jsp 위치
		@WebInitParam(name="login",value="login")} )  //package

public class testController extends  MskimRequestMapping {
	
	@RequestMapping("index") // /test/index
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping("best") // /test/best
	public String best(HttpServletRequest request, HttpServletResponse response) {
		return "best";
	}
}
