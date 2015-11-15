<%-- 
    Document   : Login
    Created on : Oct 2, 2015, 6:28:46 PM
    Author     : sivad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SENG_Project</title>
    </head>
    <body>
        <div class ="container" align="center">
            <h1 align="center"> Login </h1>
            <form role="form" action="Login" method="post">
                <div class="form-group">
                    <label for="email"> Email: </label>
                    <input type="email" name="emailID" class="form-control" id="email" placeholder="Enter Email">
                </div><br>
                <div class="form group">
                    <label for="pwd"> Password: </label>
                    <input type="password" name="password" class="form-control" id="pwd" placeholder="Enter password">
                </div>
                <button type="submit" class="btn btn-default" align="center">
                    <span class="glyphicon glyphicon-log-in"></span>Login
                </button>
            </form>
        </div>
    </body>
</html>
