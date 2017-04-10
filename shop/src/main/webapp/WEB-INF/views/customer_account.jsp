<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
                        <h1>My account</h1>
                    </div>
                    <div class="col-md-5">
                        <ul class="breadcrumb">

                            <li><a href="index.html">Home</a>
                            </li>
                            <li>My account</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div id="content" class="clearfix">

            <div class="container">

                <div class="row">

                    <!-- *** LEFT COLUMN ***
			 _________________________________________________________ -->

                    <div class="col-md-9 clearfix" id="customer-account">

                        <p class="lead">Здесь Вы можете изменить свои персональные данные.</p>
                        <p class="text-muted">Pellentesque habitant morbi tristique senectus et.</p>

                        <div class="box">

                            <div class="heading">
                                <h3 class="text-uppercase">Изменить пароль!</h3>
                            </div>


 <spring:url value="/changePassword" var="changePass"/>
            <form:form method="POST" action="${changePass}" modelAttribute="changePassForm" >
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <form:label path="oldPassword">Old password</form:label>
                                            <form:password path="oldPassword" cssClass="form-control" id="password_old"/>
                                            <form:errors path="oldPassword" cssClass="error"/>
                                            <h3>${wrong_password}</h3>
                                           <%--  <h3>${wrong_password}</h3> --%>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <form:label path="newPassword">New password</form:label>
                                            <form:password path="newPassword" cssClass="form-control" id="new_Password"/>
                                            <form:errors path="newPassword"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <form:label path="newPasswordConfirm">Retype new password</form:label>
                                            <form:password path="newPasswordConfirm" cssClass="form-control" id="new_PasswordConfirm"/>
                                            <form:errors path="newPasswordConfirm"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.row -->
                                
<%-- <h2>Your password is : ${changePassword.newPassword}</h2> --%>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-template-main"><i class="fa fa-save"></i> Save new password</button>
                                </div>
                            </form:form>

                        </div>
                        <!-- /.box -->


                    <div class="box clearfix">
                <div class="heading">
                  <h3 class="text-uppercase">Personal details</h3>
                </div>
                
 <spring:url value="/alterCustomer" var="alterCust"/>
                <form:form  method="POST" action="${alterCust}" modelAttribute="customerToAlter">
                  <div class="row">
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label for="firstname">Firstname</label>
                        <form:input path="name" cssClass="form-control" id="firstname"/>
                      </div>
                    </div>
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label for="lastname">Lastname</label>
                        <form:input path="lastName" cssClass="form-control" id="lastname"/>
                      </div>
                    </div>
                  </div>
                  <!-- /.row -->
                  <div class="row">
                    <div class="col-sm-6"><div class="form-group">
                        <label for="phone">Telephone</label>
                        <form:input path="telephone" cssClass="form-control" id="phone"/>
                      </div></div>
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label for="street">Street</label>
                         <form:input path="address" cssClass="form-control" id="street"/>
                         <form:errors path="address"/>
                      </div>
                    </div>
                  </div>
                  <!-- /.row -->
                  <div class="row">
                    <div class="col-sm-6 col-md-3">
                    
                    <div class="form-group">
                        <form:label path="zipCode">ZIP</form:label>
                        <form:input path="zipCode" cssClass="form-control" id="zip"/>
                      </div></div>
                    <div class="col-sm-6 col-md-3">
                      
                    <div class="form-group">
                        <label for="country">Country</label>
                        <select class="form-control" id="country"></select>
                      </div></div>
                    
                    <div class="col-sm-6 col-md-3">
                      
                    <div class="form-group">
                        <label for="email_account">Email</label>
                        <form:input path="email" cssClass="form-control" id="email_account" />
                      </div></div>
                    <div class="col-sm-6">
                      
                    </div>
                    <div class="col-sm-6">
                      
                    </div>
                    <div class="col-sm-12 text-center">
                      <button type="submit" class="btn btn-template-main">
                        <i class="fa fa-save"></i>Save changes</button>
                    </div>
                  </div>
                </form:form>
              </div>
                    </div>
                    <!-- /.col-md-9 -->

                    <!-- *** LEFT COLUMN END *** -->

                    <!-- *** RIGHT COLUMN ***
			 _________________________________________________________ -->

                    <div class="col-md-3">
                        <!-- *** CUSTOMER MENU ***
 _________________________________________________________ -->
                        <div class="panel panel-default sidebar-menu">

                            <div class="panel-heading">
                                <h3 class="panel-title">Customer section</h3>
                            </div>

                            <div class="panel-body">

                                <ul class="nav nav-pills nav-stacked">
                                    <li class="active">
                                        <a href="customer-orders.html"><i class="fa fa-list"></i> My orders</a>
                                    </li>
                                    <li>
                                        <a href="customer-wishlist.html"><i class="fa fa-heart"></i> My wishlist</a>
                                    </li>
                                    <li>
                                        <a href="customer-account.html"><i class="fa fa-user"></i> My account</a>
                                    </li>
                                    
     <!-- ***при нажатии Logout срабатывает global.js скрипт и вместо # исполняет "logout-form" c url /bambam
       security-context перехватывает /bambam (прописано в security:logout...) и понимает, что нужно клиента разлогинить!!! и перекинуть по
       logout-success-url="..." адресу    метод POST обязателен!!!*** -->                               
                                    <li>
                                        <a id="logout" href="#"><i class="fa fa-sign-out"></i> Logout</a>
                                        <form id="logout-form" action="<c:url value="/bambam"/>" method="post">
		<sec:csrfInput/>
							</form>
                                    </li>
                                    
                                    
                                </ul>
                            </div>

                        </div>
                        <!-- /.col-md-3 -->

                        <!-- *** CUSTOMER MENU END *** -->
                    </div>

                    <!-- *** RIGHT COLUMN END *** -->

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