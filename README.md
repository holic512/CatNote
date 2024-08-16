# CatNote 📓

**CatNote** 是一个基于 Vue 3 和 Spring Boot 的云笔记平台，更简洁，更高效，更智能, 让你的笔记管理更加轻松！

![CatNote](https://img.shields.io/badge/Project-CatNote-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)
![Version](https://img.shields.io/badge/Version-1.0.0-orange)

## 目录

- [项目概述](#项目概述)
- [主要功能](#主要功能-rocket)
    - [用户管理](#用户管理)
        - [用户注册和登录](#用户注册和登录)
        - [用户个人笔记](#用户个人笔记)
    - [笔记功能](#笔记功能)
    - [编辑和预览](#编辑和预览)
    - [导入和导出功能](#导入和导出功能)
    - [分享功能](#分享功能)
    - [代码高亮](#代码高亮)
    - [图片处理](#图片处理)
    - [版本控制](#版本控制)
    - [后台管理功能](#后台管理功能)
        - [用户管理](#用户管理-1)
        - [笔记管理](#笔记管理)
- [如何运行项目](#如何运行项目)
- [贡献指南](#贡献指南)
- [许可证](#许可证)

## 项目概述

**CatNote** 结合了现代前后端技术，为用户提供了一个功能丰富的笔记平台。项目的主要技术栈如下：

- **前端**: Node.JS V20.12.0 + Vue 3 + Vite + Element Plus
- **后端**: Java17 + Spring Boot
- **数据库**: MySQL + Redis
- **富文本编辑器**: TipTap
- **数据展示**: Echarts
- **容器化**: Docker
- **存储方式**: 笔记以 Markdown 格式存储，图片支持本地存储或第三方图床

## 主要功能 

### 用户管理

- **用户注册和登录**:
    - 🆕 **注册**：通过邮箱或其他认证方式创建新账户。
    - 🔑 **登录**：通过邮箱,账号和密码登录系统。
    - 🛡️ **身份验证**：使用 JWT（JSON Web Token）进行用户认证。

- **用户个人笔记**:
    - 📓 **个人笔记管理**：用户可以访问和管理自己的笔记。
    - 👤 **用户配置文件**：用户可以编辑自己的个人信息。

### 笔记功能

- ✏️ **创建/编辑笔记**：创建新笔记并进行编辑。
- 🗑️ **删除/归档笔记**：支持笔记分类以便组织和检索。

### 编辑和预览

- 🖋️ **Markdown 编辑器**：支持 Markdown 语法，实时预览 Markdown 内容。
- 👀 **同屏编辑预览**：编辑栏和预览栏同屏显示。

### 导入和导出功能

- **导入功能**:
    - 📥 **Markdown 导入**：支持从本地文件或其他云存储服务中导入 Markdown 格式的笔记。
    - 🔗 **分享链接导入**：通过分享的展示链接导入其他用户的笔记。

- **导出功能**:
    - 📤 **Markdown 导出**：将笔记导出为 Markdown 格式文件，支持单个笔记和批量导出。
    - ⚙️ **导出设置**：提供导出选项，如是否包括图片、标签等。

### 分享功能

- **生成展示链接**:
    - 🔗 **分享设置**：为笔记生成公共展示链接。
    - 🔒 **权限设置**：设置分享链接的权限，如公开、仅限查看等。
    - 📜 **访问控制**：查看分享记录，撤销分享。

- **链接访问**:
    - 🌐 **展示页面**：生成的展示链接将带用户进入一个只读的展示页面。
    - 🔐 **防盗链**：确保分享链接的安全性，防止未经授权的访问。

### 代码高亮

- 💻 **定制代码高亮**：支持多种编程语言的代码高亮显示，自定义高亮规则和样式。
- 🌈 **彩虹方括号**：为括号添加彩虹色显示，增加代码的可读性。

### 图片处理

- 🖼️ **拖放/粘贴图片**：支持直接从剪贴板粘贴或拖放图片到编辑器中，自动上传并插入图片链接。

### 版本控制

- ⏳ **历史版本功能**：记录每次保存的版本，支持查看和恢复历史版本。
- 🔍 **版本比较**：对比不同版本的差异。

### 后台管理功能

- **用户管理**:
    - 👥 **用户管理**：显示所有注册用户的列表,并可以添加,修改,更新,删除用户。
    - 🛠️ **权限管理**：为用户分配不同角色，如普通用户、管理员。

- **笔记管理**:
    - 📋 **笔记列表**：查看所有用户的笔记（管理员权限）。
    - 📜 **笔记审查**：审查和管理笔记内容，删除违规内容。
    - 📊 **统计分析**：查看笔记使用情况统计数据，如创建数量、最活跃用户等。

## 如何运行项目

=======暂无=======

# 贡献指南

如果你想为此项目做出贡献，请 fork 该项目，并提交 pull request。

1. Fork 本仓库
2. 创建功能分支 (git checkout -b feature/AmazingFeature)
3. 提交更改 (git commit -m 'Add some AmazingFeature')
4. 推送到分支 (git push origin feature/AmazingFeature)
5. 打开一个 pull request

## 许可证

=======暂无=======

---

感谢您使用 CatNote！😊 如果您有任何问题或建议，请随时联系我。
