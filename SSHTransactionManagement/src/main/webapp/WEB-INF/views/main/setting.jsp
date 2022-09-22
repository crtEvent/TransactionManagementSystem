<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Main content : Setting Page -->
<section class="content">
	<div class="container-fluid">

		<!-- Main Head Row -->
		<div class="row">
			<div class="col-12">
				<!-- Card -->
				<div class="card my-2">
					<!-- Card-body -->
					<div class="card-body py-2">

						<div class="row">
							<span class="align-middle h3 text-bold mb-0">&nbsp;설 정</span>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.main head row -->

		<!-- Main Body Row -->
		<div class="row">

			<section class="col-lg-4">

				<!-- 1. Card - 파일 저장소 위치 변경 -->
				<div class="card my-2">
					<div class="card-header text-bold">파일 저장소 위치 변경</div>
					<!-- Card-body -->
					<div class="card-body py-2">

						<div class="form-group">
							<div class="input-group mb-1">
								<input type="text" class="form-control" name="root_path"
									value="${root_path}">
								<div class="input-group-append" onclick="fn_updatePath()">
									<span class="input-group-text">수 정</span>
								</div>
							</div>
							<div class="text-center">예시) C:\ssh</div>
						</div>

					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->

				<!-- 2. Card - DB 백업 -->
				<div class="card my-2">
					<div class="card-header text-bold">Database 백업</div>
					<!-- Card-body -->
					<div class="card-body py-2 text-center">

						<a class="btn btn-app" onclick="fn_backupDB()"> <i
							class="fas fa-save"></i> DB 백업
						</a>
						<div>저장소/backup 폴더에 저장됩니다.</div>

					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->

			</section>
			<!-- /.col-lg-4 -->

			<section class="col-lg-4">
				<!-- 1. 계정(유저) 관리 -->
				<div class="card my-2">
					<div class="card-header text-bold py-2">
						<span class="align-middle">계정 관리</span>
						<button class="btn btn-info btn-sm text-bold float-right"
							onclick="fn_openInsertUserModal()">[+] 계정 추가</button>
					</div>
					<!-- Card-body -->
					<div class="card-body py-2 text-center">

						<table id="userTable"
							class="table table-sm table-bordered table-hover">
							<thead>
								<tr>
									<th style="width: 10%;">No.</th>
									<th style="width: 70%;">계정</th>
									<th style="width: 20%;">수정/삭제</th>
								</tr>
							</thead>
							<tbody>

								<c:choose>
									<c:when test="${fn:length(userList) > 0}">
										<c:forEach var="row_item" varStatus="status"
											items="${userList}">
											<tr>
												<td>${status.index + 1}</td>
												<td>${row_item.user_id}</td>
												<td>
													<button class="btn btn-primary btn-xs" title="수정"
														onclick="fn_openUpdateUserModal('${row_item.user_idx}', '${row_item.user_id}')">
														<i class="fas fa-pencil-alt"></i>
													</button>
													<c:if test="${row_item.user_idx ne 1}">
														<button class="btn btn-danger btn-xs" title="삭제"
															onclick="fn_openDeleteUserModal('${row_item.user_idx}')">
															<i class="fas fa-trash-alt"></i>
														</button>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</section>
			<!-- /.col-lg-4 -->

			<section class="col-lg-4"></section>
			<!-- /.col-lg-4 -->

		</div>
		<!-- /.main body row -->

	</div>
</section>
<!-- /.main content : setting page -->

<%@include file="../modal/modal_insert_user.jsp"%>
<%@include file="../modal/modal_update_user.jsp"%>
<%@include file="../modal/modal_delete_user.jsp"%>