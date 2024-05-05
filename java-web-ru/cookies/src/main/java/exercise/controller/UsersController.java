package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        try {
            var firstName = ctx.formParamAsClass("firstName", String.class).get();
            var lastName = ctx.formParamAsClass("lastName", String.class).get();
            var email = ctx.formParamAsClass("email", String.class).get();
            var password = ctx.formParamAsClass("password", String.class).get();
            var token = Security.generateToken();
            ctx.cookie("token", token);

            User user = new User(firstName, lastName, email, password, token);
            UserRepository.save(user);
            ctx.redirect(NamedRoutes.userPath(user.getId()));
        } catch (Exception e) {

        }
    }

    public static void show(Context ctx) {
        var currentTokenCookie = ctx.cookie("token");
        var id = ctx.pathParamAsClass("id", Long.class).get();

        if (UserRepository.find(id).isPresent()) {
            User user = UserRepository.find(id).get();

            if (user.getToken().equals(currentTokenCookie)) {
                UserPage page = new UserPage(user);
                ctx.render("users/show.jte", model("page", page));
            } else {
                ctx.redirect("/users/build");
            }
        } else {
            ctx.status(404);
        }
    }
    // END
}
