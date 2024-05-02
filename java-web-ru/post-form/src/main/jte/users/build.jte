@template.layout.page(
    content = @`
        <form method="POST" action="/users">
            <label for="firstName">firstname</label>
            <input type="text" name="firstName" id="firstName" required>

            <label for="lastName">lastname</label>
            <input type="text" name="lastName" id="lastName" required>

            <label for="email">email</label>
            <input type="email" name="email" id="email" required>

            <label for="password">password</label>
            <input type="password" name="password" id="password" required>

            <input type="submit" value="Send">
        </form>
    `
)