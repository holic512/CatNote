/**
 * File Name: fetchPageData.java
 * Description: 用于获取 页数 和 页码的 请求体
 * Author: holic512
 * Created Date: 2024-10-09
 * Version: 1.0
 * Usage:
 * 用于获取 页数 和 页码的 请求体
 */
package org.example.backend.admin.userMm.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class FetchPageData {

    // 页数
    @PositiveOrZero
    @NotNull(message = "page cannot be null")
    int page;

    // 行数大小
    @PositiveOrZero
    @NotNull(message = "rows cannot be null")
    int rows;
}
