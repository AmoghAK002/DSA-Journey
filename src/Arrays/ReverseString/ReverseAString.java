package Arrays.ReverseString;

public class ReverseAString {
    public static void ReverseString(char[] str){
        if(str.length==0){
            System.out.println("Empty String");
        }
        int j = str.length-1;
        int i = 0;
        while(i < j){
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {
        char[] str = {'H','e','l','l','o'};
        ReverseString(str);
        for(int j=0;j<str.length;j++){
            System.out.print(str[j]+" ");
        }
    }
}
