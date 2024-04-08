package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired

    private BookRepo bookRepo;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book> >getAllBooks()
    {
     try {
         List<Book> booklist = new ArrayList<>();
         bookRepo.findAll().forEach(booklist::add);

         if(booklist.isEmpty())
         {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         return new ResponseEntity<>(booklist,HttpStatus.OK);

     }
     catch (Exception ex)
     {
return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
       Optional<Book>bookdata= bookRepo.findById(id);

       if(bookdata.isPresent())
       {
           return new ResponseEntity<>(bookdata.get(),HttpStatus.OK);
       }


       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addBook")
    public  ResponseEntity<Book>addBook(@RequestBody Book book)
    {
    Book bookobj= bookRepo.save(book);
    return  new ResponseEntity<>(bookobj,HttpStatus.OK);
    }

    @PostMapping("/updateBookById/{id}")
    public  ResponseEntity<Book> updateBookById(@PathVariable Long id,@RequestBody Book newBookData)
    {
       Optional<Book> oldBookData= bookRepo.findById(id);
       if(oldBookData.isPresent())
       {
           Book updatedBookData= oldBookData.get();
           updatedBookData.setTitle(newBookData.getTitle());
           updatedBookData.setPrice(newBookData.getPrice());

           Book bookobj = bookRepo.save(updatedBookData);
           return new ResponseEntity<>(bookobj,HttpStatus.OK);
       }
       return   new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById( @PathVariable Long id)
    {
        bookRepo.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);

    }
}
