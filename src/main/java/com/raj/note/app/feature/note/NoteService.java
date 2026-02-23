package com.raj.note.app.feature.note;

import com.raj.note.app.feature.note.dto.NoteResponse;
import com.raj.note.app.feature.user.User;
import com.raj.note.app.feature.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository,
                       UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public NoteResponse createNote(String title, String content, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setCreatedAt(LocalDateTime.now());
        note.setUser(user);

        return map(noteRepository.save(note));
    }
    public NoteResponse updateNote(Long id, String title, String content, String email) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        // Security check: Ensure this note belongs to the user trying to edit it
        if (!note.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized to edit this note");
        }

        note.setTitle(title);
        note.setContent(content);
        // note.setCreatedAt(LocalDateTime.now()); // Optional: update the time too?

        return map(noteRepository.save(note));
    }

    public List<NoteResponse> getAllNotes() {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return noteRepository.findByUserEmail(email)
                .stream()
                .map(this::map)
                .toList();
    }

    public NoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
        return map(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    private NoteResponse map(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt()
        );


    }
}