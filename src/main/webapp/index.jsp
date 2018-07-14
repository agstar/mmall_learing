<!doctype html>
<html lang="en">
<head>
    <%@ page pageEncoding="UTF-8" %>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
SPringMVC上传文件
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="springmvc上传文件">
</form>

富文本图片上传文件
<form name="form1" action="/manage/product/richtxt_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="springmvc上传文件">
</form>
</body>
</html>
