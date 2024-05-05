package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    public static String buildPostPath() {
        return "/posts/build";
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // BEGIN
    public static String editPosts(Long id) {
        return editPosts(String.valueOf(id));
    }

    public static String editPosts(String id) {
        return "/posts/" + id;
    }
    public static String editPostPath(Long id) {
        return editPostPath(String.valueOf(id));
    }
    public static String editPostPath(String id) {
        return "/posts/" + id + "/edit";
    }
    // END
}
