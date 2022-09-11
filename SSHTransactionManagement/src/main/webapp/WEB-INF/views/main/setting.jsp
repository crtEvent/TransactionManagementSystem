<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Main content : Setting Page -->
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
							<span class="align-middle h3 text-bold mb-0">&nbsp;설 정</span>
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

			<section class="col-lg-4">

				<!-- 1. Card - 파일 저장소 위치 변경 -->
				<div class="card my-2">
					<div class="card-header text-bold">파일 저장소 위치 변경</div>
					<!-- Card-body -->
					<div class="card-body py-2">
						
						<div class="form-group">
							<div class="input-group mb-3">
                  				<input type="text" class="form-control" name="root_path" value="${root_path}">
                  				<div class="input-group-append">
                   					<span class="input-group-text">수 정</span>
                 				</div>
                			</div>
						</div>

					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->

				<!-- 2. Card - DB 백업 -->
				<div class="card my-2">
					<div class="card-header text-bold">Database 백업</div>
					<!-- Card-body -->
					<div class="card-body py-2 text-center">
					
						<a class="btn btn-app" onclick="fn_backupDB()">
                  			<i class="fas fa-save"></i> DB 백업
                		</a>
                		<div>저장소/backup 폴더에 저장됩니다.</div>
                		
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->

			</section>
			<!-- /.col-lg-6 -->

			<section class="col-lg-8"></section>
			<!-- /.col-lg-6 -->

		</div>
		<!-- /.main body row -->

	</div>
</section>
<!-- /.main content : setting page -->