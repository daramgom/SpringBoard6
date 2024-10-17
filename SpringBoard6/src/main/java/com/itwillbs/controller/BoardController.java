package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*") // /board/~ 로 시작하는 모든주소를 처리하는 컨트롤러
public class BoardController {
	
	// BoardService 객체 주입
	@Inject
	private BoardService bService;
	
	private static final Logger logger 
	     = LoggerFactory.getLogger(BoardController.class);
	
	
	// http://localhost:8088/controller/register
	// http://localhost:8088/register
	// http://localhost:8088/board/register
	// 글쓰기 - 게시판에 작성될 내용을 입력받는 동작 - GET
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String registGET() throws Exception {
		logger.debug(" /register ->  registerGET() 호출 ");
		logger.debug(" /views/board/register.jsp 페이지 매핑 ");		
		
		return "/board/register";
	}
	
	// 글쓰기 - 입력받은 정보를 처리하는 동작 - POST
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String registPOST(BoardVO vo) throws Exception {
		logger.debug(" /register.jsp -> registerPOST() 호출 ");
		
		// 한글처리 인코딩 => 생략(web.xml 필터)
		// 전달된 정보(파라메터)를 저장
		logger.debug("vo : "+vo);
		
		// 서비스객체 -> DAO객체 호출
		bService.register(vo);
		logger.debug(" 글쓰기 완료! ");
		// 페이지 이동(list)
		
		return "redirect:/board/listAll";
	}
	
	// 게시판 리스트 - GET
	@RequestMapping(value = "/listAll",method = RequestMethod.GET)
	public void listAllGET(Model model) throws Exception{
		logger.debug(" /listAll -> listAllGET() 호출 ");
		
		// 서비스 -> DAO 메서드 호출 (출력할 정보 가져오기)
		List<BoardVO> boardList = bService.listAll();
		logger.debug(" 리스트 사이즈 : "+boardList.size());
		
		// Model 객체를 사용해서 정보를 저장
		model.addAttribute(boardList); 
		model.addAttribute("boardList", boardList);
		
		// 연결된 뷰페이지에서 출력		
		logger.debug(" ( •̀ ω •́ )✧ /board/listAll.jsp 페이지 이동 ");
		
		
	}
	
	
	
	
	
	
}// controller
