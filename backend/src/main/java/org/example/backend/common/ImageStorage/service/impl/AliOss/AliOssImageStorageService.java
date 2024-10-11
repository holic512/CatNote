/**
 * File Name: AliOssImageStorageService.java
 * Description: 阿里云 OSS 图片存储服务实现
 * Author: holic512
 * Created Date: 2024-10-10
 * Version: 1.0
 * Usage: 提供将图片保存到阿里云 OSS、删除图片、获取图片和生成图片访问 URL 的功能。
 */
package org.example.backend.common.ImageStorage.service.impl.AliOss;

import com.aliyun.core.utils.IOUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import org.example.backend.common.ImageStorage.service.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class AliOssImageStorageService implements ImageStorageService {

    private static final Logger logger = LoggerFactory.getLogger(AliOssImageStorageService.class);

    private final String bucketName;
    private final String urlPrefix;
    private final OSS aliOss;

    public AliOssImageStorageService(AliOssConnection aliOssConnection) {
        this.aliOss = aliOssConnection.createOssClient();
        this.bucketName = aliOssConnection.getBucketName();
        this.urlPrefix = aliOssConnection.getUrlPrefix();
    }

    @Override
    public void saveImage(String id, byte[] image) {
        try (InputStream inputStream = new ByteArrayInputStream(image)) {
            // 创建上传请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, id, inputStream);
            aliOss.putObject(putObjectRequest);
            logger.info("成功保存图片: {}", id);
        } catch (Exception e) {
            logger.error("保存图片到阿里云 OSS 失败: {}", e.getMessage());
            throw new RuntimeException("保存图片到阿里云 OSS 失败", e);
        }
    }

    @Override
    public void deleteImage(String id) {
        try {
            aliOss.deleteObject(bucketName, id);
            logger.info("成功删除图片: {}", id);
        } catch (Exception e) {
            logger.error("删除图片失败: {}", e.getMessage());
            throw new RuntimeException("删除图片失败", e);
        }
    }

    @Override
    public byte[] getImage(String id) {
        try (OSSObject ossObject = aliOss.getObject(bucketName, id);
             InputStream inputStream = ossObject.getObjectContent()) {

            byte[] imageData = IOUtils.toByteArray(inputStream);
            logger.info("成功获取图片: {}", id);
            return imageData;
        } catch (IOException e) {
            logger.error("获取图片失败: {}", e.getMessage());
            throw new RuntimeException("获取图片失败", e);
        }
    }

    @Override
    public String getImageUrl(String id) {
        try {
            Date expiration = new Date(new Date().getTime() + 3600 * 1000); // 设置 URL 过期时间为1小时
            URL url = aliOss.generatePresignedUrl(bucketName, id, expiration);
            logger.info("生成图片访问 URL: {}", url);
            return url.toString();
        } catch (Exception e) {
            logger.error("获取图片 URL 失败: {}", e.getMessage());
            throw new RuntimeException("获取图片 URL 失败", e);
        }
    }
}
