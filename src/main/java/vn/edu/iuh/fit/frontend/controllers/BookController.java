package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.models.Book;
import vn.edu.iuh.fit.frontend.models.BookModel;


@Controller
@RequestMapping({"/home","/"})
public class BookController {

    private final BookModel bm;
    public BookController(BookModel bm) {
        this.bm = bm;
    }
    @GetMapping("/books")
    public String directToBooks(Model model){
        model.addAttribute("books",bm.getAllBook());
        return "/books";
    }
    @GetMapping("/createbook")
    public String showCreateBookForm() {
        return "createbook";
    }
    @RequestMapping(value = "/createbook",method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book, Model model) {
        bm.addBook(book);
        model.addAttribute("books", bm.getAllBook());
        return "redirect:/books";
    }
    @GetMapping("/books/update/{isbn}")
    public String showUpdateBookForm(@PathVariable String isbn, Model model) {
        model.addAttribute("book", bm.getBookById(isbn));
        return "updatebook";
    }
    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute Book book, Model model) {
        bm.updateBook(book.getIsbn(), book);
        model.addAttribute("books", bm.getAllBook());
        return "redirect:/books";
    }

    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam String isbn, Model model) {
        bm.deleteBook(isbn);
        model.addAttribute("books", bm.getAllBook());
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam String query, Model model) {
        model.addAttribute("books", bm.searchBooks(query));
        return "books";
    }


}
