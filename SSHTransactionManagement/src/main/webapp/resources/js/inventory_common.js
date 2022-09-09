/**
 * 재고 관리, 전용 품목 관련 스크립트
 * 사용된 페이지
 *  - /include/include_main_navbar.jsp
 */
 
 function fn_getInventoryManagementPage() {
 	$.ajax({
		url: "/ssh/inventory/inventory-management",
		type: 'post',
		success: function(result){
			$("#contentWrapper").html(result);
			fn_loadInventoryItemListTable();
		},
		error: function(){
			alert("fn_getInventoryManagementPage() 에러");
		}
	})
 }
 
 /* 전체 재고 품목 리스트(Inventory Item List) 불러오기 (jsGrid) */
 function fn_loadInventoryItemListTable(company_idx) {

	$.ajax({
		type: "GET",
		url: "/ssh/inventory/inventory-item-list?company_idx="+company_idx
	}).done(function(data) {
		/* jsGrid */
		$("#inventoryListTable").jsGrid({
			width: "100%",
			height: "auto",
			autoload:   true,
			filtering: true,
			sorting: true,
			paging:     true,
			pageSize:   25,
			pageButtonCount: 5,
			pageIndex:  1,
		 
			controller: {
				loadData: function(filter) {
					return $.grep(data, function(item) {
                             return (!filter.company_name|| item.company_name.indexOf(filter.company_name) > -1) 
                             && (!filter.item_code|| item.item_code.indexOf(filter.item_code) > -1)
                             && (!filter.content|| item.content.indexOf(filter.content) > -1)
                             && (!filter.unit_price|| item.unit_price.indexOf(filter.unit_price) > -1)
        		             && (!filter.initial_quantity|| item.initial_quantity.indexOf(filter.initial_quantity) > -1) 
        		             && (!filter.current_quantity|| item.current_quantity.indexOf(filter.current_quantity) > -1)
            	  			});
	  	      
				} // loadData end
			},
			fields: [
				{name:"company_name", title:"업체명", type:"text", align:"center", width: 40},
				{name:"item_code", title:"품목코드", type:"text", align:"center", width: 25},
				{name:"content", title:"내용", type:"text", align:"center"},
				{name:"unit_price", title:"공급가", type:"text", align:"center", width: 25},
				{name:"initial_quantity", title:"초기수량", type:"text", align:"center", width: 25},
				{name:"current_quantity", title:"현재고", type:"text", align:"center", width: 25}
			],
			rowClick: function(args) {
       	    	
      	  	}
		}); // jsGrid end
	}); // .done() end

 }; // function end

/* 업체별 재고 리스트 불러오기 */
 function fn_getInventoryListByCompany(company_idx) {
	
	$.ajax({
		url: "/ssh/inventory/inventory-list-by-company",
		type: 'post',
		data: {company_idx : company_idx},
		success: function(result){
			$("#mainBodyRow").html(result);
			fn_loadInventoryDataTables();
		},
		error: function(){
			alert("fn_getInventoryListByCompany() 에러");
		}
	})
	
 }; // function end
 
 /* 업체별 재고 리스트 (datatables) */
 function fn_loadInventoryDataTables() {
 	
 	$('#inventoryDataTables').DataTable({
		"paging": false,
		"lengthChange": false,
		"searching": true,
		"ordering": true,
		"info": true,
		"autoWidth": false,
		"responsive": true,
		"buttons": ["copy", "colvis"]
    }).buttons().container().appendTo('#inventoryDataTables_wrapper .col-md-6:eq(0)');
 	
 } // function end
 
 // 업체별 재고 목록 인쇄
 function printInventoryTable() {
	
	const html = document.querySelector('html');
	const printContents = document.querySelector('#inventoryDataTables').parentNode.innerHTML;
	const printDiv = document.createElement('div');
	printDiv.id = 'printDiv';
	
	html.appendChild(printDiv);
	printDiv.innerHTML = printContents;
	
	// 인쇄되지 않는 부분 display = 'none'
	document.body.style.display = 'none';
	window.print();
	
	document.body.style.display = 'block';
	document.querySelector('#printDiv').remove();

 } // function end
 
 // ------------------- Modal ----------------------------------------
 
 var isCompanyIdxTrue = false;
 var isItemCodeTrue = false;
 
 var insertinventoryItemModal = $('#insertInventoryItemModal');
 
 /* 재고 품목 입력 Modal 창 열기*/
 function fn_openInsertInventoryModal() {
 	
 	// 업체 idx 자동 입력
 	var company_idx = $('#mainDetailsCard').find('input[name=company_idx]').val();
 	var companyIdxTag = insertinventoryItemModal.find('input[name=company_idx]');
 	companyIdxTag.val(company_idx);
 	fn_getCompanyName(companyIdxTag);
 	
 	insertinventoryItemModal.modal('show');
 }
 
 /* [File 파일 추가]************************************************************************************** */
var invenFileCnt = 1;

