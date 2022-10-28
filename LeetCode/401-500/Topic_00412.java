package leetcode_BasicAlgorithm.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZTY
 * @Create: 2022-10-28
 * @Time: 8:17
 * @Description: 题目412 : Fizz Buzz
 */
public class Topic_00412 {
    /**
     * 方法： 模拟
     */
    public List<String> fizzBuzz(int n) {
        List<String> arrys=new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if(i%15==0){
                arrys.add("FizzBuzz");
            }else if(i%3==0){
                arrys.add("Fizz");
            }else if(i%5==0){
                arrys.add("Buzz");
            }else {
                arrys.add(i+"");
            }
        }
        return arrys;
    }
}
