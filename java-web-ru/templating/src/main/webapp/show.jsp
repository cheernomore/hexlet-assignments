<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<div>
    <p>${user.lastName}</p>
    <p>${user.firstName}</p>
    <a href="/users/delete?id=${id}">Delete</a>
</div>
<!-- END -->
