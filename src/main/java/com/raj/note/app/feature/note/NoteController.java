package com.raj.note.app.feature.note;

import com.raj.note.app.feature.note.dto.CreateNoteRequest;
import com.raj.note.app.feature.note.dto.NoteResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @PostMapping
    public NoteResponse createNote(
            @RequestBody CreateNoteRequest request,
            Authentication authentication) {

        return service.createNote(
                request.getTitle(),
                request.getContent(),
                authentication.getName()
        );
    }

    @GetMapping
    public List<NoteResponse> getAllNotes(){
        return service.getAllNotes();
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteById(@PathVariable Long id){
        return service.getNoteById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteNote(id);
    }

    @PutMapping("/{id}")
    public NoteResponse updateNote(
            @PathVariable Long id,
            @RequestBody CreateNoteRequest request,
            Authentication authentication) {

        return service.updateNote(id, request.getTitle(), request.getContent(), authentication.getName());
    }

    @PutMapping("/{id}")
public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
    try {
        // 1. Find the existing note in the database
        Note existingNote = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        
        // 2. Update the fields with the new data from the frontend
        existingNote.setTitle(noteDetails.getTitle());
        existingNote.setContent(noteDetails.getContent());
        
        // 3. Save it back to the database
        Note savedNote = noteRepository.save(existingNote);
        
        return ResponseEntity.ok(savedNote);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Failed to update note");
    }
}


}
