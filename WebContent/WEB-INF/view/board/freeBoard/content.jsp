<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%
	pageContext.setAttribute("LF", "\n");
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
									<li><a href="${pageContext.request.contextPath}/research/researchPersonInfo.jsp">평가시작하기</a></li>
									<li><a href="${pageContext.request.contextPath}/research/Result.jsp">통계결과보기</a></li>
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
	<div class="container" id="content">
		<c:choose >
			<c:when test="${empty authInfo}">
			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="jumbotron">
					<form:form action="${pageContext.request.contextPath}/login" method="post" commandName="loginCommand">
						<h2 class="form-signin-heading">로그인</h2>
						<c:choose>
						<c:when test="${!empty REMEMBER}">
						<form:input class="form-control" path="id" placeholder="아이디" maxlength="20" value="${REMEMBER}" />
						</c:when>
						<c:otherwise>
						<form:input class="form-control" path="id" placeholder="아이디" maxlength="20" />
						</c:otherwise>
						</c:choose>
						<form:input class="form-control" path="password" type="password" placeholder="비밀번호" maxlength="20"/>
						<label>
						<form:checkbox path="rememberEmail"/>아이디 기억하기
						</label>
						<div class="form-group">
							<input type="submit" value="로그인" class="btn btn-primary">
							<input type="button" value="회원가입" onclick="javascript:window.location='${pageContext.request.contextPath}/member/join'" class="btn btn-default">
						</div>
						<form:errors />
						<form:errors path="id"/>
						<form:errors path="password"/>
					</form:form>
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="jumbotron">
					<form>
					<h4 class="form-signin-heading">${authInfo.name}님 반갑습니다.</h4>
					<input type="button" value="정보수정" class="btn btn-default" onClick="javascript:window.location='${pageContext.request.contextPath}/member/edit'">
					<input type="button" value="로그아웃" class="btn btn-default" onClick="javascript:window.location='${pageContext.request.contextPath}/member/logout'">
					</form>
				</div>
			</div>
			</c:otherwise>
	  </c:choose>
		<div class="container" id="main">
			<div class="blog-header" style="margin-bottom:50px; text-align:center">
		    	<h1 class="blog-title">자유게시판</h1>
			</div>
      		<div class="row">
        		<div class="col-sm-8 blog-main">
					<div class="blog-post">
					<form>
						<table class="table table-striped table-bordered">
							<tr>
								<th>글 번호</th>
								<td>${article.num}</td>
								<th>조회수</th>
								<td>${article.readcount}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${article.id}</td>
								<th>작성일</th>
								<td>${article.regdate}</td>
							</tr>
							<tr>
								<th>글제목</th>
								<c:set var="string1" value="${article.title}"/>
								<c:set var="string2" value="${fn:replace(string1, ' ', '&nbsp;')}" />
								<c:set var="string2" value="${fn:replace(string1, '<', '&lt;')}" />
								<c:set var="string2" value="${fn:replace(string1, '>', '&gt;')}" />
								<c:set var="string2" value="${fn:replace(string1, LF, '<br>')}" />
								<td colspan="3" class="contenttitle>">${string2}</td>
							</tr>
							<tr>
								<c:set var="string3" value="${article.content}"/>
								<c:set var="string4" value="${fn:replace(string3, ' ', '&nbsp;')}" />
								<c:set var="string4" value="${fn:replace(string3, '<', '&lt;')}" />
								<c:set var="string4" value="${fn:replace(string3, '>', '&gt;')}" />
								<c:set var="string4" value="${fn:replace(string3, LF, '<br>')}" />
								<th>글내용</th>
								<td colspan="3" class="content" style="height:200px;">${string4}</td>
							</tr>
							<tr>
						</table>
					<c:choose>
						<c:when test="${article.id eq authInfo.id}">
						<input class="btn btn-primary" type="button" value="글수정"
						onClick="document.location.href=
						'${pageContext.request.contextPath}/freeBoard/update?num=${article.num}&pageNum=${pageNum}'">
							&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="btn btn-default" type="button" value="글삭제"
						onClick="document.location.href=
						'${pageContext.request.contextPath}/freeBoard/delete?num=${article.num}&pageNum=${pageNum}'">
						    &nbsp;&nbsp;&nbsp;&nbsp;	
						<input class="btn btn-default" type="button" value="글목록"
							onClick="document.location.href=
							'board?pageNum=${pageNum}'">
					</c:when>
					<c:otherwise>
						<input type="button" value="글목록"
						onClick="document.location.href=
						'board.jsp?pageNum=${pageNum}'">
					</c:otherwise>
				</c:choose>
					</form>
				</div>
				<div class="jumbotron">
				<c:forEach var="comment" items="${commentList}" varStatus="loop">
					<div class="form-group">
						<label>${comment.id}</label><br>
						<label>${comment.content}</label><br>
						<label><fmt:formatDate value="${comment.regdate}" pattern="yyyy년MM월dd일HH시mm분ss초"/></label>
						<c:if test="${comment.id eq authInfo.id}">
						<input class="btn btn-primary" type="button" value="삭제" onClick="javascript:location.href='${pageContext.request.contextPath}/freeBoard/commentDelete?cnum=${comment.cnum}&bnum=${article.num}&pageNum=${pageNum}'">
						</c:if>
					</div>
				</c:forEach>
				<c:if test="${!empty authInfo}">
					<div class="form-group">
						<form action="commentInsert" method="POST">
						<label><input class="form-control" type="text" value="${authInfo.id}" disabled></label><br>
						<textarea class="form-control" name="content" cols="100" rows="3" ></textarea><br>
						<input name="bnum" value="${article.num}" hidden>
						<input name="id" value="${authInfo.id}" hidden>
						<input class="btn btn-primary" type="submit" value="등록">
						<input name="pageNum" value="${pageNum}" hidden>
						</form>
					</div>
				</c:if>
				</div>
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