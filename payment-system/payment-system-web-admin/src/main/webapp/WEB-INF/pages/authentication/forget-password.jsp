<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <p><b>Admin</b>LTE</p>
  </div>

  <div class="card">
    <div class="card-body register-card-body">
      <p class="login-box-msg">Get a password</p>
       <c:if test="${user != null}">
            <p class="form-title">
                Your login = '${user.login}', your password = '${user.password}'
            </p>
       </c:if>

      <form action="get-password" method="post">
	  
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
            <button type="submit" class="btn btn-primary btn-block">Send</button>
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
