package Arrays.ContainsDuplicates;

public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        if(nums.length ==0) return false;
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {5};
        System.out.println(containsDuplicate(nums));
    }
}
