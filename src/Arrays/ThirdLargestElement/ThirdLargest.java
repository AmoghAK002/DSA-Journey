/*package Arrays.ThirdLargestElement;

public class ThirdLargest {
    public static Integer thirdLargest(int[] arr){

        if(arr == null || arr.length == 0){
            return null;
        }

        int largest = arr[0];
        Integer SecondLargest = null;
        Integer thirdlargest =  null;
        for(int i = 1 ; i < arr.length ; i++){
            int current = arr[i];
            if(current > largest){
                thirdlargest= SecondLargest;
                SecondLargest = largest;
                largest = current;
            }
            if(SecondLargest == null){
                if( current!=largest){
                    SecondLargest = current;
                }
            }else if(current < largest && current > SecondLargest){
                thirdlargest = SecondLargest;
                SecondLargest = current;
            }
             if(thirdlargest == null){
                 if(current != largest && current!= SecondLargest)
                thirdlargest = current;
            }else if(current < largest && current < SecondLargest && current > thirdlargest){
                 thirdlargest = current;
             }
        }
        return thirdlargest;
    }
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 6};
        System.out.println(thirdLargest(arr));
    }
}
*/

//cleaner code
package Arrays.ThirdLargestElement;

public class ThirdLargest {

    public static Integer thirdLargest(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int largest = arr[0];
        Integer secondLargest = null;
        Integer thirdLargest = null;

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];

            if (current > largest) {
                thirdLargest = secondLargest;
                secondLargest = largest;
                largest = current;
            } else if (current < largest) {
                if (secondLargest == null || current > secondLargest) {
                    thirdLargest = secondLargest;
                    secondLargest = current;
                } else if (current < secondLargest
                        && (thirdLargest == null || current > thirdLargest)) {
                    thirdLargest = current;
                }
            }
        }

        return thirdLargest;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 6};
        System.out.println(thirdLargest(arr));
    }
}
