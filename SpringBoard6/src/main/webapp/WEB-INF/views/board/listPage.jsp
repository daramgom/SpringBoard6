<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
result : ${result } <br>
pageVO : ${pageVO } <br>
<div class="box">
	<div class="box-header">
		<h3 class="box-title">아이티윌 자유게시판</h3>
		<div class="box-tools">
			
		</div>
	</div>

	<div class="box-body no-padding">
		<table class="table">
			<tbody>
				<tr>
					<th>bno</th>
					<th>title</th>
					<th>writer</th>
					<th>Label</th>
				</tr>
				<c:forEach var="vo" items="${boardList }">
					<tr>
						<td>${vo.bno }</td>
						<td>
							<a href="/board/read?bno=${vo.bno }">${vo.title }</a>
						</td>
						<td>${vo.writer }</td>
						<td><span class="badge bg-red">${vo.viewcnt }</span></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<div class="box-footer">
			<ul class="pagination pagination-sm no-margin pull-right">
				<!-- 이전 버튼 -->
				<c:if test="${pageVO.prev }">
					<li><a href="/board/listPage?page=${pageVO.startPage - 1 }">«</a></li>
				</c:if>
				<c:forEach begin="${pageVO.startPage }" end="${pageVO.endPage }" var="i">
					<li ${pageVO.cri.page == i? 'class="active"':'' }><a href="/board/listPage?page=${i }">${i }</a></li>
				</c:forEach>
				<!-- 다음 버튼 -->
				<c:if test="${pageVO.next }">
					<li><a href="/board/listPage?page=${pageVO.endPage + 1 }">»</a></li>
				</c:if>
			</ul>
	</div>
</div>


<script type="text/javascript">
	
	/* JSP 페이지의 실행 순서
		JSP(JAVA) -> JSTL/EL -> HTML -> JS / JQuery
	*/
	/* JS에서 el표현식의 데이터를 사용가능 */
	// var result = ${result }; => var result = INSERTOK;
	var result = '${result }';
	
	if(result == "INSERTOK") {
		alert(" 정상적으로 글쓰기 동작 완료! ");
	}
	if(result == "MODIFYOK") {
		alert(" 정상적으로 글수정 동작 완료! ");
	}
	if(result == "DELETEOK") {
		alert(" 정상적으로 글삭제 동작 완료! ");
	}
	
</script>

<%@ include file="../include/footer.jsp"%>




