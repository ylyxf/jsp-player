<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/include/head.jsp"%>
</head>
<body>
	<div class="container-fluid">

		<!-- 顶部菜单 -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">jsp-player</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li id="apiList" class="navlink" link="${appPath}/api/List.jsp">
							<a href="#">API</a>
						</li>
						<li id="proxyList" class="navlink"
							link="${appPath}/proxy/List.jsp"><a href="#">代理</a></li>

					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a id="json">json</a></li>
						<li><a id="jsp">jsp</a></li>
						<li><a id="js">js</a></li>
						<li><a id="database">数据库</a></li>
					</ul>

				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>


		<!-- iframe框架 -->
		<div class="row">
			<div class="col-md-12" style="height: calc(100vh - 80px)">
				<iframe id="workareaFrame"
					style="height: 100%; width: 100%; border: none;"> </iframe>
			</div>
		</div>

	</div>
</body>
<script>

	$(document).ready(function() {
		$(".navlink").on('click', function() {
			$(".navlink").removeClass("active");
			$(this).addClass("active");
			$("#workareaFrame").attr("src", $(this).attr("link"));
		});

		$("#apiList").click();

		$("#database").on('click', function() {
			$.get('${appPath}/database/index.jsp', function(data) {
				window.open(data, 'jsp-player-database')
			})
		});
		
		$("#json").on('click', function() {
			$.post('${appPath}/json/do',{a:'x',b:'y',c:{d:{e:'z'}}}, function(data) {
				console.log(data);
			});
		});
		
		$("#js").on('click', function() {
			$.post('${appPath}/js/do',{a:'x',b:'y',c:{d:{e:'z'}}}, function(data) {
				console.log(data);
			});
		});
		
		$("#jsp").on('click', function() {
			$.post('${appPath}/jsp/do',{a:'x',b:'y',c:{d:{e:'z'}}}, function(data) {
				console.log(data);
			});
		});
		
	});
</script>

</html>