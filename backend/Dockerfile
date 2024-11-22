# 第一阶段：构建阶段
FROM maven:3.8.6-openjdk-17 AS build

# 设置工作目录
WORKDIR /app

# 复制pom.xml文件
COPY pom.xml .

# 下载所有依赖
RUN mvn dependency:go-offline

# 复制源代码
COPY src ./src

# 构建应用
RUN mvn clean package -DskipTests

# 第二阶段：运行阶段
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 从构建阶段复制编译好的JAR文件
COPY --from=build /app/target/my-app.jar /app/my-app.jar

# 暴露服务端口
EXPOSE 8080

# 定义启动命令
CMD ["java", "-jar", "my-app.jar"]