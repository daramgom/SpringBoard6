package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	// 글쓰기
	public void register(BoardVO vo) throws Exception;
	
	// 글 리스트 조회
	public List<BoardVO> listAll() throws Exception; 
}
