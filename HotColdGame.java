import java.util.Scanner;

public class HotColdGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int randomNumber=(int)(Math.random()*101);
        
        int guessNumber = -1 ;
        int countOfGuess = 0 ;

        while ( guessNumber != randomNumber ){

            System.out.println("Enter your guess.");
            guessNumber=scanner.nextInt();


            countOfGuess++;

            if ( randomNumber == guessNumber ){
                System.out.println("Congratulations, you guessed right on the" + countOfGuess + "th" + "try");
            } else {
                
                if ( guessNumber > randomNumber )
                    System.out.println("Down");
                else if ( guessNumber < randomNumber )
                    System.out.println("Up");
                
            }
        }
    }
}

