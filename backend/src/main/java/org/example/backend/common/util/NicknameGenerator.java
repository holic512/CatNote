/**
 * File Name: NicknameGenerator.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-09-20
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.util;

import java.util.Random;

public class NicknameGenerator {
    private static final String[] adjectives = {
            "胖胖的", "聪明的", "快乐的", "神秘的", "勇敢的", "可爱的", "迅速的", "美丽的", "疯狂的", "勤劳的"
    };

    private static final String[] nouns = {
            "雷达", "小猫", "火箭", "小狗", "星星", "影子", "骑士", "美女", "西瓜", "苹果"
    };

    public static String generateNickname() {
        Random random = new Random();
        String randomAdjective = adjectives[random.nextInt(adjectives.length)];
        String randomNoun = nouns[random.nextInt(nouns.length)];
        return randomAdjective + randomNoun;
    }

    public static void main(String[] args) {
        // 示例用法
        for (int i = 0; i < 5; i++) {
            System.out.println(generateNickname());
        }
    }
}
