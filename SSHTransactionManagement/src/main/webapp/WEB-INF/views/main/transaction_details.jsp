<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!-- Main content -->
<section class="content">
	<div class="container-fluid">
		
		<!-- Select Period & Search & Insert Btn -->
		<div class="row">
			<div class="col-12">
				<!-- Card -->
				<div class="card">
					<!-- Card-body -->
					<div class="card-body">
						<div class="row">
							<div class="col-1">
								<button type="button" class="btn btn-default"
									onclick="fn_openInsertOrderModal()">거래 입력</button>
							</div>
							
							<div class="col-2">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="far fa-calendar-alt"></i>
										</span>
									</div>
									<input type="text" class="form-control float-right text-center"
										id="selectPeriod">
								</div>
							</div>
								
							<div class="col text-center">
								${CompanyDetails.company_name} +수정 버튼
							</div>
							
							<div class="col">
								<div class="row float-right">
									<div>
										<input type="text" name="keyword" class="form-control"
													placeholder="Search" required>
									</div>
									<div>
										<button type="button" class="btn btn-default"
													id="doSearchBtn">
											<i class="fas fa-search"></i><b> 검색</b>
										</button>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.select period & search & insert btn -->
	
	</div>
</section>
<!-- /.main content -->
