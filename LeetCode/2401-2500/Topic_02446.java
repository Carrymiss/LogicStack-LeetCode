package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-25
 * @Time: 8:48
 * @Description: 题目2446 ：判断两个事件是否存在冲突
 */
public class Topic_02446 {
    /**
     * 方法1 转换字符串 比较
     * <p>  思路：
     * <p>      区间 [a, b] 与 区间 [c, d] 的交集条件：
     * <p>          a <= d && c <= b
     */
    public boolean haveConflict01(String[] event1, String[] event2) {
        Integer e1_f = Integer.parseInt(event1[0].replace(":", ""));
        Integer e1_e = Integer.parseInt(event1[1].replace(":", ""));
        Integer e2_f = Integer.parseInt(event2[0].replace(":", ""));
        Integer e2_e = Integer.parseInt(event2[1].replace(":", ""));
        if (e1_e <= e2_e) {
            if (e1_e >= e2_f) {
                return true;
            }
        } else {
            if (e2_e >= e1_f) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法二： 不转换成数字
     * <p>  字符串通过compareTo比较
     */
    public boolean haveConflict02(String[] event1, String[] event2) {
        return event1[0].compareTo(event2[1]) <= 0 && event1[1].compareTo(event2[0]) >= 0;
    }

    /**
     * 方法三：逆向思维
     */
    public boolean haveConflict03(String[] event1, String[] event2) {
        return !(event1[0].compareTo(event2[1]) > 0 || event1[1].compareTo(event2[0]) < 0);
    }

    /**
     * 方法四：使用string的 hashcode()方法
     */
    public boolean haveConflict04(String[] event1, String[] event2) {
        return !(event1[0].hashCode() > event2[1].hashCode() || event1[1].hashCode() < event2[0].hashCode());
    }
}
