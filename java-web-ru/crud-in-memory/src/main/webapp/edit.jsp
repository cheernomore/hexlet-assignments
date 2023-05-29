<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit user</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <a href='/users'>Все пользователи</a>
            <!-- BEGIN -->
                <form action='/users/edit?id=${user.get("id")}' method="post">
                    <input type="text" name="firstName" value='${user.get("firstName")}'>
                    <input type="text" name="lastName" value='${user.get("lastName")}'>
                    <input type="text" name="email" value='${user.get("email")}'>
                    <button type="submit">Редактировать</button>
                </form>
            <!-- END -->
        </div>
    </body>
</html>
