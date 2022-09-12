<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSH ERP</title>
<%@include file="../include/include_main_head.jsp" %>
</head>
<body class="control-sidebar-slide-open layout-fixed">

	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/include_main_navbar.jsp" %>
		<%@include file="../include/include_main_sidebar.jsp" %>
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper" id="contentWrapper">
			<!-- main_details.jsp 들어갈 자리 -->
		</div>
		<!-- /.content wrapper -->
		
		<%@include file="../include/include_main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	
	<%@include file="../modal/modal_confirm.jsp" %>
	<%@include file="../modal/modal_insert_company.jsp" %>
	<%@include file="../modal/modal_update_company.jsp" %>
	<%@include file="../modal/modal_insert_transaction.jsp" %>
	<%@include file="../modal/modal_update_transaction.jsp" %>
	<%@include file="../modal/modal_insert_inventory_item.jsp" %>
	<%@include file="../modal/modal_update_inventory_item.jsp" %>
	<%@include file="../modal/modal_view_inventory_item.jsp" %>
	
	<%@include file="../include/include_main_plugins.jsp" %>
	<script type="text/javascript" src="<c:url value="/resources/js/common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/search_company.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/company_common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/transaction_common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/main_details.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/view_files.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/inventory_common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/setting.js"/>"></script>
</body>
</html>