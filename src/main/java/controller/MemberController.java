package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Member;
import service.MemberMybatis;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	MemberMybatis md;

	Model m;
	HttpSession session;
	HttpServletRequest request;

	// 초기화 작업을 한다, 객체 초기화시에 사용한다
	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		session = request.getSession();

	}

	@RequestMapping("joinForm") // /member/joinForm
	public String joinForm() {

		return "member/joinForm";
	}

	@RequestMapping("loginForm") // /member/joinForm
	public String loginForm() {

		return "member/loginForm";
	}

	@RequestMapping("joinPro") // /member/joinForm
	public String joinPro(Member mem) {

		int num = md.insertMember(mem);
		String msg = "";
		String url = "";
		if (num > 0) {
			// insert ok
			msg = mem.getName() + "님이 가입을 하였습니다";
			url = "member/loginForm";
		} else {
			// insert error
			msg = "회원가입이 실패 하였습니다";
			url = "member/joinForm";
		}
		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	}

	@RequestMapping("loginPro") // /member/joinForm
	public String loginPro(String id, String pass) {

		Member mem = md.oneMember(id);
		String msg = "";
		String url = "";
		if (mem == null) { // id 없음
			msg = "아이디를 확인 하세요";
			url = "member/loginForm";
		} else {
			if (pass.equals(mem.getPass())) { // login ok
				session.setAttribute("id", id);
				msg = mem.getName() + "님이 로그인 하셨습니다";
				url = "home/index";
			} else {
				msg = "비밀번호를 확인 하세요";
				url = "member/loginForm";
			}
		}
		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	}

	@RequestMapping("logout") // /member/joinForm
	public String logout() {

		String id = (String) session.getAttribute("id");
		String name = md.oneMember(id).getName();
		session.invalidate();
		String msg = name + "님이 로그아웃 되었습니다";
		String url = "member/loginForm";
		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberInfo") // /member/joinForm
	public String memberInfo() {

		String id = (String) session.getAttribute("id");
		Member mem = md.oneMember(id);

		m.addAttribute("mem", mem);
		return "member/memberInfo";
	}

	@RequestMapping("memberUpdateForm")
	public String memberUpdateForm() {

		String id = (String) session.getAttribute("id");
		Member mem = md.oneMember(id);

		m.addAttribute("mem", mem);
		return "member/memberUpdateForm";
	}

	@RequestMapping("checkDuplicateId")

	public String checkDuplicateId(String id) {

		Member mem = md.oneMember(id);
		if (mem == null)
			m.addAttribute("chk", "사용 가능한 아이디 입니다.");
		else
			m.addAttribute("chk", "아이디가 이미 사용 중입니다. 다른 아이디를 선택해주세요.");

		return "member/checkDuplicateId";
	}

	@RequestMapping("memberUpdatePro")
	public String memberUpdatePro(Member newm) {

		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다";
		String url = "member/loginForm";
		newm.setId(id);
		Member dbm = md.oneMember(id); // password 확인

		if (dbm != null) {
			if (dbm.getPass().equals(newm.getPass())) {
				int num = md.updateMember(newm);

				if (num > 0) {
					msg = newm.getName() + "님의 정보 수정이 되었습니다";
					url = "member/memberInfo";
				} else {
					msg = "정보수정이 실패 하였습니다";
					url = "member/memberUpdateForm";
				}
			} else {
				msg = "비밀번호가 틀렸습니다";
				url = "member/memberUpdateForm";
			}
		} // password 확인

		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberPassForm")
//    @Login("MemberLoginUser")
	public String memberPassForm() {

		return "member/memberPassForm";
	}

	@RequestMapping("memberPassPro")
	public String memberPassPro(String pass, String chgpass1, String chgpass2) {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다";
		String url = "member/loginForm";

		Member dbm = md.oneMember(id); // password 확인
		if (dbm != null) {
			if (dbm.getPass().equals(pass)) {
				int num = md.changePass(id, chgpass1);
				if (num > 0) {
					msg = dbm.getName() + "님의 비밀번호가 수정 되었습니다";
					url = "member/memberInfo";
				} else {
					msg = "비밀번호 수정을 실패 하였습니다";
					url = "member/memberPassForm";
				}
			} else {
				msg = "비밀번호가 틀렸습니다";
				url = "member/memberPassForm";
			}
		} // password 확인

		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberDeleteForm")
	public String memberDeleteForm() {

		return "member/memberDeleteForm";
	}

	@RequestMapping("memberDeletePro")
	public String memberDeletePro(String pass) {

		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다";
		String url = "member/loginForm";

		Member dbm = md.oneMember(id); // password 확인

		if (dbm != null) {
			if (dbm.getPass().equals(pass)) {
				int num = md.deleteMember(id);
				if (num > 0) {
					msg = dbm.getName() + "님의 탈퇴 처리가 완료되었습니다";
					session.invalidate();
					url = "member/loginForm";
				} else {
					msg = "회원 탈퇴가 실패 하였습니다";
					url = "member/memberDeleteForm";
				}
			} else {
				msg = "비밀번호가 틀렸습니다";
				url = "member/memberDeleteForm";
			}
		} // password 확인

		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberList")
	public String memberList() {
		List<Member> li = md.memberList();

		m.addAttribute("li", li);
		return "member/memberList";
	}

	@RequestMapping("adminMemberDelete")
	public String MemberAdminDelete() {

		String[] chkdel = request.getParameterValues("chkdel");

		for (String cid : chkdel) {

			md.deleteMember(cid);
			String msg = "해당 회원의 탈퇴 처리가 되었습니다";
			String url = "member/memberList";
			m.addAttribute("msg", msg);
			m.addAttribute("url", url);
		}

		return "alert";
	}
}