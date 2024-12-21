package vn.edu.iuh.fit.backend.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.models.Book;
import vn.edu.iuh.fit.backend.services.imp.BookService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class BookResource {

    @Autowired
    private BookService bookService;
//    @GetMapping("/books/page")
//    public ResponseEntity<List<Book>> getAllWithpage(@RequestParam
//                                               Optional<Integer> page,
//                                               @RequestParam
//                                               Optional<Integer> size
//                                               ){
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(10);
//        Page<Book> bookPage =bookService.getAllWithPage(currentPage-1,pageSize,"publishedDate", "asc");
//        return ResponseEntity.ok().body(bookPage.getContent());
//    }
//    @GetMapping("/books")
//    public ResponseEntity<List<Book>> getAllBook(){
//        return ResponseEntity.ok(bookService.getAll());
//    }
//    @PostMapping("/books")
//    public ResponseEntity<Book> saveBook(@RequestBody Book book){
//        return ResponseEntity.ok(bookService.save(book));
//    }

    @GetMapping
    public List<Book>getAllBook(){
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public Book getBookById (@PathVariable ("id") String id){
        return bookService.findById(id);
    }
    @PostMapping
    public Book addBook(@RequestBody Book book) {
//        // Validate title
//        if (book.getTitle() == null || book.getTitle().isEmpty()) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        // Validate authors
//        if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        // Validate publishedDate
//        if (book.getPublishedDate() == null || book.getPublishedDate().isAfter(LocalDate.now())) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        // Validate totalPages
//        if (book.getTotalPages() <= 0) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        // Validate ISBN
//        if (book.getIsbn() == null || !book.getIsbn().matches("^(978|979)\\d{10}$")) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//
//        return ResponseEntity.ok(bookService.add(book));
        return bookService.add(book);
    }
    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book book) {
        Book b = bookService.findById(isbn);
        if (b==null)
           return bookService.add(book);
        else
            return bookService.update(book);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteById(isbn);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
}
