package com.example.easynotes.repository;

import com.example.easynotes.model.Book;
import com.example.easynotes.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by  on 27/06/17.
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public Book findByName(String name);
    public List<Book> findAllByPublisher(Optional<Publisher> publisher);
}
