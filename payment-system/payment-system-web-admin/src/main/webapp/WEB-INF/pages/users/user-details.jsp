<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mvc" uri="http://eduard.htp.by/mvc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Payment System</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="/WEB-INF/pages/common/css-include.jsp"></jsp:include>

</head>
<body class="hold-transition sidebar-mini">
    <!-- Site wrapper -->
    <div class="wrapper">

        <jsp:include page="/WEB-INF/pages/common/nav-bar.jsp"></jsp:include>

        <jsp:include page="/WEB-INF/pages/common/main-menu.jsp"></jsp:include>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1>Users</h1>
                        </div>
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <li class="breadcrumb-item"><a href="">Home</a></li>
                                <li class="breadcrumb-item active">Blank Page</li>
                            </ol>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </section>

            <!-- Main content -->
            <section class="content">

                <!-- Default box -->
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Create New User</h3>

                        <jsp:include page="/WEB-INF/pages/common/card-panel-tools.jsp"></jsp:include>
                    </div>

                    <div class="card-body">
                        <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4">
                            <div class="row">
                                <div class="col-sm-12 col-md-6"></div>
                                <div class="col-sm-12 col-md-6"></div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="tab-pane" id="settings">
                                        <form action="save-user" method="post" class="form-horizontal">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Login</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="login" value="${user.login}" class="form-control" id="inputName" placeholder="Login">
                                                    
                                                    <mvc:error code="user.login.empty">
                                                        <span id="inputLogin-error" class="error">Please enter a login</span>
                                                    </mvc:error>
                                                    <mvc:error code="user.login.duplicate">
                                                        <span id="inputLogin-error" class="error">User with this login already exist!</span>
                                                    </mvc:error>
                                                    
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Password</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="password" value="${user.password}" class="form-control" id="inputName" placeholder="Password">

                                                    <mvc:error code="user.password.empty">
                                                        <span id="inputLogin-error" class="error">Please enter a password</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Name</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="name" value="${user.name}" class="form-control" id="inputName" placeholder="Name">
                                                    <mvc:error code="user.name.empty">
                                                        <span id="inputLogin-error" class="error">Please enter name</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Surname</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="surname" value="${user.surname}" class="form-control" id="inputName" placeholder="Surname">
                                                    <mvc:error code="user.surname.empty">
                                                        <span id="inputLogin-error" class="error">Please enter surname</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Address</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="address" value="${user.address}" class="form-control" id="inputName" placeholder="Address">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>Role</label> <select name="roleId" class="form-control select2 select2-hidden-accessible" style="width: 100%;"
                                                    data-select2-id="1" tabindex="-1" aria-hidden="true">

                                                    <c:forEach var="role" items="${allRoles}">
                                                        <option value="${role.id}" data-select2-id="30">${role.name}</option>
                                                    </c:forEach>

                                                </select> <span class="select2 select2-container select2-container--default select2-container--below select2-container--focus"
                                                    dir="ltr" data-select2-id="2" style="width: 100%;"><span class="selection"></span><span
                                                    class="dropdown-wrapper" aria-hidden="true"></span></span>
                                            </div>

                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Passport Series</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="passportSeries" value="${user.passportSeries}" class="form-control" id="inputName" placeholder="Passport Series">
                                                    <mvc:error code="user.passportSeries.empty">
                                                        <span id="inputLogin-error" class="error">Please enter passport series</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Passport Id</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="passportId" value="${user.passportId}" class="form-control" id="inputName" placeholder="Passport Id">
                                                    <mvc:error code="user.passportId.empty">
                                                        <span id="inputLogin-error" class="error">Please enter passport id</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Codeword</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="codeWord" value="${user.codeWord}" class="form-control" id="inputName" placeholder="Codeword">
                                                    <mvc:error code="user.codeWord.empty">
                                                        <span id="inputLogin-error" class="error">Please enter codeword</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Phone Number</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="phoneNumber" value="${user.phoneNumber}" class="form-control" id="inputName" placeholder="Phone Number">
                                                    <mvc:error code="user.phoneNumber.empty">
                                                        <span id="inputLogin-error" class="error">Please enter phone number</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Residence Registration</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="residenceRegistr" value="${user.residenceRegistr}" class="form-control" id="inputName" placeholder="Residence Registration">
                                                    <mvc:error code="user.residenceRegistr.empty">
                                                        <span id="inputLogin-error" class="error">Please enter residence registration</span>
                                                    </mvc:error>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="offset-sm-2 col-sm-10">
                                                    <a href="user-list" type="submit" class="btn btn-default">Cancel</a>
                                                    <button type="submit" class="btn btn-info float-right">Save</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12 col-md-5">
                                    <div class="dataTables_info" id="example2_info" role="status" aria-live="polite">Showing 1 to 10 of 57 entries</div>
                                </div>
                                <div class="col-sm-12 col-md-7">
                                    <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button page-item previous disabled" id="example2_previous"><a href="#" aria-controls="example2"
                                                data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li>
                                            <li class="paginate_button page-item active"><a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0"
                                                class="page-link">1</a></li>
                                            <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0"
                                                class="page-link">2</a></li>
                                            <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="3" tabindex="0"
                                                class="page-link">3</a></li>
                                            <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="4" tabindex="0"
                                                class="page-link">4</a></li>
                                            <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="5" tabindex="0"
                                                class="page-link">5</a></li>
                                            <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="6" tabindex="0"
                                                class="page-link">6</a></li>
                                            <li class="paginate_button page-item next" id="example2_next"><a href="#" aria-controls="example2" data-dt-idx="7"
                                                tabindex="0" class="page-link">Next</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- /.card-body -->
                    <div class="card-footer">Footer</div>
                    <!-- /.card-footer-->
                </div>
                <!-- /.card -->

            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <jsp:include page="/WEB-INF/pages/common/footer.jsp"></jsp:include>

        <jsp:include page="/WEB-INF/pages/common/control-sidebar.jsp"></jsp:include>

    </div>
    <!-- ./wrapper -->

    <jsp:include page="/WEB-INF/pages/common/js-include.jsp"></jsp:include>

</body>
</html>
