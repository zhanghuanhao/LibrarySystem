<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息添加</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .form-group {
            margin-bottom: 0;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="img/sky.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header"></div>
<div style="position: relative;padding-top: 60px; width: 80%;margin-left: 10%">
    <form action="book_add_do.html" method="post" id="addbook">
        <div class="form-group">
            <label for="name">图书名</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="请输入书名">
        </div>
        <div class="form-group">
            <label for="author">作者</label>
            <input type="text" class="form-control" name="author" id="author" placeholder="请输入作者名">
        </div>
        <div class="form-group">
            <label for="publish">出版社</label>
            <input type="text" class="form-control" name="publish" id="publish" placeholder="请输入出版社">
        </div>
        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input type="text" class="form-control" name="isbn" id="isbn" placeholder="请输入ISBN">
        </div>
        <div class="form-group">
            <label for="introduction">简介</label>
            <textarea class="form-control" rows="3" name="introduction" id="introduction"
                      placeholder="请输入简介"></textarea>
        </div>
        <div class="form-group">
            <label for="language">语言</label>
            <input type="text" class="form-control" name="language" id="language" placeholder="请输入语言">
        </div>
        <div class="form-group">
            <label for="price">价格</label>
            <input type="text" class="form-control" name="price" id="price" placeholder="请输入价格">
        </div>
        <div class="form-group">
            <label for="pubstr">出版日期</label>
            <input type="date" class="form-control" name="pubstr" id="pubstr" placeholder="请输入出版日期">
        </div>
        <div class="form-group">
            <label for="classId">分类号</label>
            <input type="text" class="form-control" name="classId" id="classId" placeholder="请输入分类号">
        </div>
        <div class="form-group">
            <label for="number">数量</label>
            <input type="text" class="form-control" name="number" id="number" placeholder="请输入图书数量">
        </div>

        <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
        <script>
            $("#addbook").submit(function () {
                if ($("#name").val() == '' || $("#author").val() == '' || $("#publish").val() == '' || $("#isbn").val() == '' || $("#introduction").val() == '' || $("#language").val() == '' || $("#price").val() == '' || $("#pubstr").val() == '' || $("#classId").val() == '' || $("#pressmark").val() == '' || $("#number").val() == '') {
                    alert("请填入完整图书信息！");
                    return false;
                }
            })
        </script>
    </form>
</div>
</body>
</html>
