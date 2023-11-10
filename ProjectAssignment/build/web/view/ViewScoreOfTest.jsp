<%-- 
    Document   : ViewGradeOfTest
    Created on : Nov 9, 2023, 3:19:51 AM
    Author     : admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Grade Information</title>
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
                            <h1><b>Grades and Averages</b></h1>
                        </div>
                    </div>
                </div>
                <!-- Hiển thị thông tin điểm số -->
                <table class="table table-striped table-hover">
                    <h2>Test Scores</h2>
                    <thead>
                        <tr>
                            <th>Test Name</th>
                            <th>Score</th>
                            <th>Percentage</th>
                        </tr>
                    <tbody>
                        <c:forEach var="g" items="${grades}">
                            <tr>
                                <td>${g.tname}</td>
                                <td>${g.score}</td>
                                <td>${g.percentage}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!-- Hiển thị thông tin điểm trung bình -->
                <h2>Average Scores</h2>
                <table>
                    <tr>
                        <th>Total</th>
                    </tr>
                    <c:forEach var="a" items="${averages}">
                        <tr>
                            <td>${a.total}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
