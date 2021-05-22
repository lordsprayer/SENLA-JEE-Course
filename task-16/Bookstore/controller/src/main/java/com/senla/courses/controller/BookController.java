package com.senla.courses.controller;

import com.senla.courses.dto.BookDto;
import com.senla.courses.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private static final Logger log = LogManager.getLogger(BookController.class.getName());
    private final IBookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /books/" + id);
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto){
        log.log(Level.INFO, "Received post request: /books");
        bookService.save(bookDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /books");
        bookService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto){
        log.log(Level.INFO, "Received put request: /books");
        bookService.update(bookDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> addOrDeleteBookToWarehouse(@PathVariable Integer id, @RequestBody Boolean availability){
        log.log(Level.INFO, "Received put request: /books");
        if(availability) {
            bookService.addBook(id);
        } else {
            bookService.cancelBook(id);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(defaultValue = "id") String sort){
        log.log(Level.INFO, "Received get all request: /books");
        return ResponseEntity.ok(bookService.getSortBooks(sort));
    }

    @GetMapping("/unsold")
    public ResponseEntity<List<BookDto>> getUnsoldBooks(@RequestParam(defaultValue = "id") String sort){
        log.log(Level.INFO, "Received get all request: /books/unsold");
        return ResponseEntity.ok(bookService.unsoldBook(sort));
    }




}
