<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="tutorDetailURL" value="/trang-chu/info" />
<c:url var="homeURL" value="/trang-chu" />
<c:url var="rateAPI" value="/api/rate" />
<html>
<head>
<title>Thông tin</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="tutor-info">
					<h1>
						<c:out value="${model.fullName}" />
					</h1>
					<p>
						<strong>Bio: </strong>
						${model.bio}
					</p>
					<p>
						<strong>Chuyên môn: </strong>
						${model.specialization}
					</p>
					<p>
						<strong>Trình độ học vấn: </strong>
						${model.education}
					</p>
					<p>
						<strong>Kinh Nghiệm: </strong>
						${model.experience}
					</p>
					<p>
						<strong>Giá thuê: </strong>
						${model.hourlyRate}
					</p>
					<p>
						<strong>Thời gian: </strong>
						${model.availableTimes}
					</p>
				</div>
				<div class="comment-section">
					<form:form class="form-horizontal comment-form" role="form" id="formSubmit" modelAttribute="rateSubmit">
						<h3>Thêm Bình Luận</h3>
						<form:hidden path="tutorID" id="tutorId" value="${model.id}"/>
						<form:input path="comment" placeholder="Nhập bình luận của bạn..." rows="5" cols="50"/>
						<div class="rating-stars">
							<span>Đánh giá: </span>
							<div class="star-rating">
								<form:radiobutton id="star5" name="rating" value="5" label="★"
									path="rate" />
								
								<form:radiobutton id="star4" name="rating" value="4" label="★"
									path="rate" />
								
								<form:radiobutton id="star3" name="rating" value="3" label="★"
									path="rate" />
								
								<form:radiobutton id="star2" name="rating" value="2" label="★"
									path="rate" />
								
								<form:radiobutton id="star1" name="rating" value="1" label="★"
									path="rate" />
							
							</div>
						</div>
						<button type="submit" class="submit-button" id="submit">Gửi
							Bình Luận</button>
					</form:form>
				</div>
				<div class="comments-container col-8">
					<c:forEach var="item" items="${rate.listResult}">
						<div class="comment-box">
							<div class="comment-header">
								<span class="comment-author">${item.fullName}</span> <span
									class="comment-date">${item.createdDate}</span>
							</div>
							<div class="stars">
								<c:forEach begin="1" end="5" var="i">
									<span class="${i <= item.rate ? 'filled' : 'empty'}">★</span>
								</c:forEach>
							</div>
							<div class="comment-content">${item.comment}</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<script>
		$('#submit').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			addRate(data);
		});

		function addRate(data) {
			$.ajax({
				url : '${rateAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${tutorDetailURL}?id=${model.id}";
				},
				error : function(error) {
					window.location.href = "${tutorDetailURL}?id=${model.id}";
				}
			});
		}
	</script>
</body>
</html>
