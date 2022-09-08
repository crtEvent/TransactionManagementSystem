<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<thead>
    	<tr class="text-center">
    		<th width="51px">No.</th>
    		<th>거래처명</th>
    	</tr>
    </thead>
	<tbody>
		<c:forEach var="row_company" items="${searchCompanyList}">
			<tr class="text-center" onclick="fn_getCompanyDetails(${row_company.COMPANY_IDX})">
				<td>${row_company.COMPANY_IDX}</td>
				<td>${row_company.COMPANY_NAME}</td>
			</tr>
		</c:forEach>
	</tbody>
