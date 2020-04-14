package com.example.easynotes.controller;

import com.example.easynotes.model.Author;
import com.example.easynotes.model.Book;
import com.example.easynotes.model.Publisher;
import com.example.easynotes.repository.AuthorRepository;
import com.example.easynotes.repository.BookRepository;
import com.example.easynotes.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @PostMapping("/add")
    public String addBook( @Valid @RequestBody AddbookRequest addbookRequest) {
        //把出版社存进去
        Publisher publisher = publisherRepository.findByName(addbookRequest.publisherName);
        if (publisher == null) {
            publisher = new Publisher(addbookRequest.publisherName);
            publisher = publisherRepository.save(publisher);
        }

        //把作者存进去
        Author author = authorRepository.findByName(addbookRequest.authorName);
        if (author == null) {
            author = new Author(addbookRequest.authorName);
            author = authorRepository.save(author);
        }

        //把书籍存进去
        Book book = bookRepository.findByName(addbookRequest.name);
        if (book == null) {
            book = new Book(addbookRequest.name, author, publisher);
            bookRepository.save(book);
        }

        return "添加成功";
    }


    @GetMapping("/queryBook")
    public List<Book> queryBook(@RequestParam(name = "publisherId", required = true) Integer publisherId,
                                @RequestParam(name = "start", required = true) Date start,
                                @RequestParam(name = "end", required = true) Date end,
                                @RequestParam(name = "offset", required = true) Integer offset,
                                @RequestParam(name = "count", required = true) Integer count)
    {
        //构造sql或者根据jpa 在book表上查询返回结果即可

        return null;
    }

    @GetMapping("/queryAuthor")
    public List<Author> queryAuthor(@RequestParam(name = "publisherId", required = true) Integer publisherId)
    {
        //构造sql或者根据jpa查询返回结果即可
        //先查到书，然后把书里面的author返回即可
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        List<Book> books = bookRepository.findAllByPublisher(publisher);
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
//            Optional<Author> tmp = Optional.ofNullable(books.get(i).getAuthor());
//            if (tmp != null) {
//                tmp = authorRepository.findById(tmp.getId());
//            }
//            ;
//            authors.add(Optional.ofNullable(tmp).get());
        }

        return authors;
    }
}


class AddbookRequest implements Serializable {
    public String name;

    public String authorName;

    public String publisherName;
}