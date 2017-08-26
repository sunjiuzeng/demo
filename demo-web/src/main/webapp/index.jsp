<%--
  Created by IntelliJ IDEA.
  User: sunjz
  Date: 2017/8/24
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script>
        function dowmloadDemo(){
            window.location.href="/export/exportFileTemplate?endDate="+new Date().getTime();
        }
        function dowmloadTemplate(){
            window.location.href="/template/fileTemplate.xlsx";
        }

    </script>
</head>
<body>
    <a href="/demo/toDemoPage">上传demo</a><br>
    <a href="javascript:void(0);"e onclick="dowmloadDemo();">下载demo</a><br>
    <a href="javascript:void(0);"e onclick="dowmloadTemplate();">下载模板</a><br>
</body>
</html>
