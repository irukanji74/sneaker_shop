 <%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="false"%>
<html>
                                 <jsp:include page="fragments/static_files.jsp" />
<body>
	<div id="all">

		                        <%--  <jsp:include page="fragments/shop_header.jsp" /> --%>
  <div id="heading-breadcrumbs">
        <div class="container">
          <div class="row">
            <div class="col-md-7">
              <h1>Восстановление пароля</h1>
            </div>
            
          </div>
        </div>
      </div>
      <div id="content">
        <div class="container">
        
        <div class="box">
        
        
   <sec:authorize access="hasRole('ROLE_USER')">
   
   
                            <div class="heading">
                                <h3 class="text-uppercase">Изменить пароль!</h3>
                            </div>

 <spring:url value="/changePassword" var="changePass"/>
 
            <form method="POST" action="${changePass}" > <!--   -->
                               <%--  <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <form:label path="oldPassword">Old password</form:label>
                                            <form:password path="oldPassword" cssClass="form-control" id="password_old"/>
                                            <form:errors path="oldPassword" cssClass="error"/>
                                            <h3>${wrong_password}</h3> 
                                        </div>
                                    </div>
                                </div> --%>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                        
                                 <input type = "password" name = "password" placeholder="New password"/>
                                        
                                          <%--   <form:label path="password">New password</form:label>
                                            <form:password path="password" cssClass="form-control" id="password"/> --%>
                                            <%-- <form:errors path="password"/> --%>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                        
                                   <input type = "password" name = "matchingPassword" placeholder="Retype new password"/>
                                   
                                           <%--  <form:label path="matchingPassword">Retype new password</form:label>
                                            <form:password path="matchingPassword" cssClass="form-control" id="matchingPassword"/> --%>
                                            <%-- <form:errors path="matchingPassword"/> --%>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.row -->
                                 <h3>${passwordsNotMatch}</h3>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-template-main"><i class="fa fa-save"></i> Reset password</button>
                                </div>
                            </form>
</sec:authorize>
                        </div>
                        <!-- /.box -->
        
          <!-- /.row -->
        </div>
        <!-- /.container -->
      </div>
      <!-- /#content -->
                               <jsp:include page="fragments/shop_footer.jsp" />
	                               
	   </div>
	<!-- /#all -->                 
</html>