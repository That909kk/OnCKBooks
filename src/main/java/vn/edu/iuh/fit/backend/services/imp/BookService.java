package vn.edu.iuh.fit.backend.services.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Book;
import vn.edu.iuh.fit.backend.repositories.BookRepository;
import vn.edu.iuh.fit.backend.services.IService;

import java.util.List;

@Service
public class BookService implements IService<Book,String> {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteById(String s) {
        bookRepository.deleteById(s);
        return findById(s)==null;
    }

    @Override
    public Book findById(String s) {
        return bookRepository.findById(s).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingOrAuthorsContaining(query, query);
    }
}
