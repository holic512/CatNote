/**
 * File Name: ImageStorageServiceFactory.java
 * Description: 图片存储服务的工厂类，根据配置生成相应的存储服务实例
 * Author: holic512
 * Created Date: 2024-10-10
 * Version: 1.0
 * Usage: 通过配置文件生成适当的 ImageStorageService 实现，支持本地存储和阿里云 OSS 存储。
 */
package org.example.backend.common.ImageStorage.service;

import org.example.backend.common.ImageStorage.repository.ServerConfigDataRepository;
import org.example.backend.common.ImageStorage.service.impl.AliOss.AliOssConnection;
import org.example.backend.common.ImageStorage.service.impl.AliOss.AliOssImageStorageService;
import org.example.backend.common.ImageStorage.service.impl.Local.LocalImageStorageService;
import org.example.backend.common.entity.ServerConfig;
import org.example.backend.common.repository.ServerConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Configuration
public class ImageStorageServiceFactory {

    private static final Logger logger = LoggerFactory.getLogger(ImageStorageServiceFactory.class);

    private final ServerConfigRepository serverConfigRepository;
    private final ServerConfigDataRepository serverConfigDataRepository;

    @Autowired
    public ImageStorageServiceFactory(ServerConfigRepository serverConfigRepository, ServerConfigDataRepository serverConfigDataRepository) {
        this.serverConfigRepository = serverConfigRepository;
        this.serverConfigDataRepository = serverConfigDataRepository;
    }

    @Bean
    public ImageStorageService imageStorageService() {
        // 在数据库中获取图片存储模式配置
        Optional<ServerConfig> optionalConfig = serverConfigRepository.findById(1L);

        if (!optionalConfig.isPresent()) {
            logger.error("未找到服务器配置，使用默认的本地存储");
            return new LocalImageStorageService();
        }

        ServerConfig serverConfig = optionalConfig.get();
        int imageStorageMode = serverConfig.getImageStorageMode();

        logger.info("当前图片存储模式: {}", imageStorageMode);

        // 根据工厂模式自动调整实例哪个实现类
        switch (imageStorageMode) {
            case 0:
                logger.info("使用本地图片存储服务");
                return new LocalImageStorageService();
            case 1:
                logger.info("使用阿里云 OSS 图片存储服务");
                AliOssConnection aliOssConnection = new AliOssConnection(serverConfigDataRepository);
                return new AliOssImageStorageService(aliOssConnection);
            default:
                logger.warn("未知的存储模式，使用默认的本地存储");
                return new LocalImageStorageService();
        }
    }
}
