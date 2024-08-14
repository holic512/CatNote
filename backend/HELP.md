# 项目描述

该目录包含 **CatNote** 后端应用。

# 项目标准

应在文件开头注释信息,模板为(编辑模式查看):

/**
*CreateTime: ${YEAR}-${MONTH}-${DAY}
*Description: Todo
*Version: 1.0
*/

当添加新的包的时候应在help.md中标注

# 技术栈

- 暂无

# 项目结构

- controller：此目录主要是存放**Controllerde**
  ,比如：UserController.java，也有的项目是把action放在controller目录下，有的是把UserController.java放在action目录下。
- service：这里分接口和实现类，接口在service目录下，接口实现类在service/impl目录下。
- dao：持久层，目前比较流行的Mybatis或者jpa之类的。
- entity：就是数据库表的实体对象。
- param：放的是请求参数和相应参数UserQueryRequest、BaseResponse等
- util：通常是一些工具类，比如说：DateUtil.java、自定义的StringUtil.java
- interrupt：项目统一拦截处理，比如：登录信息，统一异常处理
- exception：自定义异常，异常错误码
- config：配置读取相关，比如RedisConfig.java