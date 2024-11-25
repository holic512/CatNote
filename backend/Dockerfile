# 使用OpenJDK镜像作为基础镜像
FROM openjdk:17-jdk-slim

# 将Spring Boot Jar包复制到容器中
ARG JAR_FILE=target/your-app-name.jar
COPY ${JAR_FILE} app.jar

# 暴露端口（与Spring Boot的server.port配置一致）
EXPOSE 8080

# 启动Spring Boot应用
ENTRYPOINT ["java", "-jar", "/app.jar"]
