@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(
    content = @`
        @if(page.getTerm() != null)
            <form action="/users" method="GET">
                <input type="text" name="term" value="${page.getTerm()}">
                <input type="submit" value="Search">
            </form>
            @for(User user: page.getUsers())
                <span>${user.getFirstName()} ${user.getLastName()}</span><br>
            @endfor
        @elseif(page.getUsers().size() == 0)
            <form action="/users" method="GET">
                <input type="text" name="term" value="${page.getTerm()}">
                <input type="submit" value="Search">
            </form>
        @else
            <form action="/users" method="GET">
                <input type="text" name="term" value="${page.getTerm()}">
                <input type="submit" value="Search">
            </form>
            @for(User user: page.getUsers())
                <span>${user.getFirstName()} ${user.getLastName()}</span><br>
            @endfor
        @endif


`
)