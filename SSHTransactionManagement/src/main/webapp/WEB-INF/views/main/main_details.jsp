<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!-- Main content -->
<section class="content">
	<div class="container-fluid">
		
		<!-- Main Head Row -->
		<div class="row">
			<div class="col-12">
				<!-- Card -->
				<div class="card my-2" id="mainDetailsCard">
					<!-- Card-body -->
					<div class="card-body py-2">
					
						<div class="row">
							<div class="col-4">
								<span class="align-middle h3 text-bold">${companyDTO.company_name}</span>
								<input type="hidden" name="company_idx" value="${companyDTO.company_idx}">
								<button class="btn btn-primary btn-sm" title="수정" onclick="fn_openUpdateCompanyModal(${companyDTO.company_idx})">
									<i class="fas fa-pencil-alt"></i>
								</button>
								<button class="btn btn-danger btn-sm" title="삭제" onclick="fn_deleteCompany(${companyDTO.company_idx})">
									<i class="fas fa-trash-alt"></i>
								</button>
							</div>
							<div class="col-4 text-center align-middle">
							</div>
							<div class="col-4 text-right">
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-flat" onclick="fn_getCompanyDetails(${companyDTO.company_idx})">
										목 록
									</button>
									<button type="button" class="btn btn-default btn-flat">
										지서서
									</button>
									<button type="button" class="btn btn-default btn-flat">
										견적서
									</button>
									<button type="button" class="btn btn-default btn-flat" onclick="fn_viewImageFiles(${companyDTO.company_idx}, '')">
										사 진
									</button>
									<button type="button" class="btn btn-default btn-flat" onclick="fn_getInventoryListByCompany(${companyDTO.company_idx})">
										전용 품목
									</button>
								</div>
							</div>
						</div><!-- /.row -->
						
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.main head row -->
		
		<!-- Main Body Row -->
		<div class="row" id="mainBodyRow">
			<div class="col-5" id="TrListDiv">
				<%@include file="./transaction_list.jsp" %>
			</div>
			<div class="col-7" id="trDetailsDiv">
			</div>
		</div>
		<!-- /.main body row -->
		
	</div>
</section>
<!-- /.main content -->
