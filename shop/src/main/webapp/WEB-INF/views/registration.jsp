<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<spring:url value="/registerr" var="toRegister"/>
         <form:form method="POST" action="${toRegister}" modelAttribute="customer" >
               
                  <div class="form-group">
                    <form:label path="name">Name</form:label>
                    <form:input type="text" cssClass="form-control" path="name"/>
                    <form:errors path="name"/>
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
                    <form:label path="passwordConfirm">Confirm Password</form:label>
                    <form:input type="password" class="form-control" path="passwordConfirm"/>
                    <form:errors path="passwordConfirm"/>
                  </div>
                  <h3>${Welcome_Message}</h3>
                  <div class="text-center">
    
    <!-- Выключается с помощью  <security:csrf disabled="true"/> в security-context.xml-->               
  <%--    <sec:csrfInput />  --%>   
           
                    <button type="submit" class="btn btn-template-main">
                      <i class="fa fa-user-md"></i>Register</button>
                  </div>
         </form:form>

</body>
</html>