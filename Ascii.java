
/*
 * @author Ali Çolak
 * 21.12.2020
 */
import java.util.Arrays;
import java.util.Scanner;

public class Ascii {

    // The program that prints the new word formed by shifting the letters in the entered word as much as the ascii code values


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the world : ");
        System.out.println(speedify(scanner.nextLine()));
    }

    public static String speedify(final String input) {

        int[] array = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            int ascii = input.charAt(i) - 65;
            array[i] = i + ascii;
        }

        int max = array[0];
        for (int i : array) {
            if (max < i) {
                max = i;
            }
        }

        String [] finalArray = new String[max + 1];
        String str = "";

        Arrays.fill(finalArray, 0, max + 1, " ");

        out: for (int i = 0; i < input.length(); i++) {
            ic: for (int j = 0; j < input.length(); j++) {
                if (array[j] == array[i]) {
                    if (j > i) {
                        continue out;
                    }
                }
            }
            finalArray[array[i]] = input.substring(i, i + 1);
        }

        for (String tempStr : finalArray) {
            str += tempStr;
        }

        return str ;
        
    }
}
