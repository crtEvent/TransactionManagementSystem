/**
 * 설정 페이지
 *  - DB 백업
 *  - 파일 저장소 위치 설정 
 */
 
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