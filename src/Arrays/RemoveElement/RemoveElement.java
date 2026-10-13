package Arrays.RemoveElement;

public class RemoveElement {
    public static int valueofValidElement(int[] nums, int val){
        if(nums==null||nums.length==0){
            return 0;
        }
        int write = 0;
        for(int i = 0; i < nums.length; i++){
                if(nums[i]!=val){
                    nums[write++]=nums[i];
                }
            }


        return write;
    }
    public static void main(String[] args) {
        int[] nums = {5, 5, 7, 4 , 5 , 2, 3};
        int write = valueofValidElement(nums, 5);
        for(int i =0 ; i < write ;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        System.out.println("Element count : "+ write);
    }
}
