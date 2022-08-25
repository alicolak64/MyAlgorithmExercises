import java.util.ArrayList;

/**
 * @author Ali Çolak
 * 10.03.2021
 */
public class VeterinarySimulation {
    public static void main(String[] args) {

        Cat cat1 = new Cat("09.05.2013", true, "Van Cat", 5);
        Cat cat2 = new Cat("12.10.2016", false, "Tabby", 2);
        Cat cat3 = new Cat("05.07.2018", true, "British", 1);
        Dog dog1 = new Dog("05.06.2012", true, "Golden", 5);
        Dog dog2 = new Dog("04.02.2014", true, "Rottweiler", 3);
        Dog dog3 = new Dog("05.06.2012", true, "Pitbull", 1);

        Customer customer1 = new Customer("Ali Çolak", "16472557478", "London");
        Customer customer2 = new Customer("Yigit Helvaci", "22968316713", "London");
        Customer customer3 = new Customer("Kerem  Guzel", "13245678925", "London");

        Veterinary veterinary1 = new Veterinary("Nesibe Guzel", "15842578945", "Akdeniz University", 12);
        Veterinary veterinary2 = new Veterinary("Elif Kilic ", "45678912312", "Harran University", 6);

        customer1.addAnimal(cat1);
        customer2.addAnimal(cat2);
        customer3.addAnimal(cat3);
        customer1.addAnimal(dog1);
        customer2.addAnimal(dog2);
        customer3.addAnimal(dog3);
        veterinary1.addCustomer(customer1);
        veterinary2.addCustomer(customer2);
        veterinary1.addCustomer(customer3);
        customer1.showAnimals();
        customer1.getNumberOfAnimals();
        customer2.showAnimals();
        customer2.getNumberOfAnimals();
        customer3.showAnimals();
        customer3.getNumberOfAnimals();
        veterinary1.showCustomers();
        veterinary1.getNumberOfCustomer();
        veterinary2.showCustomers();
        veterinary2.getNumberOfCustomer();

        CityVeterinary london = new CityVeterinary("London");
        london.addVeterinaries(veterinary1);
        london.addVeterinaries(veterinary2);
        london.showVeterinaries();
        london.getNumberOfVeterinaries();
        System.out.println("*************************************************");


        ManagementPanel<Animal> managementPanelAnimal = new ManagementPanel<>();

        managementPanelAnimal.getInformation(cat1);
        managementPanelAnimal.getInformation(cat2);
        managementPanelAnimal.getInformation(cat3);
        managementPanelAnimal.getInformation(dog1);
        managementPanelAnimal.getInformation(dog2);
        managementPanelAnimal.getInformation(dog3);


        ManagementPanel<Customer> managementPanelCustomer = new ManagementPanel<>();

        managementPanelCustomer.getInformation(customer1);
        managementPanelCustomer.getInformation(customer2);
        managementPanelCustomer.getInformation(customer3);


        ManagementPanel<Veterinary> managementPanelVeterinary = new ManagementPanel<>();

        managementPanelVeterinary.getInformation(veterinary1);
        managementPanelVeterinary.getInformation(veterinary2);


        managementPanelCustomer.showAnimals(customer1);
        managementPanelCustomer.showAnimals(customer2);
        managementPanelCustomer.showAnimals(customer3);

        managementPanelVeterinary.showCustomers(veterinary1);
        managementPanelVeterinary.showCustomers(veterinary2);

        managementPanelAnimal.getNumberOfAnimals(customer1);
        managementPanelAnimal.getNumberOfAnimals(customer2);
        managementPanelAnimal.getNumberOfAnimals(customer3);
        managementPanelAnimal.getNumberOfCustomer(veterinary1);
        managementPanelAnimal.getNumberOfCustomer(veterinary2);
    }
}

abstract class Animal {
    private String birthDate;
    private boolean isRecord;

    public Animal(String birthDate, boolean isRecord) {
        this.birthDate = birthDate;
        this.isRecord = isRecord;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isRecord() {
        return isRecord;
    }

    public void setRecord(boolean isRecord) {
        this.isRecord = isRecord;
    }

    abstract void getInformation();

    @Override
    public String toString() {
        return "Animal{" +
                "birthDate='" + birthDate + '\'' +
                ", isRecord=" + isRecord +
                '}';
    }
}

class Cat extends Animal {

    private int numberOfVaccines;
    private String type;
    private String breed;

    public Cat(String birthDate, boolean isRecord, String breed, int numberOfVaccines) {
        super(birthDate, isRecord);
        this.type = "Cat";
        this.breed = breed;
        this.numberOfVaccines = numberOfVaccines;
    }

