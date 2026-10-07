package Arrays.SecondLargestElement;

public class SecondLargest {
    public static void main(String[] args) {
        int[] arr = {25, 85, 45, 10, 18, 45};
        int largest=arr[0];
        Integer secondLargest = null;
        for(int i = 1 ;i < arr.length ; i++) {
            int current = arr[i];
            if (current > largest) {
                secondLargest = largest;
                largest = current;
            } else if (secondLargest == null && current < largest) {
                secondLargest = current;
            } else if (secondLargest != null &&current < largest && current > secondLargest) {
                secondLargest = current;
            }
        }
        System.out.println(largest);
        System.out.println(secondLargest);

}
}
