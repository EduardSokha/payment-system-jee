<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="../../index3.html" class="brand-link"> <c:url value="/static-content/images/AdminLTELogo.png" var="adminLTELogo" /> <img src="${adminLTELogo}"
        alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> <span class="brand-text font-weight-light">AdminLTE 3</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <c:url value="/static-content/images/user2-160x160.jpg" var="imagesUser2"></c:url>
                <img src="${imagesUser2}" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="#" class="d-block">Alexander Pierce</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
                
                <li class="nav-item"><a href="user" class="nav-link"> <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Demo user profile <i class="right fas fa-angle-left"></i>
                        </p>
                </a></li>
                <li class="nav-item">
                    <a href="user-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                            <p>
                                Users <i class="right fas fa-angle-left"></i>
                            </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="payment-system-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Payment Systems <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="trade-names-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Trade Names Cards <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>                
                <li class="nav-item">
                    <a href="currency-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Currencies <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="roles-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Roles <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="cards-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Cards <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="accounts-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Accounts <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="payments-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            List Payments<i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="status-list" class="nav-link"> 
                    <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            List Status<i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>