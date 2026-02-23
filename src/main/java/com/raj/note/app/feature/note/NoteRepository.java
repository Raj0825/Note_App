package com.raj.note.app.feature.note;

import com.raj.note.app.feature.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

    List<Note> findByUserEmail(String email);
}