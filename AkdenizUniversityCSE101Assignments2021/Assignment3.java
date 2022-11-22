import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Ali Çolak
 */

public class Assignment3 {

    // 2021 Fall Akdeniz Üniversitesi CSE101 Assignmnent 3

    public static void main(String[] args) {

        int [] acctNums;
        String[] acctNames;
        String[] acctSurnames;
        double [] acctBalances;
        int numOfAccounts;

        String fileName = args[0] + "_AccountInfo.txt";
        String fileName2 = args[0] + "_AccountInfoOut.txt";

        numOfAccounts = countAccounts(fileName);

        acctNums = new int[numOfAccounts];
        acctNames = new String[numOfAccounts];
        acctSurnames = new String[numOfAccounts];
        acctBalances = new double[numOfAccounts];
    
        readAccountInfo(acctNums,acctNames,acctSurnames,acctBalances,fileName);
  
        System.out.println("Num of accounts : "+numOfAccounts);

        System.out.println("The information");

        for (int i = 0; i < acctNums.length ; i++ ) {
            System.out.println(acctNums[i]+"  "+acctNames[i]+"  "+acctSurnames[i]+"  "+acctBalances[i]);
        }
    
        System.out.println("Success of deposit : "+deposit(acctBalances,0,100));
  
        System.out.println("First Account new balance : "+acctBalances[0]);
    
        System.out.println("Success of withdraw : "+withdrawal(acctBalances,1,100));
  
        System.out.println("Second Account new balance : "+acctBalances[1]);
      
        System.out.println("Result of transfer : "+transfer(acctNums,acctBalances,12345,67890,150));
  
        System.out.println("First Account new balance : "+ acctBalances[0]);
        System.out.println("Second Account new balance : "+acctBalances[1]);
      
        System.out.println("Result of transfer : "+transfer(acctNums,acctBalances,98765,67890,150));
  
        System.out.println("First Account new balance : "+ acctBalances[5]);
        System.out.println("Second Account new balance : "+acctBalances[1]);
    
        writeAccountInfo(acctNums,acctNames,acctSurnames,acctBalances,fileName2);

    }

    public static int countAccounts (String filename) {

        File file = new File(filename);
        int countAccount = 0;

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                countAccount++;
                reader.nextLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countAccount;
    }

            
    public static void readAccountInfo(int [] acctNums, String [] names, String [] surnames, double [] balances, String filename) {

        File file   = new File(filename);
        int count=0;

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] array = line.split(" ");
                acctNums[count] = Integer.parseInt(array[0]);
                names[count] = array[1];
                surnames[count] = array[2];
                balances[count] = Double.parseDouble(array[3]);
                count++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public static boolean deposit (double  []  balances,int index,double amount) {
        if (balances.length >index && index>=0 && isDepositValid(amount)) {
            balances[index]+=amount;
            return true;
        }
        return false;
    }
  
    public static boolean withdrawal (double  [] balances,int index,double amount) {
        if ( balances.length> index && index>=0 && isWithdrawalValid(balances[index],amount) ) {
            balances[index]-=amount;
            return true;
        }
        return false;
    }
  
    public static int transfer(int [] acctNums,double [] balances, int acctNumFrom, int acctNumTo,double amount) {
 
        int index1 = findAcct(acctNums, acctNumFrom);
        int index2 = findAcct(acctNums,acctNumTo);
  
        if (index1==-1)
            return  1 ;
        if (index2==-1)
            return 2;
 
        if (!isWithdrawalValid(balances[index1],amount))
            return 3;
  
        balances[index1] -= amount;
        balances[index2]+=amount;
        return 0;
    }

            
    public static void writeAccountInfo(int [] acctNums, String [] names, String [] surnames, double [] balances, String filenameOut) {

        int baseFileNameIndex = filenameOut.lastIndexOf('_');
 
        String baseFileName = filenameOut.substring(0,baseFileNameIndex);
  
        String filename = baseFileName+"_TransferInfo.txt";

        File file = new File(filename);
        int numOfTransfer = countAccounts(filename);
        String [] transferCode = new String[numOfTransfer];
        int[] transferAcctNumsFrom = new int[numOfTransfer];
        int []transferAcctNumsTo = new int[numOfTransfer];
        double []   transferAmount = new double[numOfTransfer];
        int count=0;

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] array = line.split(" ");
                transferCode[count] = array[0];
                transferAcctNumsFrom[count] = Integer.parseInt(array[1]);
                transferAcctNumsTo[count] = Integer.parseInt(array[2]);
                transferAmount[count] = Double.parseDouble(array[3]);
                count++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { 
            PrintWriter writer = new PrintWriter(new FileWriter(filenameOut,true));
            for (int i = 0; i < acctNums . length; i++)   {
                writer.write(acctNums[i]+" "+names[i]+" "+surnames[i]+" "+balances[i]+"\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {   
            PrintWriter writer = new PrintWriter(new FileWriter(baseFileName+"_log.txt",true));
            for (int i = 0; i < numOfTransfer ; i++) {  
                        
                int code  =  transfer(acctNums,balances,transferAcctNumsFrom[i],transferAcctNumsTo[i],transferAmount[i]);
                if (code==0) {      
                             
                    writer.write( "Transfer "+transferCode[i]+" resulted in code "+code+": STX - Transfer Successful\n");
                } else if (code==1) {      
                             
                    writer.write( "Transfer "+transferCode[i]+" resulted in code "+code+": TNF - Insufficient Funds\n");
                } else if (code==2) {      
                             
                    writer.write( "Transfer "+transferCode[i]+" resulted in code "+code+": FNF - To Account Not Found\n");
                } else if (code==3) {      
                             
                    writer.write("Transfer "+transferCode[i]+" resulted in code "+code+": NSF - From Account Not Found\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int findAcct (int [] numbers, int number) {

        int x = -1;
        for (int i = 0; i  <  numbers.length; i++) {
            if (numbers[i] == number) {
                x=i;
            }
        }
        return x;

    }
 
    public static boolean isDepositValid(double amount){
        return amount>0;
    }

    public static boolean isWithdrawalValid(double balance,double amount){
        return amount>0 && amount<=balance;
    }



}