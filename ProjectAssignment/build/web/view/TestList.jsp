<%-- 
    Document   : TestList
    Created on : Nov 8, 2023, 12:14:17 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Test Information</title>
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
                                <h1><b>Test Information</b></h1>
                            </div>
                        <div class="col-sm-6">
                            <a href="#deleteStudentModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="user-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                                <th>Test ID</th>
                                <th>Test Name</th>
                                <th>Type</th>
                                <th>Time</th>
                                <th>Knowledge</th>
                                <th>Condition</th>
                            </tr>
                        <tbody>
                        <c:forEach var="t" items="${tests}">
                            <tr>
                                <td>
                                    <span class="user-checkbox">
                                        <input type="checkbox" id="checkbox${stu.stucode}" name="options[]" value="${stu.stucode}">
                                        <label for="checkbox${stu.stucode}"></label>
                                    </span>
                                </td>
                                <td><c:out value="${t.tid}" /></td>
                                <td><c:out value="${t.tname}" /></td>
                                <td><c:out value="${t.type}" /></td>
                                <td><c:out value="${t.time}" /></td>
                                <td><c:out value="${t.knowledge}" /></td>
                                <td><c:out value="${t.condition}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
