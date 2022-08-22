<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Card -->
<div class="card">
	<!-- <!-- Card-header -->
	<div class="card-header py-2">
		<span class="align-middle text-bold">
			<button class="btn btn-info btn-sm text-bold" onclick="fn_openInsertTransactionModal()">
				[+] 등록
			</button>
		</span>
	</div>
	<!-- Card-body -->
	<div class="card-body">
		<!-- jsGrid -->
		<div id="transactionListTable"></div>
	</div>
</div>