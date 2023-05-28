<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<form action="/users/delete?id=${id}" method="post">
    <p>Username: ${user.firstName}</p>
    <button type="submit">Delete</button>
</form>
<!-- END -->
