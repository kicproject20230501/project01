package controller;

import java.io.IOException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import board.Board;
import board.BoardDao;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;

@WebServlet(urlPatterns = { "/board/*" }, initParams = { @WebInitParam(name = "view", value = "/view/board/"), // jsp 위치
		@WebInitParam(name = "login", value = "login") }) // package

public class BoardController extends MskimRequestMapping {
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		return "index";
	} // index Test
	
	@RequestMapping("boardForm")
	public String boardForm(HttpServletRequest request, HttpServletResponse response) {

		return "boardForm";
	}
	
	@RequestMapping("boardPro")
	public String boardPro(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getServletContext().getRealPath("/")+"view/board/images"; // 사진 파일 경로
		String msg = "게시물 등록에 실패하였습니다.";
		String url = "/board/boardForm";
		
		String boardid = (String) request.getSession().getAttribute("boardid"); // boardid(게시판 종류) 설정
		if (boardid == null) boardid = "1"; // boardid 기본 1로 설정
		
		String filename="";
		try {
			MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "utf-8");
			filename = multi.getFilesystemName("image");
			Board board = new Board();
			board.setBoardid(boardid);
			board.setName(multi.getParameter("name"));
			board.setSubject(multi.getParameter("subject"));
			board.setContent(multi.getParameter("content"));
			board.setImage(filename);
			BoardDao bd = new BoardDao();
			int num = bd.insertBoard(board);
			if (num > 0) {
				msg = "게시물이 등록되었습니다.";
				url = "/board/boardList";
			}
		} catch (IOException e) {
			e.printStackTrace(); // 오류 메시지 콘솔에 출력
		}
		
		request.setAttribute("msg", filename+": "+msg);
		request.setAttribute("url", url);
		return "alert";
	} // boardList End
	
	@RequestMapping("boardList")
	public String boardList(HttpServletRequest request, HttpServletResponse response) {
		
		return "boardList";
	} // boardList End
	
} // BoardController End
