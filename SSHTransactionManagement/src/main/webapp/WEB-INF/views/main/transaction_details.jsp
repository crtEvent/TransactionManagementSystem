<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Card -->
<div class="card sticky-top">
	<!-- <!-- Card-header -->
	<div class="card-header py-2">
		<span class="align-middle text-bold">${transactionDTO.date} [${transactionDTO.subject}]</span>
		<div class="card-tools">
			<button class="btn btn-primary btn-sm" title="수정" onclick="fn_openUpdateTransactionModal(${transactionDTO.transaction_idx})">
				<i class="fas fa-pencil-alt"></i>
			</button>
		</div>
	</div>
	<!-- Card-body -->
	<div class="card-body">
		<!-- Item Table -->
		<div class="row table-responsive-sm">
			<table
				class="table table-sm table-hover table-bordered text-nowrap mb-1"
				style="font-size: 14px;">
				<thead>
					<tr>
						<th style="width: 3%"></th>
						<th style="width: 7%">코드</th>
						<th style="width: 43%">품목명</th>
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
								<tr onclick="fn_openViewInventoryModal(${row_item.inventory_item_idx})">
									<td class="text-center">${status.index + 1}</td>
									<td>${row_item.item_code}</td>
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
									<th colspan="8">데이터가 없습니다.</th>
								</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!-- /.item Table -->
		
		<!-- Memo Table -->
		<div class="row table-responsive-sm">
			<table
				class="table table-sm table-hover table-bordered text-nowrap" style="font-size:14px;">
				<tbody>
					<c:choose>
						<c:when test="${fn:length(memoList) > 0}">
							<c:forEach var="row_memo" varStatus="status"
										items="${memoList}">
										
								<tr>
									<td class="text-center bg-warning" style="width: 4%">
										메모
									</td>
									<td>
										${row_memo.content}
									</td>
								</tr>
					
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!-- /.memo Table -->
		
		<!-- Quote File Download -->
		<h5 class="mb-1">견적서 파일</h5>
		<c:choose>
			<c:when test="${fn:length(quoteFileList) > 0}">
				<c:forEach var="row_quoteFile" varStatus="status"
										items="${quoteFileList}">
					
					<div class="row">
						<div class="col-8">
							<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-paperclip"></i></span>
							</div>
							<input type="text" class="form-control bg-white" readonly 
								value="${row_quoteFile.file_name }" onclick="fn_downloadFile('quote',${row_quoteFile.file_idx})" 
								>
						</div>
						</div>
						<div class="col-4">
							<a href="/ssh/file/download?file_idx=${row_quoteFile.file_idx}" target="_blank">다운로드</a>
						</div>
					</div>
					
				</c:forEach>
			</c:when>
		</c:choose><!-- /.quote file download -->
		
		<!-- Order File Download -->
		<h5 class="mb-1 mt-2">지시서 파일</h5>
		<c:choose>
			<c:when test="${fn:length(orderFileList) > 0}">
				<c:forEach var="row_orderFile" varStatus="status"
										items="${orderFileList}">
					
					<div class="row">
						<div class="col-8">
							<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-paperclip"></i></span>
							</div>
							<input type="text" class="form-control bg-white" readonly 
								value="${row_orderFile.file_name }" onclick="fn_downloadFile('order',${row_orderFile.file_idx})" 
								style="cursor:pointer">
						</div>
						</div>
						<div class="col-4">
							<a href="/ssh/file/download?file_idx=${row_orderFile.file_idx}" target="_blank">다운로드</a>
						</div>
					</div>
					
				</c:forEach>
			</c:when>
		</c:choose><!-- /.order file download -->
		
		<!-- Image File Download -->
		<h5 class="mb-1 mt-2">이미지 파일</h5>
		<c:choose>
			<c:when test="${fn:length(imageFileList) > 0}">
				<c:forEach var="row_imageFile" varStatus="status"
										items="${imageFileList}">
					
					<div class="row">
						<div class="col-8">
							<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-paperclip"></i></span>
							</div>
							<input type="text" class="form-control bg-white" readonly 
								value="${row_imageFile.file_name }" onclick="fn_downloadFile('image',${row_imageFile.file_idx})" 
								style="cursor:pointer">
						</div>
						</div>
						<div class="col-4">
							<a href="/ssh/file/download?file_idx=${row_imageFile.file_idx}" target="_blank">다운로드</a>
						</div>
					</div>
					
				</c:forEach>
			</c:when>
		</c:choose><!-- /.image file download -->
		
		<!-- Other File Download -->
		<h5 class="mb-1 mt-2">기타 파일</h5>
		<c:choose>
			<c:when test="${fn:length(otherFileList) > 0}">
				<c:forEach var="row_otherFile" varStatus="status"
										items="${otherFileList}">
					
					<div class="row">
						<div class="col-8">
							<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-paperclip"></i></span>
							</div>
							<input type="text" class="form-control bg-white" readonly 
								value="${row_otherFile.file_name }" onclick="fn_downloadFile('other',${row_otherFile.file_idx})" 
								style="cursor:pointer">
						</div>
						</div>
						<div class="col-4">
							<a href="/ssh/file/download?file_idx=${row_otherFile.file_idx}" target="_blank">다운로드</a>
						</div>
					</div>
					
				</c:forEach>
			</c:when>
		</c:choose><!-- /.other file download -->
		
	</div><!-- /.card-body -->
</div><!-- /.card -->