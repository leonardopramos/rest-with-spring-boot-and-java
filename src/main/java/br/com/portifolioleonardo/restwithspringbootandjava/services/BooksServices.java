package br.com.portifolioleonardo.restwithspringbootandjava.services;

import br.com.portifolioleonardo.restwithspringbootandjava.controller.BookController;
import br.com.portifolioleonardo.restwithspringbootandjava.controller.PersonController;
import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v1.BookVO;
import br.com.portifolioleonardo.restwithspringbootandjava.exceptions.RequiredObjectIsNullException;
import br.com.portifolioleonardo.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.portifolioleonardo.restwithspringbootandjava.mapper.Custom.BookMapper;
import br.com.portifolioleonardo.restwithspringbootandjava.mapper.DozerMapper;
import br.com.portifolioleonardo.restwithspringbootandjava.model.Book;
import br.com.portifolioleonardo.restwithspringbootandjava.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServices {
    private final Logger logger = Logger.getLogger(BooksServices.class.getName());

    @Autowired
    BooksRepository repository;
    @Autowired
    BookMapper mapper;
    public List<BookVO> findAll() {
        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class) ;

            books
                .stream()
                .forEach(b -> {
                    try {
                        b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return books;
    }

    public BookVO createPerson(BookVO book) throws Exception {

        if(book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one Book");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo =  DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public BookVO update(BookVO book) throws Exception {
        if(book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book");

        var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setTitle(book.getTitle());
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id) {
        logger.info("Deleting one Book");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        repository.delete(entity);
    }
    public BookVO findById(Long id) throws Exception {
        logger.info("Finding one Book!");
        var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id."));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

}
