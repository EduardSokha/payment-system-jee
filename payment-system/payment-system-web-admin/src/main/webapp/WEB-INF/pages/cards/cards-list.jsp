<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>List cards</title>
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
                            <h1>Cards</h1>
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
                        <h3 class="card-title">List cards</h3>

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
                                    <!-- <a href="add-card" class="btn btn-success">Add Card</a>  -->
                                    <a href="add-card-and-account" class="btn btn-success">Add card and account</a>
                                    <br> <br>

                                    <table id="example2" class="table table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">
                                        <thead>
                                            <tr role="row">
                                                <th class="sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending"
                                                    aria-label="Rendering engine: activate to sort column descending">Id</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Date</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Id Account</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Number Account</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Payment System</th>
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Platform(s): activate to sort column ascending">Trade Name Card</th>                                                
                                                <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                    aria-label="Browser: activate to sort column ascending">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="card" items="${allCards}">
                                                <tr role="row" class="odd">
                                                    <td class="sorting_1">${card.id}</td>
                                                    <td>${card.date}</td>
                                                    <td>${card.idAccount}</td>
                                                    <td>${card.numberAccount}</td>
                                                    <td>${card.namePaymentSystem}</td>
                                                    <td>${card.tradeNameCard}</td>
                                                    
                                                    <td>
                                                        
                                                        <c:url value="delete-card" var="deleteUrl">
                                                            <c:param name="cardId" value="${card.id}" />
                                                        </c:url>
                                                        <c:url value="create-pay" var="payUrl">
                                                            <c:param name="cardId" value="${card.id}" />
                                                        </c:url>
                                                        
                                                        <a href="${payUrl}" type="button" class="btn btn-info btn-xs">Make payment</a>
                                                         &nbsp;&nbsp;&nbsp;
                                                        <a href="${deleteUrl}" class="btn btn-danger btn-xs">Delete</a>
                                                        
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

        <jsp:include page="/WEB-INF/pages/common/control-sidebar.jsp"></jsp:include>

    </div>
    <!-- ./wrapper -->

    <jsp:include page="/WEB-INF/pages/common/js-include.jsp"></jsp:include>

</body>
</html>
