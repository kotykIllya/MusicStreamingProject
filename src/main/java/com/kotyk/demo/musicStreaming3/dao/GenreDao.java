package com.kotyk.demo.musicStreaming3.dao;

import com.kotyk.demo.musicStreaming3.entities.models.Genre;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreDao extends JpaRepository<Genre, Integer> {

    Optional<Genre> findById(int id);

    @Query("SELECT g FROM Genre g WHERE g.name LIKE CONCAT('%', :query, '%')")
    List<Genre> searchGenres(String query);

}
