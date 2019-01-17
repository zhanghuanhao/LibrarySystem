<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部图书信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>
    <script>
        $(function () {
            $('#header').load('reader_header.html');
        })
    </script>
</head>
<body>
<div id="header"></div>
<div style="padding: 30px 550px 10px">
    <form   method="post" action="reader_querybook_do.html" class="form-inline"  id="searchform">
        <div class="input-group">
            <input type="text" placeholder="输入图书号或图书名" class="form-control" id="search" name="searchWord" class="form-control">
            <span class="input-group-btn">
                            <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
    <script>
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
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
<c:if test="${!empty books}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                查询结果：
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>ISBN</th>
                    <th>价格</th>
                    <th>状态</th>
                    <th>详情</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td><c:out value="${book.name}"></c:out></td>
                        <td><c:out value="${book.author}"></c:out></td>
                        <td><c:out value="${book.publish}"></c:out></td>
                        <td><c:out value="${book.isbn}"></c:out></td>
                        <td><c:out value="${book.price}"></c:out></td>
                        <c:if test="${book.number>0}">
                            <td><a href="lendbook.html?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-primary btn-xs">借阅</button></a></td>
                        </c:if>
                        <td><a href="readerbookdetail.html?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-success btn-xs">详情</button></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>


</body>
</html>
