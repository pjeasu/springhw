package edu.bit.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;

public interface BoardMapper { //���Ӱ��� : persistence layer
	@Select("select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc")
	public List<BoardVO> selectBoardList();
	
	@Insert("insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, #{bName}, #{bTitle}, #{bContent}, 0, mvc_board_seq.currval, 0, 0 )")
	public void insertBoard(BoardVO param);
	
	@Select("select * from mvc_board where bId = #{bId}")
	public BoardVO selectBoardOne(String bId);
	
	@Select("select count(*) from mvc_board")
	public int selectCountBoard();
	//�˾Ƽ� ���� int ������ ���� ��ȯ���ش�
	
	@Update("update mvc_board set bStep = bStep + 1 where bGroup = #{bGroup} and bStep > #{bStep}")
	public void updateShape(BoardVO boardVO);
	//��ü�� ���ڸ� �޾ƿö��� @Param ������� �ʾƵ���
	
	@Insert("insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, #{bName}, #{bTitle},#{bContent}, #{bGroup}, #{bStep}+1, #{bIndent}+1)")
	public void insertReply(BoardVO boardVO);

	@Update("update mvc_board set bName = #{bName}, bTitle = #{bTitle}, bContent = #{bContent} where bId = #{bId}")
	public void updateBoard(BoardVO boardVO);
	
	@Delete("delete from mvc_board WHERE bId = #{bId}")
	public void delete(BoardVO boardVO);
	
	@Update("update mvc_board set bHit = bHit+1 where bId = #{bId}")
	public void upHit(String bId);

	public List<BoardVO> selectBoardListPage(Criteria criteria);
}
