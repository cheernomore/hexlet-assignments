package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import java.util.List;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import exercise.util.Generator;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;


public class PostsController {
    private static List<Post> posts = PostRepository.getEntities();
    // BEGIN
    public static void index(Context context) {
        Integer index = context.queryParamAsClass("page", Integer.class).getOrDefault(null);
        int totalPages = (int) Math.ceil((double) posts.size() / 5);
        int startIndex, endIndex;

        if (index == null) {
            List<Post> targetPosts = posts.subList(0, 5);
            PostsPage page = new PostsPage(targetPosts, 0, totalPages);
            context.render("posts/index.jte", model("page", page));
        } else {
            startIndex = (index - 1) * 5;
            endIndex = startIndex + 5;


            if (endIndex >= posts.size()) {
                List<Post> targetPosts = posts.subList(startIndex, posts.size() - 1);
                PostsPage page = new PostsPage(targetPosts, index, totalPages);
                context.render("posts/index.jte", model("page", page));
            } else {
                List<Post> targetPosts = posts.subList(startIndex, endIndex);
                PostsPage page = new PostsPage(targetPosts, index, totalPages);
                context.render("posts/index.jte", model("page", page));
            }
        }
    }

    public static void show(Context context) {
        Long id = context.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.getEntities().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (post == null) {
            context.status(404);
            context.result("Page not found");
        } else {
            PostPage page = new PostPage(post);
            context.render("posts/show.jte", model("page", page));
        }
    }
    // END
}
