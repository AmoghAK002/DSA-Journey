package Arrays.RemoveDuplicatesFromASortedArray;

public class RemoveDuplicates {
    public static int  removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int write = 1;
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i - 1] != nums[i]){
                nums[write++] = nums[i];
            }
        }
        return write;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,5,6,7};
        int k = removeDuplicates(nums);
        System.out.println("Unique Count : " + k);
        //optional
        System.out.println("Updated Array : ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }

    }
}
