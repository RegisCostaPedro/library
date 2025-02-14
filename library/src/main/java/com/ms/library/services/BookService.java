package com.ms.library.services;

import com.ms.library.models.BookModel;
import com.ms.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public BookModel createBook(BookModel book){

        return bookRepository.save(book);
    }

    public List<BookModel> findAllBooks(){
        return bookRepository.findAll();
    }

    public BookModel findBookById(UUID id){
        return bookRepository.findById(id).get();
    }

    public BookModel updateBook(BookModel book,UUID id){
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

    public void deleteBook(UUID id){
         bookRepository.deleteById(id);
    }

}
