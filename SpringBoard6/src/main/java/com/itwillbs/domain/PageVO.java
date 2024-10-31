package com.itwillbs.domain;
/*
 *  페이징처리 1) 게시판의 글을 원하는 만큼 가져오기
 *  		   2) 페이지 하단에 페이징블럭
 *  		   3) 본문,수정,삭제 동작후 다시 원래 페이지로 이동
 *  
 *  페이징 처리 정보를 계산하는 객체(1,2단계)
 *  
 *  - 시작페이지 번호 startPage
 *  - 끝페이지 번호 endPage
 *  - 전체 게시판 데이터 개수 totalCount
 *  - 이전페이지 prev
 *  - 다음페이지 next
 *  
 *  ex) 한번에 10개씩 출력, 총 글의 개수 122개 / 페이지블럭 개수 10개씩
 *  
 *  	*page = 3일때, startPage = 1, endPage = 10, next = True, prev = False
 *  	*page = 10일때, startPage = 1, endPage = 10, next = True, prev = False
 *  	*page = 12일때, startPage = 11, endPage = 20 -> 13, next = False, prev = True
 *  
 */

public class PageVO {
	private int startPage;						// 페이지블럭 시작번호
	private int endPage;						// 페이지블럭 끝번호
	private int totalCount;						// 총 글의 개수
	
	private boolean prev;						// 이전 버튼 사용여부
	private boolean next;						// 다음 버튼 사용여부
	
	private int pageBlock = 10;					// 페이지 블럭의 크기 1 ... 10, 11 ... 20
	
	// private int page; 							// 페이지 번호
	// private int pageSize;						// 페이지 출력개수
	private Criteria cri;
	
	
	
	
	
	/* setter */
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		// 페이징처리에 필요한 정보를 계산
		calcData();
	}
	
	private void calcData() {
		System.out.println("( •̀ ω •́ )✧ 페이징 처리 정보 계산 ---------- ");
		// endPage 계산 = (int)올림(페이지번호 / 페이지블럭) * 페이지블럭
		// 1 -10 1/10 = 0.xx
		// 11 -20 11/20 = 1.xx
		// 21 -30 21/30 = 2.xx
		endPage = (int)(Math.ceil(cri.getPage()/(double)pageBlock) * pageBlock); 
		
		// startPage 계산 = endPage - pageBlock + 1
		// 1 -10 10 - 10 = 0 + 1 = 1
		// 11 -20 20 - 10 = 10 + 1 = 11
		// 21 -30 30 - 10 = 20 + 1 = 21
		startPage = (endPage - pageBlock) + 1;
		
		// endPage 재계산(페이지 개수가 다른 경우)
		// 게시판 글 개수에 따른 필요한 페이지 수를 계산
		// int tmpEndPage = totalCount / cri.getPageSize() + (totalCount%cri.getPageSize() == 0 ? 0 : 1);
		int tmpEndPage = (int)Math.ceil((double)totalCount / cri.getPageSize());
		
		if(tmpEndPage < endPage) { // 게시판 페이지 수가 모자란 경우 endPage 변경
			endPage = tmpEndPage;
		}
		
		// prev : 이전 버튼 => 시작 페이지 번호 > 페이지 블럭 => true
		// 					   시작 페이지 번호가 1인지 체크
		// prev = startPage == 1 ? false : true;
		prev = startPage != 1;
		
		// next : 다음 버튼 => 끝 페이지 번호 * 페이지 사이즈 < totalCount
		// next = (endPage * cri.getPageSize()) >= totalCount? false : true;
		next = (endPage * cri.getPageSize()) < totalCount;
		
		System.out.println("( •̀ ω •́ )✧ 페이징 처리 정보 계산 ---------- ");
	}
	
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	/* getter */
	public Criteria getCri() {
		return cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	
	
	@Override
	public String toString() {
		return "PageVO [startPage=" + startPage + ", endPage=" + endPage + ", totalCount=" + totalCount + ", prev="
				+ prev + ", next=" + next + ", pageBlock=" + pageBlock + ", cri=" + cri + "]";
	}
	
	
	
	
	
	

}
