/**
 * 업체 등록/수정/삭제 관련 script
 * 사용된 페이지
 *  - /views/include/include_main_sidebar.jsp
 *  - /views/modal/modal_insert_company.jsp
 *  - /views/modal/modal_update_company.jsp
 */
 
var insertCompanyModal = $('#insertCompanyModal');
var updateCompanyModal = $('#updateCompanyModal');
var currentCompnayName = '';
 
/* [Modal]: 업체 등록 Modal 열기 */
$('[name=open_insert_company_modal]').on('click', function() {
	insertCompanyModal.modal("show");
});

/* [Modal]: 업체 수정 Modal 열기 */
function fn_openUpdateCompanyModal(company_idx) {

	$.ajax({
			url: '/ssh/company/update-modal',
			type: 'get',
			data: {company_idx : company_idx},
			async: false,
			success: function(result){
				currentCompnayName = result.company_name;
				updateCompanyModal.find("input[name=company_name]").val(result.company_name);
				updateCompanyModal.find("input[name=company_reg_num]").val(result.company_reg_num);
				updateCompanyModal.find("input[name=representative]").val(result.representative);
				updateCompanyModal.find("input[name=address_1]").val(result.address_1);
				updateCompanyModal.find("input[name=address_2]").val(result.address_2);
				updateCompanyModal.find("input[name=tel]").val(result.tel);
				updateCompanyModal.find("input[name=fax]").val(result.fax);
				updateCompanyModal.find("input[name=mobile]").val(result.mobile);
				updateCompanyModal.find("textarea[name=ect]").val(result.ect);
				fn_viewEctFieldLength(updateCompanyModal); // ect란 글자수 표시 함수
				
				// 업체명 표시
				updateCompanyModal.find('.modal-title').text('['+currentCompnayName+'] 업체 정보 수정');
				
				updateCompanyModal.modal("show"); // Modal창 열기
			},
			error: function(){
				alert("fn_openUpdateCompanyModal 오류");
			}
		});
	
}

function fn_openDeleteConfirmModal(company_idx) {
	
	$('#confirmModal').find('#confirmBtn').attr('onclick', 'fn_deleteCompany('+company_idx+')');
	
	$('#confirmModal').modal("show"); // Modal창 열기
}

/* 다시 입력 - 입력된 내용 초기화 */
$('#resetInsertCompanyModalBtn').on('click', function() {
	fn_resetCompanyModal(insertCompanyModal);
});
$('#resetUpdateCompanyModalBtn').on('click', function() {
	fn_resetCompanyModal(updateCompanyModal);
});

function fn_resetCompanyModal(modal) {
	modal.find('input').val('');
	modal.find('textarea').val('');
	fn_viewEctFieldLength(modal);
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
	if(!fn_checkCompanyName(insertCompanyModal)) {
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
	fn_resetCompanyModal(insertCompanyModal);
}

/* 업체 수정 */
$('#updateCompanyBtn').on('click', function() {
	fn_updateCompany();
});

function fn_updateCompany() {
	
	var company_idx = $('#mainDetailsCard').find('input[name=company_idx]').val();
	var name = updateCompanyModal.find('input[name=company_name]').val();
	var content = updateCompanyModal.find('textarea[name=ect]').val();
	
	// 유효성 검사 1 - 업체명은 필수로 입력해야 한다
	if(name == null || name == '') {
		alert('업체명을 입력해 주세요.');
		return;
	}
	
	// 유효성 검사 2 - 업체명 중복 체크, 자신의 이름은 제외
	if(name != currentCompnayName) { // 자신의 이름은 제외
		if(!fn_checkCompanyName(updateCompanyModal)) {
			alert("이미 등록된 업체명 입니다.");
			return;
		}
	}
	
	// 유효성 검사 3 - ect는 2000자 이하로 작성해야 한다
	if(content.length > 3000) {
		alert('기타 내용은 3000자 이내로 작성해 주세요.');
		return;
	}
	
	$.ajax({
		url: '/ssh/company/update',
		type: 'get',
		data: {company_idx : company_idx
			   , company_name : name
			   , company_reg_num : updateCompanyModal.find('input[name=company_reg_num]').val()
			   , representative : updateCompanyModal.find('input[name=representative]').val()
			   , address_1 : updateCompanyModal.find('input[name=address_1]').val()
			   , address_2 : updateCompanyModal.find('input[name=address_2]').val()
			   , tel : updateCompanyModal.find('input[name=tel]').val()
			   , fax : updateCompanyModal.find('input[name=fax]').val()
			   , mobile : updateCompanyModal.find('input[name=mobile]').val()
			   , ect : content},
		success: function(){
				alert('변경 완료');
				updateCompanyModal.modal("hide");
				fn_getCompanyDetails(company_idx);
				fn_searchCompany();
		},
		error: function(error) {
			alert('[에러]\n'+error.status+'\n'+error.responseText);
		}
	});
	
	
}


/* ect란 글자수 표시 */
insertCompanyModal.find('textarea[name=ect]').keyup(function(){
	fn_viewEctFieldLength(insertCompanyModal);
})
updateCompanyModal.find('textarea[name=ect]').keyup(function(){
	fn_viewEctFieldLength(updateCompanyModal);
})

function fn_viewEctFieldLength(modal) {
	
	var ectFieldLength = modal.find('textarea[name=ect]').val().length;
	var ectFieldLengthDiv = modal.find('[name=ectFieldLength]');
	
	if(ectFieldLength > 3000){
    	ectFieldLengthDiv.html('(<span class="text-danger">'+ectFieldLength+'자</span> / 최대 3,000자)');
    	return;
    }
    
    ectFieldLengthDiv.html('(<span>'+ectFieldLength+'자</span> / 최대 3,000자)');
}

/* 업체명 중복 체크 */
function fn_checkCompanyName(modal) {
		
		var checkDupCompanyName;
		
		$.ajax({
			url: '/ssh/company/check-company-name',
			type: 'get',
			data: {company_name : modal.find('[name=company_name]').val()},
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

/* 업체 삭제 */
function fn_deleteCompany(company_idx) {
	
	if(!confirm("삭제된 데이터는 복구할 수 없습니다. 해당 업체를 삭제하시겠습니까?")) {
		return;
	}
	
	$.ajax({
		url: '/ssh/company/delete',
		type: 'get',
		data: {company_idx : company_idx},
		success: function(){
			alert("삭제되었습니다.");
			fn_searchCompany();
			$('#contentWrapper').empty();
			$('#confirmModal').modal("hide");
		},
		error: function(){
			alert("fn_deleteCompany() 에러");
		}
	});
	
}