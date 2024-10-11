/**
 * File Name: AliOssConnection.java
 * Description: 阿里云 OSS 连接类，用于创建和管理 OSS 客户端连接
 * Author: holic512
 * Created Date: 2024-10-10
 * Version: 1.0
 * Usage: 通过从数据库加载配置来初始化阿里云 OSS 客户端连接，提供与 OSS 交互的方法。
 */
package org.example.backend.common.ImageStorage.service.impl.AliOss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.common.entity.ServerConfigData;
import org.example.backend.common.ImageStorage.repository.ServerConfigDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Data
public class AliOssConnection {

    private static final Logger logger = LoggerFactory.getLogger(AliOssConnection.class);

    private String endpoint;
    private String accessKeyID;
    private String accessKeySecret;
    private String bucketName;
    private String urlPrefix;

    public AliOssConnection(ServerConfigDataRepository dataRepository) {
        // 查询 AliOss 配置
        ServerConfigData serverConfigData = dataRepository.findConfigJsonByConfigName("AliOss");
        String json = serverConfigData.getConfigJson();

        try {
            // 使用 Jackson 解析
            ObjectMapper objectMapper = new ObjectMapper();
            AliOssConfig config = objectMapper.readValue(json, AliOssConfig.class);

            this.endpoint = config.getEndpoint();
            this.accessKeyID = config.getAccessKeyID();
            this.accessKeySecret = config.getAccessKeySecret();
            this.bucketName = config.getBucketName();
            this.urlPrefix = config.getUrlPrefix();

            logger.info("成功加载阿里云 OSS 配置");
        } catch (IOException e) {
            logger.error("加载阿里云 OSS 配置失败: {}", e.getMessage());
            throw new RuntimeException("加载阿里云 OSS 配置失败", e);
        }
    }

    /**
     * 创建阿里云 OSS 客户端
     *
     * @return 阿里云 OSS 客户端对象
     */
    public OSS createOssClient() {
        logger.info("正在创建阿里云 OSS 客户端");
        return new OSSClientBuilder().build(endpoint, accessKeyID, accessKeySecret);
    }

    /**
     * 阿里云 OSS 配置文件的 POJO 类
     */
    @Setter
    @Getter
    public static class AliOssConfig {
        private String endpoint;
        private String accessKeyID;
        private String accessKeySecret;
        private String bucketName;
        private String urlPrefix;
    }
}
