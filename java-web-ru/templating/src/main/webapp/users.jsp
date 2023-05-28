<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<table>
    <tr><th>id</th><th>firstname</th><th>lastname</th><th>email</th></tr>
<c:forEach var="user" items="${users}">
    <tr><td>${user.id}</td><td>${user.firstName}</td><td>${user.lastName}</td><td>${user.email}</td>
</c:forEach>
</table>
<!-- END -->
