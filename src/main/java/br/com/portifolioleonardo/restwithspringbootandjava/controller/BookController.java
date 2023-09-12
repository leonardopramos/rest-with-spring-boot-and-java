package br.com.portifolioleonardo.restwithspringbootandjava.controller;

import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v1.BookVO;
import br.com.portifolioleonardo.restwithspringbootandjava.services.BooksServices;
import br.com.portifolioleonardo.restwithspringbootandjava.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book/v1")
public class BookController {

    @Autowired
    private BooksServices booksServices;


    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public BookVO findById(@PathVariable(value = "id")Long id)throws Exception{
        return booksServices.findById(id);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public List<BookVO> findAll() {
        return booksServices.findAll();
    }
    @PostMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public BookVO create(@RequestBody BookVO bookVO) throws Exception {
        return booksServices.createPerson(bookVO);
    }
    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public BookVO update(@RequestBody BookVO bookVO) throws Exception {
        return booksServices.update(bookVO);
    }
    @DeleteMapping(value = "{id}")

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        booksServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
