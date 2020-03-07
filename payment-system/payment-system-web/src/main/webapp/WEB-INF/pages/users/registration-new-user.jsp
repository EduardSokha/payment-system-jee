<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Create new user</title>
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
      <p class="login-box-msg">Register a new membership</p>
      <p class="form-title">${response}</p>

      <form action="save-new-user" method="post">
	  <input type="hidden" name="roleId" value="2">
        <div class="input-group mb-3">
          <input type="text" name="login" class="form-control" placeholder="Login">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-user"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="password" class="form-control" placeholder="Password">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
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
          <input type="text" name="name" class="form-control" placeholder="Full Name">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="far fa-address-card"></span>
            </div>
          </div>
        </div>
		<div class="input-group mb-3">
          <input type="text" name="surname" class="form-control" placeholder="Full Surname">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="far fa-address-card"></span>
            </div>
          </div>
        </div>
		<div class="input-group mb-3">
          <input type="text" name="address" class="form-control" placeholder="Your Address">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-map-marker-alt"></span>
            </div>
          </div>
        </div>
		<div class="input-group mb-3">
          <input type="text" name="passportSeries" class="form-control" placeholder="Passport Series">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="far fa-address-card"></span>
            </div>
          </div>
        </div>
		<div class="input-group mb-3">
          <input type="text" name="passportId" class="form-control" placeholder="Passport Id">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="far fa-address-card"></span>
            </div>
          </div>
        </div>
		<div class="input-group mb-3">
          <input type="text" name="codeWord" class="form-control" placeholder="Code Word">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-key"></span>
            </div>
          </div>
        </div>
		<div class="input-group mb-3">
          <input type="text" name="phoneNumber" class="form-control" placeholder="Phone Number">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-mobile"></span>
            </div>
          </div>
        </div>
		<div class="input-group mb-3">
          <input type="text" name="residenceRegistr" class="form-control" placeholder="Residence Registration">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-map-pin"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="agreeTerms" name="terms" value="agree">
              <label for="agreeTerms">
               I agree to the <a href="#">terms</a>
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-4">
            <button type="submit" class="btn btn-primary btn-block">Register</button>
          </div>
          <!-- /.col -->
        </div>
      </form>

		<c:url value="/" var="backUrl"></c:url>
		<a href="${backUrl}" class="text-center">Back</a>
    </div>
    <!-- /.form-box -->
  </div><!-- /.card -->
</div>
<!-- /.register-box -->

<jsp:include page="/WEB-INF/pages/common/js-include.jsp"></jsp:include>

</body>
</html>
