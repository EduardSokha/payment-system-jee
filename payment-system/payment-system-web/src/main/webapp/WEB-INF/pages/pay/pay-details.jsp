<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>New pay</title>
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
                            <h1>New payment</h1>
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
                        <h3 class="card-title">Make payment</h3>

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
                                        <form action="save-pay" method="post" class="form-horizontal">
                                        <input type="hidden" name="idAccount" value="${card.idAccount}">
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Amount</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="price" class="form-control" id="inputName" placeholder="Amount">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Description</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="description" class="form-control" id="inputName" placeholder="Description">
                                                </div>
                                            </div>
                                            
                                            <div class="form-group row">
                                                <div class="offset-sm-2 col-sm-10">
                                                    <a href="cards-list" type="submit" class="btn btn-default">Cancel</a>
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
