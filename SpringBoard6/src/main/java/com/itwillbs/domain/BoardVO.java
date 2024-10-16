package com.itwillbs.domain;
/*
 * 	tbl_board 테이블의 정보를 저장하는 객체
 */

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private Timestamp regdate;
	
}
