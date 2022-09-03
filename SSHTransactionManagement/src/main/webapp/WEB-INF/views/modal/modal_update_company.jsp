<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Insert Company Modal -->
<div class="modal fade" id="updateCompanyModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title">업체 수정</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>

			<div class="modal-body">
				<!-- Row 1 -->
				<div class="row">
					<div class="col-6 pr-3">
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">업체명</label>
							<div class="col-sm-10">
								<input name="company_name" class="form-control"
										type="text" maxlength="50" autocomplete="off">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center"><small>등록No</small></label>
							<div class="col-sm-10">
								<input name="company_reg_num" class="form-control"
										type="text" maxlength="20" autocomplete="off">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">대표자</label>
							<div class="col-sm-10">
								<input name="representative" class="form-control"
										type="text" maxlength="50" autocomplete="off">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">주소1</label>
							<div class="col-sm-10">
								<input name="address_1" class="form-control"
										type="text" maxlength="1000" autocomplete="off">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">주소2</label>
							<div class="col-sm-10">
								<input name="address_2" class="form-control"
										type="text" maxlength="1000" autocomplete="off">
							</div>
						</div>
					</div>
					
					<div class="col-6 pr-3">
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">tel</label>
							<div class="col-sm-10">
								<input name="tel" class="form-control"
										type="text" maxlength="50" autocomplete="off">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">fax</label>
							<div class="col-sm-10">
								<input name="fax" class="form-control"
										type="text" maxlength="50" autocomplete="off">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">Mobile</label>
							<div class="col-sm-10">
								<input name="mobile" class="form-control"
										type="text" maxlength="50" autocomplete="off">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label text-center">기타</label>
							<div class="col-sm-10">
								<textarea name="ect" class="form-control" rows="2" style="height: 90px;"></textarea>
								<div class="float-right" name="ectFieldLength">(0 / 최대 3,000자)</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer justify-content-between">
				<button id="resetUpdateCompanyModalBtn" type="button" class="btn btn-default">다시 입력</button>
				<button id="updateCompanyBtn" type="button" class="btn btn-primary">등록</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>