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
/*
IF THEY ASK US TO RETURN THE MODIFIED ARRAY NOT THE COUNT
EX : ARR = [1,2,2,3,4,2] VAL = 2 THEN RETURN OP = [1,3,4] NOT COUNT
THEN USE THIS

package Arrays.RemoveElement;

import java.util.Arrays;

public class RemoveElement {

    public static int[] valueofValidElement(int[] nums, int val) {
        if (nums == null) {
            return null;
        }

        int write = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[write++] = nums[i];
            }
        }

        return Arrays.copyOf(nums, write); //Arrays.copyOf(nums, write) creates a new array of exactly write elements.
    }

    public static void main(String[] args) {
        int[] nums = {5, 5, 7, 4, 5, 2, 3};

        int[] result = valueofValidElement(nums, 5);

        System.out.println(Arrays.toString(result)); or we can print usinf for loop also
    }
}
*/