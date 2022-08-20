import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

/**
 * @author Ali Çolak
 * 21.05.2022
 */
public class Assignment3 {

    // 2022 Spring Akdeniz Üniversitesi CSE102 Assignmnent 3

    public static void main(String[] args) {

        Bank b = new Bank("My Bank", "My Bank's Address");
        b.addCompany(1, "Company 1");
        b.getCompany(1).openAccount("1234", 0.05);
        b.addAccount(b.getCompany(1).getAccount("1234"));
        b.getAccount("1234").deposit(500000);
        b.getCompany(1).getAccount("1234").deposit(500000);
        b.getCompany(1).openAccount("1235", 0.03);
        b.addAccount(b.getCompany(1).getAccount("1235"));
        b.getCompany(1).getAccount("1235").deposit(25000);
        b.addCompany(2, "Company 2");
        b.getCompany(2).openAccount("2345", 0.03);
        b.addAccount(b.getCompany(2).getAccount("2345"));
        b.getCompany(2).getAccount("2345").deposit(350);
        b.addCustomer(1, "Customer" ,"1");
        b.addCustomer(2, "Customer", "2");
        Customer c = b.getCustomer(1);
        c.openAccount("3456");
        c.openAccount("3457");
        c.getAccount("3456").deposit(150);
        c.getAccount("3457").deposit(250);
        c = b.getCustomer(2);
        c.openAccount("4567");
        c.getAccount("4567").deposit(1000);
        b.addAccount(c.getAccount("4567"));
        c = b.getCustomer(1);
        b.addAccount(c.getAccount("3456"));
        b.addAccount(c.getAccount("3457"));


        Collection collections = new ArrayList();
        collections.add(new Transaction(3,"3456",500));
        collections.add(new Transaction(1,"4567",500));
        collections.add(new Transaction(2,"1235","3456",100));
        collections.add(new Transaction(3,"3456",500));
        collections.add(new Transaction(1,"4567",500));
        collections.add(new Transaction(2,"4567","3456",100));
        collections.add(new Transaction(3,"1235",500));
        collections.add(new Transaction(1,"3456",500));
        collections.add(new Transaction(2,"4567","3456",100));
        collections.add(new Transaction(3,"1235",500));
        collections.add(new Transaction(1,"4567",500));
        collections.add(new Transaction(2,"4567","1235",100));
        collections.add(new Transaction(3,"3456",500));
        collections.add(new Transaction(1,"4567",500));
        collections.add(new Transaction(2,"1235","3456",100));
        b.processTransactions(collections,"outFile");
        System.out.println(b);

    }

}

class Transaction {

    private int type;
    private String to;
    private String from;
    private double amount;

    // 1-> deposit
    // 2-> transfer
    // 3-> withdrawal

    public Transaction(int type, String to, String from, double amount) {

        if (type == 1 || type == 2 || type == 3) {
            this.type = type;
            this.to = to;
            this.from = from;
            this.amount = amount;
        }

        else
            throw new InvalidParameterException("Invalid transaction type.");
    }

    public Transaction(int type, String account, double amount) {

        if (type == 1) {
            this.type = type;
            this.to = account;
            this.from = null;
            this.amount = amount;
        }

        else if (type == 3) {
            this.type = type;
            this.from = account;
            this.to = null;
            this.amount = amount;
        } else
            throw new InvalidParameterException("Invalid transaction type.");
    }

    public int getType() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public double getAmount() {
        return amount;
    }

}

class Bank {

    private String name;
    private String address;

    ArrayList<Customer> customers;
    ArrayList<Company> companies;
    ArrayList<Account> accounts;

    Customer c;
    Company comp;
    Account a;

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;

        customers = new ArrayList<>();
        companies = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addCustomer(int id, String name, String surname) {
        Customer c = new Customer(id, name, surname);
        customers.add(c);

    }

