package Arrays.MoveZeroes;

public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        if(nums==null||nums.length==0){
            System.out.println("Empty Array");
        }
        int write = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[write++] = nums[i];
            }
        }
        for(int i = write; i < nums.length; i++){
            nums[i] = 0;
        }

        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
    }
    public static void main(String[] args) {
        int[] nums = {};
        moveZeroes(nums);
    }
}
