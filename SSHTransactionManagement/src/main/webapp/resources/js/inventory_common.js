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
 
 function printInventoryTable() {
	
	const html = document.querySelector('html');
	const printContents = document.querySelector('#printArea').innerHTML;
	const printDiv = document.createElement('div');
	printDiv.id = 'printDiv';
	
	html.appendChild(printDiv);
	printDiv.innerHTML = printContents;
	
	// 인쇄되지 않는 부분 display = 'none'
	document.body.style.display = 'none';
	window.print();
	
	document.body.style.display = 'block';
	document.querySelector('#printDiv').remove();

 }