<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="col-12">
<!-- Card -->
<div class="card">

	<!-- <!-- Card-header -->
	<div class="card-header">
		<div class="row">
			<div class="form-group col-md-2 container">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text">
							<i class="far fa-calendar-alt"></i>
						</span>
					</div>
					<input type="text" name="year" class="form-control text-center text-bold" autocomplete="off">
				</div>
			</div>
		</div>
	</div>
	<!-- /.card-header -->

	<!-- Card-body -->
	<div class="card-body">
		<div class="row">
			<c:choose>
				<c:when test="${fn:length(imageList) > 0}">
					<c:forEach var="row_image" varStatus="status" items="${imageList}">
						<div class="col-sm-3 text-center">
							<a href="/ssh/file/download?file_type=image&file_idx=${row_image.file_idx}"
								target="_blank">
								<img src="/ssh/file/download?file_type=image&file_idx=${row_image.file_idx}"
									class="img-fluid mb-2" />
								<h2>${row_image.file_name}</h2>
							</a>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>사진 파일이 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- /.card-body -->

</div>
<!-- /.card -->
</div>