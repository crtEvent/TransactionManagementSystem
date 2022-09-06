<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Main content : Inventory Management -->
<section class="content">
	<div class="container-fluid">
	
		<!-- Main Head Row -->
		<div class="row">
			<div class="col-12">
				<!-- Card -->
				<div class="card my-2">
					<!-- Card-body -->
					<div class="card-body py-2">
						
						<div class="row">
							<span class="align-middle h3 text-bold mb-0">&nbsp;재고관리</span>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.main head row -->
		
		<!-- Main Body Row -->
		<div class="row">
			<div class="col-12">
				<!-- Card -->
				<div class="card my-2">
					<!-- Card-body -->
					<div class="card-body py-2">
						
						<!-- jsGrid -->
						<div id="inventoryListTable"></div>
						
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div><!-- /.main body row -->
	</div>
</section>
<!-- /.main content : inventory management -->