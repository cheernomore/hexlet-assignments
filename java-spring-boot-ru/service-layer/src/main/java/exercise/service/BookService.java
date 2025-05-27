package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {
    // BEGIN

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        return bookMapper.map(book);
    }

    public BookDTO createBook(BookCreateDTO bookCreateDTO) {
        Book book = bookMapper.map(bookCreateDTO);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO updateBook(BookUpdateDTO bookUpdateDTO, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        bookMapper.update(bookUpdateDTO, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        bookRepository.delete(book);
        return bookMapper.map(book);
    }
    // END
}
