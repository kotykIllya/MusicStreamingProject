package com.kotyk.demo.musicStreaming3.dao;

import com.kotyk.demo.musicStreaming3.entities.models.Album;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumDao extends JpaRepository<Album, Integer> {

    Optional<Album> findById(int id);

    @Query("SELECT a FROM Album a WHERE a.artist.id = :id")
    List<Album> findAllAlbumsByArtistId(@Param("id")int id);

    @Query("SELECT a FROM Album a JOIN Genre g ON a.id = g.id WHERE g.id = :id")
    List<Album> findAllAlbumsByGenreId(@Param("id")int id);

    @Query("SELECT a FROM Album a WHERE a.name LIKE CONCAT('%', :query, '%')")
    List<Album> searchAlbums(String query);

}
