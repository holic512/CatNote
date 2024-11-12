/**
 * 文件名: PNoteImageService.java
 * 描述: 用户笔记图片处理服务接口，提供上传并压缩图片的功能。
 * 作者: holic512
 * 创建日期: 2024-11-08
 * 版本: 1.0
 * 用法: 该接口定义了处理用户笔记图片的服务方法。具体实现会在 `PNoteImageServiceImpl` 中提供，
 * 包括上传图片并压缩的功能。通过该接口，用户可以上传图片并返回处理结果及图片 URL。
 */

package org.example.backend.user.note.noteImage.service;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.web.multipart.MultipartFile;

public interface PUNoteImageService {

    /**
     * 上传并压缩用户笔记图片。
     * <p>
     * 该方法接收用户上传的图片文件，对其进行压缩处理，并将处理后的图片保存到指定位置。
     * 返回处理结果和保存后图片的 URL 地址。如果操作成功，返回保存图片的 URL；如果失败，返回错误信息。
     *
     * @param uid   用户的唯一标识符，用于生成图片文件名及与用户相关联的图片存储路径。
     * @param image 需要上传的图片文件，类型为 MultipartFile。
     * @param type  图片文件的类型（例如：jpg、png），用于生成保存图片的扩展名。
     * @return 返回一个 Pair 对象，包含操作状态和图片的 URL 地址（如果成功）或者错误信息（如果失败）。
     * - 第一个元素为布尔值，表示操作是否成功（true 为成功，false 为失败）。
     * - 第二个元素为图片的 URL 地址（如果成功）或 null（如果失败）。
     */
    Pair<Object, Object> saveCompressedNoteImage(String uid, MultipartFile image, String type);
}
