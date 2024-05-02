@template.layout.page(
    content = @`
        <form method="POST" action="/users">
            <label for="firstname">firstname</label>
            <input type="text" name="firstname" id="firstname">

            <label for="lastname">lastname</label>
            <input type="text" name="lastname" id="lastname">

            <label for="email">email</label>
            <input type="email" name="email" id="email">

            <label for="password">password</label>
            <input type="password" name="password" id="password">

            <input type="submit" value="Send">
        </form>
    `
)