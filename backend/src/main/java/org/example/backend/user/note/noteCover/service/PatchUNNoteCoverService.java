/**
 * File Name: GetUNNoteCoverService.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-10
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteCover.service;

import org.example.backend.user.note.noteCover.enums.PatchContextEnum;

public interface PatchUNNoteCoverService {
    PatchContextEnum UpNoteCover(Long userId, Long noteId,String coverUrl);
}
