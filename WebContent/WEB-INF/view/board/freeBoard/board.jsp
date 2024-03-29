<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/Resource/css/bootstrap.css" rel="stylesheet">
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>    
	</head>
<body>
	<header>
		<nav class= "navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class = "container-fluid">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span> 
		    		</button>
					<div class="navbar-header">
				    	<a class="navbar-brand" href="${pageContext.request.contextPath}/main">SSZ의 블로그</a>
				    </div>
				    <div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">게시판</a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/freeBoard/board">자유게시판</a></li>
									<li><a href="#">갤러리</a></li>
									<li><a href="#">끄적끄적</a></li>
									<li><a href="${pageContext.request.contextPath}/databoard/list.do">자료실</a></li>
								</ul>
							</li>
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">연애능력평가</a>
								<ul class="dropdown-menu">
									<li><a href="research/researchPersonInfo.jsp">평가시작하기</a></li>
									<li><a href="research/Result.jsp">통계결과보기</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
		<div>
			<img src="${pageContext.request.contextPath}/Resource/img/nightView2.jpg" class="img-responsive">
		</div>
	</header>
		<div class="container" id="content" style="text-align:center;">
			    <div class="blog-header" style="margin-bottom:50px;">
			      <h1 class="blog-title">자유게시판</h1>
			    </div>
      		<div class="row" >
      			<div class="col-sm-2" ></div>
        		<div class="col-sm-8 blog-main">
					<div class="blog-post">
					<table class="table table-striped table-bordered table-hover">
					<thead>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
					</thead>
					<c:set var="number" value="${pageCommand.number}"/>
					<c:forEach var="article" items="${boardList}" varStatus="loop">
					<tbody>				
						<td><c:out value="${number}"/></td>
						<c:set var="number" value="${number-1}"/>
						<td>
						<a href="content?num=${article.num}&pageNum=${pageCommand.currentPage}">${article.title}</a>
						</td>
						<td>
							<a>${article.id}</a>
						</td>
						<td>${article.regdate}</td>
						<td>${article.readcount}</td>
					</tbody>
					</c:forEach>
					</table>
					</div>
					<div class="row">
					<c:if test="${pageCommand.count gt 0 }">
							<c:if test="${pageCommand.startPage gt pageCommand.pageBlock}">
								<a href="board?pageNum=${pageCommand.startPage-pageCommand.pageBlock}">[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${pageCommand.startPage}" end="${pageCommand.endPage}">
								<a href="board?pageNum=<c:out value="${i}"/>">[<c:out value="${i}"/>]</a>
							</c:forEach>
							<c:if test="${pageCommand.endPage lt pageCommand.pageCount}">
								<a href="${pageContext.request.contextPath}/freeBoard/board?pageNum=${pageCommand.startPage+pageCommand.pageBlock}">[다음]</a>
							</c:if>
					</c:if>
					<c:if test="${!empty authInfo.id}">
					<input class="col-lg-2 btn btn-primary" type="button" onClick="javascript:window.location='${pageContext.request.contextPath}/freeBoard/write'" style= "float:left;"value="글쓰기"></input>
					</c:if>
					</div>
				</div>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/Resource/js/bootstrap.min.js"></script>
</body>
</html>