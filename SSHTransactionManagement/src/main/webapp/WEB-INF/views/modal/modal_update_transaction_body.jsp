<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-header">

	<input type="hidden" name="transaction_idx" value="${transactionDTO.transaction_idx}">

	<!-- Modal 타이틀 -->
	<h4 class="modal-title">거래 내역 수정</h4>
	<!-- Order Group 제목 -->
	<div class="col">
		<div class="input-group col-8 float-right">
			<div class="input-group-prepend">
				<button type="button" class="btn btn-default">제목</button>
			</div>
			<input type="text" name="subject" class="form-control"
				autocomplete="off" value="${transactionDTO.subject}">
		</div>
	</div>
	<!-- Modal 닫기 -->
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">×</span>
	</button>
</div>

<div class="modal-body">

	<!-- Row - 날짜, 총 공급가, 총 부가세, 총 합계 -->
	<div class="row">

		<div class="col-sm-4">
			<!-- 날짜 -->
			<div class="input-group">
				<div class="input-group-prepend">
					<button type="button" class="btn btn-default">날&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;짜</button>
				</div>
				<input type="date" name="date" class="form-control"
					value="${transactionDTO.date}">
			</div>
			<!-- 거래처 -->
			<div class="input-group">
				<div class="input-group-prepend">
					<button type="button" class="btn btn-default"
						style="font-size: 11px;">거래처 No</button>
				</div>
				<input type="text" name="company_idx" value=""
					class="form-control text-center" autocomplete="off">
			</div>
		</div>

		<div class="col-sm-4">
			<!-- Empty -->
		</div>

		<div class="col-sm-4">
			<!-- 총 공급가 -->
			<div class="input-group float-right">
				<div class="input-group-prepend">
					<button type="button" class="btn btn-default">공급가</button>
				</div>
				<input type="text" name="total_supply_price"
					class="form-control text-right" autocomplete="off" readonly>
			</div>
			<!-- 총 부가세 -->
			<div class="input-group float-right">
				<div class="input-group-prepend">
					<button type="button" class="btn btn-default">부가세</button>
				</div>
				<input type="text" name="total_tax_price"
					class="form-control text-right" autocomplete="off" readonly>
			</div>
			<!-- 총 합계 -->
			<div class="input-group float-right mb-3">
				<div class="input-group-prepend">
					<button type="button" class="btn btn-default">총합계</button>
				</div>
				<input type="text" name="total_total_price"
					class="form-control text-right" autocomplete="off" readonly>
			</div>
		</div>
	</div>
	<!-- /.row - 날짜, 총 공급가, 총 부가세, 총 합계 -->

	<!-- Item Table -->
	<div class="row table-responsive-sm">
		<table id="updateItemTable"
			class="table table-sm table-hover table-bordered text-nowrap  mb-1"
			style="font-size: 14px;">
			<thead>
				<tr>
					<th style="width: 45%">품목명</th>
					<th style="width: 7%">수량</th>
					<th style="width: 10%">단가</th>
					<th style="width: 10%">공급가</th>
					<th style="width: 10%">부가세</th>
					<th style="width: 10%">합계</th>
					<th style="width: 3%"></th>
				</tr>
			</thead>
			<tbody id="itemTbodyInUpdateModal">
				<c:choose>
					<c:when test="${fn:length(itemList) > 0}">
						<c:forEach var="row_item" varStatus="status" items="${itemList}">
							<tr>
								<td><input type="text" name="content" autocomplete="off"
									class="form-control form-control-sm"
									value="${row_item.content}"></td>
								<td class="text-right"><input type="text" name="amount"
									autocomplete="off" class="form-control form-control-sm"
									value="<fmt:formatNumber value="${row_item.amount}" pattern="#,###" />" 
									onkeyup="fn_autoInputItemValue($(this))">
								</td>
								<td class="text-right"><input type="text" name="unit_price"
									autocomplete="off" class="form-control form-control-sm"
									value="<fmt:formatNumber value="${row_item.unit_price}" pattern="#,###" />" 
									onkeyup="fn_autoInputItemValue($(this))">
								</td>
								<td class="text-right"><input type="text"
									name="supply_price" autocomplete="off"
									class="form-control form-control-sm"
									value="<fmt:formatNumber value="${row_item.supply_price}" pattern="#,###" />" 
									onkeyup="fn_autoInputTotalValue($(this))">
								</td>
								<td class="text-right"><input type="text" name="tax_price"
									autocomplete="off" class="form-control form-control-sm"
									value="<fmt:formatNumber value="${row_item.tax_price}" pattern="#,###" />" 
									onkeyup="fn_autoInputTotalValue($(this))">
								</td>
								<td class="text-right"><input type="text"
									name="total_price" autocomplete="off"
									class="form-control form-control-sm"
									value="<fmt:formatNumber value="${row_item.total_price}" pattern="#,###" />" 
									onkeyup="fn_autoInputTotalValue($(this))">
								</td>
								<td class="text-center">
									<button class="btn btn-sm"
										onclick="fn_deleteParentParentForItem($(this))">
										<i class="fas fa-times-circle"></i>
									</button>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
				<!-- fn_addItem()으로 거래 내역 추가될 자리 -->
			</tbody>
		</table>
	</div>
	<!-- /.item Table -->

	<!-- Memo Table -->
	<div class="row table-responsive-sm" id="update_memo_table">
		<table id="updateMemoTable"
			class="table table-sm table-hover table-bordered text-nowrap"
			style="font-size: 14px;">
			<tbody>
				<c:choose>
					<c:when test="${fn:length(memoList) > 0}">
						<c:forEach var="row_memo" varStatus="status" items="${memoList}">

							<tr>
								<td class="text-center bg-warning" style="width: 5%">메모</td>
								<td><input type="text" name="memo" autocomplete="off"
									class="form-control form-control-sm"
									value="${row_memo.content}"></td>
								<td style="width: 3%" class="text-center">
									<button class="btn btn-sm"
										onclick="fn_deleteParentParent($(this))">
										<i class="fas fa-times-circle"></i>
									</button>
								</td>
							</tr>
							<!-- fn_addMemo()으로 메모 추가될 자리 -->
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</div>
	<!-- /.memo Table -->

	<!-- Row - 이미지 파일, 기타 첨부 파일 -->
	<div class="row">

		<div class="col-6">
			<div class="col-sm-12 mb-2">
				<div class="d-inline-flex">견적서 파일 ▶</div>
				<div class="d-inline-flex flex-column" id="update_quote_file_div">
					<c:choose>
						<c:when test="${fn:length(quoteFileList) > 0}">
							<c:forEach var="row_quoteFile" varStatus="status"
								items="${quoteFileList}">
								<div class="input-group mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="fas fa-paperclip"></i></span>
									</div>
									<input type="text" value="${row_quoteFile.file_name}"
										class="form-control bg-white" readonly>
									<input type="hidden" name="existingFile_idx" value="${row_quoteFile.file_idx}">
									<input type="hidden" name="existingFile_type" value="${row_quoteFile.file_type}">
									<button type="button" class="btn btn-sm"
										onclick="fn_deleteFile($(this))">
										<i class="fas fa-times-circle"></i>
									</button>
								</div>
							</c:forEach>
						</c:when>
					</c:choose>
					<!-- input[type=file] 들어갈 자리 -->
				</div>
			</div>

			<div class="col-sm-12">
				<div class="d-inline-flex">지시서 파일 ▶</div>
				<div class="d-inline-flex flex-column" id="update_order_file_div">
					<c:choose>
						<c:when test="${fn:length(orderFileList) > 0}">
							<c:forEach var="row_orderFile" varStatus="status"
								items="${orderFileList}">
								<div class="input-group mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="fas fa-paperclip"></i></span>
									</div>
									<input type="text" value="${row_orderFile.file_name}"
										class="form-control bg-white" readonly>
									<input type="hidden" name="existingFile_idx" value="${row_orderFile.file_idx}">
									<input type="hidden" name="existingFile_type" value="${row_orderFile.file_type}">
									<button type="button" class="btn btn-sm"
										onclick="fn_deleteFile($(this))">
										<i class="fas fa-times-circle"></i>
									</button>
								</div>
							</c:forEach>
						</c:when>
					</c:choose>
					<!-- input[type=file] 들어갈 자리 -->
				</div>
			</div>
		</div>

		<div class="col-6">
			<div class="col-sm-12 text-right mb-2">
				<div class="d-inline-flex flex-column" id="update_image_file_div">
					<c:choose>
						<c:when test="${fn:length(imageFileList) > 0}">
							<c:forEach var="row_imageFile" varStatus="status"
								items="${imageFileList}">
								<div class="input-group mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="fas fa-paperclip"></i></span>
									</div>
									<input type="text" value="${row_imageFile.file_name}"
										class="form-control bg-white" readonly>
									<input type="hidden" name="existingFile_idx" value="${row_imageFile.file_idx}">
									<input type="hidden" name="existingFile_type" value="${row_imageFile.file_type}">
									<button type="button" class="btn btn-sm"
										onclick="fn_deleteFile($(this))">
										<i class="fas fa-times-circle"></i>
									</button>
								</div>
							</c:forEach>
						</c:when>
					</c:choose>
					<!-- input[type=file] 들어갈 자리 -->
				</div>
				<div class="d-inline-flex">◀ 이 미 지&nbsp;&nbsp;&nbsp;&nbsp;파 일</div>
			</div>
			<div class="col-sm-12 text-right">
				<div class="d-inline-flex flex-column" id="update_other_file_div">
					<c:choose>
						<c:when test="${fn:length(otherFileList) > 0}">
							<c:forEach var="row_otherFile" varStatus="status"
								items="${otherFileList}">
								<div class="input-group mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="fas fa-paperclip"></i></span>
									</div>
									<input type="text" value="${row_otherFile.file_name}"
										class="form-control bg-white" readonly>
									<input type="hidden" name="existingFile_idx" value="${row_otherFile.file_idx}">
									<input type="hidden" name="existingFile_type" value="${row_otherFile.file_type}">
									<button type="button" class="btn btn-sm"
										onclick="fn_deleteFile($(this))">
										<i class="fas fa-times-circle"></i>
									</button>
								</div>
							</c:forEach>
						</c:when>
					</c:choose>
					<!-- input[type=file] 들어갈 자리 -->
				</div>
				<div class="d-inline-flex">◀ 기타 첨부 파일</div>
			</div>

		</div>

	</div>
	<!-- Row - 이미지 파일, 파일 -->
</div>

<div class="modal-footer justify-content-between">
	<button type="button" class="btn btn-default"
		onclick="fn_confirmResetTransactionModal('update')">리셋</button>
	<div>
		<button type="button" class="btn btn-info"
			onclick="fn_addItem('update')">내역+</button>
		<button type="button" class="btn btn-info"
			onclick="fn_addMemo('update')">메모+</button>
		<button type="button" class="btn btn-secondary"
			onclick="fn_addFile('update', 'quote')">견적서+</button>
		<button type="button" class="btn btn-secondary"
			onclick="fn_addFile('update', 'order')">지시서+</button>
		<button type="button" class="btn btn-secondary"
			onclick="fn_addFile('update', 'image')">이미지 파일+</button>
		<button type="button" class="btn btn-secondary"
			onclick="fn_addFile('update', 'other')">기타 파일+</button>
	</div>
	<button type="button" class="btn btn-primary"
		onclick="fn_updateTransaction()">수정하기</button>
</div>