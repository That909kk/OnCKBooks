package vn.edu.iuh.fit.frontend.models;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.models.Book;

import java.util.List;


@Component
public class BookModel {

   private static final String URL = "http://localhost:8080/api/v1/book";
   private final RestTemplate rt = new RestTemplate();


    public List<Book>getAllBook(){return rt.getForObject(URL,List.class);}

    public Book getBookById(String isbn){return rt.getForObject(URL+"/"+isbn, Book.class);}

    public Book addBook(Book book) {
        return rt.postForObject(URL, book, Book.class);
    }
    public Book updateBook(String isbn, Book book) {
        rt.put(URL + "/" + isbn, book);
        return book;
    }

    public void deleteBook(String isbn) {
        rt.delete(URL + "/" + isbn);
    }
    public List<Book> searchBooks(String query) {
        String searchUrl = URL + "/search?query=" + query;
        return rt.getForObject(searchUrl, List.class);
    }
//    public List<Book> getAllBook() {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<Book>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Book>>() {
//                });
//        return response.getBody();
//    }
}
