@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(
    content = @`
        @if(page.getTerm() != null)
            @for(User user: page.getUsers())
                <span>${user.getFirstName()}</span><br>
            @endfor
        @endif

        @if(page.getUsers().size() == 0)
            <form action="/users" method="GET">
                <input type="text" name="term" value="${page.getTerm()}">
                <input type="submit" value="Search">
            </form>
        @endif
    ` 
)