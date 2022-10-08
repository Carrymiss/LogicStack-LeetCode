package practise;

import java.util.Scanner;

/**
 * @Author: LQ
 * @Create: 2022-04-03
 * @Time: 14:05
 * @Description: 题目278 ：第一个错误的版本
 */
public class Topic_00278 {
    public class VersionControl {
        boolean isBadVersion(int version) {
            int bad = 0;
            if (bad == version) {
                return false;
            } else {
                return true;
            }
        }
    }

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int left=1,right=n;
            while (left<right){
                int minbad = (right-left)/2+left;
                if(isBadVersion(minbad)){
                    right=minbad;
                }else {
                    left=minbad+1;
                }
            }
            return left;
        }
    }


}