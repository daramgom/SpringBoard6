package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {
	
	@Inject
	private DataSource ds;
	@Inject
	private BoardDAO bdao;
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	//@Test
	public void 디비연결테스트() {
		System.out.println("디비 연결 : "+ds);
	}
	
	@Test
	public void 페이징처리테스트() throws Exception {
		// List<BoardVO> boardList = bdao.listPage(2);
		// List<BoardVO> boardList = bdao.listPage(2);
		
		// 페이징 처리 정보 저장객체 생성
		Criteria cri = new Criteria();
		List<BoardVO> boardList = bdao.listPage(cri);
		
		// 0, 10 1page (0번~9번)/ 10,10 2page (10번~19번)/ 20,20 3page(20번~29번)/ 30,30 4page(30번~39번)
		for(BoardVO vo:boardList) {
			logger.debug(" ( •̀ ω •́ )✧ "+vo.getBno()+"/"+vo.getTitle());
		}
		
	}
	
	
}
