package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", context -> {
            String term = context.queryParam("term");
            UsersPage usersPage = new UsersPage(USERS, term);
            if (term != null) {
                List<User> res = usersPage.getUsers()
                        .stream()
                        .filter(user -> StringUtils.startsWith(
                                StringUtils.lowerCase(user.getFirstName()),
                                StringUtils.lowerCase(term)))
                        .toList();
                UsersPage page = new UsersPage(res, term);
                context.render("users/index.jte", model("page", page));
            } else {
                context.render("users/index.jte", model("page", usersPage));
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
