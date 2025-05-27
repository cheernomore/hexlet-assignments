package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    // BEGIN
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        return authorMapper.map(author);
    }

    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO) {
        Author author = authorMapper.map(authorCreateDTO);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO updateAuthor(AuthorUpdateDTO authorUpdateDTO, Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        authorMapper.update(authorUpdateDTO, author);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        authorRepository.delete(author);
        return authorMapper.map(author);
    }
    // END
}
