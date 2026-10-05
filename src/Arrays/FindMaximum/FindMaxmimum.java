package Arrays.FindMaximum;

import java.util.*;

public class FindMaxmimum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a =  new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int currentMax = a[0];
        for(int i = 1; i < n; i++) {
            if(currentMax < a[i]){
                currentMax = a[i];
            }
            }
        System.out.println(currentMax);
        }
    }