    public void addCompany(int id, String name) {
        Company comp = new Company(id, name);
        companies.add(comp);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Customer getCustomer(int id) {
        c = null;

        for (Customer customer : customers) {
            if (customer.getId() == id)
                c = customer;
        }
        if (c == null)
            throw new CustomerNotFoundException(id);
        else
            return c;
    }

    public Customer getCustomer(String name, String surname) {
        c = null;

        for (Customer customer : customers) {
            if (customer.getName().equals(name) && customer.getSurname().equals(surname))
                c = customer;
        }
        if (c == null)
            throw new CustomerNotFoundException(name, surname);
        else
            return c;
    }

    public Company getCompany(int id) {
        comp = null;

        for (Company company : companies) {
            if (company.getId() == id)
                comp = company;
        }
        if (comp == null)
            throw new CompanyNotFoundException(id);
        else
            return comp;
    }

    public Company getCompany(String name) {
        comp = null;

        for (Company company : companies) {
            if (company.getName().equals(name))
                comp = company;
        }
        if (comp == null)
            throw new CompanyNotFoundException(name);
        else
            return comp;
    }

    public Account getAccount(String accountNum) {
        a = null;

        for (Account account : accounts) {
            if (account.getAcctNum().equals(accountNum))
                a = account;
        }
        if (a == null)
            throw new AccountNotFoundException(accountNum);
        else
            return a;
    }

    public void transferFunds(String accountFrom, String accountTo, double amount) {
        getAccount(accountFrom);
        getAccount(accountTo);

        if (amount <= 0)
            throw new InvalidAmountException(amount);

        if (getAccount(accountFrom).getBalance() < amount)
            throw new InvalidAmountException(amount);

        else {
            getAccount(accountFrom).withdrawal(amount);
            getAccount(accountTo).deposit(amount);
        }

    }

    public void closeAccount(String accountNum) {
        for (Account account : accounts) {
            if (account.getAcctNum().equals(accountNum))
                a = account;
        }

        if (a != null) {

            if (a.getBalance() == 0)
                accounts.remove(a);
            else
                throw new BalanceRemainingException(a.getBalance());
        } else
            throw new AccountNotFoundException(accountNum);
    }

    public void processTransactions(Collection<Transaction> transactions , String outFile) {

        ArrayList<Transaction> list = new ArrayList<>();

        for (Transaction transaction : transactions) {
            list.add(transaction);
        }

        list.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction transaction1, Transaction transaction2) {
                if (transaction1.getType() > transaction2.getType()) {
                    return 1;
                } else if (transaction1.getType() < transaction2.getType()) {
                    return -1;
                } else {
                    if (transaction1.getType() == 3) {
                        if ( Integer.parseInt(transaction1.getFrom()) > Integer.parseInt(transaction2.getFrom())) {
                            return 1;
                        } else return -1;
                    } else {
                        if (Integer.parseInt(transaction1.getTo()) > Integer.parseInt(transaction2.getTo())) {
                            return 1;
                        } else return -1;
                    }
                }
            }
        });

        

        for (Transaction transaction : list) {

            if (transaction.getType() == 1) {

                try {
                    getAccount(transaction.getTo()).deposit(transaction.getAmount());
                } catch (AccountNotFoundException accountNotFoundException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: AccountNotFoundException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (InvalidAmountException invalidAmountException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: InvalidAmountException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else if (transaction.getType() == 2) {

                try {
                    transferFunds(transaction.getFrom(),transaction.getTo(),transaction.getAmount());
                } catch (AccountNotFoundException accountNotFoundException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: AccountNotFoundException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (InvalidAmountException invalidAmountException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: InvalidAmountException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else if (transaction.getType() == 3){

                try {
                    getAccount(transaction.getFrom()).withdrawal(transaction.getAmount());
                } catch (AccountNotFoundException accountNotFoundException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: AccountNotFoundException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (InvalidAmountException invalidAmountException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: InvalidAmountException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }


    }

    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append(name).append("\t").append(address).append("\n");

        for (Company compCounter : companies) {
            str.append("\t").append(compCounter.toString()).append("\n");

            for (int i = 0; i < compCounter.businessAccounts.size(); i++) {
                str.append("\t\t").append(compCounter.businessAccounts.get(i).getAcctNum());
                str.append("\t").append(compCounter.businessAccounts.get(i).getRate());
                str.append("\t").append(compCounter.businessAccounts.get(i).getBalance()).append("\n");
            }
        }

        for (Customer customer : customers) {
            str.append("\t").append(customer.toString()).append("\n");

            for (int i = 0; i < customer.personalAccounts.size(); i++) {
                int counter = 1;
                if (counter == customer.personalAccounts.size()) {
                    str.append("\t\t").append(customer.personalAccounts.get(i).getAcctNum());
                    str.append("\t").append(customer.personalAccounts.get(i).getBalance());
                } else {
                    str.append("\t\t").append(customer.personalAccounts.get(i).getAcctNum());
                    str.append("\t").append(customer.personalAccounts.get(i).getBalance()).append("\n");
                }
                counter++;
            }
        }

        return str.toString();
    }

}

class Account {

    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        balance = 0;
    }

    public Account(String accountNumber, double balance) {
        this(accountNumber);
        this.balance = balance;

        if (balance < 0)
            throw new IllegalArgumentException("Balance must be non-negative!");
    }

    public String getAcctNum() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount >= 0)
            balance += amount;
        else
            throw new InvalidAmountException(amount);
    }

    public void withdrawal(double amount) {
        if (amount > 0 && amount <= balance)
            balance -= amount;
        else
            throw new InvalidAmountException(amount);

    }

    public String toString() {
        return "Account " + accountNumber + " has " + balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0)
            this.balance = balance;
    }

}

class PersonalAccount extends Account {

    private String name;
    private String surname;
    private String pin;

    public PersonalAccount(String accountNumber, double balance, String name, String surname) {
        super(accountNumber, balance);
        this.name = name;
        this.surname = surname;

        Random rnd = new Random();
        int tempPin = rnd.nextInt(9999);
        pin = String.valueOf(String.format("%04d", tempPin));

    }

    public PersonalAccount(String accountNumber, String name, String surname) {
        super(accountNumber);
        this.name = name;
        this.surname = surname;

        Random rnd = new Random();
        int tempPin = rnd.nextInt(9999);
        pin = String.valueOf(String.format("%04d", tempPin));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPIN() {
        return pin;
    }

    public void setPIN(String pin) {
        this.pin = pin;
    }

    public String toString() {
        return "Account " + getAcctNum() + " belonging to " + name + " " + surname.toUpperCase() + " has "
                + getBalance();
    }

}

class BusinessAccount extends Account {

    private double interestRate;

    public BusinessAccount(String accountNumber, double balance, double rate) {
        super(accountNumber, balance);

        if (rate <= 0)
            throw new IllegalArgumentException("Rate must be positive!");
        else
            this.interestRate = rate;

    }

    public BusinessAccount(String accountNumber, double rate) {
        super(accountNumber);

        if (rate <= 0)
            throw new IllegalArgumentException("Rate must be positive!");
        else
            this.interestRate = rate;
    }

    public double getRate() {
        return interestRate;
    }

    public void setRate(double rate) {
        if (rate > 0)
            this.interestRate = rate;
    }

    public double calculateInterest() {

        return getBalance() * getRate();

    }

}

class Customer {

    private int id;
    private String name;
    private String surname;

    private PersonalAccount p;

    ArrayList<PersonalAccount> personalAccounts;

    public Customer(int id, String name, String surname) {
        if (id <= 0)
            throw new IllegalArgumentException("ID must be positive!");
        else {
            this.id = id;
            this.name = name;
            this.surname = surname;
            personalAccounts = new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0)
            this.id = id;
    }

    public void openAccount(String acctNum) {
        PersonalAccount pAccount = new PersonalAccount(acctNum, name, surname);
        personalAccounts.add(pAccount);
    }

    public PersonalAccount getAccount(String accountNum) {
        p = null;

        for (PersonalAccount personalAccount : personalAccounts) {
            if (personalAccount.getAcctNum().equals(accountNum))
                p = personalAccount;
        }
        if (p == null)
            throw new AccountNotFoundException(accountNum);
        else
            return p;

    }

    public void closeAccount(String accountNum) {
        p = null;

        for (PersonalAccount personalAccount : personalAccounts) {
            if (personalAccount.getAcctNum().equals(accountNum))
                p = personalAccount;
        }

        if (p != null) {
            if (p.getBalance() == 0)
                personalAccounts.remove(p);
            else
                throw new BalanceRemainingException(p.getBalance());
        } else
            throw new AccountNotFoundException(accountNum);

    }

    public String toString() {
        return name + " " + surname.toUpperCase();
    }

}

class Company {

    private int id;
    private String name;

    private BusinessAccount b;

    ArrayList<BusinessAccount> businessAccounts;

    public Company(int id, String name) {
        if (id <= 0)
            throw new IllegalArgumentException("ID must be positive!");
        else {
            this.id = id;
            this.name = name;
            businessAccounts = new ArrayList<BusinessAccount>();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0)
            this.id = id;
    }

    public void openAccount(String acctNum, double rate) {
        BusinessAccount bAccount = new BusinessAccount(acctNum, rate);
        businessAccounts.add(bAccount);
    }

    public BusinessAccount getAccount(String acctNum) {
        b = null;

        for (BusinessAccount businessAccount : businessAccounts) {
            if (businessAccount.getAcctNum().equals(acctNum))
                b = businessAccount;
        }
        if (b == null)
            throw new AccountNotFoundException(acctNum);
        else
            return b;
    }

    public void closeAccount(String accountNum) {
        b = null;

        for (BusinessAccount businessAccount : businessAccounts) {
            if (businessAccount.getAcctNum().equals(accountNum))
                b = businessAccount;
        }

        if (b != null) {
            if (b.getBalance() == 0)
                businessAccounts.remove(b);
            else
                throw new BalanceRemainingException(b.getBalance());
        } else
            throw new AccountNotFoundException(accountNum);

    }

    public String toString() {
        return name;
    }

}

class AccountNotFoundException extends RuntimeException {
    private String acctNum;

    public AccountNotFoundException(String acctNum) {
        this.acctNum = acctNum;
    }

    @Override
    public String toString() {
        return "AccountNotFoundException: " + acctNum;
    }
}

class BalanceRemainingException extends RuntimeException {
    private double balance;

    public BalanceRemainingException(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceRemainingException: " + balance;
    }

    public double getBalance() {
        return balance;
    }
}

class CompanyNotFoundException extends RuntimeException {
    private int id;
    private String name;

    public CompanyNotFoundException(int id) {
        this.id = id;
        name = null;
    }

    public CompanyNotFoundException(String name) {
        this.name = name;
        id = 0;
    }

    @Override
    public String toString() {
        if (name != null)
            return "CompanyNotFoundException: name - " + name;
        else
            return "CompanyNotFoundException: id - " + id;
    }

}

class CustomerNotFoundException extends RuntimeException {
    private int id;
    private String name;
    private String surname;

    public CustomerNotFoundException(int id) {
        this.id = id;
        name = null;
        surname = null;
    }

    public CustomerNotFoundException(String name, String surname) {
        this.name = name;
        this.surname = surname;
        id = 0;
    }

    @Override
    public String toString() {
        if (name != null && surname != null)
            return "CustomerNotFoundException: name - " + name + " " + surname;
        else
            return "CustomerNotFoundException: id - " + id;

    }

}

class InvalidAmountException extends RuntimeException {
    private double amount;

    public InvalidAmountException(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvalidAmountException: " + amount;
    }
}