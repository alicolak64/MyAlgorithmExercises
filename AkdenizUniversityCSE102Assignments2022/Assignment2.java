import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ali Çolak
 *         6.04.2022
 */

public class Assignment2 {

    // 2022 Spring Akdeniz Üniversitesi CSE102 Assignmnent 2

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
        b.addCustomer(1, "Customer", "1");
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
        System.out.println(b);

    }

}

class Bank {

    private String name;
    private String address;
    private ArrayList<Customer> customers;
    private ArrayList<Company> companies;
    private ArrayList<Account> accounts;

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
        this.customers = new ArrayList<>();
        this.companies = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public void addCustomer(int id, String name, String surname) {
        Customer customer = new Customer(id, name, surname);
        customers.add(customer);
    }

    public void addCompany(int id, String name) {
        Company company = new Company(id, name);
        companies.add(company);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Customer getCustomer(int id) {
        Customer customer = null;

        for (Customer tempCustomer : customers) {
            if (tempCustomer.getId() == id) {
                customer = tempCustomer;
            }
        }

        if (customer == null) {
            throw new CustomerNotFoundException(id);
        } else
            return customer;
    }

    public Customer getCustomer(String name, String surname) {

        Customer customer = null;

        for (Customer tempCustomer : customers) {
            if (tempCustomer.getName().equals(name) && tempCustomer.getSurname().equals(surname)) {
                customer = tempCustomer;
            }
        }

        if (customer == null) {
            throw new CustomerNotFoundException(name, surname);
        } else
            return customer;

    }

    public Company getCompany(int id) {
        Company company = null;

        for (Company tempCompany : companies) {
            if (tempCompany.getId() == id) {
                company = tempCompany;
            }
        }

        if (company == null) {
            throw new CompanyNotFoundException(id);
        } else
            return company;
    }

    public Company getCompany(String name) {
        Company company = null;

        for (Company tempCompany : companies) {
            if (tempCompany.getName().equals(name)) {
                company = tempCompany;
            }
        }

        if (company == null) {
            throw new CompanyNotFoundException(name);
        } else
            return company;
    }

    public Account getAccount(String accountNum) {
        Account account = null;

        for (Account tempAccount : accounts) {
            if (tempAccount.getAccountNumber().equals(accountNum)) {
                account = tempAccount;
            }
        }

        if (account == null) {
            throw new AccountNotFoundException(accountNum);
        } else
            return account;
    }

    public void transferFunds(String accountFrom, String accountTo, double amount) {
        Account account1 = null;
        Account account2 = null;
        boolean hasAccount1 = false;
        boolean hasAccount2 = false;
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountFrom)) {
                hasAccount1 = true;
                account1 = account;
            }
            if (account.getAccountNumber().equals(accountTo)) {
                hasAccount2 = true;
                account2 = account;
            }
        }
        if (hasAccount1 && hasAccount2) {

            if (account1.getBalance() >= amount) {

                account1.withdrawal(amount);
                account2.deposit(amount);

            } else
                throw new InvalidAmountException(amount);

        } else {
            if (!hasAccount1)
                throw new AccountNotFoundException(accountFrom);
            if (!hasAccount2)
                throw new AccountNotFoundException(accountTo);
        }
    }

    public void closeAccount(String accountNum) {

        boolean hasAccount = false;
        Account account1 = null;

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNum)) {
                hasAccount = true;
                account1 = account;
            }
        }

        if (hasAccount) {

            if (!(account1.getBalance() > 0)) {
                accounts.remove(account1);
            } else
                throw new BalanceRemainingException(account1.getBalance());

        } else
            throw new AccountNotFoundException(accountNum);

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.name).append("\t").append(this.address).append("\n");
        for (Company company : companies) {
            str.append("\t").append(company.getName()).append("\n");
            for (BusinessAccount businessAccount : company.getBusinessAccounts()) {
                str.append("\t\t").append(businessAccount.getAccountNumber()).append("\t")
                        .append(businessAccount.getInterestRate()).append("\t").append(businessAccount.getBalance())
                        .append("\n");
            }
        }
        for (Customer customer : customers) {
            str.append("\t").append(customer.getName()).append(" ").append(customer.getSurname()).append("\n");
            for (PersonalAccount personalAccount : customer.getPersonalAccounts()) {
                int x = customer.getPersonalAccounts().size();
                if (x > 1) {
                    str.append("\t\t").append(personalAccount.getAccountNumber()).append("\t")
                            .append(personalAccount.getBalance()).append("\n");
                } else
                    str.append("\t\t").append(personalAccount.getAccountNumber()).append("\t")
                            .append(personalAccount.getBalance());
                x--;
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
        this.balance = 0;
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;

        if (balance >= 0)
            this.balance = balance;
        else
            throw new InvalidBalanceException();

    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new InvalidAmountException(amount);
        } else {
            balance += amount;
        }

    }

    public void withdrawal(double amount) {
        if (amount < 0 || amount > this.balance) {
            throw new InvalidAmountException(amount);
        } else {
            balance -= amount;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account " + accountNumber + " has " + balance;
    }

}

