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
				{name:"unit_price", title:"단가", type:"text", align:"center", width: 25},
				{name:"initial_quantity", title:"초기수량", type:"text", align:"center", width: 25},
				{name:"current_quantity", title:"현재고", type:"text", align:"center", width: 25}
			],
			rowClick: function(args) {
       	    	fn_openUpdateInventoryModal(args.item.item_idx, 'jsgrid');
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
 
  /* 재고 품목 거래내역 item history 보기 */
 function fn_getItemHistory(item_idx) {
 	
 	$.ajax({
		type: "GET",
		url: "/ssh/inventory/inventory-item-history", 
		data: {item_idx : item_idx}
	}).done(function(data) {
		/* jsGrid */
		$("#itemHistoryTable").jsGrid({
			width: "100%",
			height: "auto",
			autoload:   true,
			filtering: true,
			sorting: true,
			paging:     true,
			pageSize:   10,
			pageButtonCount: 5,
			pageIndex:  1,
			controller: {
				loadData: function(filter) {
					return $.grep(data, function(item) {
                             return(!filter.company_name|| item.company_name.indexOf(filter.company_name) > -1)
                             && (!filter.date|| item.date.indexOf(filter.date) > -1)
        		             && (!filter.transaction_type|| item.transaction_type.indexOf(filter.transaction_type) > -1)
        		             && (!filter.content|| item.content.indexOf(filter.content) > -1)
        		             && (!filter.amount|| item.amount.indexOf(filter.amount) > -1)
        		             && (!filter.unit_price|| item.unit_price.indexOf(filter.unit_price) > -1)
        		             && (!filter.supply_price|| item.supply_price.indexOf(filter.supply_price) > -1)
        		             && (!filter.tax_price|| item.tax_price.indexOf(filter.tax_price) > -1) 
        		             && (!filter.total_price|| item.total_price.indexOf(filter.total_price) > -1)
            	  			});
	  	      
				} // loadData end
			},
			fields: [
				{name:"company_name", title:"업체명", type:"text", align:"center", width: 35},
				{name:"date", title:"날짜", type:"text", align:"center", width: 25},
				{itemTemplate: function(_, item) {  
						if(item.transaction_type == '출고') {
							return $("<small>").attr('class', 'badge badge-danger').text(item.transaction_type);
						} else if(item.transaction_type == '입고') {
							return $("<small>").attr('class', 'badge badge-info').text(item.transaction_type);
						}
					}, name:"transaction_type" , title:"입출고", type:"text", align:"center", width: 25},
				{name:"content", title:"내용", type:"text", align:"center"},
				{name:"amount", title:"수량", type:"text", align:"center", width: 25},
				{name:"unit_price", title:"단가", type:"text", align:"center", width: 25},
				{itemTemplate: function(_, item) {
						return $('<button>')
								.attr('class', 'btn btn-block btn-info btn-sm')
								.attr('onclick', 'fn_findTransaction('+item.company_idx+','+item.transaction_idx+')')
								.text('이동');
					}, name:"btn", title:"이동", type:"text", align:"center", width: 10
				}
			]
		}); // jsGrid end
	}); // .done() end
 	
 } // function end
 
 // ------------------- Modal ----------------------------------------
 
 var isCompanyIdxTrue = false;
 var isItemCodeTrue = false;
 
 var insertinventoryItemModal = $('#insertInventoryItemModal');
 var updateInventoryItemModal = $('#updateInventoryItemModal');
 var viewInventoryItemModal = $('#viewInventoryItemModal');
 
 /* 재고 품목 입력 Modal 창 열기 */
 function fn_openInsertInventoryModal() {
 
 	isCompanyIdxTrue = false;
 	isItemCodeTrue = false;
 	
 	// 업체 idx 자동 입력
 	var company_idx = $('#mainDetailsCard').find('input[name=company_idx]').val();
 	var companyIdxTag = insertinventoryItemModal.find('input[name=company_idx]');
 	companyIdxTag.val(company_idx);
 	fn_getCompanyName(companyIdxTag, 'insert');
 	
 	insertinventoryItemModal.modal('show');
 }
 
 /* 재고 품목 수정 Modal 창 열기 */
 function fn_openUpdateInventoryModal(item_idx, tableType) {
 	
 	isCompanyIdxTrue = false;
 	isItemCodeTrue = false;
 	
 	// select
 	$.ajax({
		url: "/ssh/inventory/get-inventory",
		type: 'post',
		data: {item_idx : item_idx},
		success: function(result){
		
			fn_resetIventroyModal('update');

			updateInventoryItemModal.find('input[name=item_idx]').val(result[0].item_idx);
			updateInventoryItemModal.find('input[name=company_idx]').val(result[0].company_idx);
			updateInventoryItemModal.find('input[name=item_code]').val(result[0].item_code);
			updateInventoryItemModal.find('input[name=old_item_code]').val(result[0].item_code);
			updateInventoryItemModal.find('input[name=content]').val(result[0].content);
			updateInventoryItemModal.find('input[name=unit_price]').val(result[0].unit_price);
			updateInventoryItemModal.find('input[name=initial_quantity]').val(result[0].initial_quantity);
			
			var companyIdxTag = updateInventoryItemModal.find('input[name=company_idx]');
			var itemCodeTag = updateInventoryItemModal.find('input[name=item_code]');
 			fn_getCompanyName(companyIdxTag, 'update');
 			fn_checkItemCode(itemCodeTag, 'update');
			
			for(var i=0; i < result[1].length; i++) {
				var addFileTag = 
							'<div class="input-group">'+
								'<div class="input-group-prepend">'+
									'<span class="input-group-text"><i class="fas fa-paperclip"></i></span>'+
								'</div>'+
								'<input type="text" class="form-control bg-white" value="'+result[1][i].file_name+'" readonly '+
									'onclick="fn_linkToDownloadFile('+result[1][i].file_idx+')">'+
								'<input type="hidden" name="existingFile_idx" value="'+result[1][i].file_idx+'">'+
								'<button type="button" class="btn btn-sm" onclick="fn_deleteFile($(this))">'+
									'<i class="fas fa-times-circle"></i>'+
								'</button>'+
							'</div>';
	
				$('#update_inventory_file_div').append(addFileTag);
			} // .for - end
			
			// tableType 전달
			updateInventoryItemModal.find('[name=updateBtn]').attr('onclick', 'updateInventory("'+tableType+'")');
			updateInventoryItemModal.find('[name=deleteBtn]').attr('onclick', 'deleteInventory('+item_idx+', "'+tableType+'")');
			
			updateInventoryItemModal.modal('show');
		},
		error: function(){
			alert("fn_openUpdateInventoryModal() 에러");
			
		}
	})// .ajax
 	
 }
 
 /* 재고 품목 보기 Modal창 열기 */
 function fn_openViewInventoryModal(item_idx) {
 	
 	if(item_idx == null) {
 		return;
 	}
 	
 	isCompanyIdxTrue = false;
 	isItemCodeTrue = false;
 	
 	// select
 	$.ajax({
		url: "/ssh/inventory/get-inventory",
		type: 'post',
		data: {item_idx : item_idx},
		success: function(result){
			
			if(result[0] == null) {
 				return;
 			}
		
			fn_resetIventroyModal('view');

			viewInventoryItemModal.find('input[name=item_idx]').val(result[0].item_idx);
			viewInventoryItemModal.find('input[name=company_idx]').val(result[0].company_idx);
			viewInventoryItemModal.find('input[name=item_code]').val(result[0].item_code);
			viewInventoryItemModal.find('input[name=old_item_code]').val(result[0].item_code);
			viewInventoryItemModal.find('input[name=content]').val(result[0].content);
			viewInventoryItemModal.find('input[name=unit_price]').val(result[0].unit_price);
			viewInventoryItemModal.find('input[name=initial_quantity]').val(result[0].initial_quantity);
			
			var companyIdxTag = viewInventoryItemModal.find('input[name=company_idx]');
			var itemCodeTag = viewInventoryItemModal.find('input[name=item_code]');
 			fn_getCompanyName(companyIdxTag, 'view');
 			fn_checkItemCode(itemCodeTag, 'view');
			
			for(var i=0; i < result[1].length; i++) {
				var addFileTag = 
							'<div class="input-group">'+
								'<div class="input-group-prepend">'+
									'<span class="input-group-text"><i class="fas fa-paperclip"></i></span>'+
								'</div>'+
								'<input type="text" class="form-control bg-white" value="'+result[1][i].file_name+'" readonly '+
									'onclick="fn_linkToDownloadFile('+result[1][i].file_idx+')">'+
								'<input type="hidden" name="existingFile_idx" value="'+result[1][i].file_idx+'">'
							'</div>';
	
				$('#view_inventory_file_div').append(addFileTag);
			} // .for - end
			
			viewInventoryItemModal.modal('show');
		},
		error: function(){
			alert("fn_openViewInventoryModal() 에러");
			
		}
	})// .ajax
	
 }
 
 function fn_openItemHistoryModal(updateOrView) {
 	
 	var itemCode = $('#'+updateOrView+'InventoryItemModal').find('input[name=item_code]').val();
 	$('#itemHistoryModal').find('.modal-title').text("[ "+itemCode+" ] - 재고 품목 거래 내역");
 
 	$('#itemHistoryModal').modal('show');
 	fn_getItemHistory($('#'+updateOrView+'InventoryItemModal').find('input[name=item_idx]').val());
 }
 
 function fn_findTransaction(company_idx, transaction_idx) {
 	
 	updateInventoryItemModal.modal('hide');
 	viewInventoryItemModal.modal('hide');
 	$('#itemHistoryModal').modal('hide');
 	
 	fn_getCompanyDetails(company_idx);
 	fn_getTransactionDetails(transaction_idx);
 }
 
 function fn_linkToDownloadFile(file_idx) {
 	window.open('/ssh/file/download?file_idx='+file_idx+'&db=inventory');
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
 function insertInventory() {
 	
 	var itemCodeTag = insertinventoryItemModal.find('input[name=item_code]');
 	var companyIdxTag = insertinventoryItemModal.find('input[name=company_idx]');
 	var content = insertinventoryItemModal.find('input[name=content]').val() == ''? ' ' : insertinventoryItemModal.find('input[name=content]').val();
 	var unit_price = insertinventoryItemModal.find('input[name=unit_price]').val() == ''? '0' : insertinventoryItemModal.find('input[name=unit_price]').val();
 	var initial_quantity = insertinventoryItemModal.find('input[name=initial_quantity]').val() == ''? '0' : insertinventoryItemModal.find('input[name=initial_quantity]').val();
 	
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
 	formData.append('unit_price', Number(unit_price.split(',').join("")));
 	formData.append('initial_quantity', Number(initial_quantity.split(',').join("")));
 	
 	console.log("formData: "+formData.get("company_idx"));
 	console.log("formData: "+formData.get("item_code"));
 	console.log("formData: "+formData.get("content"));
 	console.log("formData: "+formData.get("unit_price"));
 	console.log("formData: "+formData.get("initial_quantity"));
 	
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
	
 } // .function - end
 
 /* 재고 품목 UPDATE */
 function updateInventory(tableType) {
 	
 	var itemIdxTag = updateInventoryItemModal.find('input[name=item_idx]');
 	var itemCodeTag = updateInventoryItemModal.find('input[name=item_code]');
 	var companyIdxTag = updateInventoryItemModal.find('input[name=company_idx]');
 	var content = updateInventoryItemModal.find('input[name=content]').val() == ''? ' ' : updateInventoryItemModal.find('input[name=content]').val();
 	var unit_price = updateInventoryItemModal.find('input[name=unit_price]').val() == ''? '0': updateInventoryItemModal.find('input[name=unit_price]').val();
 	var initial_quantity = updateInventoryItemModal.find('input[name=initial_quantity]').val() == ''? '0': updateInventoryItemModal.find('input[name=initial_quantity]').val();
 	
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
 	
 	/* [재고 품목 정보 넣기] */
 	var formData = new FormData();
 	formData.append('item_idx', itemIdxTag.val());
 	formData.append('company_idx', companyIdxTag.val());
 	formData.append('item_code', itemCodeTag.val());
 	formData.append('content', content);
 	formData.append('unit_price', Number(unit_price.split(',').join("")));
 	formData.append('initial_quantity', Number(initial_quantity.split(',').join("")));
 	
 	/* [기존에 있던 file 넣기] */
	var existingFileList = new Array();
	var existingFileIdx = updateInventoryItemModal.find('input[name=existingFile_idx]');
	
	for(var i = 0; i < existingFileIdx.length; i++) {
		var existingFileData = new Object();
		existingFileData.file_idx = existingFileIdx.eq(i).val();
		existingFileList.push(existingFileData);
	}
	
	formData.append('existingFileJsonData', JSON.stringify(existingFileList));
	
	/* [새로 추가된 file 넣기] */
	var inputFiles = updateInventoryItemModal.find('input[type=file]'); // input[type=file] 여러개 다 가져옴
	
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
	
	// update
	 	$.ajax({
		url: "/ssh/inventory/update-inventory-item",
		type: 'post',
		data: formData,
		contentType: false,
		processData: false,
		enctype : 'multipart/form-data',
		success: function(result){
			fn_resetIventroyModal('update');
			
			switch(tableType) {
			case 'jsgrid' : 
				fn_loadInventoryItemListTable();
				break;
			case 'datatable' :
				fn_getInventoryListByCompany($('#mainDetailsCard').find('input[name=company_idx]').eq(0).val());
				break;
			default :
				break;
			}
		},
		error: function(){
			alert("updateInventoryItem() 에러");
			
		}
	})// .ajax
	
 }
 
 /* 재고품목 삭제 */
 function deleteInventory(item_idx, tableType) {
 	
 	if(!confirm("삭제된 데이터는 복구할 수 없습니다. 해당 품목을 삭제하시겠습니까?")) {
		return;
	}
	
	$.ajax({
		url: '/ssh/inventory/delete-inventory-item',
		type: 'get',
		data: {item_idx : item_idx},
		success: function(){
			alert("삭제되었습니다.");
			
			switch(tableType) {
			case 'jsgrid' : 
				fn_loadInventoryItemListTable();
				break;
			case 'datatable' :
				fn_getInventoryListByCompany($('#mainDetailsCard').find('input[name=company_idx]').eq(0).val());
				break;
			default :
				break;
			}
			
			updateInventoryItemModal.modal('hide');
		},
		error: function(){
			alert("deleteInventory() 에러");
		}
	});
 }
 
 /* 업체 idx 유효성 체크 - 숫자만 입력, 유효한 idx인지 검사 */
 function fn_checkCompnayIdxInInventoryModal(tag, insertOrUpdate) {
 	var inputVal = tag.val().replace(/[^0-9]/gi,'');
 	tag.val(inputVal);
 	fn_getCompanyName(tag, insertOrUpdate);
 }
 
 /* 업체명 불러오기 */
 function fn_getCompanyName(companyIdxTag, insertOrUpdate) {
 	
 	var msgTag = $('#'+insertOrUpdate+'CompanyIdxMsg');
 	
 	if(companyIdxTag.val() == '') {
 		msgTag.text('업체 idx를 입력하세요.').css('color', 'red');
 		isCompanyIdxTrue = false;
 		return;
 	}
 	
 	$.ajax({
		url: "/ssh/inventory/get-company-name",
		type: 'post',
		data: {company_idx : companyIdxTag.val()},
		success: function(result){
			if(result=='') {
				msgTag.text('존재하지 않는 업체입니다.').css('color', 'red');
				isCompanyIdxTrue = false;
			} else {
				msgTag.text(result).css('color', 'green');
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
 function fn_checkItemCode(itemCodeTag, insertOrUpdate) {
 
 	var msgTag = $('#'+insertOrUpdate+'ItemCodeMsg');
 	
 	if(itemCodeTag.val() == '') {
 		msgTag.text('아이템 코드를 입력하세요.').css('color', 'red');
 		isItemCodeTrue = false;
 		return;
 	}
 	
 	if(insertOrUpdate == 'update' || insertOrUpdate == 'view') {
 		if(itemCodeTag.val() == $('#'+insertOrUpdate+'InventoryItemModal').find('input[name=old_item_code]').val()) {
 			msgTag.text('기존의 아이템 코드').css('color', 'green');
 			isItemCodeTrue = true;
 			return;
 		}
 	}
 
 	$.ajax({
		url: "/ssh/inventory/check-code",
		type: 'post',
		data: {item_code : itemCodeTag.val()},
		success: function(result){
			
			if(result == false) {
				// 코드 중복
				msgTag.text('중복된 코드 입니다.').css('color', 'red');
				isItemCodeTrue = false;
			} else {
				// 사용 가능한 코드
				msgTag.text('사용 가능한 코드입니다.').css('color', 'green');
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
 	fn_checkItemCode(tag.find('input[name=item_code]'), insertOrUpdate);
 }
 
 