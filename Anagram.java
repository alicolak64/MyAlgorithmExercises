import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Ali Çolak
 * 29.06.2022
 */

public class Anagram {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("First world : ");
        String str1 = scanner.nextLine();
        System.out.print("Second world : ");
        String str2 = scanner.nextLine();
        boolean isAnagram = true;

        if (str1.length() != str2.length()) {
            isAnagram = false;
        } else {
            char [] array1 = str1.toCharArray();
            char [] array2 = str2.toCharArray();

            Arrays.sort(array1);
            Arrays.sort(array2);

            if (Arrays.compare(array1,array2) < 0) {
                isAnagram = false ;
            }
        }

        if (isAnagram) {
            System.out.println("Anagram");
        } else
            System.out.println("Not anagram");

    }

}
