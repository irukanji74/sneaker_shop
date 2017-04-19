 <%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
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
              <h1>Регистрация / Войти</h1>
            </div>
            <div class="col-md-5">
              <ul class="breadcrumb">
                <li>
                  <a href="index.html">Home</a>
                </li>
                <li>Регистрация / Войти</li>
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
                <h2 class="text-uppercase">Регистрация</h2>
                <p class="lead">Not our registered customer yet?</p>
                <p>With registration with us new world of fashion, fantastic discounts and
                  much more opens to you! The whole process will not take you more than a
                  minute!</p>
                <p class="text-muted">If you have any questions, please feel free to
                  <a href="contact.html">contact us</a>, our customer service center is working for you 24/7.</p>
                <hr>
                
         <spring:url value="/register" var="toRegister"/>
         <form:form method="POST" action="${toRegister}" modelAttribute="customerDto" >
               
                  <div class="form-group">
                    <form:label path="firstName">Name</form:label>
                    <form:input type="text" cssClass="form-control" path="firstName"/>
                    <form:errors path="firstName"/>
                  </div>
                  
                  <div class="form-group">
                    <form:label path="email">Email</form:label>
                    <form:input class="form-control" path="email"/>
                    <form:errors path="email"/>
                    <h3>${email_exists}</h3>
                  </div>
                  
                  <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input type="password" class="form-control" path="password"/>
                    <form:errors path="password"/>
                  </div>
                  
                  <div class="form-group">
                    <form:label path="matchingPassword">Confirm Password</form:label>
                    <form:input type="password" class="form-control" path="matchingPassword"/>
                    <form:errors path="matchingPassword"/>
                  </div>
                  <h3>${Welcome_Message}</h3>
                  <div class="text-center">
    
    <!-- Выключается с помощью  <security:csrf disabled="true"/> в security-context.xml-->               
  <%--    <sec:csrfInput />  --%>   
           
                    <button type="submit" class="btn btn-template-main">
                      <i class="fa fa-user-md"></i>Register</button>
                  </div>
         </form:form>
              </div>
            </div>
            
            <div class="col-md-6">
              <div class="box">
                <h2 class="text-uppercase">Заходи в свою пещеру!</h2>
                <p class="lead">Уже наш чел?</p>
                <p class="text-muted">Воу. Велкам снова на наш сайт. Ну ёлы палы, как же круто, что ты снова с нами.
                У нас столько всего нового... Просто залогинься и вперед за покупками, Йихууу!!!</p>
                <hr>
                  
        <spring:url value="/to_login" var="toLogin"/>
        <form:form method="POST" action="${toLogin}">
         
                  <div class="form-group">
                    <label for="email">Ваш email</label>
                    <input type="text" class="form-control" name="custom_username" >
                  </div>
                  <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password" class="form-control" name="custom_password" >
                  </div>
                  <div class="text-center">
                  
 <%--  <sec:csrfInput />   --%>         
  
                  <c:if test="${param.error != null }">
				    <p>Неправильный email или пароль.</p>
			      </c:if>  
			      
			      <c:if test="${param.logout != null }">
				   <p>Вы вышли из аккаунта, Пичалька...</p>
			      </c:if>  
			       
                    <button type="submit" class="btn btn-template-main">
                      <i class="fa fa-sign-in"></i>Log in</button>
                  </div>
         </form:form>
         <a href="#" class="btn btn-link">Забыл пароль?</a>
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