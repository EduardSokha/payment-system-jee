<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Account</title>
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
                            <h1>Account</h1>
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
                        <h3 class="card-title">Create new account</h3>
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
                                        <form action="save-account" method="post" class="form-horizontal">
                                            <input type="hidden" name="balance" value="0">
                                            <input type="hidden" name="idStatus" value="2">
                                           
                                            <div class="form-group">
                                                <label>Users</label> 
                                                <select name="idUser" class="form-control select2 select2-hidden-accessible" style="width: 100%;"
                                                    data-select2-id="1" tabindex="-1" aria-hidden="true">
                                                    
                                                    <c:forEach var="user" items="${allUsers}">
                                                        <option value="${user.id}" data-select2-id="30">${user.id} ${user.login} ${user.name} ${user.surname}</option>
                                                    </c:forEach>
                                                    
                                                </select> 
                                                <span class="select2 select2-container select2-container--default select2-container--below select2-container--focus"
                                                    dir="ltr" data-select2-id="2" style="width: 100%;"><span class="selection"></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label>Currency</label> 
                                                <select name="idCurrency" class="form-control select2 select2-hidden-accessible" style="width: 100%;"
                                                    data-select2-id="1" tabindex="-1" aria-hidden="true">
                                                    
                                                    <c:forEach var="currency" items="${allCurrencies}">
                                                        <option value="${currency.id}" data-select2-id="30">${currency.name}</option>
                                                    </c:forEach>
                                                    
                                                </select> 
                                                <span class="select2 select2-container select2-container--default select2-container--below select2-container--focus"
                                                    dir="ltr" data-select2-id="2" style="width: 100%;"><span class="selection"></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
                                            </div>
                                           
                                            <div class="form-group row">
                                                <div class="offset-sm-2 col-sm-10">
                                                    <a href="accounts-list" type="submit" class="btn btn-default">Cancel</a>
                                                    <button type="submit" class="btn btn-info float-right">Save</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>                           
                        </div>
                    </div>
                </div>
                <!-- /.card -->

            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <jsp:include page="/WEB-INF/pages/common/control-sidebar.jsp"></jsp:include>

    </div>
    <!-- ./wrapper -->

    <jsp:include page="/WEB-INF/pages/common/js-include.jsp"></jsp:include>

</body>
</html>
