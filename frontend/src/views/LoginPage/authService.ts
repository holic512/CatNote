// authService.ts

interface User {
    username: string;
    email: string;
    password: string;
}

export async function login(identifier: string, credential: string, method: 'email' | 'account'): Promise<void> {
    try {
        // 模拟登录请求
        const response = await fetch('/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ identifier, credential, method }),
        });

        if (!response.ok) {
            throw new Error('登录失败');
        }

        const data = await response.json();
        console.log('登录成功', data);
    } catch (error) {
        console.error('登录失败', error);
    }
}

export async function register(username: string, email: string, password: string): Promise<void> {
    try {
        // 模拟注册请求
        const response = await fetch('/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, email, password }),
        });

        if (!response.ok) {
            throw new Error('注册失败');
        }

        const data = await response.json();
        console.log('注册成功', data);
    } catch (error) {
        console.error('注册失败', error);
    }
}
