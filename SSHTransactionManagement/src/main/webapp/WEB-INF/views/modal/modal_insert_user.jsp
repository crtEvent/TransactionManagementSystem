<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Insert User Modal -->
<div class="modal fade" id="insertUserModal">
	<div class="modal-dialog modal-default">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title">계정 등록&nbsp;</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>

			<div class="modal-body">

				<!-- 1. 아이디 -->
				<div class="row">
					<div class="form-group mb-2 col-sm-8 container">
						<label class="col-form-label">아이디</label>
						<div class="input-group">
							<input type="text" class="form-control" name="user_id"
								autocomplete="off" placeholder="아이디"
								onkeyup="fn_checkUserId('insert')">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>
						<div class="" name="msg_user_id"></div>
					</div>
				</div>

				<!-- 2. 비밀번호 -->
				<div class="row">
					<div class="form-group mb-2 col-sm-8 container">
						<label class="col-form-label">비밀번호</label>
						<div class="input-group">
							<input type="password" class="form-control" name="user_password"
								placeholder="비밀번호" onkeyup="fn_checkUserPassword('insert')">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 3. 비밀번호 확인 -->
				<div class="row">
					<div class="input-group col-sm-8 container">
						<input type="password" class="form-control"
							name="check_user_password" placeholder="비밀번호 확인"
							onkeyup="fn_checkUserPassword('insert')">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
						<div class="container" name="msg_user_password"></div>
					</div>
				</div>
				
				<!-- 4. 관리자 비밀번호 -->
				<div class="row">
					<div class="form-group mb-2 col-sm-8 container">
						<label class="col-form-label">관리자 비밀번호</label>
						<div class="input-group">
							<input type="password" class="form-control" name="admin_password"
								placeholder="관리자 비밀번호">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
							<div class="container" name="msg_admin_password"></div>
						</div>
					</div>
				</div>

			</div>

			<div class="modal-footer text-right">
				<button type="button" class="btn btn-primary" onclick="fn_insertUser()">등록</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>