/* 지시서 파일 추가 */
function fn_addInvenFile(insertOrUpdate) {

	var addFileTag = 
		'<div class="input-group">'+
			'<div class="input-group-prepend">'+
				'<span class="input-group-text"><i class="fas fa-paperclip"></i></span>'+
			'</div>'+
			'<input type="text" name="invenFileName'+invenFileCnt+'" class="form-control bg-white" readonly>'+
			'<button type="button" class="btn btn-sm" onclick="fn_deleteFile($(this))">'+
				'<i class="fas fa-times-circle"></i>'+
			'</button>'+
			'<input type="file" name="invenFile'+invenFileCnt+'" onchange="fn_showFileName(&#39;invenFileName&#39;, this,'+invenFileCnt+')" style="display:none"/>'+
		'</div>';
	
	$('#'+insertOrUpdate+'_inventory_file_div').append(addFileTag);
	
	$('input[name=invenFile'+invenFileCnt+']').click();
	
	invenFileCnt++;

} /* [File 파일 추가] - END */
 
 /* 재고 품목 INSERT */
 function insertInventoryItem() {
 	// 매개변수 company_idx는 현재 업체 페이지의 company_idx (입력할 item의 company_idx가 아님)
 
 	var itemCodeTag = insertinventoryItemModal.find('input[name=item_code]');
 	var companyIdxTag = insertinventoryItemModal.find('input[name=company_idx]');
 	var content = insertinventoryItemModal.find('input[name=content]').val();
 	var unit_price = insertinventoryItemModal.find('input[name=unit_price]').val();
 	var initial_quantity = insertinventoryItemModal.find('input[name=initial_quantity]').val();
 	
 	/* 업체명 불러오기, 검사 */
 	if(!isCompanyIdxTrue) {
 		alert('존재하는 업체번호를 입력해 주세요.');
 		return;
 	}
 	
 	/* 아이템 코드 중복 검사 */
 	if(!isItemCodeTrue) {
 		alert('아이템 코드를 다시 입력해 주세요.');
 		return;
 	}
 	
 	var formData = new FormData();
 	formData.append('company_idx', companyIdxTag.val());
 	formData.append('item_code', itemCodeTag.val());
 	formData.append('content', content);
 	formData.append('unit_price', unit_price);
 	formData.append('initial_quantity', initial_quantity);
 	
 	/* [file 넣기] */
	var inputFiles = insertinventoryItemModal.find('input[type=file]'); // input[type=file] 여러개 다 가져옴
	
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
 	
 	// insert
 	$.ajax({
		url: "/ssh/inventory/insert-inventory-item",
		type: 'post',
		data: formData,
		contentType: false,
		processData: false,
		enctype : 'multipart/form-data',
		success: function(result){
			fn_resetIventroyModal('insert');
			fn_getInventoryListByCompany($('#mainDetailsCard').find('input[name=company_idx]').eq(0).val());
		},
		error: function(){
			alert("insertInventoryItem() 에러");
			
		}
	})// .ajax
	
 }
 
 /* 업체 idx 유효성 체크 - 숫자만 입력, 유효한 idx인지 검사 */
 function fn_checkCompnayIdxInInventoryModal(tag) {
 	var inputVal = tag.val().replace(/[^0-9]/gi,'');
 	tag.val(inputVal);
 	fn_getCompanyName(tag);
 }
 
 /* 업체명 불러오기 */
 function fn_getCompanyName(companyIdxTag) {
 
 	if(companyIdxTag.val() == '') {
 		$('#companyIdxMsg').text('업체 idx를 입력하세요.').css('color', 'red');
 		isCompanyIdxTrue = false;
 		return;
 	}
 	
 	$.ajax({
		url: "/ssh/inventory/get-company-name",
		type: 'post',
		data: {company_idx : companyIdxTag.val()},
		success: function(result){
			if(result=='') {
				$('#companyIdxMsg').text('존재하지 않는 업체입니다.').css('color', 'red');
				isCompanyIdxTrue = false;
			} else {
				$('#companyIdxMsg').text(result).css('color', 'green');
				isCompanyIdxTrue = true;
			}
		},
		error: function(){
			alert("fn_checkItemCode() 에러");
			isCompanyIdxTrue = false;
		}
	})
 	
 }
 
  /* 아이템 코드 중복 검사 */
 function fn_checkItemCode(itemCodeTag) {
 	
 	if(itemCodeTag.val() == '') {
 		$('#itemCodeMsg').text('아이템 코드를 입력하세요.').css('color', 'red');
 		isItemCodeTrue = false;
 		return;
 	}
 
 	$.ajax({
		url: "/ssh/inventory/check-code",
		type: 'post',
		data: {item_code : itemCodeTag.val()},
		success: function(result){
			
			if(result == false) {
				// 코드 중복
				$('#itemCodeMsg').text('중복된 코드 입니다.').css('color', 'red');
				isItemCodeTrue = false;
			} else {
				// 사용 가능한 코드
				$('#itemCodeMsg').text('사용 가능한 코드입니다.').css('color', 'green');
				isItemCodeTrue = true;
			}
		},
		error: function(){
			alert("fn_checkItemCode() 에러");
			isItemCodeTrue = false;
		}
	})
 	
 }
 
 /* modal 리셋 */
 function fn_resetIventroyModal(insertOrUpdate) {
 	var tag = $('#'+insertOrUpdate+'InventoryItemModal');
 	tag.find('input[name=item_code]').val('');
 	tag.find('input[name=content]').val('');
 	tag.find('input[name=unit_price]').val('');
 	tag.find('input[name=initial_quantity]').val('');
 	$('#'+insertOrUpdate+'_inventory_file_div').empty();
 	fn_checkItemCode(tag.find('input[name=item_code]'));
 }