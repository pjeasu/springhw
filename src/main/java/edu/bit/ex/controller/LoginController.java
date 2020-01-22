package edu.bit.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.page.PageMaker;
import edu.bit.ex.service.BoardService;
import edu.bit.ex.service.LoginService;
import edu.bit.ex.vo.BoardVO;
import edu.bit.ex.vo.UserVO;

@Controller
@RequestMapping("/member")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, RedirectAttributes rttr) {
		System.out.println("login 호출");
		
		HttpSession session = req.getSession();
		
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		UserVO login = loginService.loginUser(id, pw);
		
		if(login == null) {
			//RedirectAttributes : 새로고침하면 날아가는 데이터 (일회성)
			rttr.addFlashAttribute("msg", false); // boolean타입도 가능
		}else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/";
	}
	
		//로그아웃
		@RequestMapping("/logout")
		public String logout(HttpSession session) {
			System.out.println("logout 호출");
			
			session.invalidate();
			return "redirect:/";
		}
		
}
