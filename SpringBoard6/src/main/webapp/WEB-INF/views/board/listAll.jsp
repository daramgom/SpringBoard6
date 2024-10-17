<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div class="box">
	<div class="box-header">
		<h3 class="box-title">Simple Full Width Table</h3>
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

<%@ include file="../include/footer.jsp"%>



