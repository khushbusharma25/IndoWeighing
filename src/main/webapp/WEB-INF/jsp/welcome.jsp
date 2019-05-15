<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Indo</title>
<jsp:include page="header.jsp"></jsp:include>
<body ng-app="myApp" ng-controller="mainController">
	<div id="header"></div>
	<div class="container-fluid" >
		<div class="row">
			<div class="col-lg-8 offset-2 text-center header">
				<h2>Indo Weighing System</h2>
			</div>
			{{alert}}
			<button  ng-click="auth()" class="authButton"> Login</button>
			<button  ng-click="register()" class="authButton"> New User</button>
		</div>
		<div class="row">
				
			<div class="wrapper col-lg-3">
				<nav id="sidebar">
					<div id="sidebar-header">
						<h2>Menu</h2>
					</div>
					<ul class="list-unstyled components">
						<li class="active">
							<a href="#/!" data-toggle="collapse" area-expanded="false">	Products </a>
						</li>
						<li><a href="#!cart"  data-toggle="collapse" area-expanded="false">	My cart </a></li>
						<li><a href="#!users" data-toggle="collapse" area-expanded="false">	Users </a></li>
						<li><a href="#/queryForm" data-toggle="collapse" area-expanded="false">	Query </a></li>
						<li><a href="#/deliverdItems" data-toggle="collapse" area-expanded="false">	Delivered Items </a> </li>
					</ul>
				</nav>
			</div>
			<div  class="col-lg-6" id="products">
				<div ng-view></div> 
			</div>
				
				
				<div class="col-lg-3 col-lg-offset-1" id="sidebar">
					<h3>our services</h3>
					<div class="marquee">
						<marquee direction="up">
							<ul class="list-group services">
								<li>Selling</li>
								<li>Repairing</li>
								<li>Sealing</li>
								<li>Managing</li>
								<li>Coustomer Services</li>
								<li>Dairy Products</li>
								<li>Sealing</li>
								<li>Managing</li>
								<li>Coustomer Services</li>
								<li>Dairy Products</li>
							</ul>
						</marquee>
					</div>
				</div>
			
		</div>
			
	</div>
	



</body>
 <jsp:include page="footer.jsp"></jsp:include>
</html>