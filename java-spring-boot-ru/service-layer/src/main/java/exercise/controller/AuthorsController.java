package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AuthorDTO>> index() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(authors.size()))
                .body(authors);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> show(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok().body(authorDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorCreateDTO authorCreateDTO) {
        AuthorDTO authorDTO = authorService.createAuthor(authorCreateDTO);
        URI location = URI.create("/authors/" + authorDTO.getId());
        return ResponseEntity.created(location).body(authorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> update(@RequestBody AuthorUpdateDTO authorUpdateDTO, @PathVariable Long id) {
        AuthorDTO authorDTO = authorService.updateAuthor(authorUpdateDTO, id);
        return ResponseEntity.ok().body(authorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<AuthorDTO> delete(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
    // END
}
