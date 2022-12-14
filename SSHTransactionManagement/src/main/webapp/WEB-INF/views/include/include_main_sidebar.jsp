<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="#" class="brand-link">
		<img src="<c:url value="/resources/adminLTE/dist/img/SSHLogo.png"/>"
			 alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
			 style="opacity: .8">
		<span class="brand-text font-weight-light">SSH</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">

		<div class="form-inline">
			<!-- Search Bar -->
			<div class="input-group mb-3">
				<input name="company_name_keyword" class="form-control" type="search" placeholder="거래처 검색" autocomplete="off">
			</div>
			
			<!-- Insert Company -->
			<div class="input-group">
				<button type="button" 
						class="btn btn-block btn-default text-bold"
						name="open_insert_company_modal">업체 등록</button>
			</div>
			
			<!-- Company List Table -->
			<div class="w-100 bg-light rounded">
				<table id="searchCompanyListTable" class="table table-sm table-hover text-nowrap w-100 table-bordered rounded">
                  <thead>
                    <tr class="text-center">
                      <th width="51px">No.</th>
                      <th>거래처명</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:choose>
                  		<c:when test="${not empty userSession.user_idx}">
                  			<c:forEach var="row_company" items="${searchCompanyList}">
                   				<tr class="text-center" onclick="fn_getCompanyDetails(${row_company.COMPANY_IDX})">
                    				<td>${row_company.COMPANY_IDX }</td>
                    				<td>${row_company.COMPANY_NAME }</td>
                    			</tr>
                    		</c:forEach>
                  		</c:when>
                  		<c:otherwise>
                  			<tr>
                  				<td colspan="2" class="text-center">로그인 하세요.</td>
                  			</tr>
                  		</c:otherwise>
                  	</c:choose>
                  
                  </tbody>
                </table>

			</div>
			
		</div>

	</div>
	<!-- /.sidebar -->
</aside>