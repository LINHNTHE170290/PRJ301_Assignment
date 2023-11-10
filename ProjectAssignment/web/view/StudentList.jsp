<%-- 
    Document   : StudentList
    Created on : Nov 7, 2023, 11:12:57 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Student Information</title>
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
                            <h2><b>Student Information</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="#deleteStudentModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>
                        </div>
                    </div>
                </div>
                <jsp:include page="../util/authenbox.jsp"></jsp:include>
    
    <form id="searchForm" action="search" method="GET">
        Department:
        <select id="department" name="did" required
                onchange="document.getElementById('searchForm').submit();">
            <option value="0">-All Depts-</option>
            <c:forEach items="${requestScope.depts}" var="d">
                <option 
                    <c:if test="${d.id eq param.did}">
                        selected="selected"
                    </c:if>
                    value="${d.id}">${d.name}</option>
            </c:forEach>
        </select>
        
        Name:
        <input type="text" id="searchName" name="searchName" value="${param.searchName}" />
        
        <input type="submit" value="Search" />
    </form>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="user-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Student Code</th>
                            <th>Birthday</th>
                            <th>Gender</th>
                            <th>Email</th>
                            <th>Phone</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="stu" items="${students}">
                            <tr>
                                <td>
                                    <span class="user-checkbox">
                                        <input type="checkbox" id="checkbox${stu.stucode}" name="options[]" value="${stu.stucode}">
                                        <label for="checkbox${stu.stucode}"></label>
                                    </span>
                                </td>
                                <td>${stu.stuid}</td>
                                <td>${stu.stuname}</td>
                                <td>${stu.stucode}</td>
                                <td>${stu.birthday}</td>
                                <td>${stu.gender ? 'Male' : 'Female'}</td>
                                <td>${stu.stuemail}</td>
                                <td>${stu.stuphone}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
</body>
   
</html>
