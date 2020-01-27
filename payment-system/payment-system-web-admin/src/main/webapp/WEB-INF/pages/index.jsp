<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mvc" uri="http://eduard.htp.by/mvc"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Log in</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="/WEB-INF/pages/common/css-include.jsp"></jsp:include>


</head>

<body class="hold-transition login-page">
    <div class="login-box">
        <div class="login-logo">
            <p>
                <b>Admin</b>LTE
            </p>
        </div>
        <!-- /.login-logo -->
        <div class="card">
            <div class="card-body login-card-body">
                <p class="login-box-msg">Sign in to start your session</p>
                
                <c:if test="${successRegistr==true}"> 
                    <p class="form-title">Registration was successful!</p>
                </c:if>
                
                <mvc:error code="no.such.user">
                    <span id="inputLogin-error" class="error">We haven't user with this login!</span>
                </mvc:error>
                <mvc:error code="user.login.empty">
                            <span id="inputLogin-error" class="error">Please enter a login</span>
                </mvc:error>
                <mvc:error code="user.password.empty">
                            <span id="inputLogin-error" class="error">Please enter a password</span>
                </mvc:error>
                
                <form action="identity" method="post">
                    <div class="input-group mb-3">
                        <input type="text" name="login" value="${authentication.login}" class="form-control" placeholder="Login">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-user"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="password" name="password" value="${authentication.password}" class="form-control" placeholder="Password">                        
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-8">
                            <div class="icheck-primary">
                                <input type="checkbox" id="remember"> <label for="remember"> Remember Me </label>
                            </div>
                        </div>
                        <!-- /.col -->
                        <div class="col-4">
                            <button type="submit" class="btn btn-primary btn-block">Sign In</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>

                <p class="mb-1">
                    <a href="forget-password">I forgot my password</a>
                </p>
                <p class="mb-0">
                    <a href="registration" class="text-center">Register a new membership</a>
                </p>
            </div>
            <!-- /.login-card-body -->
        </div>
    </div>
    <!-- /.login-box -->

    <jsp:include page="/WEB-INF/pages/common/js-include.jsp"></jsp:include>

</body>
</html>
