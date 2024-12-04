#前端安装
# 使用官方Nginx镜像作为基础镜像
FROM nginx:alpine


# 设置时区为上海（中国时区）
RUN apk update && \
    apk add --no-cache tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone


# 删除默认的Nginx配置文件
RUN rm /etc/nginx/conf.d/default.conf

# 将自定义的Nginx配置文件复制到容器中
COPY default.conf /etc/nginx/conf.d/

# 将构建好的Vue项目复制到Nginx的默认目录
COPY dist /usr/share/nginx/html

# 暴露80端口
EXPOSE 80

# 启动Nginx
CMD ["nginx", "-g", "daemon off;"]

