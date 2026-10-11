package Arrays.SecondSmallest;

public class SecondSmallest {
    public static Integer SecSmallest(int[] arr){
        int smallest = arr[0];
        Integer SecondSmallest = null;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < smallest){
                SecondSmallest =smallest;
                smallest = arr[i];
            }
            else if(SecondSmallest == null && arr[i] > smallest){
                SecondSmallest = arr[i];
            }
            else if(SecondSmallest != null && arr[i] > smallest && arr[i] < SecondSmallest){
                SecondSmallest = arr[i];
            }
        }
        return SecondSmallest;
    }
    public static void main(String[] args) {
        int[] arr = {5,5,5,5,5,5};
        System.out.println(SecSmallest(arr));
    }
}
