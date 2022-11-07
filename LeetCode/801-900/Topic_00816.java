package practise;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZTY
 * @Create: 2022-11-07
 * @Time: 6:45
 * @Description: 题目816 ：模糊坐标
 */
public class Topic_00816 {
    /**
     * 方法1：模拟 笛卡儿积
     */
    public List<String> ambiguousCoordinates01(String s) {
        List<String> res = new ArrayList<>();
        // 去掉括号
        s = s.substring(1, s.length() - 1);
        for (int i = 1; i < s.length(); i++) {
            // 获取所有组合
            String temp1 = s.substring(0, i);
            String temp2 = s.substring(i);
            // 去除不符合条件的组合
            List<String> list1 = getPos(temp1);
            if (list1.isEmpty()) {
                continue;
            }
            List<String> list2 = getPos(temp2);
            if (list2.isEmpty()) {
                continue;
            }

            // 获取正确的笛卡儿积
            for (String s1 : list1) {
                for (String s2 : list2) {
                    res.add("(" + s1 + ", " + s2 + ")");
                }
            }
        }
        return res;
    }

    public List<String> getPos(String s) {
        List<String> pos = new ArrayList<>();
        // 去除首位是0 同时字符串不为0
        if (s.charAt(0) != '0' || "0".equals(s)) {
            pos.add(s);
        }
        for (int p = 1; p < s.length(); p++) {
            // 去除首位，末尾不为0的组合
            if ((p != 1 && s.charAt(0) == '0') || s.charAt(s.length() - 1) == '0') {
                continue;
            }
            pos.add(s.substring(0, p) + "." + s.substring(p));
        }
        return pos;
    }
}
