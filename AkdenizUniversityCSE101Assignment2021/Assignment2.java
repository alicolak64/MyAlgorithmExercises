package Algorithms;

import java.util.Scanner;

/**
 * @author Ali Çolak
 * 8.02.2022
 */
public class Assignment2 {

    // 2021 Fall Akdeniz Üniversitesi CSE101 Assignmnent 2

    public static void bankLogin (int [] numbers, String [] names,
                                  String [] surnames,
                                  String [] PINs, double [] balances) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your account number >> ");
        int number = scanner.nextInt();


        scanner.nextLine();
        System.out.print("Please enter your PIN >> ");


        String PIN = scanner.nextLine();

        int index = findAcct(numbers,number);

        if (index!=(-1) && PINs[index].equals(PIN)) {
            atm(names,surnames,balances,index,scanner);
        } else {
            System.out.println("ERROR: Account/PIN combination not found.");
        }
    }

    public static int findAcct (int [] numbers, int number) {

        int x = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i]==number) {
                x=i;
            }
        }
        return x;

    }

    public static void atm (String [] names, String [] surnames,
                            double [] balances, int index,Scanner scanner) {

        String [] menu = {"Account Balance","Deposit"
                ,"Withdrawal", "Change Name"};

        int choice = 0 ;

        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].substring(0,1).toUpperCase()+names[i].
                    substring(1).toLowerCase();
        }

        for (int i = 0; i < surnames.length; i++) {
            surnames[i] = surnames[i].toUpperCase();
        }

        do {

            System.out.println("\n"+"Hello "+names[index]+" "+surnames[index]);
            System.out.println("What would you like to do today?");

            choice = menuDisplay(menu,scanner);

            if (choice==1) {

                System.out.println("Your account balance is: " +
                        balances[index]);

            }

            else if (choice==2) {

                System.out.print("How much are you depositing? ");
                double deposit = scanner.nextDouble();

                if(isDepositValid(deposit))
                    balances[index]+=deposit;
                else
                    System.out.println("ERROR: Invalid deposit amount.");

            }

            else if (choice==3){

                System.out.print("How much are you withdrawing? ");
                double withdraw = scanner.nextDouble();

                if (isWithdrawalValid(balances[index],withdraw)) {
                    balances[index]-=withdraw;
                    System.out.print(moneyGiven(withdraw));
                }
                else
                    System.out.println("ERROR: Invalid withdrawal amount.");

            } else if (choice==4) {

                System.out.print("Please enter your name >> ");

                scanner.nextLine();
                String name = scanner.nextLine();

                System.out.print("Please enter your surname >> ");
                String surname = scanner.nextLine();

                names[index] = name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();

                surnames[index] = surname.toUpperCase();

            }
            else if (choice==0)
                System.out.println("Thank you for using our ATM. " +
                        "Have a nice day!");
            else
                System.out.println("Invalid choice. Exiting System.");

        } while (choice==1 || choice==2 || choice==3 || choice==4);

    }


    public static int menuDisplay (String [] choices, Scanner scanner) {

        for (int i = 0; i < choices.length ; i++) {
            System.out.println((i+1)+" - "+choices[i]);
        }

        System.out.println("0 to quit");
        System.out.print("Please enter your selection >> ");
        int choice = scanner.nextInt();
        return choice;
    }


    public static boolean isDepositValid(double amount){
        boolean depositValid=amount>0;
        return depositValid;
    }

    public static boolean isWithdrawalValid(double balance,double amount){
        boolean withdrawalvalid=amount>0 && amount<=balance;
        return withdrawalvalid;
    }

    public static String moneyGiven(double amount) {

        int money = (int)(amount*100);
        String s = "";

        if (money >= 20000){
            s+=money/20000 + " - 200\n";
            money%=20000;
        }

        if (money >= 10000){
            s+=money/10000 + " - 100\n";
            money%=10000;
        }

        if (money >= 5000){
            s+=money/5000 + " - 50\n";
            money%=5000;
        }

        if (money >= 2000){
            s+=money/2000 + " - 20\n";
            money%=2000;
        }

        if (money >= 1000){
            s+=money/1000 + " - 10\n";
            money%=1000;
        }

        if (money >= 500){
            s+=money/500 + " - 5\n";
            money%=500;
        }

        if (money >= 100){
            s+=money/100 + " - 1\n";
            money%=100;
        }

        if (money >= 50){
            s+=money/50 + " - 0.50\n";
            money%=50;
        }

        if (money >= 25){
            s+=money/25 + " - 0.25\n";
            money%=25;
        }

        if (money >= 10){
            s+=money/10 + " - 0.10\n";
            money%=10;
        }

        if (money >= 5){
            s+=money/5 + " - 0.05\n";
            money%=5;
        }

        if (money >= 1){
            s+=money/1 + " - 0.01\n";
        }

        return s;
    }

}
