<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="loginURL" value="/dang-nhap"/>
<c:url var="regisURL" value="/dang-ky"/>
<c:url var="regisAPI" value="/api/register"/>
<html>
<head>
<title>Đăng Ký</title>
</head>
<body>
<div class="container">
	<h1 style="margin-top: 100px; text-align: center;">Đăng Ký</h1>
			<div class="login-form">
				<div class="main-div">
					<c:if test="${message == true}">
						<div class="alert alert-danger">	
							Username is exist
						</div>
					</c:if>
					<form:form role="form" id="formLogin" modelAttribute="model">
						<div class="form-group">
							<form:input path="username" cssClass="form-control" placeholder="Tên đăng nhập"/>
						</div>
						<div class="form-group">
						  	<form:input type="password" path="password" cssClass="form-control" placeholder="Mật Khẩu"/>
						</div>
						<div class="form-group">
						  	<form:input path="fullName" cssClass="form-control" placeholder="Tên"/>
						</div>
							<button class="btn btn-primary" type="button" id="registerBtn">Đăng Ký</button>
					</form:form>
				</div>
			</div>
</div>

<script>
	$('#registerBtn').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formLogin').serializeArray();
	    $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
	    	register(data);
	});
	
	function register(data) {
		$.ajax({
            url: '${regisAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${loginURL}";
            },
            error: function (error) {
            	window.location.href = "${regisURL}";
            }
        });
	}
	
</script>
</body>
</html>
