/**
 * File Name: Note.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-11
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "notes")
public class Note {

    @Id
    private Long noteId;

    private String content;

    private LocalDateTime lastSavedAt;

}
