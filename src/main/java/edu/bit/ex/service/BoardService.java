package edu.bit.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;
import edu.bit.ex.mapper.BoardMapper;

@Service
public class BoardService {//비즈니스로직~~~
	
	@Autowired
	BoardMapper boardMapper;
	
	//게시판 List를 출력하기 위한 로직
	public List<BoardVO> selectBoardList(){
		return boardMapper.selectBoardList();
	}
	
	
	//게시판에서 리플을 쓰는 로직
	public void writeReply(BoardVO boardVO) {
		
		//step과 indent를 +1 해주는 로직 필요 
		boardMapper.updateShape(boardVO);
		boardMapper.insertReply(boardVO);
	}

	//글작성
	public void insertBoard(BoardVO boardVO) {
		boardMapper.insertBoard(boardVO);

	}

	//글보기
	public BoardVO selectBoardOne(String bId) {
		boardMapper.upHit(bId);
		return boardMapper.selectBoardOne(bId);
	}
	
	//글수정
	public void updateBoard(BoardVO boardVO) {
		boardMapper.updateBoard(boardVO);
	}
	
	//글삭제
	public void delete(BoardVO boardVO) {
		boardMapper.delete(boardVO);
	}


	public int selectCountBoard() {
		return boardMapper.selectCountBoard();
	}


	public List<BoardVO> selectBoardListPage(Criteria criteria) {
		return boardMapper.selectBoardListPage(criteria);
	}
	
}
