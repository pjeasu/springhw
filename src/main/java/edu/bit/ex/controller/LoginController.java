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
	
	//�α���
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, RedirectAttributes rttr) {
		System.out.println("login ȣ��");
		
		HttpSession session = req.getSession();
		
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		UserVO login = loginService.loginUser(id, pw);
		
		if(login == null) {
			//RedirectAttributes : ���ΰ�ħ�ϸ� ���ư��� ������ (��ȸ��)
			rttr.addFlashAttribute("msg", false); // booleanŸ�Ե� ����
		}else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/";
	}
	
		//�α׾ƿ�
		@RequestMapping("/logout")
		public String logout(HttpSession session) {
			System.out.println("logout ȣ��");
			
			session.invalidate();
			return "redirect:/";
		}
		
}
