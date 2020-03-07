<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Your password</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="/WEB-INF/pages/common/css-include.jsp"></jsp:include>
</head>
<body class="hold-transition register-page">
    <div class="register-box">
        <div class="register-logo">
             <p><b>Fast&Secure</b>Pay</p>
        </div>

        <div class="card">
            <div class="card-body register-card-body">
                <p class="login-box-msg">Your password was sent on email!</p>

                <p class="mb-1">
                    <c:url value="/" var="backUrl"></c:url>
                    <a href="${backUrl}" class="text-center">Back</a>
                </p>
            </div>
            <!-- /.form-box -->
        </div>
        <!-- /.card -->
    </div>
    <!-- /.register-box -->

    <jsp:include page="/WEB-INF/pages/common/js-include.jsp"></jsp:include>

</body>
</html>
