// * Description: axios实例
// * Version: 1.0

import axios from 'axios';

// 创建一个 Axios 实例
const instance = axios.create({
    baseURL: 'http://localhost:8080/', // 这里是你的基础 URL
    timeout: 3000, // 可选的超时时间
    headers: {'Content-Type': 'application/json'}
});

export default instance;
