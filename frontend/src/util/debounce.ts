// 定义防抖函数的类型
import {ElMessage} from "element-plus";

export function debounceImmediate<T extends (...args: any[]) => any>(
    func: T,
    delay: number
): (...args: Parameters<T>) => void {
    let timer: ReturnType<typeof setTimeout> | null = null;
    let isInvoked = false;

    return (...args: Parameters<T>): void => {
        // 如果已经有定时器在运行，清除它
        if (timer) clearTimeout(timer);
        if (isInvoked) {
            ElMessage.warning("点击太过频繁了,稍后再试");
        }
        // 立即执行第一次点击
        if (!isInvoked) {
            func(...args);
            isInvoked = true;
        }

        // 重新设置定时器
        timer = setTimeout(() => {
            isInvoked = false;
        }, delay);
    };
}
