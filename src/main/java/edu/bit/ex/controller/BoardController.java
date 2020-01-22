package edu.bit.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.page.PageMaker;
import edu.bit.ex.vo.BoardVO;
import edu.bit.ex.vo.UserVO;
import edu.bit.ex.service.BoardService;

@Controller
public class BoardController {
	
	@Inject
	BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
		List<BoardVO> list =  boardService.selectBoardList();
		model.addAttribute("list", list);
		
		return "list";
	}


	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO) {
		System.out.println("write()");
		
		boardService.insertBoard(boardVO);
		return "redirect:list2";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest req, Model model) {
		System.out.println("content_view()");
		
		String bId = req.getParameter("bId");
		model.addAttribute("content_view", boardService.selectBoardOne(bId));
				
		return "content_view";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO boardVO) {
		System.out.println("update()");
		
		boardService.updateBoard(boardVO);
	
		return "redirect:list2";
	}
	
	
	@RequestMapping("/reply_view") //content_view와 같다 
	public String reply_view(HttpServletRequest req, Model model) {
		System.out.println("reply_view()");
		
		String bId = req.getParameter("bId");
		model.addAttribute("reply_view", boardService.selectBoardOne(bId));
				
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(BoardVO boardVO) {
		System.out.println("reply()");
		
		boardService.writeReply(boardVO); //쿼리문 두개 있음...
		return "redirect:list2";
	}
	
	@RequestMapping("/delete")
	public String delete(BoardVO boardVO) {
		System.out.println("delete()");
		
		boardService.delete(boardVO);
		return "redirect:list2";
	}
	
	
	@RequestMapping("/list2")
	public String list2(Criteria criteria, Model model, HttpSession session) {
		System.out.println("list2()");
		
//		//첫번째방법
//		UserVO member = (UserVO)session.getAttribute("member");
//		
//		if(member==null) {
//			System.out.println("멤버 세션값 없음");
//			return "redirect:/";
//		}
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		System.out.println(criteria.getPerPageNum());
		System.out.println(criteria.getPage());
		
		//전체 값 세팅
		int totalCount = boardService.selectCountBoard();
		System.out.println("전체 게시물 수를 구함:" + totalCount);
		pageMaker.setTotalCount(totalCount);
		
		
		List<BoardVO> boardList =  boardService.selectBoardListPage(criteria);
		model.addAttribute("list", boardList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "list2";
	}

	
	
	
	
	
}
