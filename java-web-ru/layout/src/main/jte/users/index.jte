@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(
    content = @`
<%--        <table class="table table-bordered">--%>
<%--            <tr>--%>
<%--                <th>first name</th>--%>
<%--                <th>last name</th>--%>
<%--                <th>email</th>--%>
<%--            </tr>--%>
<%--        @for(User user: page.getUsers())--%>
<%--            <tr>--%>
<%--                <td>${user.getFirstName()}</td>--%>
<%--                <td>${user.getLastName()}</td>--%>
<%--                <td>${user.getEmail()}</td>--%>
<%--            </tr>--%>
<%--        @endfor--%>
<%--        </table>--%>

        @for(User user: page.getUsers())
            <p>
                <span>${user.getFirstName()}</span>
                <span>${user.getLastName()}</span>
                <span>${user.getEmail()}</span>
            </p>
        @endfor

    `
)