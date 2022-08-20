import java.util.Random;

/**
 * @since 16.03.2022
 * @author Ali Colak
 */


public class Assignment1 {

    // 2022 Spring Akdeniz Üniversitesi CSE102 Assignmnent 1

    public static void main(String[] args) {


        Account account = new Account("1234",34);
        System.out.println(account);
        PersonalAccount personalAccount=new PersonalAccount("1234","Ali"
                ,"Colak");
        System.out.println(personalAccount);
        BusinessAccount businessAccount=new BusinessAccount("1234",34,2);
        System.out.println(businessAccount);
        Customer customer=new Customer("Joseph","Ledet");
        System.out.println(customer);
        Company company=new Company("Akdeniz University");
        System.out.println(company);

        /*

        Account a = new Account("1234",100);
        PersonalAccount pa = new PersonalAccount("9876","Joseph","Ledet");
        BusinessAccount ba = new BusinessAccount("5678",1000,0.09);
        Customer cu = new Customer("John","Smith");
        Company co = new Company("Akdeniz Universitesi");

        a.withdrawal(50);
        System.out.println(a);
        pa.deposit(150);
        System.out.println(pa);
        System.out.println("PIN is " + pa.getPIN() );
        ba.deposit(ba.calculateInterest());
        System.out.println(ba);
        ba.withdrawal(100);
        System.out.println(ba);

        System.out.println(cu);
        cu.openAccount("3456");
        cu.getAccount().deposit(123);
        System.out.println(cu.getAccount());

        System.out.println(co);
        co.openAccount("6543",0.05);
        co.getAccount().deposit(321);
        System.out.println(co.getAccount());

         */



    }

}
class Account{
    private String accountNumber;
    private double balance;

    public Account(String accountNumber,double balance){
        this.accountNumber=accountNumber;
        if(balance>=0){
            this.balance=balance;
        }
    }

    public String getAcctNum(){
        return this.accountNumber;
    }

    public double getBalance(){
        return this.balance;
    }
    public void deposit(double depositAmount){
        if(depositAmount>0){
            balance += depositAmount;
        }
    }
    public void withdrawal(double withdrawalAmount){
        if(withdrawalAmount<=balance){
            balance-=withdrawalAmount;
        }
    }

    public String toString(){
        return "Account" +" "+ this.accountNumber +" "+ "has " + this.balance;
    }
}
class PersonalAccount extends Account {
    private String name;
    private String surname;
    private String PIN;

    public PersonalAccount(String accountNumber,String name,
                           String surname){
        super(accountNumber,0);
        this.name=name;
        this.surname=surname;
        this.PIN = assignPIN();
    }

    private String assignPIN () {
        String PIN = "";
        Random random = new Random();
        int tempPin = random.nextInt(10000);
        if (tempPin ==0) {
            PIN = "0000";
        } else if (tempPin<10) {
            PIN = "000" + tempPin;
        } else if (tempPin<100) {
            PIN = "00" + tempPin;
        } else if (tempPin<1000) {
            PIN = "0" + tempPin;
        } else PIN = String.valueOf(tempPin);

        return PIN;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSurname(){
        return this.surname;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getPIN(){
        return this.PIN;
    }
    public void setPIN(String PIN){
        if(PIN.length()==4){
            this.PIN=PIN;
        }
    }
    public String toString(){
        return "Account" + " " +super.getAcctNum()+" "+"belonging to"+" "
                +this.name + " " + this.surname.toUpperCase()+" " + "has " + super.getBalance();
    }



}
class BusinessAccount extends Account {

    private double interestRate;

    public BusinessAccount(String accountNumber, double interestRate) {
        super(accountNumber,0);
        this.interestRate = interestRate;
    }

    public BusinessAccount(String accountNumber, double balance,
                           double interestRate){
        super(accountNumber,balance);
        this.interestRate=interestRate;

    }
    public double getRate(){
        return this.interestRate;
    }
    public void setRate(double interestRate){
        this.interestRate=interestRate;
    }
    public double calculateInterest(){
        return getBalance()*interestRate;
    }

}
class Customer{
    private String name;
    private String surname;
    private PersonalAccount personalAccount;

    public Customer(String name,String surname){
        this.name=name;
        this.surname=surname;
        personalAccount = null;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public void openAccount(String acctNum){
        personalAccount = new PersonalAccount(acctNum,name,surname);
    }
    public PersonalAccount getAccount(){
        return personalAccount;
    }
    public String toString(){
        return this.name + " " + this.surname.toUpperCase();
    }

}
class Company{
    private String name;
    private BusinessAccount businessAccount;

    public Company(String name){
        this.name=name;
        this.businessAccount = null;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public void openAccount(String acctNum, double rate){
        businessAccount = new BusinessAccount(acctNum,0,rate);
    }
    public BusinessAccount getAccount(){
        return businessAccount;
    }
    public String toString(){
        return this.name;
    }
}
