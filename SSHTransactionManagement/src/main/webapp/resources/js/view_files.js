/**
 * 이미지 파일만 따로 보기
 * 사용된 페이지
 *  - /main/main_detail.jsp
 *  - /main/view_image_list.jsp
 */
 
 function fn_viewImageFiles(company_idx, year) {
 
 	if(year == null || year == '') {
 		let today = new Date();
 		year = today.getFullYear();
 	}
 	
 	$.ajax({
		url: "/ssh/view/view-image",
		type: 'post',
		data: {company_idx : company_idx
			   , year : year},
		success: function(result){
			$("#mainBodyRow").html(result);
			$('input[name=year]').val(year);
			fn_loadDateRangePicker(company_idx, year);
		},
		error: function(){
			alert("에러");
		}
	});
 	
 };
 
 function fn_loadDateRangePicker(company_idx, year) {
 	
 	$('input[name=year]').datepicker({
		autoclose: true,
		format: " yyyy",
		viewMode: "years",
		minViewMode: "years",
		disableTouchKeyboard: true,
		language: "ko"
	}).on("changeDate", function(e) {
		fn_viewImageFiles(company_idx, $('input[name=year]').val().trim());
	});
	
 }
