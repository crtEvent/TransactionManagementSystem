/**
 * 거래 내역 입력/수정 공통 함수
 * 사용된 페이지
 *  - /main/transaction_list.jsp
 *  - /main/transaction_details.jsp
 *  - /modal/modal_insert_transaction.jsp
 */
 
let insertTransactionModal = $('#insertTransactionModal');
let updateTransactionModal = $('#updateTransactionModal');

 /* [Modal]: 거래 내역 입력 Modal 열기 */
function fn_openInsertTransactionModal() {
	insertTransactionModal.find('input[name=company_idx]').val($('#mainDetailsCard').find('input[name=company_idx]').val());
	insertTransactionModal.find('input[type=radio][value=출고]').prop('checked', true);
	insertTransactionModal.modal("show");
}

 /* [Modal]: 거래 내역 수정 Modal 열기 */
function fn_openUpdateTransactionModal(transaction_idx) {
	$.ajax({
		url: "/ssh/transaction/transaction-update-modal",
		type: 'post',
		data: {transaction_idx : transaction_idx},
		success: function(result){
			updateTransactionModal.find('.modal-content').html(result);
			updateTransactionModal.find('input[name=company_idx]').val($('#mainDetailsCard').find('input[name=company_idx]').val());
			fn_inputTotalPrice($('#itemTbodyInUpdateModal'));
			updateTransactionModal.modal("show");
		},
		error: function(){
			alert("fn_openUpdateTransactionModal 에러");
		}
	})
}

/* [ Item 거래 내역 ]************************************************************************************** */
/* 거래내역 추가 */
var itemCnt = 1;
function fn_addItem(insertOrUpdate) {
	
	var addItemTag = 
	'<tr id="itemTag'+itemCnt+'">'+
		'<td><input type="text" name="content" autocomplete="off" class="form-control form-control-sm"></td>'+
		'<td><input type="text" name="amount" autocomplete="off" class="form-control form-control-sm" onkeyup="fn_autoInputItemValue($(this))"></td>'+
		'<td><input type="text" name="unit_price" autocomplete="off" class="form-control form-control-sm" onkeyup="fn_autoInputItemValue($(this))"></td>'+
		'<td><input type="text" name="supply_price" autocomplete="off" class="form-control form-control-sm" onkeyup="fn_autoInputTotalValue($(this))"></td>'+
		'<td><input type="text" name="tax_price" autocomplete="off" class="form-control form-control-sm" onkeyup="fn_autoInputTotalValue($(this))"></td>'+
		'<td><input type="text" name="total_price" autocomplete="off" class="form-control form-control-sm" onkeyup="fn_autoInputTotalValue($(this))"></td>'+
		'<td class="text-center">'+
			'<button class="btn btn-sm" onclick="fn_deleteParentParentForItem($(this))">'+
				'<i class="fas fa-times-circle"></i>'+
			'</button>'+
		'</td>'+
	'</tr>';
	
	$('#'+insertOrUpdate+'ItemTable').find('tbody').append(addItemTag);
	
	itemCnt++;


	
}/* [ Item 거래 내역 ] - END */

/* [Memo 메모]******************************************************************************************* */
/* 메모 추가 */
var memoCnt = 1;
function fn_addMemo(insertOrUpdate) {
	
	var addMemoTag =
		'<tr id="memoTag'+memoCnt+'">'+
			'<td style="width: 5%">'+
				'<div class="btn btn-warning btn-sm">메모</div>'+
			'</td>'+
			'<td>'+
				'<input type="text" name="memo" autocomplete="off" class="form-control form-control-sm">'+
			'</td>'+
			'<td style="width: 3%" class="text-center">'+
				'<button class="btn btn-sm" onclick="fn_deleteParentParent($(this))">'+
					'<i class="fas fa-times-circle"></i>'+
				'</button>'+
			'</td>'+
		'</tr>';
		
	$('#'+insertOrUpdate+'MemoTable').find('tbody').append(addMemoTag);
	
	memoCnt++;

} /* [Memo 메모] - END */

/* [File 파일 추가]************************************************************************************** */
var fileCnt = 1;

