<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-12">
	<!-- Card -->
	<div class="card">
		<!-- <!-- Card-header -->
		<div class="card-header py-2">
			<span class="align-middle text-bold">
				<button class="btn btn-info btn-sm text-bold"
					onclick="fn_openInsertInventoryModal()">[+] 품목 등록</button>
				<button class="btn btn-default bg-gray btn-sm text-bold"
					onclick="printInventoryTable()">&nbsp;인쇄&nbsp;</button>
			</span>
		</div>
		<!-- Card-body -->
		<div class="card-body" id="printArea">
			<table id="inventoryDataTables"
				class="table table-bordered table-hover">
				<thead>
					<tr>
						<th style="width: 17%;">업체명</th>
						<th style="width: 12%;">품목코드</th>
						<th style="width: 51%;">내용</th>
						<th style="width: 10%;">단가</th>
						<th style="width: 10%;">현재고</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(inventoryList) > 0}">
							<c:forEach var="row_item" varStatus="status"
								items="${inventoryList}">
								<tr onclick="fn_openUpdateInventoryModal(${row_item.item_idx}, 'datatable')">
									<td>${row_item.company_name}</td>
									<td>${row_item.item_code}</td>
									<td>${row_item.content}</td>
									<td>${row_item.unit_price}</td>
									<td>${row_item.current_quantity}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<th colspan="6">데이터가 없습니다.</th>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div><!-- /.card-body -->
	</div><!-- /.card -->
</div><!-- /.col-12 -->