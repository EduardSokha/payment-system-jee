<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body class="hold-transition sidebar-mini sidebar-collapse">
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
                            <h1>Profile</h1>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-3">

                            <!-- Profile Image -->
                            <div class="card card-primary card-outline">
                                <div class="card-body box-profile">
                                    <div class="text-center">
                                    </div>

                                    <h3 class="profile-username text-center">${authentication.name} ${authentication.surname}</h3>

                                    <ul class="list-group list-group-unbordered mb-3">
                                        <li class="list-group-item"><b>Id</b> <a class="float-right">${authentication.id}</a></li>
                                        <li class="list-group-item"><b>Login</b> <a class="float-right">${authentication.login}</a></li>
                                        <li class="list-group-item"><b>Phone Number</b> <a class="float-right">${authentication.phoneNumber}</a></li>
                                    </ul>

                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->

                            <!-- About Me Box -->
                            <div class="card card-primary">
                                <div class="card-header">
                                    <h3 class="card-title">About Me</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <strong><i class="fas fa-map-marker-alt mr-1"></i>Address</strong>

                                    <p class="text-muted">${authentication.address}</p>
                                    
                                    <hr>

                                    <strong><i class="far fa-file-alt mr-1"></i>Passport Series</strong>

                                    <p class="text-muted">${authentication.passportSeries}</p>
                                    
                                    <hr>

                                    <strong><i class="far fa-file-alt mr-1"></i>Passport Id</strong>

                                    <p class="text-muted">${authentication.passportId}</p>
                                    
                                    <hr>

                                    <strong><i class="far fa-file-alt mr-1"></i>Residence Registration</strong>

                                    <p class="text-muted">${authentication.residenceRegistr}</p>
                                    
                                    <hr>

                                    <strong><i class="far fa-file-alt mr-1"></i>CodeWord</strong>

                                    <p class="text-muted">${authentication.codeWord}</p>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </div>
                        <!-- /.col -->
                        <div class="col-md-9">
                            <div class="card">
                                <div class="card-header p-2">
                                    <ul class="nav nav-pills">
                                        <li class="nav-item"><a class="nav-link active" href="#activity" data-toggle="tab">Му cards</a></li>
                                        <li class="nav-item"><a class="nav-link" href="#timeline" data-toggle="tab">Му accounts</a></li>
                                        <li class="nav-item"><a class="nav-link" href="#settings" data-toggle="tab">Correction data</a></li>
                                        <li class="nav-item"><a class="nav-link" href="#payments" data-toggle="tab">My payments</a></li>
                                    </ul>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <div class="tab-content">
                                        <div class="active tab-pane" id="activity">
                                            <!-- Post -->
                                            <div class="post clearfix">
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
                                                                aria-label="Platform(s): activate to sort column ascending">Balance</th>
                                                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending">Currency</th>
                                                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending">Status</th>
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
                                                                <td>${card.balance}</td>
                                                                <td>${card.currency}</td>
                                                                <td>${card.status}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.post -->
                                        </div>
                                        <!-- /.tab-pane -->
                                        <div class="tab-pane" id="timeline">
                                        
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
                                                            aria-label="Rendering engine: activate to sort column descending">User Login</th>
                                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                            aria-label="Platform(s): activate to sort column ascending">Status</th>
                                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                            aria-label="Browser: activate to sort column ascending">Currency</th>
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
                                                        </tr>
                                                    </c:forEach>
        
                                                </tbody>
        
                                            </table>
                                            
                                        </div>
                                        <!-- /.tab-pane -->
                                        <div class="tab-pane" id="settings">
                                            <form class="form-horizontal" action="save-user">
                                            
                                            <input type="hidden" name="id" value="${authentication.id}">
                                            <input type="hidden" name="roleId" value="${authentication.roleId}">

                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Login</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="login" value="${authentication.login}" class="form-control" id="inputName" placeholder="Login">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Password</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="password" value="${authentication.password}" class="form-control" id="inputName" placeholder="Password">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Name</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="name" value="${authentication.name}" class="form-control" id="inputName" placeholder="Name">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Surname</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="surname" value="${authentication.surname}" class="form-control" id="inputName" placeholder="Surname">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Address</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="address" value="${authentication.address}" class="form-control" id="inputName" placeholder="Address">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Passport Series</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="passportSeries" value="${authentication.passportSeries}" class="form-control" id="inputName" placeholder="Passport Series">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Passport Id</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="passportId" value="${authentication.passportId}" class="form-control" id="inputName" placeholder="Passport Id">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Codeword</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="codeWord" value="${authentication.codeWord}" class="form-control" id="inputName" placeholder="Codeword">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Phone Number</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="phoneNumber" value="${authentication.phoneNumber}" class="form-control" id="inputName" placeholder="Phone Number">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="inputName" class="col-sm-2 col-form-label">Residence Registration</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="residenceRegistr" value="${authentication.residenceRegistr}" class="form-control" id="inputName" placeholder="Residence Registration">
                                                </div>
                                            </div>
                                                
                                                <div class="form-group row">
                                                    <div class="offset-sm-2 col-sm-10">
                                                        <button type="submit" class="btn btn-danger">Save</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <!-- /.tab-pane -->
                                        <div class="tab-pane" id="payments">
                                            
                                            <table id="example2" class="table table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">
                                                <thead>
                                                    <tr role="row">
                                                        <th class="sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending"
                                                            aria-label="Rendering engine: activate to sort column descending">Id</th>
                                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                            aria-label="Platform(s): activate to sort column ascending">Date</th>
                                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                            aria-label="Browser: activate to sort column ascending">Amount</th>
                                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                            aria-label="Browser: activate to sort column ascending">Id Account</th>
                                                        <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1"
                                                            aria-label="Browser: activate to sort column ascending">Description</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
        
                                                    <c:forEach var="payment" items="${allPayments}">
                                                        <tr role="row" class="odd">
                                                            <td class="sorting_1">${payment.id}</td>
                                                            <td>${payment.date}</td>
                                                            <td>${payment.price}</td>
                                                            <td>${payment.idAccount}</td>
                                                            <td>${payment.description}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                                
                                        </div>
<!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
                                    </div>
                                    <!-- /.tab-content -->
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.nav-tabs-custom -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
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