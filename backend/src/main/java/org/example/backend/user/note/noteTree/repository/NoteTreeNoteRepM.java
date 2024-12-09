/**
 * File Name: NoteTreeNoteRepM.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-09
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteTree.repository;

import org.example.backend.common.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteTreeNoteRepM extends MongoRepository<Note, Long> {

}