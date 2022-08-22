<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Insert Modal -->
<div class="modal fade" id="insertTransactionModal">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-xl">
		<!-- Modal-content -->
		<div class="modal-content card-info">

			<div class="modal-header">
				<!-- Modal 타이틀 -->
				<h4 class="modal-title">거래 내역 입력</h4>
				<!-- Order Group 제목 -->
				<div class="col">
					<div class="input-group col-8 float-right">
						<div class="input-group-prepend">
							<button type="button" class="btn btn-default">제목</button>
						</div>
						<input type="text" name="subject" class="form-control" autocomplete="off">
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
								<input type="date" name="date" class="form-control" value="">
							</div>
							<!-- 거래처 -->
							<div class="input-group">
								<div class="input-group-prepend">
									<button type="button" class="btn btn-default" style="font-size:11px;">거래처 No</button>
								</div>
								<input type="text" name="company_idx" class="form-control text-center" autocomplete="off">
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
								<input type="text" name="total_supply_price" class="form-control text-right" autocomplete="off" readonly>
							</div>
							<!-- 총 부가세 -->
							<div class="input-group float-right">
								<div class="input-group-prepend">
									<button type="button" class="btn btn-default">부가세</button>
								</div>
								<input type="text" name="total_tax_price" class="form-control text-right" autocomplete="off" readonly>
							</div>
							<!-- 총 합계 -->
							<div class="input-group float-right mb-3">
								<div class="input-group-prepend">
									<button type="button" class="btn btn-default">총합계</button>
								</div>
								<input type="text" name="total_total_price" class="form-control text-right" autocomplete="off" readonly>
							</div>
						</div>
					</div>
					<!-- /.row - 날짜, 총 공급가, 총 부가세, 총 합계 -->
					
					<!-- Item Table -->
					<div class="row table-responsive-sm">
						<table id="insertItemTable"
							class="table table-sm table-hover table-bordered text-nowrap  mb-1" style="font-size:14px;">
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
							<tbody>
								<!-- fn_addItem()으로 거래 내역 추가될 자리 -->
							</tbody>
						</table>
					</div>
					<!-- /.item Table -->
					
					<!-- Memo Table -->
					<div class="row table-responsive-sm" id="insert_memo_table">
						<table id="insertMemoTable"
							class="table table-sm table-hover table-bordered text-nowrap" style="font-size:14px;">
							<tbody>
								<!-- fn_addMemo()으로 거래 내역 추가될 자리 -->
							</tbody>
						</table>
					</div>
					<!-- /.memo Table -->
					
					<!-- Row - 이미지 파일, 기타 첨부 파일 -->
					<div class="row">
					
						<div class="col-6">
							<div class="col-sm-12 mb-2">
								<div class="d-inline-flex">견적서 파일 ▶</div>
								<div class="d-inline-flex flex-column" id="insert_quote_file_div">
									<!-- input[type=file] 들어갈 자리 -->
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="d-inline-flex">지시서 파일 ▶</div>
								<div class="d-inline-flex flex-column" id="insert_order_file_div">
									<!-- input[type=file] 들어갈 자리 -->
								</div>
							</div>
						</div>
						
						<div class="col-6">
							<div class="col-sm-12 text-right mb-2">
								<div class="d-inline-flex flex-column" id="insert_image_file_div">
								<!-- input[type=file] 들어갈 자리 -->
								</div>
								<div class="d-inline-flex">◀ 이 미 지&nbsp;&nbsp;&nbsp;&nbsp;파 일</div>
							</div>
							<div class="col-sm-12 text-right">
								<div class="d-inline-flex flex-column" id="insert_other_file_div">
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
					onclick="fn_confirmResetTransactionModal('insert')">리셋</button>
				<div>
					<button type="button" class="btn btn-info"
					onclick="fn_addItem()">내역+</button>
					<button type="button" class="btn btn-info"
					onclick="fn_addMemo()">메모+</button>
					<button type="button" class="btn btn-secondary"
					onclick="fn_addFile('insert', 'quote')">견적서+</button>
					<button type="button" class="btn btn-secondary"
					onclick="fn_addFile('insert', 'order')">지시서+</button>
					<button type="button" class="btn btn-secondary"
					onclick="fn_addFile('insert', 'image')">이미지 파일+</button>
					<button type="button" class="btn btn-secondary"
					onclick="fn_addFile('insert', 'other')">기타 파일+</button>
				</div>
				<button type="button" class="btn btn-primary"
					onclick="fn_insertTransaction()">입력하기</button>
			</div>
			
		</div>
		<!--  /.modal content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.insert modal -->