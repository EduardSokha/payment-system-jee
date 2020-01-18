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
                    <p class="form-title">${response}</p>
                    <form action="save-new-user" method="post" class="register-form" id="login-form">
                    <input type="hidden" name="roleId" value="2">
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="login" id="name" placeholder="Login"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="password" name="password" id="name" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="password" name="password2" id="name" placeholder="Repeat Password"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="surname" id="name" placeholder="Your Surname"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="address" id="name" placeholder="Your Address"/>
                            </div>
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
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="phoneNumber" id="name" placeholder="phoneNumber"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi"></i></label>
                                <input type="text" name="residenceRegistr" id="name" placeholder="Residence Registration"/>
                            </div>
                            
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
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
