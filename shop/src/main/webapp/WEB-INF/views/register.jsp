 <%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="false"%>
<html>
                                 <jsp:include page="fragments/static_files.jsp" />
<body>
	<div id="all">

		                         <jsp:include page="fragments/shop_header.jsp" />
  <div id="heading-breadcrumbs">
        <div class="container">
          <div class="row">
            <div class="col-md-7">
              <h1>New account / Sign in</h1>
            </div>
            <div class="col-md-5">
              <ul class="breadcrumb">
                <li>
                  <a href="index.html">Home</a>
                </li>
                <li>New account / Sign in</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div id="content">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <div class="box">
                <h2 class="text-uppercase">New account</h2>
                <p class="lead">Not our registered customer yet?</p>
                <p>With registration with us new world of fashion, fantastic discounts and
                  much more opens to you! The whole process will not take you more than a
                  minute!</p>
                <p class="text-muted">If you have any questions, please feel free to
                  <a href="contact.html">contact us</a>, our customer service center is working for you 24/7.</p>
                <hr>
                <form action="customer-orders.html" method="post">
                  <div class="form-group">
                    <label for="name-login">Name</label>
                    <input type="text" class="form-control" id="name-login">
                  </div>
                  <div class="form-group">
                    <label for="email-login">Email</label>
                    <input type="text" class="form-control" id="email-login">
                  </div>
                  <div class="form-group">
                    <label for="password-login">Password</label>
                    <input type="password" class="form-control" id="password-login">
                  </div>
                  <div class="text-center">
                    <button type="submit" class="btn btn-template-main">
                      <i class="fa fa-user-md"></i>Register</button>
                  </div>
                </form>
              </div>
            </div>
            <div class="col-md-6">
              <div class="box">
                <h2 class="text-uppercase">Заходи в свою пещеру!</h2>
                <p class="lead">Уже наш чел?</p>
                <p class="text-muted">Воу. Велкам снова на наш сайт. Ну ёлы палы, как же круто, что ты снова с нами.
                У нас столько всего нового... Просто залогинься и вперед за покупками, Йихууу!!!</p>
                <hr>
                <form action="<spring:url value="/register"/>" method="post">
                  <div class="form-group">
                    <label for="email">Login Name</label>
                    <input type="text" class="form-control" name="custom_username" >
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="custom_password" >
                  </div>
                  <div class="text-center">
                  
  <sec:csrfInput />           
  
                  <c:if test="${param.error != null }">
				    <p>Invalid Username and Password.</p>
			      </c:if>  
			      
			      <c:if test="${param.logout != null }">
				   <p>Вы вышли из аккаунта, Пичалька...</p>
			      </c:if>  
			       
                    <button type="submit" class="btn btn-template-main">
                      <i class="fa fa-sign-in"></i>Log in</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <!-- /.row -->
        </div>
        <!-- /.container -->
      </div>
      <!-- /#content -->
                               <jsp:include page="fragments/shop_footer.jsp" />
	                               
	   </div>
	<!-- /#all -->                 
</html>