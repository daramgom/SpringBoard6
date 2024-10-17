package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	// 디비연결정보를 처리하는 객체를 주입사용
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger 
	   = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	private static final String NAMESPACE 
	                   ="com.itwillbs.mapper.BoardMapper.";
	
	
	@Override
	public void createBoard(BoardVO vo) throws Exception {
		logger.debug(" createBoard(BoardVO vo) 호출 ");
		logger.debug(" 디비 연결 - SQL 호출 - 실행 ");
		sqlSession.insert(NAMESPACE + "insertBoard",vo);
	}


	@Override
	public List<BoardVO> listAll() throws Exception {
		logger.debug(" listAll() 호출 ");
		return sqlSession.selectList(NAMESPACE + "listAll");
	}


	@Override
	public BoardVO getBoard(int bno) throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ getBoard(int bno) 호출 ");
		return sqlSession.selectOne(NAMESPACE + "getBoard", bno);
	}
	
	

}
