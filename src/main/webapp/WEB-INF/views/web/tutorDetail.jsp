<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="infoURL" value="/info"/>
<c:url var="homeURL" value="/trang-chu"/>
<c:url var="tutorAPI" value="/api/tutor"/>
<html>
<head>
<title>Thông tin</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="page-content" modelAttribute="model">
			<div class="row">
				<div class="col-8" style="text-align: width: 50%; margin: 0 auto;">
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">
  							${message}
						</div>
					</c:if>
					
					<div class="row align-items-center my-5">
						<div class="col-lg-12">
							<h1 class="font-weight-light"><c:out value="${model.fullName}"/></h1>
							<p><strong>Bio: </strong><c:out value="${model.bio}"/></p>
							<p><strong>Chuyên môn: </strong><c:out value="${model.specialization}"/></p>
							<p><strong>Trình độ học vấn: </strong><c:out value="${model.education}"/></p>
							<p><strong>Kinh Nghiệm: </strong><c:out value="${model.experience}"/></p>
							<p><strong>Giá thuê: </strong><c:out value="${model.hourlyRate}"/></p>
							<p><strong>Thời gian: </strong><c:out value="${model.availableTimes}"/></p>
							<a class="btn btn-primary" href="#">Call to Action!</a>
						</div>
					</div>
					
					 <div>
				        <h2>Bình luận</h2>
				        <form action="CommentServlet" method="post">
					        <label for="comment">Viết bình luận:</label>
					        <input type="text" id="comment" name="comment" required>
					        <button type="submit">Gửi</button>
				    	</form>
				        <ul>
				            <c:forEach var="comment" items="${comments}">
				                <li>${comment}</li>
				            </c:forEach>
				        </ul>
				    </div>
				</div>
			</div>
		</div>
	</div>
</div>	

<script>
	$('#btnAddOrUpdateTutor').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
	    $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
	    var id = $('#tutorId').val();
	    if (id == "") {
	    	addTutor(data);
	    } else {
	    	updateTutor(data);
	    }
	});
	
	function addTutor(data) {
		$.ajax({
            url: '${tutorAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${infoURL}?userId="+result.userID+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${infoURL}?userId="+result.userID+"&message=error_system";
            }
        });
	}
	
	function updateTutor(data) {
		$.ajax({
            url: '${tutorAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${infoURL}?userId="+result.userID+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${infoURL}?userId="+result.userID+"&message=error_system";
            }
        });
	}
	
	$('#back').click(function (e) {
	    e.preventDefault();
	    //window.location.href = "${homeURL}?page=1&limit=5";
	    window.location.href = "${homeURL}";
	});
</script>
</body>
</html>
