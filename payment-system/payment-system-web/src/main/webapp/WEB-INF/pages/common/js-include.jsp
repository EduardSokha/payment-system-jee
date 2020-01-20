<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- jQuery -->
    <c:url value="/static-content/js/plugins/jquery/jquery.min.js" var="jQueryJs"></c:url>
    <script src="${jQueryJs}"></script>
    <!-- Bootstrap 4 -->
    <c:url value="/static-content/js/plugins/bootstrap/js/bootstrap.bundle.min.js" var="bootstrapJs"></c:url>
    <script src="${bootstrapJs}"></script>
    <!-- AdminLTE App -->
    <c:url value="/static-content/js/dist/js/adminlte.min.js" var="adminLteDistJs"></c:url>
    <script src="${adminLteDistJs}"></script>