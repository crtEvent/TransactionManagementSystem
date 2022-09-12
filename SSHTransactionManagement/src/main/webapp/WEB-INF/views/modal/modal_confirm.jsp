<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Insert Company Modal -->
<div class="modal fade" id="confirmModal">
	<div class="modal-dialog modal-default">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title">삭제하기</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>

			<div class="modal-body text-center">
				
				<div>삭제된 데이터는 복구할 수 없습니다.</div>
				<div>해당 업체를 삭제하시겠습니까?</div>
				
			</div>
			
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">닫기</button>
				<button id="confirmBtn" type="button" class="btn btn-primary">삭제하기</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>