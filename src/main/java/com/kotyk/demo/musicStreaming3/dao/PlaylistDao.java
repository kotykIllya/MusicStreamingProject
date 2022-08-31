package com.kotyk.demo.musicStreaming3.dao;

import com.kotyk.demo.musicStreaming3.entities.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistDao extends JpaRepository<Playlist, Integer> {

    @Query("SELECT p FROM Playlist p WHERE p.user.id = :id")
    List<Playlist> findAllByUserId(@Param("id")int id);

    Optional<Playlist> findById(int id);

}
