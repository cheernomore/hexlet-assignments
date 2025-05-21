package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;


    @GetMapping
    public List<PostDTO> index() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPostId(post.getId());
            List<CommentDTO> commentDTOList = new ArrayList<>();
            PostDTO postDTO = new PostDTO();

            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setBody(comment.getBody());
                commentDTOList.add(commentDTO);
            }

            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setBody(post.getBody());
            postDTO.setComments(commentDTOList);

            postDTOList.add(postDTO);
        }

        return postDTOList;
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {
        if (postRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Post with id " + id + " not found");
        }

        Post post = postRepository.findById(id).get();


        List<Comment> comments = commentRepository.findByPostId(post.getId());

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setId(comment.getId());
            commentDTO.setBody(comment.getBody());

            commentDTOList.add(commentDTO);
        }

        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(commentDTOList);

        return postDTO;
    }

}
// END