class PersonalAccount extends Account {
    private String name;
    private String surname;
    private String PIN;

    public PersonalAccount(String accountNumber, String name, String surname) {
        super(accountNumber, 0);
        this.name = name;
        this.surname = surname;
        PIN = RandomString.getAlphaNumericString(4);

    }

    public PersonalAccount(String accountNumber, String name, String surname,
            double balance) {
        super(accountNumber, balance);
        this.name = name;
        this.surname = surname;
        PIN = RandomString.getAlphaNumericString(4);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPIN() {
        return PIN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(String PIN) {
        this.PIN = PIN;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public double getBalance() {
        return super.getBalance(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAccountNumber() {
        return super.getAccountNumber(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Account " + getAccountNumber() + " belonging to " + getName()
                + getSurname().toUpperCase() + " has " + getBalance();
    }

}

class BusinessAccount extends Account {

    private double interestRate;

    public BusinessAccount(String accountNumber, double rate) {
        super(accountNumber, 0);
        if (rate > 0) {
            this.interestRate = rate;
        } else {
            throw new InvalidRateException();
        }

    }

    public BusinessAccount(String accountNumber, double balance, double rate) {
        super(accountNumber, balance);
        if (rate > 0) {
            this.interestRate = rate;
        } else {
            throw new InvalidRateException();

        }
    }

    @Override
    public String getAccountNumber() {
        return super.getAccountNumber(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getBalance() {
        return super.getBalance(); // To change body of generated methods, choose Tools | Templates.
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double calculateInterest() {
        return interestRate * getBalance();
    }
}

class Customer {
    private int id;
    private String Name;
    private String Surname;
    public ArrayList<PersonalAccount> personalAccounts;

    public Customer(int id, String Name, String Surname) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new InvalidIDException();
        }

        this.Name = Name;
        this.Surname = Surname;
        personalAccounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public ArrayList<PersonalAccount> getPersonalAccounts() {
        return personalAccounts;
    }

    public void openAccount(String acctNum) {
        PersonalAccount personalAccount = new PersonalAccount(acctNum, Name, Surname, 0);
        personalAccounts.add(personalAccount);
    }

    public PersonalAccount getAccount(String acctNum) {

        boolean hasAccount = false;
        PersonalAccount personalAccount = null;

        for (PersonalAccount account : personalAccounts) {
            if (account.getAccountNumber().equals(acctNum)) {
                hasAccount = true;
                personalAccount = account;
            }
        }
        if (hasAccount) {
            return personalAccount;
        } else
            throw new AccountNotFoundException(acctNum);

    }

    public void closeAccount(String acctNum) {
        boolean hasAccount = false;
        PersonalAccount personalAccount = null;

        for (PersonalAccount account : personalAccounts) {
            if (account.getAccountNumber().equals(acctNum)) {
                hasAccount = true;
                personalAccount = account;
            }
        }
        if (hasAccount) {
            if (!(personalAccount.getBalance() >= 0)) {
                personalAccounts.remove(personalAccount);
            } else
                throw new BalanceRemainingException(personalAccount.getBalance());
        } else
            throw new AccountNotFoundException(acctNum);
    }

    @Override
    public String toString() {
        return this.Name + " " + this.Surname.toUpperCase();
    }
}

class Company {
    private int id;
    private String Name;
    private ArrayList<BusinessAccount> businessAccounts;

    public Company(int id, String Name) {
        this.id = id;
        this.Name = Name;
        this.businessAccounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<BusinessAccount> getBusinessAccounts() {
        return businessAccounts;
    }

    public void openAccount(String acctNum, double rate) {
        BusinessAccount businessAccount = new BusinessAccount(acctNum, 0, rate);
        businessAccounts.add(businessAccount);
    }

    public BusinessAccount getAccount(String acctNum) {
        boolean hasAccount = false;
        BusinessAccount businessAccount = null;

        for (BusinessAccount account : businessAccounts) {
            if (account.getAccountNumber().equals(acctNum)) {
                hasAccount = true;
                businessAccount = account;
            }
        }
        if (hasAccount) {
            return businessAccount;
        } else
            throw new AccountNotFoundException(acctNum);
    }

    public void closeAccount(String acctNum) {
        boolean hasAccount = false;
        BusinessAccount businessAccount = null;

        for (BusinessAccount account : businessAccounts) {
            if (account.getAccountNumber().equals(acctNum)) {
                hasAccount = true;
                businessAccount = account;
            }
        }
        if (hasAccount) {
            if (!(businessAccount.getBalance() >= 0)) {
                businessAccounts.remove(businessAccount);
            } else
                throw new BalanceRemainingException(businessAccount.getBalance());
        } else
            throw new AccountNotFoundException(acctNum);
    }

    @Override
    public String toString() {
        return this.Name;
    }

}

class AccountNotFoundException extends RuntimeException {

    private String acctNum;

    public AccountNotFoundException(String acctNum) {
        this.acctNum = acctNum;
    }

    @Override
    public String toString() {
        return "AccountNotFoundException:" + this.acctNum;
    }

}

class BalanceRemainingException extends RuntimeException {

    private double balance;

    public BalanceRemainingException(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BalanceRemainingException:" + this.balance;
    }

}

class CompanyNotFoundException extends RuntimeException {
    private int id;
    private String name;

    public CompanyNotFoundException(int id) {
        this.id = id;
        this.name = null;

    }

    public CompanyNotFoundException(String name) {
        this.name = name;
        this.id = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if (name == null) {
            return "CompanyNotFoundException: id - " + this.id;
        } else {
            return "CompanyNotFoundException: name - " + this.name;
        }
    }

}

class CustomerNotFoundException extends RuntimeException {

    private int id;
    private String name;
    private String surname;

    public CustomerNotFoundException(String name, String surname) {
        this.id = 5;
        this.name = name;
        this.surname = surname;
    }

    public CustomerNotFoundException(int id) {
        this.id = id;
        this.name = null;
        this.surname = null;
    }

    @Override
    public String toString() {
        if (name == null && surname == null) {
            return "CustomerNotFoundException: id - " + id;
        } else {
            return "CustomerNotFoundException: name - " + name + " " + surname;
        }

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

class InvalidIDException extends RuntimeException {
    @Override
    public String toString() {
        return "ID Cannot be zero or negatif";
    }

}

class InvalidBalanceException extends RuntimeException {
    @Override
    public String toString() {
        return "Balance cannot be negatif value!";
    }
}

class InvalidRateException extends RuntimeException {
    @Override
    public String toString() {
        return "IntrestRate Must Be Positive!!!";
    }

}

class RandomString {

    public static String getAlphaNumericString(int n) {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString = new String(array, StandardCharsets.UTF_8);

        // Create a StringBuffer to store the result
        StringBuilder r = new StringBuilder();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if ((

            (ch >= '0' && ch <= '9'))
                    && (n > 0)) {

                r.append(ch);
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }
}