<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block" onclick="fn_getInventoryManagementPage()">
      	<button type="button" class="btn btn-block btn-default">재고관리</button>
      </li>
      <li class="nav-item d-none d-sm-inline-block" onclick="fn_getSettingPage()">
      	<button type="button" class="btn btn-block btn-default">설정</button>
      </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto" id="rightNavBar">
      <li class="nav-item">
      	<div class="nav-link">
      		<i class="fas fa-user"></i>
      		<span id="userInfo">&nbsp;
      			<c:if test="${not empty userSession.user_idx}">
      				${userSession.user_id}
      			</c:if>
      		</span>
      	</div>
      </li>
      <c:if test="${not empty userSession.user_idx}">
      	<li id="logoutLiTag" class="nav-item">
      		<div class="nav-link" onclick="fn_logout()">로그아웃</div>
      	</li>
      </c:if>
    </ul>
  </nav>
  <!-- /.navbar -->