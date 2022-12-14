/**
 * 업체별 거래 내역
 * 사용된 페이지
 *  - /views/include/include_main_sidebar.jsp
 *  - /views/main/main.jsp
 *  - /views/main/main_details.jsp
 *  - /views/main/transaction_details.jsp
 */
	
/* 기업 - 거래 내역 불러오기 */
function fn_getCompanyDetails(company_idx) {

	$.ajax({
		url: "/ssh/company/company-details",
		type: 'post',
		data: {company_idx : company_idx},
		success: function(result){
			$("#contentWrapper").html(result);
			fn_loadTransactionListTable(company_idx);
			fn_resetTransactionModal('insert');
		},
		error: function(){
			alert("에러");
		}
	});
}

/* 거래 내역 리스트(Transaction List) 불러오기 (jsGrid) */
function fn_loadTransactionListTable(company_idx) {

	$.ajax({
		type: "GET",
		url: "/ssh/transaction/transaction-list?company_idx="+company_idx
	}).done(function(data) {
		/* jsGrid */
		$("#transactionListTable").jsGrid({
			width: "100%",
			height: "auto",
			autoload:   true,
			filtering: true,
			sorting: true,
			paging:     true,
			pageSize:   25,
			pageButtonCount: 5,
			pageIndex:  1,
			pageNextText: "다음",
			pagePrevText: "이전",
			pageFirstText: "처음",
			pageLastText: "마지막",
			pagerFormat: "페이지: {first} {prev} {pages} {next} {last}    ( {pageIndex} / {pageCount} )",
		 
			controller: {
				loadData: function(filter) {
					return $.grep(data, function(item) {
                             return(!filter.date|| item.date.indexOf(filter.date) > -1)
                             && (!filter.transaction_type|| item.transaction_type.indexOf(filter.transaction_type) > -1) 
        		             && (!filter.subject|| item.subject.indexOf(filter.subject) > -1) 
            	  			});
	  	      
				} // loadData end
			},
			fields: [
				{name:"date", title:"날짜", type:"text", align:"center", width: 25},
				{itemTemplate: function(_, item) {  
						if(item.transaction_type == '출고') {
							return $("<small>").attr('class', 'badge badge-danger').text(item.transaction_type);
						} else if(item.transaction_type == '입고') {
							return $("<small>").attr('class', 'badge badge-info').text(item.transaction_type);
						}
					}, name:"transaction_type" , title:"입출고", type:"text", align:"center", width: 25},
				{name:"subject", title:"제목", type:"text", align:"center"}
			],
			rowClick: function(args) {
       	    	// transaction_details 보여주기
      	    	fn_getTransactionDetails(args.item.transaction_idx);
      	  	}
		}); // jsGrid end
	}); // .done() end

}; // function end

/* 거래 세부내역 불러오기 */
function fn_getTransactionDetails(transaction_idx) {
	
	$.ajax({
		url: "/ssh/transaction/transaction-details",
		type: 'post',
		data: {transaction_idx : transaction_idx},
		success: function(result){
			$("#trDetailsDiv").html(result);
		},
		error: function(){
			alert("에러");
		}
	});
	
}

/* 파일 다운로드 */
function fn_downloadFile(file_type, file_idx) {
	var url = '/ssh/file/download?file_type='+file_type+'&file_idx='+file_idx;
	window.open(url, '_blank');
}
