<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${not empty cookie.remember_user}">
	<c:set value="checked" var="checked"/>
</c:if>

<!-- Login Modal -->
<div class="modal fade" id="loginModal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content card-outline card-primary">

			<div class="modal-header justify-content-between">
				<h4 class="modal-title">로그인</h4>
			</div>

			<div class="modal-body">

				<div class="input-group mb-3">
					<input type="text" class="form-control" name="user_id"
						autocomplete="off" placeholder="아이디"
						value="${cookie.remember_user.value }">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-user"></span>
						</div>
					</div>
				</div>

				<div class="input-group mb-3">
					<input type="password" class="form-control" name="user_password"
						placeholder="비밀번호">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-8">
						<div class="icheck-primary">
							<input type="checkbox" name="remember_id" id="remember" value="true" ${checked}>
							<label for="remember"> 아이디 저장 </label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-4">
						<button onclick="fn_login()"
							class="btn btn-primary btn-block">로그인</button>
					</div>
					<!-- /.col -->
					<div class="col-12 mt-2 text-center text-danger text-bold" id="loginMsg"></div>
				</div>

			</div><!-- /.modal-body -->

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>