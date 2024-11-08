/**
 * File Name: PNoteImageService.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-08
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteImage.service;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.web.multipart.MultipartFile;

public interface PNoteImageService {
    Pair<Object, Object> saveCompressedNoteImage(String uid, MultipartFile image, String type);
}
