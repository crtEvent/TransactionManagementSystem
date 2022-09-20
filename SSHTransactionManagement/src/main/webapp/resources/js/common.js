/**
 * 
 */
$('input[name=date]').val(new Date().toISOString().slice(0, 10));

/* 로그인 */
function fn_login() {

	var modalTag = $('#loginModal')
	
	$.ajax({
		url: "/ssh/user/login",
		type: 'post',
		data: {user_id : modalTag.find('input[name=user_id]').val() 
			   , user_password : modalTag.find('input[name=user_password]').val()
			   , remember_id : modalTag.find('input[name=remember_id]:checked').val()},
		async: false,
		success: function(result){
			if(result != '') {
				// 로그인 성공 - modal창 닫기
				modalTag.modal('hide');
				modalTag.find('input[name=user_password]').val('');
				$('#loginMsg').text('');
				$('#userInfo').text(' '+result);
				$('#rightNavBar').append('<li id="logoutLiTag" class="nav-item"><div class="nav-link" onclick="fn_logout()">로그아웃</div></li>');
				fn_searchCompany();
			} else {
				// 로그인 실패 - 메세지 띄우기
				$('#loginMsg').text('아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해 주세요.');
			}
		},
		error: function(){
			alert("fn_login() 에러");
		}
	});
}

/* 로그아웃 */
function fn_logout() {

	$.ajax({
		url: "/ssh/user/logout",
		type: 'post',
		success: function(){
			$('#loginModal').find('input[name=user_password]').val('');
			$('#loginMsg').text('');
			$('#userInfo').text('');
			$('#logoutLiTag').remove();
			$('#loginModal').modal({ keyboard: false, backdrop: 'static' });
		},
		error: function(){
			alert("fn_logout() 에러");
		}
	});
}