<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="home" class="brand-link"> 
        <c:url value="/static-content/images/AdminLTELogo.png" var="adminLTELogo" /> 
        <img src="${adminLTELogo}" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> 
        <span class="brand-text font-weight-light"><b>Fast&Secure</b>Pay</span>
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
                <a href="#" class="d-block">${authentication.name} ${authentication.surname}</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
                
                <li class="nav-item">
                    <a href="cards-list" class="nav-link"> 
                    <i class="nav-icon fas fa fa-credit-card"></i>
                        <p>
                            Cards <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="accounts-list" class="nav-link"> 
                    <i class="nav-icon fas far fa-file-invoice-dollar"></i>
                        <p>
                            Accounts <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="payments-list" class="nav-link"> 
                    <i class="nav-icon fas far fa-money-check-alt"></i>
                        <p>
                            My payments<i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>