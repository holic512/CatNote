# 使用Node.js的官方镜像
FROM node:20.12.0-alpine

# 设置工作目录
WORKDIR /app

# 复制package.json和package-lock.json和 密钥 .npmrc
COPY .npmrc ./
COPY package*.json ./

RUN cat .npmrc

# 安装依赖
RUN npm install

# 复制应用源代码
COPY . .

# 暴露开发服务器端口
EXPOSE 8081

# 定义启动命令
CMD ["npm", "run", "dev"]