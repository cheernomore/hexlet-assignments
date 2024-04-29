@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage usersPage

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Users</h1>

    <table>
        <tr>
            <th>id</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>email</th>
        </tr>
        @for(User user: usersPage.getUsers())
            <tr>
                <td><a href="users/${user.getId()}">${user.getId()}</a></td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
            </tr>
        @endfor
    </table>
</body>
</html>