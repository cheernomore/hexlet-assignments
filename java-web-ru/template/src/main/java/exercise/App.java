package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import io.javalin.rendering.template.JavalinJte;

import java.util.Collections;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", context -> {
            UsersPage usersPage = new UsersPage(USERS);
            context.render("users/index.jte", model("usersPage", usersPage));
        });
        app.get("/users/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            User user = USERS.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
            if (user == null) {
                context.status(404);
                context.render("users/not-found.jte");
            } else {
                UserPage userPage = new UserPage(user);
                context.render("users/show.jte", model("userPage", userPage));
            }
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
