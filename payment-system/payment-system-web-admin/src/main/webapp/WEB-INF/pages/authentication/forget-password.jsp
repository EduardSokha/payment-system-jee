<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mvc" uri="http://eduard.htp.by/mvc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Get a password</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="/WEB-INF/pages/common/css-include.jsp"></jsp:include>
</head>
<body class="hold-transition register-page">
    <div class="register-box">
        <div class="register-logo">
            <p>
                <b>Admin</b>LTE
            </p>
        </div>

        <div class="card">
            <div class="card-body register-card-body">
                <p class="login-box-msg">Get a password</p>
                <c:if test="${user != null}">
                    <p class="form-title">Your login = '${user.login}', your password = '${user.password}'</p>
                </c:if>

                <form action="get-password" method="post">

                    <div class="input-group mb-3">
                        <input type="text" name="passportSeries" value="${authentication.passportSeries}" class="form-control" placeholder="Passport Series">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="far fa-address-card"></span>
                            </div>
                        </div>
                        <mvc:error code="user.passportSeries.empty">
                            <span id="inputLogin-error" class="error">Please enter passport series</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="passportId" value="${authentication.passportId}" class="form-control" placeholder="Passport Id">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="far fa-address-card"></span>
                            </div>
                        </div>
                        <mvc:error code="user.passportId.empty">
                            <span id="inputLogin-error" class="error">Please enter passport id</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="codeWord" value="${authentication.codeWord}" class="form-control" placeholder="Code Word">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-key"></span>
                            </div>
                        </div>
                        <mvc:error code="user.codeWord.empty">
                            <span id="inputLogin-error" class="error">Please enter codeword</span>
                        </mvc:error>
                    </div>

                    <div class="row">
                        
                        <!-- /.col -->
                        <div class="col-4">
                            <button type="submit" class="btn btn-primary btn-block">Send</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>

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
