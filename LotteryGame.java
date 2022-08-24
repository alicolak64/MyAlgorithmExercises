import java.util.Scanner;

public class LotteryGame {

    public static void main(String[] args) {
        
        System.out.println("Welcome to today's lottery game.");
        System.out.println("If you have not bought a ticket, it will be taken from the box office and register for the lottery Note: Ticket Fee: 50 TL");
        System.out.println("If you guess the two-digit number we randomly assigned, you will win 1000 TL, if you guess the opposite, 500 TL, if you guess only one digit, you will win 100 TL.");
        System.out.println("I wish you good luck");

        int randomNumber = (int)( Math.random() * 90 ) + 10 ;

        System.out.println("Please enter a two digit number.");
        int randomNumberFirstDigit  = randomNumber / 10;
        int randomNumberSecondDigit = randomNumber % 10;

        Scanner scanner = new Scanner(System.in);

        int guessNumber = scanner.nextInt();
        int guessNumberFirstDigit  = guessNumber / 10 ;
        int guessNumberSecondDigit = guessNumber % 10 ;

        if ( guessNumber <= 99 && guessNumber >= 10 ){
            if ( guessNumber == randomNumber ){
                System.out.println("Number randomly assigned by the system : "+randomNumber+"  Your guess number : "+guessNumber);
                System.out.println("Congratulations, you have guessed the number correctly. You have WINNED the big jackpot, namely 1000 TL. You can get your prize at the box office.");
            }
            else if ( ( guessNumberFirstDigit == randomNumberSecondDigit ) &&  ( guessNumberSecondDigit == randomNumberFirstDigit ) ){
                System.out.println("Number randomly assigned by the system : "+randomNumber+"  Your guess number : "+guessNumber);
                System.out.println("Congratulations, you guessed the number incorrectly. YOU WIN the second prize, 500 TL. You can get your prize at the box office.");
            }
            else if ( ( guessNumberFirstDigit == randomNumberFirstDigit ) || ( guessNumberSecondDigit == randomNumberFirstDigit ) || ( guessNumberSecondDigit == randomNumberSecondDigit ) || ( guessNumberFirstDigit == randomNumberSecondDigit ) ){
                System.out.println("Number randomly assigned by the system : "+randomNumber+"  Your guess number : "+guessNumber);
                System.out.println("Congratulations, you have guessed one digit of the number correctly. You have WINNED the third prize, that is, 100 TL. You can get your prize at the box office.");
            }
            else {
                System.out.println("Number randomly assigned by the system : "+randomNumber+"  Your guess number : "+guessNumber);
                System.out.println("Unfortunately, you did not win anything. But your luck is not completely over, you can buy tickets from the box office and try again.");
                System.out.println("Süleyman Çakır : Kumarda sadece oynatan kazanır!!!!!");
            }
        }
        else {
            System.out.println("Since you did not enter a two-digit number, refresh the system and enter a number again.");
        }

    }
}