<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Card -->
<div class="card  sticky-top">
	<!-- <!-- Card-header -->
	<div class="card-header py-2">
		<span class="align-middle text-bold">${transactionDTO.date} [${transactionDTO.subject}]</span>
		<div class="card-tools">
			<button class="btn btn-primary btn-sm" title="수정" name="">
				<i class="fas fa-pencil-alt"></i>
			</button>
		</div>
	</div>
	<!-- Card-body -->
	<div class="card-body">
		<!-- Item Table -->
		<div class="row table-responsive-sm">
			<table
				class="table table-sm table-hover table-bordered text-nowrap  mb-1"
				style="font-size: 14px;">
				<thead>
					<tr>
						<th style="width: 3%"></th>
						<th style="width: 45%">품목명</th>
						<th style="width: 7%">수량</th>
						<th style="width: 10%">단가</th>
						<th style="width: 10%">공급가</th>
						<th style="width: 10%">부가세</th>
						<th style="width: 10%">합계</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(itemList) > 0}">
							<c:forEach var="row_item" varStatus="status"
										items="${itemList}">
								<tr>
									<td class="text-center">${status.index + 1}</td>
									<td>${row_item.content}</td>
									<td class="text-right">
										<fmt:formatNumber value="${row_item.amount}" pattern="#,###" />
									</td>
									<td class="text-right">
										<fmt:formatNumber value="${row_item.unit_price}" pattern="#,###" />
									</td>
									<td class="text-right">
										<fmt:formatNumber value="${row_item.supply_price}" pattern="#,###" />
									</td>
									<td class="text-right">
										<fmt:formatNumber value="${row_item.tax_price}" pattern="#,###" />
									</td>
									<td class="text-right">
										<fmt:formatNumber value="${row_item.total_price}" pattern="#,###" />
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
								<tr>
									<th colspan="7">데이터가 없습니다.</th>
								</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!-- /.item Table -->
	</div>
</div>