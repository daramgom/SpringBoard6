<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>


<h1>/board/read.jsp</h1>

${boardVO }

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 글 본문내용</h3>
	</div>


	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">제 목</label>
			<input type="text"
				name="title" class="form-control" id="exampleInputEmail1"
				value="${boardVO.title }" disabled>
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">작성자</label>
			<input type="text"
				name="writer" class="form-control" id="exampleInputEmail1"
				value="${boardVO.writer }" disabled>
		</div>

		<div class="form-group">
			<label>내 용</label>
			<textarea class="form-control" name="content" rows="3" 
				    disabled>${boardVO.content }</textarea>
		</div>


	</div>

	<div class="box-footer">
		<button type="submit" class="btn btn-primary">글수정</button>
		<button type="submit" class="btn btn-danger">글삭제</button>
		<button type="submit" class="btn btn-default">글목록</button>
	</div>
</div>

<!-- /include/header.jsp 페이지에 추가 코드 있음 -->
<!-- jquery 사용해서 페이지 이동 버튼 활성화 -->
<script type="text/javascript">
	/* $(function(){}); */
	$(document).ready(function(){
		
		// 목록버튼 클릭 시 게시판 목록으로 이동
		$(".btn-default").click(function(){
			location.href="/board/listAll";
		});
		
		
		
		
	});//DOM준비
	
</script>



<%@ include file="../include/footer.jsp"%>