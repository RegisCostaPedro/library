package com.ms.library.services;

import com.ms.library.enums.RoleUser;
import com.ms.library.enums.StatusLoan;
import com.ms.library.exceptions.NoBooksAvailableException;
import com.ms.library.models.BookModel;
import com.ms.library.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public BookModel createBook(BookModel book) {

        return bookRepository.save(book);
    }

    public List<BookModel> findAllBooks() {
        return bookRepository.findAll();
    }

    public BookModel findBookById(UUID id) {
        return bookRepository.findById(id).get();
    }

    public BookModel updateBook(BookModel book, UUID id) {
        var bookFind = bookRepository.findById(id).get();

        bookFind.setIsbn(bookFind.getIsbn());
        bookFind.setAuthor(book.getAuthor());
        bookFind.setTitle(book.getTitle());
        bookFind.setDescription(book.getDescription());
        bookFind.setQuantity_available(book.getQuantity_available());
        bookFind.setPublisher(book.getPublisher());
        bookFind.setYear_publication(book.getYear_publication());
        bookFind.setGenre(book.getGenre());
        bookFind.setImage(book.getImage());

        return bookRepository.save(bookFind);
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void updateBookQuantityByRole(UUID id, RoleUser roleUser, Integer quantity) {

        var bookFind = bookRepository.findById(id).get();

        if (bookFind.getQuantity_available() <= 0 || quantity > bookFind.getQuantity_available()) {
            throw new NoBooksAvailableException("Insufficient quantity of books, only " + bookFind.getQuantity_available() + " books left in stock");
        }

        switch (roleUser) {
            case LIBRARIAN, ADMIN, TEACHER ->
                    bookFind.setQuantity_available(bookFind.getQuantity_available() - quantity);
            case STUDENT -> bookFind.setQuantity_available(bookFind.getQuantity_available() - 1);
        }


        bookRepository.save(bookFind);
    }

}
