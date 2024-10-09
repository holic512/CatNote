/**
 * File Name: AdminOUserMmService.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-09-23
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.admin.userMm.service;

import org.example.backend.admin.userMm.request.FetchPageData;
import org.springframework.http.ResponseEntity;

public interface AdminOUserMmService {

    /**
     * 获取在线用户数量
     *
     * @return 用户数量
     */
    public int fetchOUserCount();


    /**
     * 获取对应页数的 在线用户 信息
     *
     * @param fetchPageData 带有 页码 和 页数的 请求体
     * @return 自定义响应体
     */
    public ResponseEntity<Object> fetchPageDate(FetchPageData fetchPageData);


    public ResponseEntity<Object> logout(String uid);

    public ResponseEntity<Object> kickout(String uid);
}
