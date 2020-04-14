package com.example.easynotes.repository;

import com.example.easynotes.model.Author;
import com.example.easynotes.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by  on 27/06/17.
 */

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    public Author findByName(String name);
}
