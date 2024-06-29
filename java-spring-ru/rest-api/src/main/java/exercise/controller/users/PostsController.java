package exercise.controller.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
class PostsController {

    List<Post> initPosts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> getPosts(@PathVariable int id) {
        List<Post> posts = initPosts.stream().filter(p -> p.getUserId() == id).toList();

        if (posts.isEmpty()) {
            throw new RuntimeException("No posts found for user " + id);
        } else  {
            return posts;
        }
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable int id, @RequestBody Post post)  {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setSlug(post.getSlug());
        newPost.setUserId(id);
        initPosts.add(newPost);
        return newPost;
    }
}
// END
