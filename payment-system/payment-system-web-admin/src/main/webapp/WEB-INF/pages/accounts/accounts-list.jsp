<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>List accounts</title>
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
                            <h1>Accounts</h1>
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
                        <h3 class="card-title">List accounts</h3>

                        <jsp:include page="/WEB-INF/pages/common/card-panel-tools.jsp"></jsp:include>
                    </div>

                    <div class="card-body">
                        <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4">
                            <div class="row">
                                <div class="col-sm-12 col-md-6"></div>
                                <div class="col-sm-12 col-md-6"></div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <a href="add-account" class="btn btn-success">Add account</a> <br> <br>

                                    <table id="example2" class="table table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">
                                        <thead>
                                            <tr role="row">
                                                <th class="sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending"
                                                    aria-label="Rendering engine: activate to sort column descending">Id</th>
                                                <th class="sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending"
                                                    aria-label="Rendering engine: activate to sort column descending">Number Account</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Balance</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Date</th>
                                                <th class="sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending"
                                                    aria-label="Rendering engine: activate to sort column descending">Login User</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Status</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Browser: activate to sort column ascending">Currency</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Browser: activate to sort column ascending">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="account" items="${allAccounts}">
                                                <tr role="row" class="odd">
                                                    <td class="sorting_1">${account.id}</td>
                                                    <td>${account.number}</td>
                                                    <td>${account.balance}</td>
                                                    <td>${account.date}</td>
                                                    <td>${account.userLogin}</td>
                                                    <td>${account.statusName}</td>
                                                    <td>${account.currencyName}</td>
                                                    
                                                    <td>
                                                        <c:url value="edit-account" var="editUrl">
                                                            <c:param name="accountId" value="${account.id}" />
                                                        </c:url>
                                                        
                                                        <c:url value="delete-account" var="deleteUrl">
                                                            <c:param name="accountId" value="${account.id}" />
                                                        </c:url>
                                                        
                                                        <c:url value="lock-unlock-account" var="lockUrl">
                                                            <c:param name="accountId" value="${account.id}" />
                                                            <c:param name="idStatus" value="1" />                                                            
                                                        </c:url>
                                                        
                                                        <c:url value="lock-unlock-account" var="unlockUrl">
                                                            <c:param name="accountId" value="${account.id}" />
                                                            <c:param name="idStatus" value="2" />                                                            
                                                        </c:url>
                                                        
                                                        <a href="${editUrl}" type="button" class="btn btn-info btn-xs">Edit</a>
                                                        &nbsp;
                                                        <a href="${deleteUrl}" class="btn btn-danger btn-xs">Delete</a>
                                                        &nbsp;
                                                        <a href="${lockUrl}" type="button" class="btn btn-info btn-xs">Lock</a>
                                                        &nbsp;
                                                        <a href="${unlockUrl}" type="button" class="btn btn-info btn-xs">Unlock</a>
                                                        &nbsp;
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>

                                    </table>
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
