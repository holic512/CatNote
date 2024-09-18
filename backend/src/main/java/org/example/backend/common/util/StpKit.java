/**
 * File Name: StpKit.java
 * Description: 针对于 satoken的 会话对象配置
 * Author: holic512
 * Created Date: 2024-09-17
 * Version: 1.0
 * Usage:
 * 例如 StpKit.user 和 StpKit.admin 是相互独立的
 */
package org.example.backend.common.util;

import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;

public class StpKit {
    /**
     * 默认原生会话对象
     */
    public static final StpLogic DEFAULT = StpUtil.stpLogic;

    /**
     * Admin 会话对象，管理 Admin 表所有账号的登录、权限认证
     */
    public static final StpLogic ADMIN = new StpLogic("admin");

    /**
     * User 会话对象，管理 User 表所有账号的登录、权限认证
     */
    public static final StpLogic USER = new StpLogic("user");
}
