<%-- 
    Document   : GroupList
    Created on : Nov 9, 2023, 1:50:01 AM
    Author     : admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Group Information</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <!-- Thêm đường link quay lại trang chính ở đây -->
                <a href="HomePage.jsp">Back to Home</a>
                <!-- Kết thúc đường link -->
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h1><b>Group List</b></h1>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Group Name</th>
                                <th>Student Code</th>
                                <th>Student Name</th>
                                <th>Student Birthday</th>
                                <th>Student Gender</th>
                                <th>Instructor Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="g" items="${groups}">
                                <tr>
                                    <td>${g.gname}</td>
                                    <td>${g.students.stucode}</td>
                                    <td>${g.students.stuname}</td>
                                    <td>${g.students.birthday}</td>
                                    <td>${g.students.gender ? 'Male' : 'Female'}</td>
                                    <td>${g.instructors.iname}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
