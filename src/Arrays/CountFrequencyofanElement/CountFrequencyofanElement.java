package Arrays.CountFrequencyofanElement;

public class CountFrequencyofanElement {
    public static int frequencey(int[] arr, int target) {
        int count = 0;
        for(int i = 0; i < arr.length ; i++ ){
            if(arr[i] == target){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[] arr = {5, 6 , 7 , 5 , 9};
        System.out.println("Frequency of the target element is : " + frequencey(arr, 5));
    }
}
