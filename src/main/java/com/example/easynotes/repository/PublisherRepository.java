package com.example.easynotes.repository;

import com.example.easynotes.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by  on 27/06/17.
 */

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    public Publisher findByName(String name);
}

