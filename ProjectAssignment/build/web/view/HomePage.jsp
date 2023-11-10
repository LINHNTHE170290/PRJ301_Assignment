<%-- 
    Document   : HomePage
    Created on : Nov 9, 2023, 12:35:31 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Granding Management System</title>
        
        <style>
            header {
                background-color: #000;
                color: #fff;
                text-align: center;
                padding: 7px;
            }
            header a {
                float: right; /* Đẩy "Notifications" sang phải */
                margin-right: 10px; /* Tạo khoảng cách từ mép phải */
                text-decoration: none; /* Loại bỏ gạch chân mặc định của liên kết */
                color: #fff; /* Màu chữ */
            }

            .main-content {
                display: flex;
                justify-content: space-between; /* Dàn layout phần content giữa phần trái và phải */
            }

            .content {
                flex: 1; /* Đảm bảo phần content mở rộng để lấp đầy không gian giữa */
                padding: 20px;
            }
            body {
                font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: row; /* Dàn layout theo chiều ngang */
    height: 100vh;
            }

            .main-content {
                flex: 1;
                display: flex;
            }

            nav {
                flex: 1;
                background-color: #006400;
                color: #fff;
                min-width: 200px;
                padding: 10px;
            }

            nav ul {
                list-style: none; /* Loại bỏ dấu chấm trước mục danh sách */
                padding: 0;
            }

            nav ul li {
                display: block; /* Hiển thị mỗi mục trên một dòng riêng biệt */
                margin: 10px 0; /* Điều chỉnh khoảng cách giữa các mục theo chiều dọc */
                padding: 5px 0; /* Điều chỉnh khoảng cách giữa các mục theo chiều ngang */
            }

            nav ul li a {
                display: block;
                text-decoration: none;
                text-align: center;
                color: #fff;
                font-weight: bold;
            }

            nav ul li a:hover {
                background-color: #00688B;
                padding: 5px;
                border-radius: 5px;
            }

            .content {
                flex: 5;
                display: flex;
                flex-direction: column;
                align-items: center;
                padding: 20px; /* Giảm padding xuống để không chiếm quá nhiều khoảng trắng */
                text-align: center;
                font-size: 24px;
            }

        </style>
    </head>
    <body>
        <header>

            <!-- Thêm tài khoản đăng nhập và thông báo vào phần header tại đây -->
            <a href="notifications">Notifications</a>
        </header>
        <div class="main-content">
            <nav>
                <ul>
                    <li><a href="CourseList.jsp">View Course</a></li>
                    <li><a href="StudentList.jsp">View Student</a></li>

                    <li><a href="InstructorList.jsp">View Instructor</a></li>
                    <li><a href="GroupList.jsp">View Group</a></li>
                    <li><a href="ViewGradeOfTest.jsp">View Grade</a></li>
                    </li>  
                    
                </ul>
            </nav>
        </div>
<!--        <ul class="navbar-nav ml-auto">

                        <li class="nav-item">
                            <a class="nav-link" href="#">View Course</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">View Students</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">View Instructors</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">View Group</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">View Scores</a>
                        </li>
        </ul>-->
            <div class="content">
                <!-- Hiển thị nội dung chức năng tại đây -->
                <h2>Granding Management System</h2>
                <p>-----------------------------</p>
                <p>Welcome to ${username}</p>

            </div>
        </div>
       
    </body>
</html>