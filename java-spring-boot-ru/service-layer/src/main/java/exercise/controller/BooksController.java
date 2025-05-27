package exercise.controller;

import java.net.URI;
import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDTO>> index() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(books.size()))
                .body(books);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> show(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok().body(bookDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateDTO bookCreateDTO) {
        BookDTO bookDTO = bookService.createBook(bookCreateDTO);
        URI location = URI.create("/books/" + bookDTO.getId());
        return ResponseEntity.created(location).body(bookDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> update(@RequestBody BookUpdateDTO bookUpdateDTO, @PathVariable Long id) {
        BookDTO bookDTO = bookService.updateBook(bookUpdateDTO, id);
        return ResponseEntity.ok().body(bookDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BookDTO> delete(@PathVariable Long id) {
        BookDTO bookDTO = bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    // END
}
