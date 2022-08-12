
	
// 거래처명검색
$('input[name=company_name_keyword]').keyup(function() {
	$.ajax({
		url: "/ssh/main/search_co",
		type: 'post',
		data: {company_name_keyword : $('input[name=company_name_keyword]').val()},
		success: function(result){
			$("#searchCompanyListTable").html(result);
		},
		error: function(){
			alert("company_name_keyword 검색 에러");
		}
	})
})

