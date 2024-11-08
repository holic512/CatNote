/**
 * File Name: AddImage.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-08
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteImage.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RQImage {
    String type;
    MultipartFile image;
}
