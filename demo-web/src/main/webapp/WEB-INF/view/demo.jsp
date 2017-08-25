<%--
  Created by IntelliJ IDEA.
  User: huige
  Date: 2017/8/25
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uploadDemo</title>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/js/ajaxfileupload.js"></script>
    <script>
        function uploadUserInfoFile() {
            var elementIds=["flag"];
            $.ajaxFileUpload({
                url: 'http://localhost:8080/upload/importDemo',
                type: 'post',
                secureuri: false, //一般设置为false
                fileElementId: 'uploadDemoFile', // 上传文件的id、name属性名
                dataType: 'json', //返回值类型，一般设置为json、application/json
                elementIds: elementIds, //传递参数到服务器
                success: function(data,status){
                    console.log(data);
                    alert(data.message);
                },
                error: function(data, status, e){
                    alert("请求失败！");
                }
            });
        }
    </script>
</head>
<body>
    <input type="file" name="uploadDemoFile" id="uploadDemoFile" size="40"><br>
    <input type="button" name="upload" value="upload" onclick="uploadUserInfoFile();"/><div style="background-color: red;width: 500px;">当前只支持{"txt", "xls", "zip","xlsx"}格式文件上传</div>
</body>
</html>
