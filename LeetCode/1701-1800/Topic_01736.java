package practise;

/**
 * @Author: ZTY
 * @Create: 2023/2/5
 * @Time: 14:11
 * @Description: 题目1736 ：卡车上的最大单元数
 */
public class Topic_01736 {
    /**
     * 方法1：模拟
     * <p> 规则：
     * <p>  第一位：如果需要被替换，优先替换为 2，当然前提是第二位不能超过 4。否则会出现 24:xx、25:xx 等；
     * <p>  第二位：如果需要被替换，根据第一位是什么，决定替换为 9 还是 3；
     * <p>  第三位：固定为 :；
     * <p>  第四位：如果需要被替换，替换为 5；
     * <p>  第五位：如果需要被替换，替换为 9。
     */
    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        sb.append(time.charAt(0) == '?' ? (time.charAt(1) == '?' || time.charAt(1) < '4') ? '2' : '1' : time.charAt(0));
        sb.append(time.charAt(1) == '?' ? sb.charAt(0) == '2' ? '3' : '9' : time.charAt(1));
        sb.append(":");
        sb.append(time.charAt(3) == '?' ? "5" : time.charAt(3));
        sb.append(time.charAt(4) == '?' ? "9" : time.charAt(4));
        return sb.toString();
    }
}
