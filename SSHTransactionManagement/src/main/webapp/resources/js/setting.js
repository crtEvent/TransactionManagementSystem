/**
 * 설정 페이지
 *  - DB 백업
 *  - 파일 저장소 위치 설정 
 */
 
 /* 설정 페이지로 이동 */
 function fn_getSettingPage() {
 	
 	$.ajax({
		url: "/ssh/setting/main",
		type: 'post',
		success: function(result){
			$("#contentWrapper").html(result);
		},
		error: function(){
			alert("fn_getSettingPage() 에러");
		}
	});
	
 }
 
 /* 파일 저장소 경로 변경 */
 function fn_updatePath() {
 
 	$.ajax({
		url: "/ssh/setting/update-path",
		type: 'post',
		data: {path : $('input[name=root_path]').val()}, 
		success: function(){
			alert("저장소 경로 변경 완료!");
		},
		error: function(){
			alert("fn_updatePath() 에러");
		}
	});
	
 }
 
 /* DB 백업 */
 function fn_backupDB() {
 	
 	$.ajax({
		url: "/ssh/setting/backup-db",
		type: 'post',
		success: function(){
			alert("백업 완료!");
		},
		error: function(){
			alert("fn_backupDB() 에러");
		}
	});
	
 }

 /* 계정 등록 Modal 열기 */
 function fn_openInsertUserModal() {
 	fn_resetUserModal('insert');
 	$('#insertUserModal').modal('show');
 }
 
 /* 계정 수정 Modal 열기 */
 function fn_openUpdateUserModal(user_idx, user_id) {
 	fn_resetUserModal('update');
 	$('#updateUserModal').find('input[name=user_idx]').val(user_idx);
 	$('#updateUserModal').find('input[name=user_id]').val(user_id);
 	$('#updateUserModal').modal('show');
 }
 
 /* 계정 삭제 Modal 열기 */
 function fn_openDeleteUserModal(user_idx) {
 	fn_resetUserModal('delete');
 	$('#deleteUserModal').find('input[name=user_idx]').val(user_idx);
 	$('#deleteUserModal').modal('show');
 }
 
 /* 계정 등록 */
 function fn_insertUser() {

 	if(!fn_checkUserId('insert') || !fn_checkUserPassword('insert') || !checkAdminPassword('insert')) {
 		return;
 	}
 
 	var modal = $('#insertUserModal');
 	var data = {
 			user_id : modal.find('input[name=user_id]').val()
 			, user_password : modal.find('input[name=user_password]').val()
 		};
 		
 	$.ajax({
		url: "/ssh/setting/insert-user",
		type: 'post',
		data: data,
		success: function(){
			modal.modal('hide');
			fn_getSettingPage();
			alert('계정 추가 완료!');
		},
		error: function(){
			alert("fn_insertUser() 에러");
		}
	});
 }
 
 /* 계정 수정 */
 function fn_updateUser() {
 
 	if(!fn_checkUserPassword('update')) {
 		return;
 	}
 	
 	if(!checkAdminPassword('update')) {
 		return;
 	}
 
 	var modal = $('#updateUserModal');
 	var data = {
 			user_idx : modal.find('input[name=user_idx]').val()
 			, user_password : modal.find('input[name=user_password]').val()
 		};
 		
 	$.ajax({
		url: "/ssh/setting/update-user",
		type: 'post',
		data: data,
		success: function(){
			modal.modal('hide');
			fn_getSettingPage();
			alert('계정 수정 완료!');
		},
		error: function(){
			alert("fn_updateUser() 에러");
		}
	});
 	    
 }
 
 /* 계정 삭제 */
 function fn_deleteUser() {
 	
 	if(!confirm('계정을 삭제하시겠습니까?')){
 		return;
 	}
 	
 	if(!checkAdminPassword('delete')) {
 		return;
 	}
 	
 	var modal = $('#deleteUserModal');
 	
 	$.ajax({
		url: "/ssh/setting/delete-user",
		type: 'post',
		data: {user_idx : modal.find('input[name=user_idx]').val()},
		success: function(){
			modal.modal('hide');
			fn_getSettingPage();
			alert('계정 삭제 완료!');
		},
		error: function(){
			alert("fn_deleteUser() 에러");
		}
	});
 }
 
 /* 아이디 검사 - 유효성 검사, 공란, 중복 검사 */
 function fn_checkUserId(insertOrUpdate) {
 	
 	var checkUserId = false;
 	var modal = $('#'+insertOrUpdate+'UserModal');
 	var user_id = modal.find('input[name=user_id]').val();
 	
 	if(user_id == '') {
 		modal.find('div[name=msg_user_id]').text('아이디를 입력하세요.').css('color', 'red');
		return checkUserId;
 	}
 
 	$.ajax({
		url: "/ssh/setting/check-user-exist",
		type: 'post',
		data: {user_id : user_id}, 
		async: false, 
		success: function(result){
			if(!result) {
				modal.find('div[name=msg_user_id]').text('사용 가능한 아이디 입니다.').css('color', 'green');
				checkUserId = true;
			} else {
				modal.find('div[name=msg_user_id]').text('중복된 아이디 입니다.').css('color', 'red');
				checkUserId = false;
			}
		},
		error: function(){
			alert("fn_checkUserIdDuplicate() 에러");
		}
	});
	
	return checkUserId;
 }
 
 /* 비밀번호 검사 */
 function fn_checkUserPassword(insertOrUpdate) {
 	
 	var checkUserPassword = false;
 	var modal = $('#'+insertOrUpdate+'UserModal');
 	var user_password = modal.find('input[name=user_password]').val();
 	var check_user_password = modal.find('input[name=check_user_password]').val();
 	
 	if(user_password == '') {
 		modal.find('div[name=msg_user_password]').text('비밀번호를 입력해 주세요.').css('color', 'red');
 		return checkUserPassword;
 	}
 	
 	if(check_user_password == '') {
 		modal.find('div[name=msg_user_password]').text('비밀번호 확인란을 입력해 주세요.').css('color', 'red');
 		return checkUserPassword;
 	}
 	
 	if(user_password != check_user_password) {
 		modal.find('div[name=msg_user_password]').text('비밀번호가 일치하지 않습니다.').css('color', 'red');
 		return checkUserPassword;
 	}
 	
 	modal.find('div[name=msg_user_password]').text('');
 	checkUserPassword = true;
 	return checkUserPassword;
 	
 }
 
 function checkAdminPassword(insertOrUpdate) {
 	var checkAdminPassword = false;
 	var modal = $('#'+insertOrUpdate+'UserModal');
 	var admin_password = modal.find('input[name=admin_password]').val();
 	
 	$.ajax({
		url: "/ssh/setting/check-admin-password",
		type: 'post',
		data: {admin_password : admin_password}, 
		async: false, 
		success: function(result){
			if(!result) {
				modal.find('div[name=msg_admin_password]').text('관리자 비밀번호가 일치하지 않습니다.').css('color', 'red');
				checkAdminPassword = false;
			} else {
				modal.find('div[name=msg_admin_password]').text('');
				checkAdminPassword = true;
			}
		},
		error: function(){
			alert("checkAdminPassword() 에러");
		}
	});
	
	return checkAdminPassword;
 }
 
 /* 리셋 User Modal*/
 function fn_resetUserModal(insertOrUpdate) {
 	var modal = $('#'+insertOrUpdate+'UserModal');
 	modal.find('input[name=user_id]').val('');
 	modal.find('input[name=user_password]').val('');
 	modal.find('input[name=check_user_password]').val('');
 	modal.find('input[name=admin_password]').val('');
 	modal.find('div[name=msg_user_id]').text('');
 	modal.find('div[name=msg_user_password]').text('');
 	
 }