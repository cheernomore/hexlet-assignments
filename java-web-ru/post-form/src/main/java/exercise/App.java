package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/users/build", context -> {
            context.render("users/build.jte");
        });

        app.post("/users", context -> {
            String firstname = StringUtils.capitalize(context.formParam("firstname"));
            String lastname = StringUtils.capitalize(context.formParam("lastname"));
            String email = Objects.requireNonNull(StringUtils.lowerCase(context.formParam("email"))).trim();
            String password = context.formParam("password");

            if (password != null) {
                Security.encrypt(password);
            }

            User user = new User(firstname, lastname, email, password);
            UserRepository.save(user);
            context.redirect("/users");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
