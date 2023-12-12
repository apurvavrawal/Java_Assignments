public class Palindrome {
    public static boolean isPalindrome(String input){

        String reverse = "";
        boolean answer = false;

        for (int i = input.length() - 1; i >= 0; i--) {
            reverse = reverse + input.charAt(i);
        }

        if (input.equals(reverse))
            answer = true;
        return answer;
    }

    public static void main(String[] args)
    {
        String str = "apurupa";
        str = str.toLowerCase();
        boolean A = isPalindrome(str);
        System.out.println(A);
    }
}
