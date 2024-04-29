@import exercise.dto.users.UserPage
@param UserPage userPage

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
<h1>User Info:</h1>
<p>
    Name: ${userPage.getUser().getFirstName()}, <br>
    Second name: ${userPage.getUser().getLastName()}, <br>
    Email: ${userPage.getUser().getEmail()}
</p>
</body>
</html>>