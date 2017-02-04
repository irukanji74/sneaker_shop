<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Static files for jsp pages shop</title>

    <spring:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeMin"/>
    <link href="${fontAwesomeMin}" rel="stylesheet"/>
    
    <!-- Css animations  -->
    <spring:url value="/resources/css/animate.css" var="animate"/>
    <link href="${animate}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/style.default.css" var="style_default"/>
    <link href="${style_default}" rel="stylesheet" id="theme-stylesheet"/>
    
    <spring:url value="/resources/css/custom.css" var="custom"/>
    <link href="${custom}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/font-awesome.css" var="fontAwesome"/>
    <link href="${fontAwesome}" rel="stylesheet"/>
    
   
   
    <%-- 
    <spring:url value="/resources/css/prettyPhoto.css" var="prettyPhoto"/>
    <link href="${prettyPhoto}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/main.css" var="main"/>
    <link href="${main}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/responsive.css" var="responsive"/>
    <link href="${responsive}" rel="stylesheet"/>

    <spring:url value="/resources/images/ico/favicon.ico" var="favicon"/>
    <link href="${favicon}" rel="stylesheet"/>
    --%>
    
                      <!-- #### JAVASCRIPT FILES ### -->
    <spring:url value="/webjars/jquery/1.11.0/jquery.min.js" var="jQuery_min"/>
    <script src="${jQuery_min}"></script>
     <script>
        window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')
    </script>
    
    <spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js" var="bootstrapJs"/>
    <script src="${bootstrapJs}"></script>
    
    <spring:url value="/resources/js/jquery.cookie.js" var="Cookie_Js"/>
    <script src="${Cookie_Js}"></script>
    
    <spring:url value="/resources/js/global.js" var="global_Js"/>
    <script src="${global_Js}"></script>
    
    <spring:url value="/resources/js/waypoints.min.js" var="waypoints_Js"/>
    <script src="${waypoints_Js}"></script>
    
    <spring:url value="/resources/js/jquery.counterup.min.js" var="counterup_Js"/>
    <script src="${counterup_Js}"></script>
    
    <spring:url value="/resources/js/jquery.jquery.parallax-1.1.3.js" var="parallax_Js"/>
    <script src="${parallax_Js}"></script>
    
    <spring:url value="/resources/js/front.js" var="front_Js"/>
    <script src="${front_Js}"></script>

   
  <%--  jquery-ui.js file is really big so we only load what we need instead of loading everything 
    <spring:url value="/webjars/jquery-ui/1.12.1/ui/jquery.ui.core.js" var="jQueryUiCore"/>
    <script src="${jQueryUiCore}"></script>

    <spring:url value="/webjars/jquery-ui/1.12.1/ui/jquery.ui.datepicker.js" var="jQueryUiDatePicker"/>
    <script src="${jQueryUiDatePicker}"></script>

    <!-- jquery-ui.css file is not that big so we can afford to load it -->
    <spring:url value="/webjars/jquery-ui/1.12.1/themes/base/jquery-ui.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"></link>  --%>
</head>

