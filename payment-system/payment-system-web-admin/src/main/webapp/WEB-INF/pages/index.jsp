<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign in</title>

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
                    <a href="registration" class="signup-image-link">Create an account</a>                    
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Sign up</h2>
                    <p class="form-title">${successRegistr}</p>
                    <form action="identity" method="post" class="register-form" id="login-form">
                        <div class="form-group">
                            <label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label> 
                            <input type="text" name="login" id="your_name" placeholder="Login" />
                        </div>
                        <div class="form-group">
                            <label for="your_pass"><i class="zmdi zmdi-lock"></i></label> 
                            <input type="password" name="password" id="your_pass" placeholder="Password" />
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" /> 
                            <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" />
                        </div>
                    </form>
                    
                    <div class="social-login">
                            <a href="forget-password" class="signup-image-link">Forget Password</a>
                    </div>
                    
                </div>
            </div>
        </div>
    </section>

</body>
</html>
