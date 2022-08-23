import java.util.Scanner;

public class TheNumberOfWordsAndCharactersInTheText {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a text");
        String enteredText = scanner.nextLine();

        String text = enteredText.trim();

        int numberOfSpaces = 0;
        int numberOfTwoSpaces = 0;

        for (int i = 0; i < text.length(); i++) {
            char tempChar = text.charAt(i);
            if (tempChar == ' ') {
                numberOfSpaces++;
            }
        }
        for (int i = 0; i < text.length() - 2; i++) {
            if (text.substring(i, i + 2).equals("  ")) {
                numberOfTwoSpaces++;
            }
        }

        int numberOfWords = ((numberOfSpaces + 1) - numberOfTwoSpaces);
        int endNumberOfSpaces = numberOfWords + numberOfTwoSpaces - 1;
        int numberOfCharacters = text.length() - (endNumberOfSpaces);

        System.out.println("Number Of Words : " + numberOfWords);
        System.out.println("Number Of Characters : " + numberOfCharacters);

    }
}
