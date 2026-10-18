package Arrays.TwoSum;
import java.util.*;
public class teoSumOptimised {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 10;
        System.out.println(Arrays.toString(twoSum(nums,target)));
    }
}
