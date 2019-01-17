<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>借还日志</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: rgb(240, 242, 245);
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body>
<div id="header"></div>

<div style="padding: 70px 550px 10px">
    <form method="post" action="querybook.html" class="form-inline" id="searchform">
        <div class="input-group">
            <input type="text" placeholder="输入图书名" class="form-control" id="search" name="searchWord"
                   class="form-control">
            <span class="input-group-btn">
                            <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
    <script>
        $("#searchform").submit(function () {
            var val = $("#search").val();
            if (val == '') {
                alert("请输入关键字");
                return false;
            }
        })
    </script>
</div>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>
<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            借还日志
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>流水号</th>
                <th>图书号</th>
                <th>读者证号</th>
                <th>借出日期</th>
                <th>归还日期</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="alog">
                <tr>
                    <td><c:out value="${alog.ser_num}"></c:out></td>
                    <td><c:out value="${alog.bookId}"></c:out></td>
                    <td><c:out value="${alog.readerId}"></c:out></td>
                    <td><c:out value="${alog.lendDate}"></c:out></td>
                    <td><c:out value="${alog.backDate}"></c:out></td>
                    <td>
                        <a href="deletebook.html?serNum=<c:out value='${alog.ser_num}'></c:out>">
                            <c:if test="${alog.backDate}">
                                <button type="button" class="btn btn-danger btn-xs">删除</button>
                            </c:if>
                            <c:if test="${empty alog.backDate}">
                                <button type="button" class="btn btn-default btn-xs" disabled="disabled">删除</button>
                            </c:if>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
