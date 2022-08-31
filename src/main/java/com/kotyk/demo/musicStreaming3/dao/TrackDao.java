package com.kotyk.demo.musicStreaming3.dao;

import com.kotyk.demo.musicStreaming3.entities.User;
import com.kotyk.demo.musicStreaming3.entities.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackDao extends JpaRepository<Track, Integer> {

    Optional<Track> findById(int id);

    @Query("SELECT t FROM Track t WHERE t.album.id = :id")
    List<Track> findAllTracksByAlbumId(@Param("id")int id);

    @Query("SELECT t FROM Track t WHERE t.name LIKE CONCAT('%', :query, '%')")
    List<Track> searchTracks(String query);

    @Query("SELECT t FROM Track t JOIN t.playlists tp WHERE tp.id = :id")
    List<Track> findAllPlaylistTracksByPlaylistId(int id);

}
