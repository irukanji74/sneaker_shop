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
              <h1>Восстановление пароля</h1>
            </div>
            
          </div>
        </div>
      </div>
      <div id="content">
        <div class="container">
          <div class="row text-left">Введите ваш email</div>
         
          <div class="row">
            <div class="col-md-6">
              <form class="form-horizontal" role="form" method="post" action="/shop/resetPassword">
                <div class="form-group">
                 <!--  <div class="col-sm-2">
                    <label for="inputEmail3" class="control-label">Email</label>
                  </div> -->
                  <div class="col-sm-6">
                    <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email">
                    <h3>${customerNotFound}</h3>
                     <h3>${check_mail}</h3>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-template-main">Послать</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
          
         <%--  </form:form> --%>
          <!-- /.row -->
        </div>
        <!-- /.container -->
      </div>
      <!-- /#content -->
                               <jsp:include page="fragments/shop_footer.jsp" />
	                               
	   </div>
	<!-- /#all -->                 
</html>