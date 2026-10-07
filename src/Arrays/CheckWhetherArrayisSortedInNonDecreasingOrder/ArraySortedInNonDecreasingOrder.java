package Arrays.CheckWhetherArrayisSortedInNonDecreasingOrder;
//solution one
/*public class ArraySortedInNonDecreasingOrder {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 5};
        boolean isSorted = true;
        for (int i = 1; i < arr.length; i++) {
            int prev = arr[i - 1];
            int curr = arr[i];
            if (prev > curr) {
                isSorted = false;
            }
        }
            if (isSorted) {
                System.out.println("Sorted array");
            } else {
                System.out.println("Not sorted");
            }
        }
    }
*/

//CLEANER SOLUTION
public class ArraySortedInNonDecreasingOrder {
    public static boolean isSorted(int[] arr){
        for(int i = 1; i < arr.length ; i++){
            if(arr[i-1] > arr[i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = {5, 6 , 7 , 8 , 9};
        System.out.println(isSorted(arr));
    }
}
