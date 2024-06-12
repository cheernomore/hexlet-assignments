package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.BasePage;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class PostsController {

    public static void build(Context ctx) {
        BuildPostPage page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void index(Context ctx) {
        List<Post> posts = PostRepository.getEntities();
        BasePage basePage = new BasePage();
        PostsPage page = new PostsPage(posts, basePage);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("posts/index.jte", model("page", page));
    }

    public static void create(Context ctx) {
        String body = ctx.formParam("body");

        try {
            String name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 3, "Value length error")
                    .get();
            ctx.sessionAttribute("flash", "Пост был успешно создан!");
            Post post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {

        }
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
