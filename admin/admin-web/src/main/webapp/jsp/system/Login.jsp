<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>Login</title>

    <link href="${ctx}/resource/adminex/css/style.css" rel="stylesheet">
    <link href="${ctx}/resource/adminex/css/style-responsive.css" rel="stylesheet">
    <script src="${ctx}/resource/adminex/js/html5shiv.js"></script>
    <script src="${ctx}/resource/adminex/js/respond.min.js"></script>
</head>
<body class="login-body">

<div class="container">

    <form class="form-signin" action="${ctx}/system/login" ng-controller="loginController">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">Sign In</h1>
            <img src="${ctx}/resource/adminex/images/login-logo.png" alt=""/>
        </div>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="LoginName" name="loginName" autofocus>
            <input type="password" class="form-control" placeholder="Password" name="password">
            <c:if test="${error=='error'}">
                <p style="color: red;margin-left: 2px;">Wrong password, please enter again..</p>
            </c:if>


            <button class="btn btn-lg btn-login btn-block" type="submit">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                Not a member yet?
                <a class="" href="">
                    Signup
                </a>
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Forgot Password?</a>

                </span>
            </label>

        </div>

        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal"
             class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Forgot Password ?</h4>
                    </div>
                    <div class="modal-body">
                        <p>Enter your e-mail address below to reset your password.</p>
                        <input type="text" name="email" placeholder="Email" autocomplete="off"
                               class="form-control placeholder-no-fix">

                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                        <button class="btn btn-primary" type="button">Submit</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal -->

    </form>

</div>
<!-- Placed js at the end of the document so the pages load faster -->
<script src="${ctx}/resource/adminex/js/jquery-1.10.2.min.js"></script>
<script src="${ctx}/resource/adminex/js/bootstrap.min.js"></script>
<script src="${ctx}/resource/adminex/js/modernizr.min.js"></script>
</body>
</html>
