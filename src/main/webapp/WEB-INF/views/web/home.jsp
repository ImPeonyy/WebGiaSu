<%@ page import="com.spring.mvc.util.SecurityUtils" %>
<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<title>Trang chủ</title>

</head>

<body>

	<!-- Page Content -->
	<div class="container">
	<form action="<c:url value='/trang-chu'/>" id="formSubmit" method="get">
		<div class="card text-white bg-secondary my-5 py-4 text-center">
			<div class="card-body">
				<form action="#" method="get" style="margin-top:10px; margin-left:10px;">
					<input type="text" name="searchTerm" placeholder="Search tutors">
					<button type="submit">Search</button>
				</form>
			</div>
		</div>
				<div class="row">
				<c:forEach var="item" items="${model.listResult}">
					<div class="col-md-4 mb-5">
						<div class="card h-100">
							<div class="card-body">
								<h2 class="card-title">${item.fullName}</h2>
								<p class="card-text"><strong>Chuyên môn: </strong>${item.specialization}</p>
								<p class="card-text"><strong>Thời gian: </strong>${item.availableTimes}</p>
								<p class="card-text"><strong>Giá thuê: </strong>${item.hourlyRate}</p>
							</div>
							<div class="card-footer">
								<a href="<c:url value='/trang-chu/info'>
											<c:param name="id" value="${item.id}"/>															
										</c:url>" class="btn btn-primary btn-sm">More Info</a>
							</div>
						</div>						
					</div>
				</c:forEach>
				</div>		
		</form>		
		</div>
		
</body>

</html>