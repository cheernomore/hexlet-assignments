@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(
    content = @`
        <table class="table table-bordered">
            <tr>
                <th>first name</th>
                <th>last name</th>
                <th>email</th>
            </tr>
        @for(User user: page.getUsers())
            <tr>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
            </tr>
        @endfor
        </table>
    `
)