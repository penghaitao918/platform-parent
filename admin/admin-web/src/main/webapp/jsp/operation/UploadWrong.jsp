<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fs" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>500 Page</title>

    <link href="${ctx}/resource/adminex/css/style.css" rel="stylesheet">
    <link href="${ctx}/resource/adminex/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <![endif]-->
</head>
<script type="text/javascript">
    function extraShow() {
        $(".extra").show();//显示
        $(".extraHide").show();//显示
        $(".extraShow").hide();//隐藏
    }
    function extraHide() {
        $(".extra").hide();//隐藏
        $(".extraHide").hide();//隐藏
        $(".extraShow").show();//显示
    }
</script>
<body class="error-page">

<section>
    <div class="container ">

        <section class="error-wrapper text-center">
            <h2>导入结果</h2>

            <div>
                <c:if test="${fs:length(returnList)==0}">
                    <h3>导入成功！</h3>
                </c:if>
                <c:forEach items="${returnList}" var="list" varStatus="i">
                    <c:if test="${i.count==5}">
                        <p onclick="extraShow()" style="cursor:pointer;color: red;" class="extraShow">展开...</p>
                    </c:if>
                    <c:if test="${i.count<5}">
                        <p>${list}</p>
                    </c:if>
                    <div class="extra" hidden>
                        <c:if test="${i.count>=5}">
                            <p>${list}</p>
                        </c:if>
                    </div>
                    <c:if test="${i.count==returnList.size()}">
                        <p onclick="extraHide()" class="extraHide" style="cursor:pointer;color: red;" hidden>收起...</p>
                    </c:if>

                </c:forEach></div>


        </section>

    </div>
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${ctx}/resource/adminex/js/jquery-1.10.2.min.js"></script>
<script src="${ctx}/resource/adminex/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ctx}/resource/adminex/js/bootstrap.min.js"></script>
<script src="${ctx}/resource/adminex/js/modernizr.min.js"></script>

<!--common scripts for all pages-->
<!--<script src="js/scripts.js"></script>-->

</body>
</html>
