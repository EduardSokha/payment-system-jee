<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mvc" uri="http://eduard.htp.by/mvc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Register a new membership</title>
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
                <p class="login-box-msg">Register a new membership</p>
                <p class="form-title">${response}</p>
                <mvc:error code="password.match">
                    <span id="inputLogin-error" class="error">Password1 and Password2 don't match!</span>
                </mvc:error>

                <form action="save-new-user" method="post">
                    <input type="hidden" name="roleId" value="2">
                    <div class="input-group mb-3">
                        <input type="text" name="login" value="${user.login}" class="form-control" placeholder="Login">                                       
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-user"></span>
                            </div>
                        </div>
                        <mvc:error code="user.login.empty">
                            <span id="inputLogin-error" class="error">Please enter a login</span>
                        </mvc:error>
                        <mvc:error code="user.login.duplicate">
                            <span id="inputLogin-error" class="error">User with this login already exist!</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="password" name="password" class="form-control" placeholder="Password">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                        <mvc:error code="user.password.empty">
                            <span id="inputLogin-error" class="error">Please enter a password</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="password" name="password2" class="form-control" placeholder="Retype Password">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="name" value="${user.name}" class="form-control" placeholder="Full Name">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="far fa-address-card"></span>
                            </div>
                        </div>
                        <mvc:error code="user.name.empty">
                            <span id="inputLogin-error" class="error">Please enter name</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="surname" value="${user.surname}" class="form-control" placeholder="Full Surname">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="far fa-address-card"></span>
                            </div>
                        </div>
                        <mvc:error code="user.surname.empty">
                            <span id="inputLogin-error" class="error">Please enter surname</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="address" value="${user.address}" class="form-control" placeholder="Your Address">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-map-marker-alt"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="passportSeries" value="${user.passportSeries}" class="form-control" placeholder="Passport Series">
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
                        <input type="text" name="passportId" value="${user.passportId}" class="form-control" placeholder="Passport Id">
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
                        <input type="text" name="codeWord" value="${user.codeWord}" class="form-control" placeholder="Code Word">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-key"></span>
                            </div>
                        </div>
                        <mvc:error code="user.codeWord.empty">
                            <span id="inputLogin-error" class="error">Please enter codeword</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="phoneNumber" value="${user.phoneNumber}" class="form-control" placeholder="Phone Number">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-mobile"></span>
                            </div>
                        </div>
                        <mvc:error code="user.phoneNumber.empty">
                            <span id="inputLogin-error" class="error">Please enter phone number</span>
                        </mvc:error>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" name="residenceRegistr" value="${user.residenceRegistr}" class="form-control" placeholder="Residence Registration">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-map-pin"></span>
                            </div>
                        </div>
                        <mvc:error code="user.residenceRegistr.empty">
                            <span id="inputLogin-error" class="error">Please enter residence registration</span>
                        </mvc:error>
                    </div>
                    <div class="row">
                        <!-- /.col -->
                        <div class="col-4">
                            <button type="submit" class="btn btn-primary btn-block">Register</button>
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
