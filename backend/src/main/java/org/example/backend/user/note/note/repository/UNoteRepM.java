/**
 * File Name: UNoteRepM.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-12
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.note.repository;

import org.example.backend.common.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UNoteRepM extends MongoRepository<Note, Long> {
}
