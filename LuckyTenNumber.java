import java.util.*;

/**
 * @author Ali Çolak
 * 17.03.2021
 */

public class LuckyTenNumber {

    static final int MAXIMUM_NUMBER = 60;
    static final int COUNT_NUMBER = 1000000;

    public static void main(String[] args) {

        Map<Integer, Integer> numbersMap = new HashMap<>();
        List<Integer> numberList = new ArrayList<>();
        Set<Integer> luckyTenNumber =new TreeSet<>();

        fillMap(numbersMap);

        for (Map.Entry<Integer, Integer> entry : numbersMap.entrySet()) {
            System.out.println(entry);
        }
        int maxValue = 0;
        for (Integer value : numbersMap.values()) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        System.out.println("Max Value : " + maxValue);

        printMap(numbersMap, numberList);

        System.out.println("Length of Array List : "+numberList.size());

        getLuckyTenNumber(luckyTenNumber,numberList);

        System.out.print("Luck Ten Number : {");
        for ( Integer temp : luckyTenNumber ) {
            System.out.print(temp+" ");
        }
        System.out.println("}");

    }

    public static void fillMap(Map<Integer, Integer> numbersMap) {

        for (int i = 0; i < COUNT_NUMBER; i++) {

            int randomNumber = createRandomNumber();
            if (numbersMap.containsKey(randomNumber)) {
                int value = numbersMap.get(randomNumber);
                numbersMap.put(randomNumber, value + 1);
            } else {
                numbersMap.put(randomNumber, 1);
            }
        }
    }

    public static int createRandomNumber() {
        return (int) ((Math.random() * MAXIMUM_NUMBER) + 1);
    }

    public static void printMap(Map<Integer, Integer> numbersMap, List<Integer> numberList) {

        for (Map.Entry<Integer, Integer> entry : numbersMap.entrySet()) {

            int key = entry.getKey();
            int value = entry.getValue();

            for (int i = 0; i <value; i++) {
                numberList.add(key);
            }
        }
    }

    public static void getLuckyTenNumber(Set<Integer> luckyTenNumber, List<Integer> numberList) {

        Collections.shuffle(numberList);

        while (luckyTenNumber.size()<10){
            int randomIndex = (int) (Math.random()*COUNT_NUMBER);
            luckyTenNumber.add(numberList.get(randomIndex));
        }
    }
    
}