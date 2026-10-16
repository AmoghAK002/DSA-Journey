package Strings.CheckPalindrome;

public class CheckPalindrome {
    public static boolean isPalindrome(String str) {
        if(str == null) return false;
        if(str.isEmpty()) return true;
        int i = 0;
        int j = str.length() - 1;
        while(i < j){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String str = null;
        System.out.println(isPalindrome(str));
    }
}
