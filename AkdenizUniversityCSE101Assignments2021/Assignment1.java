import java.util.Scanner;

/**
 * @author Ali Çolak
 */

public class Assignment1 {

    // 2021 Fall Akdeniz Üniversitesi CSE101 Assignmnent 1

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your first name >> ");
        String firstName = input.nextLine();

        System.out.print("Please enter your surname >> ");
        String surName = input.nextLine().toUpperCase();

        double balance;

        do {
            System.out.print("Please enter your starting balance: ");
            balance = input.nextDouble();

            if (balance < 0)
                System.out.println("ERROR: Invalid balance");

        } while (balance < 0);

        int choice;

        do {
            System.out.println("\nHello " + firstName + " " + surName);
            System.out.println("What would you like to do today?");
            System.out.println("1 for Account Balance");
            System.out.println("2 for Cash Deposit");
            System.out.println("3 for Cash Withdrawal");
            System.out.println("0 to quit");

            System.out.print("Please enter your selection >> ");
            choice = input.nextInt();

            if (choice == 1) {

                System.out.println("Your account balance is: " + balance);

            } else if (choice == 2) {

                System.out.print("How much are you depositing? ");
                double deposit = input.nextDouble();

                if (isDepositValid(deposit))
                    balance += deposit;
                else
                    System.out.println("ERROR: Invalid deposit amount.");

            } else if (choice == 3) {

                System.out.print("How much are you withdrawing? ");
                double withdraw = input.nextDouble();

                if (isWithdrawalValid(balance, withdraw)) {
                    balance -= withdraw;
                    System.out.print(moneyGiven(withdraw));
                } else
                    System.out.println("ERROR: Invalid withdrawal amount.");

            } else if (choice == 0)
                System.out.println("Thank you for using our ATM. " +
                        "Have a nice day!");
            else
                System.out.println("Invalid choice. Exiting System.");
        } while (isChoiceValid(choice));

    }

    public static boolean isChoiceValid(int choice) {
        return choice == 1 || choice == 2 || choice == 3;
    }

    public static boolean isDepositValid(double amount) {
        return amount > 0;
    }

    public static boolean isWithdrawalValid(double balance, double amount) {
        return amount > 0 && balance >= amount;
    }

    public static String moneyGiven(double amount) {
        String str = "";
        if (amount >= 200) {
            str += (int) (amount / 200) + " - 200\n";
            amount %= 200;
        }
        if (amount >= 100) {
            str += (int) (amount / 100) + " - 100\n";
            amount %= 100;
        }
        if (amount >= 50) {
            str += (int) (amount / 50) + " - 50\n";
            amount %= 50;
        }
        if (amount >= 20) {
            str += (int) (amount / 20) + " - 20\n";
            amount %= 20;
        }
        if (amount >= 10) {
            str += (int) (amount / 10) + " - 10\n";
            amount %= 10;
        }
        if (amount >= 5) {
            str += (int) (amount / 5) + " - 5\n";
            amount %= 5;
        }
        if (amount >= 1) {
            str += (int) (amount) + " - 1\n";
            amount %= 1;
        }
        if (amount >= 0.50) {
            str += (int) (amount / 0.50) + " - 0.50\n";
            amount %= 0.5;
        }
        if (amount >= 0.25) {
            str += (int) (amount / 0.25) + " - 0.25\n";
            amount %= 0.25;
        }
        if (amount >= 0.1) {
            str += (int) (amount / 0.1) + " - 0.10\n";
            amount %= 0.1;
        }
        if (amount >= 0.05) {
            str += (int) (amount / 0.05) + " - 0.05\n";
            amount %= 0.05;
        }
        if (amount >= 0.01) {
            str += (int) (amount / 0.01) + " - 0.01\n";
        }
        return str;
    }

}
