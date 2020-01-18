<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Colorlib</title>

<c:url value="/static-content/css/logination/fonts/material-icon/css/material-design-iconic-font.min.css" var="designIconic"></c:url>
<!-- Font Icon -->
<link rel="stylesheet" href="${designIconic}">

<c:url value="/static-content/css/logination/css/style.css" var="styleCss"></c:url>
<!-- Main css -->
<link rel="stylesheet" href="${styleCss}">

</head>
<body class="hold-transition sidebar-mini">

    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <c:url value="/static-content/images/payment-system.jpg" var="image"></c:url>
                        <img src="${image}" alt="sing up image">
                    </figure>                  
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Sign up</h2>
                    <c:if test="${user != null}">
                        <p class="form-title">
                            Your login = '${user.login}', your password = '${user.password}'
                        </p>
                    </c:if>
                    <form action="get-password" method="post" class="register-form" id="login-form">
                    
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="passportSeries" id="name" placeholder="Passport Series"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="passportId" id="name" placeholder="Passport Id"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="codeWord" id="name" placeholder="CodeWord"/>
                            </div>                            
                            
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Get Password"/>
                            </div>
                    </form>
                    
                    <div class="social-login">
                    <c:url value="/" var="backUrl"></c:url>
                            <a href="${backUrl}" class="signup-image-link">Back</a>
                    </div>
                    
                </div>
            </div>
        </div>
    </section>

</body>
</html>
