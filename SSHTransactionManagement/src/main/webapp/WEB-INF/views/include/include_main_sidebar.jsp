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
		
			<div class="input-group mb-3">
				<input name="company_name_keyword" class="form-control" type="search" placeholder="거래처 검색" autocomplete="off">
			</div>
			
			<div class="w-100 bg-light rounded">
				<table id="searchCompanyListTable" class="table table-sm table-hover text-nowrap w-100 table-bordered rounded">
                  <thead>
                    <tr class="text-center">
                      <th>No.</th>
                      <th>거래처명</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach var="row_company" items="${searchCompanyList}">
                   		<tr class="text-center">
                    		<td>${row_company.COMPANY_IDX }</td>
                    		<td>${row_company.COMPANY_NAME }</td>
                    	</tr>
                    </c:forEach>
                  </tbody>
                </table>
				
				
			</div>
			
		</div>

	</div>
	<!-- /.sidebar -->
</aside>