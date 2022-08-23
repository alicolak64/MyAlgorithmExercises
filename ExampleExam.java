import java.util.Scanner;

/**
 * @author Ali Çolak
 *         20.05.2021
 */
public class ExampleExam {

    // 2020 X University Computer Programming Department Final Exam Question

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int week;
        int weight;

        Patient david = new Patient("David", "Male", 286);
        // Define a patient name David, gender male, hospitalization (baseline) weight 286 kg
    
        Patient milla = new Patient("Milla", "Female", 276);
        // Define a patient name Milla, gender female, hospitalization (baseline) weight 276 kg

        System.out.println("Welcome to the obese patient follow-up program.");
        System.out.print("How many weeks of weight will you enter for David? ");

        week = scanner.nextInt();

        for (int i = 1; i <= week; i++) {
            System.out.println("How much did David weigh at the end of the " + i + "th" + " week?");
            weight = scanner.nextInt();
            david.enterWeight(i, weight);
        }

        System.out.print("\nHow many weeks of weight will you enter for Milla?");

        week = scanner.nextInt();

        for (int i = 1; i <= week; i++) {
            System.out.println("How much did Milla weigh at the end of the " + i + "th" + " week?");
            weight = scanner.nextInt();
            milla.enterWeight(i, weight);
        }

        System.out.print("\n");

        // 1 - Print Milla's information
        milla.getInformation();
        System.out.print("\n");

        // 2 - Print the weight Milla lost at the clinic
        System.out.println("Milla lost weight at the clinic : " + milla.getGivenWeight());

        // 3 - Print Milla's smallest weight reached
        System.out.println("The smallest weight Milla has reached at the clinic : " + milla.getMinimumWeight());

        // 4 - Print which week David lost the most weight
        System.out.println("The week David lost the most weight at the clinic: " + david.getLostMostWeightWeek());

        // 5 - Print David's information if David is weaker than Milla
        System.out.print("\n");
        if (david.isItWeaker(milla))
            david.getInformation();

    }

}

class Patient {

    final private String name;
    final private String gender;
    final private int[] weights;
    private int numberOfWeeks;

    public Patient(String name, String gender, int startingWeight) {
        this.name = name;
        this.gender = gender;
        this.numberOfWeeks = 0;
        weights = new int[52];
        weights[0] = startingWeight;
    }

    public int getGivenWeight() {
        return weights[0] - weights[numberOfWeeks];
    }

    public void enterWeight(int week, int weight) {
        weights[week] = weight;
        numberOfWeeks++;
    }

    public void getInformation() {
        System.out.println("Patient name: " + name);
        System.out.println("Gender: " + gender);
        System.out.print("Week : |");
        for (int i = 0; i <= numberOfWeeks; i++) {
            System.out.print("  " + i + "  |");
        }
        System.out.print("\n");
        System.out.print("Weight  : |");
        for (int i = 0; i <= numberOfWeeks; i++) {
            System.out.print(" " + weights[i] + " |");
        }
        System.out.print("\n");
    }

    public int getLastWeight() {
        return weights[numberOfWeeks];
    }

    public int getMinimumWeight() {
        int minWeight = weights[0];
        for (int i = 1; i <= numberOfWeeks; i++) {
            if (weights[i] < weights[i - 1]) {
                minWeight = weights[i];
            }
        }
        return minWeight;
    }

    public int getLostMostWeightWeek() {
        int maximumLost = 0;
        int week = 0;
        for (int i = 1; i <= numberOfWeeks; i++) {
            if (maximumLost < weights[i - 1] - weights[i]) {
                week = i;
                maximumLost = weights[i] - weights[i - 1];
            }
        }
        return week;
    }

    public boolean isItWeaker(Patient otherPatient) {
        return this.weights[numberOfWeeks] < otherPatient.weights[otherPatient.numberOfWeeks];
    }
}
