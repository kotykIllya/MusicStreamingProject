package com.kotyk.demo.musicStreaming3.dao;

import com.kotyk.demo.musicStreaming3.entities.models.Artist;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistDao extends JpaRepository<Artist, Integer> {

    Optional<Artist> findById(int id);

    @Query("SELECT a FROM Artist a WHERE a.name LIKE CONCAT('%', :query, '%')")
    List<Artist> searchArtists(String query);

}