/* 지시서 파일 추가 */
function fn_addFile(insertOrUpdate, fileType) {

	// inputOrUpdate - input, update
	// fileType - quote, order, image, other

	var addFileTag = 
		'<div class="input-group mb-1">'+
			'<div class="input-group-prepend">'+
				'<span class="input-group-text"><i class="fas fa-paperclip"></i></span>'+
			'</div>'+
			'<input type="text" name="'+fileType+'FileName'+fileCnt+'" class="form-control bg-white" readonly>'+
			'<button type="button" class="btn btn-sm" onclick="fn_deleteFile($(this))">'+
				'<i class="fas fa-times-circle"></i>'+
			'</button>'+
			'<input type="file" name="'+fileType+'File'+fileCnt+'" onchange="fn_showFileName(&#39;'+fileType+'FileName&#39;, this,'+fileCnt+')" style="display:none"/>'+
		'</div>';
	
	$('#'+insertOrUpdate+'_'+fileType+'_file_div').append(addFileTag);
	
	$('input[name='+fileType+'File'+fileCnt+']').click();
	
	fileCnt++;

} /* [File 파일 추가] - END */

/* [Common 공통 함수]************************************************************************************** */
/* 부모 태그 삭제 */
function fn_deleteParent(obj) {
	obj.parent().remove();
}

/* 부모 태그의 부모 태그 삭제 */
function fn_deleteParentParent(obj) {
	obj.parent().parent().remove();
}

/* 부모 태그의 부모 태그 삭제 - item 용 */
function fn_deleteParentParentForItem(obj) {
	var tag = obj.closest('tbody');
	obj.parent().parent().remove();
	fn_inputTotalPrice(tag);
	
}

/* 파일 삭제(견적서, 지시서, 이미지, 기타 공통) */
function fn_deleteFile(obj) {
	
	if(confirm("파일을 삭제하시겠습니까?")){
		fn_deleteParent(obj)
	}
}

/* 유효성 검사 - 숫자만 입력 가능, 자리수 제한, 세 자리수 콤마 */
function fn_checkNumberOnly(obj) {
	// 정규식 - 숫자만 입력 가능
	var inputVal = obj.val().replace(/[^0-9]/gi,'');
	
	// 자리수 제한
    if(inputVal.length > 15) {
    	alert("금액 값은 15자리수 까지만 입력 가능합니다.");
    	inputVal = inputVal.slice(0, 15);
    }
    
    // 세 자리수 마다 콤마 찍기 + 값 입력
    obj.val(fn_inputComma(inputVal));
    
} /* 유효성 검사 function-end */

/* 유효성 검사 - 세 자리수 마다 콤마 찍기 */
function fn_inputComma(price) {
	var regComma = /(^[+-]?\d+)(\d{3})/;
	
	while(regComma.test(price)) {
    	price = price.toString().replace(regComma, '$1'+','+'$2');
    }
    
    return price;
}

/* 파일 체크 - 파일 등록 시 이름 보여주기 */
function fn_showFileName(inputName, file, attachFileCnt) {
	var fileName = file.files[0].name;
	$('input[name='+inputName+attachFileCnt+']').val(fileName);
}

/* 전체 리셋 - 아이템, 메모, 파일 모두 제거 */
function fn_confirmResetTransactionModal(insertOrUpdate) {

	if(confirm('입력한 내용을 모두 삭제하시겠습니까?')) {
		fn_resetTransactionModal(insertOrUpdate)
	}
}

function fn_resetTransactionModal(insertOrUpdate) {

		$('#'+insertOrUpdate+'ItemTable').find('tbody').empty();
		$('#'+insertOrUpdate+'MemoTable').find('tbody').empty();
		$('#'+insertOrUpdate+'_quote_file_div').empty();
		$('#'+insertOrUpdate+'_order_file_div').empty();
		$('#'+insertOrUpdate+'_image_file_div').empty();
		$('#'+insertOrUpdate+'_other_file_div').empty();
		
		var modalTag = $('#insertTransactionModal')
		modalTag.find('input[name=subject]').val('');
		modalTag.find('input[name=total_supply_price]').val('');
		modalTag.find('input[name=total_tax_price]').val('');
		modalTag.find('input[name=total_total_price]').val('');
}

/* [Common 공통 함수] - END */


/* [자동 입력값 설정]************************************************************************************** */


/* 수량, 단가 입력시 - item당 공급가, 부가세, 합계 자동 입력 */
function fn_autoInputItemValue(obj) {
	fn_checkNumberOnly(obj);
	fn_inputItemPrice(obj);
}

/* 공급가, 부가세, 합계 입력시 - Total 부분 공급가, 부가세, 합계 자동 입력 */
function fn_autoInputTotalValue(obj) {
	fn_checkNumberOnly(obj);
	fn_inputTotalPrice(obj);
}

