<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Insert User Modal -->
<div class="modal fade" id="updateUserModal">
	<div class="modal-dialog modal-default">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title">계정 비밀번호 수정&nbsp;</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>

			<div class="modal-body">
			
				<!-- 1. user_idx -->
				<input type="hidden" name="user_idx">

				<!-- 2. 아이디 - 수정 불가 -->
				<div class="row">
					<div class="form-group mb-2 col-sm-8 container">
						<label class="col-form-label">아이디</label>
						<div class="input-group">
							<input type="text" class="form-control bg-white" name="user_id"
								autocomplete="off" placeholder="아이디" readonly="readonly">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>
						<div class="" name="msg_user_id"></div>
					</div>
				</div>
				
				<!-- 3. 관리자 비밀번호 -->
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

				<!-- 4. 비밀번호 -->
				<div class="row">
					<div class="form-group mb-2 col-sm-8 container">
						<label class="col-form-label">새 비밀번호</label>
						<div class="input-group">
							<input type="password" class="form-control" name="user_password"
								placeholder="새 비밀번호" onkeyup="fn_checkUserPassword('update')">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 5. 비밀번호 확인 -->
				<div class="row">
					<div class="input-group col-sm-8 container">
						<input type="password" class="form-control"
							name="check_user_password" placeholder="새 비밀번호 확인"
							onkeyup="fn_checkUserPassword('update')">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
						<div class="container" name="msg_user_password"></div>
					</div>
				</div>

			</div>

			<div class="modal-footer text-right">
				<button type="button" class="btn btn-primary" onclick="fn_updateUser()">수정</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>