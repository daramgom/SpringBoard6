package com.itwillbs.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 예외처리 수행하는 클래스 
 * DAO, Service, Controller에서 던져놓은 예외를 받는(처리하는) 객체
 */
// @ControllerAdvice: 해당 클래스가 컨트롤러에서 발생하는 모든 예외를 처리하는 객체정보 등록

@ControllerAdvice
public class CommonExceptionAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	// 예외처리 동작 구현
	
	/*
	  try {
	  
	  } 
	  catch(NullPointerException e) {
	  
	  }
	  catch(Exception e) {
	  
	  }
	*/
	/*
	@ExceptionHandler(NullPointerException.class)
	public void nullEx() {
		
	}
	*/
	
	// 컨트롤러의 동작을 비슷하게 수행 가능하다
	// 리턴 String 일 때 특정문자.jsp 뷰페이지 연결
	
	// 모든 예외 발생시 실행하는 메서드
	@ExceptionHandler(Exception.class)
	public String commonEx(Exception e, HttpServletRequest req) {
		logger.debug(" ( •̀ ω •́ )✧ 예외 발생! ");
		logger.debug(" ( •̀ ω •́ )✧ e : "+e);
		logger.debug(" ( •̀ ω •́ )✧ 메세지 : "+e.getMessage());
		logger.debug(" ( •̀ ω •́ )✧ URL : "+req.getRequestURI());
		
		return "error";
	}
	
}// class
