package Arrays.TwoSum;
import java.util.*;
public class twoSum {
    public static int[] TwoSum(int[] nums, int target) {
        for(int i = 0 ; i < nums.length - 1; i++){
            for(int j = i + 1 ; j < nums.length ; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int[] nums = {-1, -2, -3, -4, -5};
        int target = -8;
        int[] ans = TwoSum(nums,target);
        System.out.println(Arrays.toString(ans));
    }
}