/* Item - 공급가, 부가세, 합계 자동 입력 */
function fn_inputItemPrice(obj) {
	var thisTrTag = obj.parent().parent(); // item을 담고 있는 tr 태그
	
	var amount = thisTrTag.find('input[name=amount]').val().split(',').join("");
	var unit_price =  thisTrTag.find('input[name=unit_price]').val().split(',').join("");
	var supply_price = amount*unit_price;
	var tax_price = Math.round(supply_price*0.1);
	var total_price = supply_price+tax_price;
	
	thisTrTag.find('input[name=supply_price]').val(fn_inputComma(supply_price));
	thisTrTag.find('input[name=tax_price]').val(fn_inputComma(tax_price));
	thisTrTag.find('input[name=total_price]').val(fn_inputComma(total_price));
	
	fn_inputTotalPrice(obj);
}

/* 전체 - 공급가, 부가세, 합계 자동 입력 */
function fn_inputTotalPrice(obj) {
	
	var tbodyTag = null;
	
	if(obj.prop('tagName') == 'tbody') {
		tbodyTag = obj;
	} else {
		tbodyTag = obj.closest('tbody');
	}

	var totalDiv = tbodyTag.closest('.modal-body');

	var totalSupplyPrice = 0;
	var totalTaxPrice = 0;
	var totalTotalPrice = 0;
	
	var supplyPriceArray = tbodyTag.find('input[name=supply_price]');
	var taxPriceArray = tbodyTag.find('input[name=tax_price]');
	var totalPriceArray = tbodyTag.find('input[name=total_price]');
	
	for(let i=0; i < supplyPriceArray.length; i++) {
		totalSupplyPrice += Number(supplyPriceArray.eq(i).val().split(',').join(""));
		totalTaxPrice += Number(taxPriceArray.eq(i).val().split(',').join(""));
		totalTotalPrice += Number(totalPriceArray.eq(i).val().split(',').join(""));
	}
	
	totalDiv.find('input[name=total_supply_price]').val(fn_inputComma(totalSupplyPrice));
	totalDiv.find('input[name=total_tax_price]').val(fn_inputComma(totalTaxPrice));
	totalDiv.find('input[name=total_total_price]').val(fn_inputComma(totalTotalPrice));

}
/* [자동 입력값 설정] - END */

/* [거래 내역 입력]************************************************************************************** */
function fn_insertTransaction() {

	var formData = new FormData();
	
	/* [transaction 정보] */
	var date = insertTransactionModal.find('input[name=date]').val();
	var company_idx = insertTransactionModal.find('input[name=company_idx]').val();
	var transaction_type = insertTransactionModal.find('input[name=transaction_type]:checked').val();
	var subject = insertTransactionModal.find('input[name=subject]').val();
	formData.append('date', date);
	formData.append('company_idx', company_idx);
	formData.append('transaction_type', transaction_type);
	formData.append('subject', subject);

	/* [item 넣기] */
	var itemList = new Array() ;
	var item = insertTransactionModal.find('input[name=amount]');
	
	for(var i = 0; i < item.length; i++) {
		var itemData = new Object();
		
		itemData.content = insertTransactionModal.find('input[name=content]').eq(i).val();
		itemData.amount = insertTransactionModal.find('input[name=amount]').eq(i).val();
		itemData.unit_price = insertTransactionModal.find('input[name=unit_price]').eq(i).val();
		itemData.supply_price = insertTransactionModal.find('input[name=supply_price]').eq(i).val();
		itemData.tax_price = insertTransactionModal.find('input[name=tax_price]').eq(i).val();
		itemData.total_price = insertTransactionModal.find('input[name=total_price]').eq(i).val();
		
		itemList.push(itemData);
	}
	
	formData.append('itemJsonData', JSON.stringify(itemList));

	/* [memo 넣기] */
	var memoList = new Array();
	var memo = insertTransactionModal.find('input[name=memo]');
	
	for(var i = 0; i < memo.length; i++) {
		var memoData = new Object();
		
		memoData.content = memo.eq(i).val();
		
		memoList.push(memoData);
	}
	
	var memoJsonData = JSON.stringify(memoList);
	
	formData.append('memoJsonData', JSON.stringify(memoList));

	/* [file 넣기] */
	var inputFiles = insertTransactionModal.find('input[type=file]'); // input[type=file] 여러개 다 가져옴
	
	// input[type=file] tag가 있는 경우
	if(inputFiles.length != 0) {
		for(var i = 0; i < inputFiles.length; i++) {
			// 파일 값이 없는 input[type=file] tag는 제거
			if(inputFiles[i].files[0] == null){
				inputFiles[i].parentElement.remove();
				continue;
			}
			formData.append(inputFiles.eq(i).attr('name'), inputFiles[i].files[0]);
		} // .for
	} // .if
	
	/* Ajax 전송 */
	$.ajax({
		url: '/ssh/transaction/insert-transaction',
		type: 'post',
		data: formData,
		contentType: false,
		processData: false,
		enctype : 'multipart/form-data',
		success: function() {
			fn_resetTransactionModal('insert');
			fn_loadTransactionListTable(company_idx);
		},
		error: function(error) {
			alert('[에러]\n'+error.status+'\n'+error.responseText);
		}
	});
}

