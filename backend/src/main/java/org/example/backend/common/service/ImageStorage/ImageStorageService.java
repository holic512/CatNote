/**
 * 文件名: ImageStorageService.java
 * 描述: 图片存储服务接口，用于定义图片的保存、删除和获取操作。
 * 作者: holic512
 * 创建日期: 2024-09-23
 * 版本: 1.0
 * 用法: 实现该接口以提供图片存储的具体实现。
 */
package org.example.backend.common.service.ImageStorage;

public interface ImageStorageService {

    /**
     * 保存图片
     *
     * @param id 图片的唯一标识符
     * @param image 图片的字节数组
     */
    public void saveImage(String id, byte[] image);

    /**
     * 删除图片
     *
     * @param id 图片的唯一标识符
     */
    public void deleteImage(String id);

    /**
     * 获取图片
     *
     * @param id 图片的唯一标识符
     * @return 图片的字节数组
     */
    public byte[] getImage(String id);

    /**
     * 获取图片的 URL
     *
     * @param id 图片的唯一标识符
     * @return 图片的访问 URL 字符串
     */
    public String getImageUrl(String id);
}
