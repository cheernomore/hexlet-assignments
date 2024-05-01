@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h5>
            <p class="card-text">${page.getUser().getEmail()}</p>
            <a href="/" class="card-link">Home</a>
            <a href="/users" class="card-link">Users</a>
        </div>
    </div>
    `
)