/* [거래 내역 수정]************************************************************************************** */
function fn_updateTransaction() {

	var formData = new FormData();
	
	/* [transaction 정보] */
	var transaction_idx = updateTransactionModal.find('input[name=transaction_idx]').val();
	var date = updateTransactionModal.find('input[name=date]').val();
	var company_idx = updateTransactionModal.find('input[name=company_idx]').val();
	var transaction_type = updateTransactionModal.find('input[name=transaction_type]:checked').val();
	var subject = updateTransactionModal.find('input[name=subject]').val();
	formData.append('transaction_idx', transaction_idx);
	formData.append('date', date);
	formData.append('company_idx', company_idx);
	formData.append('transaction_type', transaction_type);
	formData.append('subject', subject);

	/* [item 넣기] */
	var itemList = new Array() ;
	var item = updateTransactionModal.find('input[name=amount]');
	
	for(var i = 0; i < item.length; i++) {
		var itemData = new Object();
		
		itemData.content = updateTransactionModal.find('input[name=content]').eq(i).val();
		itemData.amount = updateTransactionModal.find('input[name=amount]').eq(i).val();
		itemData.unit_price = updateTransactionModal.find('input[name=unit_price]').eq(i).val();
		itemData.supply_price = updateTransactionModal.find('input[name=supply_price]').eq(i).val();
		itemData.tax_price = updateTransactionModal.find('input[name=tax_price]').eq(i).val();
		itemData.total_price = updateTransactionModal.find('input[name=total_price]').eq(i).val();
		
		itemList.push(itemData);
	}
	
	formData.append('itemJsonData', JSON.stringify(itemList));

	/* [memo 넣기] */
	var memoList = new Array();
	var memo = updateTransactionModal.find('input[name=memo]');
	
	for(var i = 0; i < memo.length; i++) {
		var memoData = new Object();
		
		memoData.content = memo.eq(i).val();
		
		memoList.push(memoData);
	}
	
	formData.append('memoJsonData', JSON.stringify(memoList));

	/* [기존에 있던 file 넣기] */
	var existingFileList = new Array();
	var existingFileIdx = updateTransactionModal.find('input[name=existingFile_idx]');
	var existingFileType = updateTransactionModal.find('input[name=existingFile_type]');
	
	for(var i = 0; i < existingFileIdx.length; i++) {
		var existingFileData = new Object();
		existingFileData.file_idx = existingFileIdx.eq(i).val();
		existingFileData.file_type = existingFileType.eq(i).val();
		existingFileList.push(existingFileData);
	}
	
	formData.append('existingFileJsonData', JSON.stringify(existingFileList));

	/* [새로 추가된 file 넣기] */
	var inputFiles = updateTransactionModal.find('input[type=file]'); // input[type=file] 여러개 다 가져옴
	
	// input[type=file] tag가 있는 경우
	if(inputFiles.length != 0) {
		for(var i = 0; i < inputFiles.length; i++) {
			// 파일 값이 없는 input[type=file] tag는 제거
			if(inputFiles[i].files[0] == null){
				inputFiles[i].parentElement.remove();
				continue;
			}
			formData.append(inputFiles.eq(i).attr('name'), inputFiles[i].files[0]);
		} // .for
	} // .if
	
	/* Ajax 전송 */
	$.ajax({
		url: '/ssh/transaction/update-transaction',
		type: 'post',
		data: formData,
		contentType: false,
		processData: false,
		enctype : 'multipart/form-data',
		success: function() {
			fn_resetTransactionModal('update');
			fn_loadTransactionListTable(company_idx);
			fn_getTransactionDetails(transaction_idx);
			updateTransactionModal.modal("hide");
		},
		error: function(error) {
			alert('[에러]\n'+error.status+'\n'+error.responseText);
		}
	});
}