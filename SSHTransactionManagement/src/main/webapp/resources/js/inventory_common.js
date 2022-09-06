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
 
 /* 재고 품목 리스트(Inventory Item List) 불러오기 (jsGrid) */
function fn_loadInventoryItemListTable() {

	$.ajax({
		type: "GET",
		url: "/ssh/inventory/inventory-item-list"
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