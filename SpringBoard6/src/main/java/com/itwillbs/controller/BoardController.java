package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
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
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.debug(" /register.jsp -> registerPOST() 호출 ");
		
		// 한글처리 인코딩 => 생략(web.xml 필터)
		// 전달된 정보(파라메터)를 저장
		logger.debug("vo : "+vo);
		
		// 서비스객체 -> DAO객체 호출
		bService.register(vo);
		logger.debug(" 글쓰기 완료! ");
		
		// view 페이지에 정보 전달(1회성 데이터)
		rttr.addFlashAttribute("result", "INSERTOK");
		
		// 페이지 이동(list)
		
		return "redirect:/board/listPage";
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
	
	// 게시판 리스트(Page) - GET
	@RequestMapping(value = "/listPage",method = RequestMethod.GET)
	public void listPageGET(Criteria cri, Model model) throws Exception{
		logger.debug(" /listPage -> listPageGET(Model model) 호출 ");
		
		// Criteria cri = new Criteria();
		
		// 페이징처리 블럭정보 계산&정보 저장
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(bService.getTotalCount()); // 임시로 총 개수를 직접 작성
		
		// 서비스 -> DAO 메서드 호출 (출력할 정보 가져오기)
		List<BoardVO> boardList = bService.listPage(cri);
		logger.debug(" 리스트 사이즈 : "+boardList.size());
		
		// Model 객체를 사용해서 정보를 저장
		model.addAttribute(boardList); 
		model.addAttribute("boardList", boardList);
		
		// 페이징처리 정보 전달
		model.addAttribute("pageVO", pageVO);
		
		// 연결된 뷰페이지에서 출력		
		logger.debug(" ( •̀ ω •́ )✧ /board/listPage.jsp 페이지 이동 ");
		
		
	}
	
	
	// 글 본문내용 보기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readGET(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ /board/read -> readGET() 호출 ");
		logger.debug(" ( •̀ ω •́ )✧ 전달정보 저장(파라메터) ");
		logger.debug(" ( •̀ ω •́ )✧ bno : "+bno);
		
		logger.debug(" ( •̀ ω •́ )✧ 디비에 글 조회수 1증가 ");
		bService.updateViewcnt(bno);
		
		logger.debug(" ( •̀ ω •́ )✧ 디비에 글 내용 정보를 가져와서 출력(전달) ");
		BoardVO vo = bService.read(bno);
		
		// 전달할 정보를 저장
		model.addAttribute(vo); // 전달이름 boardVO
		
		// 페이지 이동
		return "/board/read";
		
	}
	
	
	// 게시판 글 수정하기
	@GetMapping(value = "/modify")
	public void modifyGET(@RequestParam int bno, Model model) throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ /board/modify -> modifyGET() 호출 ");
		
		// 전달받을 정보(bno) 저장
		logger.debug(" ( •̀ ω •́ )✧ bno : "+bno);
		// 서비스 -> DAO : 게시판 글 정보를 가져오기
		// BoardVO resultVO = bService.read(bno); 
		// 가져온 글 정보를 뷰페이지에 출력
		// model.addAttribute("resultVO", resultVO);
		model.addAttribute(bService.read(bno));
		
	}
	
	
	// 게시판 글 수정하기
	@PostMapping(value = "/modify")
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ /board/modify -> modifyPOST() 호출 ");
		
		// 전달받을 정보(파라메터) 저장(bno, title, content, writer)
		logger.debug(" ( •̀ ω •́ )✧ vo : "+vo);
		
		// 서비스 -> DAO : 글정보 수정하기 동작
		bService.modify(vo);
		
		// 다시 글 리스트 페이지로 이동
		// view 페이지에 정보 전달(1회성 데이터)
		rttr.addFlashAttribute("result", "MODIFYOK");
		
		return "redirect:/board/listPage";
	}
	
	
	// 게시판 글 삭제 - POST
	@PostMapping(value = "/remove")
	public String removePOST(@RequestParam int bno, RedirectAttributes rttr) throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ /board/modify -> removePOST() 호출 ");
		
		// 전달정보 저장(bno)
		
		// 서비스 -> DAO : 게시판 글 삭제 동작
		int result = bService.remove(bno);
		if (result == 0) {
			return "redirect:/board/read?bno="+bno;
		}
		rttr.addFlashAttribute("result","DELETEOK");
		return "redirect:/board/listPage";
	}
	
	
	
	
	
	
	
}// controller
