package Arrays.FindMinimumElement;

public class FindMinimum {
    public static void main(String[] args) {
        int[] arr = {25, 25 ,-6 , 17, 101, -10001, 100};
        int currMin = arr[0];
        for(int i = 1; i < arr.length - 1; i++){
            if(currMin > arr[i]){
                currMin = arr[i];
            }
        }
        System.out.println(currMin);
    }
}
