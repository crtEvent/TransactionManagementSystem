<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Insert Company Modal -->
<div class="modal fade" id="deleteUserModal">
	<div class="modal-dialog modal-default">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title">계정 삭제하기</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>

			<div class="modal-body text-center">
				
				<div>삭제된 계정은 복구할 수 없습니다.</div>
				<div>해당 계정을 삭제하시겠습니까?</div>
				
				<br>
				
				<!-- 1. user_idx -->
				<input type="hidden" name="user_idx">
				
				<!-- 2. 관리자 비밀번호 -->
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
			
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">닫기</button>
				<button type="button" class="btn btn-primary" onclick="fn_deleteUser()">삭제하기</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>