    public int getNumberOfVaccines() {
        return numberOfVaccines;
    }

    public void setNumberOfVaccines(int numberOfVaccines) {
        this.numberOfVaccines = numberOfVaccines;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    void getInformation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Type:" + type + ", Breed:" + breed + "," + " Is record : " + isRecord() + ", Birth Data : " + getBirthDate() + ", Number of Vaccines: " + numberOfVaccines;
    }
}


class Dog extends Animal {

    private int numberOfVaccines;
    private String type;
    private String breed;

    public Dog(String birthDate, boolean isRecord, String breed, int numberOfVaccines) {
        super(birthDate, isRecord);
        this.type = "Dog";
        this.breed = breed;
        this.numberOfVaccines = numberOfVaccines;
    }

    public int getNumberOfVaccines() {
        return numberOfVaccines;
    }

    public void setNumberOfVaccines(int numberOfVaccines) {
        this.numberOfVaccines = numberOfVaccines;
    }

    @Override
    void getInformation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Type:" + type + ", Breed:" + breed + "," + " Is record : " + isRecord() + ", Birth Data : " + getBirthDate() + ", Number of Vaccines: " + numberOfVaccines;
    }
}

abstract class Person {
    private String name;
    private String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    abstract void getInformation();

    @Override
    public String toString() {
        return "Name: " + name + ", Id: " + id;
    }
}

class Customer extends Person {

    private String address;
    private String type;
    private ArrayList<Animal> animals;

    public Customer(String name, String id, String address) {
        super(name, id);
        this.address = address;
        this.type = "Customer";
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void showAnimals() {
        System.out.println(getName() + "'s animals : ");
        for (int i = 0; i < animals.size(); i++) {
            System.out.print((i + 1) + "->");
            animals.get(i).getInformation();
        }
    }

    public void getNumberOfAnimals() {
        System.out.println(getName() + "'s number of animals : " + animals.size());
    }

    public String getAddress() {
        return address;
    }

    public void getAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    void getInformation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Customer -> " + super.toString() + " Address: " + address;
    }
}

class Veterinary extends Person {

    private String graduatedSchool;
    private int workingTime;
    private String type;
    private ArrayList<Customer> customers;
    private int numberOfCustomer;


    public Veterinary(String name, String id, String graduatedSchool, int workingTime) {
        super(name, id);
        this.graduatedSchool = graduatedSchool;
        this.workingTime = workingTime;
        this.type = "Veterinary";
        customers = new ArrayList<>();
        numberOfCustomer = 0;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        numberOfCustomer++;
    }

    public void showCustomers() {
        System.out.println(getName() + "'s customers : ");
        for (int i = 0; i < customers.size(); i++) {
            System.out.print((i + 1) + "->");
            customers.get(i).getInformation();
        }
    }

    public void getNumberOfCustomer() {
        System.out.println(getName() + "'s number of customer : " + customers.size());
    }


    public void setNumberOfCustomer(int numberOfCustomer) {
        this.numberOfCustomer = numberOfCustomer;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    void getInformation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Customer -> " + super.toString() + ", Graduated School : " + graduatedSchool +
                ",Working time : " + workingTime + ", Number of Customer : " + numberOfCustomer;
    }
}

class CityVeterinary {

    private String city;
    private ArrayList<Veterinary> veterinaries;
    private int numberOfVeterinaries;

    public CityVeterinary(String city) {
        this.city = city;
        veterinaries = new ArrayList<>();
        numberOfVeterinaries = 0;
    }

    public void addVeterinaries(Veterinary veterinary) {
        veterinaries.add(veterinary);
        numberOfVeterinaries++;
    }

    public void showVeterinaries() {

        System.out.println(getCity() + "  veterinaries");

        for (int i = 0; i < veterinaries.size(); i++) {
            System.out.print((i + 1) + "->");
            veterinaries.get(i).getInformation();
        }

    }

    public void getNumberOfVeterinaries() {
        System.out.println(getCity() + " city number of veterinaries : " + numberOfVeterinaries);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

class ManagementPanel<T> {

    public void getInformation(T type) {
        System.out.println(type);
    }

    public <T extends Customer> void showAnimals(T customer) {
        customer.showAnimals();
    }

    public <T extends Veterinary> void showCustomers(T veterinary) {
        veterinary.showCustomers();
    }

    public <T extends Customer> void getNumberOfAnimals(T customer) {
        customer.getNumberOfAnimals();
    }

    public <T extends Veterinary> void getNumberOfCustomer(T veterinary) {
        veterinary.getNumberOfCustomer();
    }

}