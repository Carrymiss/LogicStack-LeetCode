package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-02
 * @Time: 9:57
 * @Description: 题目1694: 重新格式化电话号码
 */
public class Topic_01694 {
    public String reformatNumber(String number) {
        // 去除空格和破折号
        String s = number.replace(" ", "").replace("-", "");
        StringBuilder sb = new StringBuilder();
        // 获取字符串长度 已知长度最少>=2
        int n = s.length();
        for (int i = 0; i < n; i += 3) {
            // 添加分隔符
            if (sb.length() != 0) {
                sb.append("-");
            }
            if (i + 5 > n) {
                if (i + 4 > n) {
                    // 添加[2] 或者[3]
                    sb.append(s.substring(i));
                } else {
                    // 添加[2][2]
                    sb.append(s.substring(i, i + 2))
                            .append("-")
                            .append(s.substring(i + 2));
                }
                break;
            }
            // 常规截取三个字符
            sb.append(s.substring(i, i + 3));
        }
        // 输出拼接好的字符串
        return sb.toString();
    }
}
