package com.raj.note.app.feature.note;

public class NoteNotFoundException extends RuntimeException{

    public NoteNotFoundException(Long id){

        super("Note not found with id: " + id);
    }
}
