/**
 * 업체 등록 관련 script
 * 사용된 페이지
 *  - /views/include/include_main_sidebar.jsp
 *  - /views/modal/modal_insert_company.jsp
 */
 
let insertCompanyModal = $('#insertCompanyModal');
 
 /* [Modal]: 업체 등록 Modal 열기 */
$('[name=open_insert_company_modal]').on('click', function() {
	$("#insertCompanyModal").modal("show");
});

/* 다시 입력 - 입력된 내용 초기화 */
$('#resetInsertCompanyModalBtn').on('click', function() {
	fn_resetInsertCompanyModal();
});

function fn_resetInsertCompanyModal() {
	insertCompanyModal.find('input').val('');
	insertCompanyModal.find('textarea').val('');
}


/* 업체 등록 */
$('#insertCompanyBtn').on('click', function() {
	fn_insertCompany();
});

function fn_insertCompany() {

	var name = insertCompanyModal.find('input[name=company_name]').val();
	var content = insertCompanyModal.find('textarea[name=ect]').val();
	
	// 유효성 검사 1 - 업체명은 필수로 입력해야 한다
	if(name == null || name == '') {
		alert('업체명을 입력해 주세요.');
		return;
	}
	
	// 유효성 검사 2 - 업체명 중복 체크
	if(!fn_checkCompanyName()) {
		alert("이미 등록된 업체명 입니다.");
		return;
	}
	
	// 유효성 검사 3 - ect는 2000자 이하로 작성해야 한다
	if(content.length > 3000) {
		alert('기타 내용은 3000자 이내로 작성해 주세요.');
		return;
	}

	// form 생성
	let insertCompanyForm = $("<form></form>");
	
	insertCompanyForm.attr("name","insertCompanyForm");
	insertCompanyForm.attr("method","post");
	insertCompanyForm.attr("action","/ssh/company/insert");
	
	// 업체 등록 Modal의 input, textarea 복제
	insertCompanyForm.append(insertCompanyModal.find('input').clone());
	insertCompanyForm.append(insertCompanyModal.find('textarea').clone());
	insertCompanyForm.appendTo("body");
	
	insertCompanyForm.submit();
	
	$('[name=insertCompanyForm]').remove();
	fn_resetInsertCompanyModal();
}

/* ect란 글자수 표시 */
insertCompanyModal.find('textarea[name=ect]').keyup(function(){
	fn_viewEctFieldLength();
})

function fn_viewEctFieldLength() {
	
	var ectFieldLength = insertCompanyModal.find('textarea[name=ect]').val().length;
	var ectFieldLengthDiv = insertCompanyModal.find('[name=ectFieldLength]');
	
	if(ectFieldLength > 3000){
    	ectFieldLengthDiv.html('(<span class="text-danger">'+ectFieldLength+'자</span> / 최대 3,000자)');
    	return;
    }
    
    ectFieldLengthDiv.html('(<span>'+ectFieldLength+'자</span> / 최대 3,000자)');
}

/* 업체명 중복 체크 */
function fn_checkCompanyName() {
		
		var checkDupCompanyName;
		
		$.ajax({
			url: '/ssh/company/check-company-name',
			type: 'get',
			data: {company_name : insertCompanyModal.find('[name=company_name]').val()},
			async: false,
			success: function(data){
				if(!data) {
					// 업체명이 중복이면 false
					checkDupCompanyName = false;
					return;
				}
				// 업체명이 중복되지 않으면 true
				checkDupCompanyName = true;
				return;
			},
			error: function(){
				alert("서버 오류");
				checkDupCompanyName = false;
				return;
			}
		});
		
		return checkDupCompanyName;
};