package practise;

/**
 * @Author: ZTY
 * @Create: 2022-11-06
 * @Time: 7:39
 * @Description: 题目1678 ：设计 Goal 解析器
 */
public class Topic_01678 {
    /**
     * 方法1 ： 使用jdk中的方法
     */
    public String interpret01(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }

    /**
     * 方法2 ： 遍历
     */
    public String interpret02(String command) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < command.length()) {
            char c = command.charAt(i);
            // 第一种情况 字符 G
            if (c == 'G') {
                sb.append(c);
                i++;
                continue;
            }

            // 判断剩余的括号情况
            char next=command.charAt(i+1);
            if(next==')'){
                sb.append('o');
                i+=2;
            }else {
                sb.append('a').append('l');
                i+=4;
            }
        }
        return sb.toString();
    }
}
