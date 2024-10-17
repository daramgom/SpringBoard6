<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
result : ${result }
<div class="box">
	<div class="box-header">
		<h3 class="box-title">아이티윌 자유게시판</h3>
		<div class="box-tools">
			<ul class="pagination pagination-sm no-margin pull-right">
				<li><a href="#">«</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">»</a></li>
			</ul>
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
						<td>${vo.title }</td>
						<td>${vo.writer }</td>
						<td><span class="badge bg-red">${vo.viewcnt }</span></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
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
	
	
</script>

<%@ include file="../include/footer.jsp"%>




