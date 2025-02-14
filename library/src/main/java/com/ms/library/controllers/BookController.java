package com.ms.library.controllers;

import com.ms.library.dtos.BookRecordDto;
import com.ms.library.models.BookModel;
import com.ms.library.services.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping
    public ResponseEntity<BookModel> createBook(@RequestBody BookRecordDto bookRecordDto) {
        var bookModel = new BookModel();

        BeanUtils.copyProperties(bookRecordDto, bookModel);
        return new ResponseEntity<>(bookService.createBook(bookModel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> findAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> findBookById(@PathVariable UUID id) {
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@RequestBody BookRecordDto bookRecordDto, @PathVariable UUID id) {
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookRecordDto, bookModel);
        return new ResponseEntity<>(bookService.updateBook(bookModel, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
    }


}
