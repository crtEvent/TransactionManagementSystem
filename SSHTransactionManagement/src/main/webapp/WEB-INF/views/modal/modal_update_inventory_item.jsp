<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Insert Inventory Item Modal -->
<div class="modal fade" id="updateInventoryItemModal">
	<!-- Modal-dialog -->
	<div class="modal-dialog">
		<!-- Modal-content -->
		<div class="modal-content card-info">

			<div class="modal-header">
				<!-- Modal 타이틀 -->
				<h4 class="modal-title">재고 품목 수정&nbsp;</h4>
				<button class="btn btn-danger btn-sm" name="deleteBtn" title="삭제" onclick="">
					<i class="fas fa-trash-alt"></i>
				</button>
				<!-- Modal 닫기 -->
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			
			<div class="modal-body">
				<input name="item_idx" type="hidden" value="">
				
				<!-- 1. 업체 IDX -->
				<div class="row">
					<div class="col-6">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">업&nbsp;체&nbsp;&nbsp;IDX</button>
							</div>
							<input name="company_idx" type="text" autocomplete="off"
								class="form-control" onkeyup="fn_checkCompnayIdxInInventoryModal($(this), 'update')">
						</div>
					</div>
					<div class="col-6 mt-1" id="updateCompanyIdxMsg">
						
					</div>
				</div>
				
				<!-- 2. 품목 코드 -->
				<div class="row">
					<div class="col-6">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">품목코드</button>
							</div>
							<input name="item_code" type="text" autocomplete="off"
								maxlength="45" class="form-control" onkeyup="fn_checkItemCode($(this), 'update')">
							<input name="old_item_code" type="hidden" value=""> 
						</div>
					</div>
					<div class="col-6 mt-1" id="updateItemCodeMsg">
						
					</div>
				</div>
				
				<!-- 3. 내용 -->
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<button type="button" class="btn btn-default">내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</button>
					</div>
					<input name="content" type="text" autocomplete="off"
						maxlength="300" class="form-control">
				</div>
				
				<!-- 4. 공급가 -->
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<button type="button" class="btn btn-default">단&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가</button>
					</div>
					<input name="unit_price" type="text" autocomplete="off"
						class="form-control" onkeyup="fn_checkNumberOnly($(this))">
				</div>
				
				<!-- 5. 초기수량 -->
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<button type="button" class="btn btn-default">초기수량</button>
					</div>
					<input name="initial_quantity" type="text" autocomplete="off"
						class="form-control" onkeyup="fn_checkNumberOnly($(this))">
				</div>
				
				<!-- 6. 파일 -->
				<div class="row">
					<div class="col-12">▶ 첨부파일 ◀</div>
				</div>
				<div class="row">
					<div class="col-12" id="update_inventory_file_div">
						
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" 
					onclick="fn_resetIventroyModal('update')">리셋</button>
					<div>
						<button type="button" class="btn btn-info"
							onclick="fn_addInvenFile('update')">파일+</button>
						<button type="button" class="btn btn-info"
							onclick="fn_openItemHistoryModal('update')">거래내역</button>
					</div>
				<button type="button" class="btn btn-primary" name="updateBtn"
					onclick="updateInventory()">수정하기</button>
			</div>
			
		</div>
		<!--  /.modal content -->
	</div>
	<!-- /.modal-dialog -->
</div>