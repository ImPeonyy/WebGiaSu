<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="tutorURL" value="/quan-tri/gia-su/danh-sach"/>
<c:url var="editTutorURL" value="/quan-tri/gia-su/chinh-sua"/>
<c:url var="tutorAPI" value="/api/tutor"/>
<html>
<head>
<title>Chỉnh sửa gia sư</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
				</li>

				<li class="active">Create/Edit</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">
  							${message}
						</div>
					</c:if>
					<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">User ID: </label>
								<div class="col-sm-9">
									<form:input path="userID" cssClass="col-xs-10 col-sm-5" />
								</div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Full Name: </label>
								<div class="col-sm-9">
									<form:input path="fullName" cssClass="col-xs-10 col-sm-5" disabled="true"/>
								</div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Specialization: </label>
								<div class="col-sm-9">
									<form:input path="specialization" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group">
							  <label for="education" class="col-sm-3 control-label no-padding-right">Education:</label>
							  <div class="col-sm-9">
							  	 <form:select path="education" id="education">
							  	 	<form:option value="" 			label="-- Select Education --"/>
							  	 	<form:option value="THPT" 		label="THPT"/>
							  	 	<form:option value="Cao Đẳng" 	label="Cao Đẳng"/>
							  	 	<form:option value="Đại Học"	label="Đại Học"/>
							  	 	<form:option value="Giáo Sư" 	label="Giáo Sư"/>
							  	 	<form:option value="Tiến Sĩ" 	label="Tiến Sĩ"/>
							  	 </form:select>
							  </div>
						</div>
						<div class="form-group">
						  	<label for="content" class="col-sm-3 control-label no-padding-right">Experience: </label>
						  	<div class="col-sm-9">
						  		<form:input path="experience" rows="5" cols="10" cssClass="form-control" id="content"/>
						  	</div>
						</div>
						<div class="form-group">
						  	<label for="content" class="col-sm-3 control-label no-padding-right">Bio: </label>
						  	<div class="col-sm-9">
						  		<form:textarea path="bio" rows="5" cols="10" cssClass="form-control" id="content"/>
						  	</div>
						</div>
						<div class="form-group">
						  	<label for="content" class="col-sm-3 control-label no-padding-right">Hourly Rate: </label>
						  	<div class="col-sm-9">
						  		<form:input path="hourlyRate" rows="5" cols="10" cssClass="form-control" id="content"/>
						  	</div>
						</div>
						<div class="form-group">
						  	<label for="content" class="col-sm-3 control-label no-padding-right">Available Times: </label>
						  	<div class="col-sm-9">
						  		<form:textarea path="availableTimes" rows="5" cols="10" cssClass="form-control" id="content"/>
						  	</div>
						</div>
						<form:hidden path="id" id="tutorId"/>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
											<c:if test="${not empty model.id}">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateTutor">
													<i class="ace-icon fa fa-check bigger-110"></i>
													Update
												</button>
											</c:if>
											<c:if test="${empty model.id}">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateTutor">
													<i class="ace-icon fa fa-check bigger-110"></i>
													Create
												</button>
											</c:if>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset" id="back">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												Cancel
											</button>
							</div>		
						</div>
					</form:form>
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
            	window.location.href = "${editTutorURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${TutorURL}?page=1&limit=5&message=error_system";
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
            	window.location.href = "${editTutorURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${editTutorURL}?id="+result.id+"&message=error_system";
            }
        });
	}
	
	$('#back').click(function (e) {
	    e.preventDefault();
	    window.location.href = "${tutorURL}?page=1&limit=5";
	});
</script>
</body>
</html>
