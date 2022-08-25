import java.util.Arrays;

/**
 * @author Ali Çolak
 * 8.02.2022
 */

public class GoogleInterviewQuestion {

    // In a 6 * 6 matrix containing only 0s and 1s, convert the 1s that are not adjacent to the 1s in the borders to zero and return the newly formed array


    public static void main(String[] args) {

        int [] [] array = {
                {1,0,0,0,0,0},
                {0,1,0,1,1,1},
                {0,0,1,0,1,0},
                {1,1,0,0,1,0},
                {1,0,1,1,0,0},
                {1,0,0,0,0,1}
        };

        array = convert(array);

        for (int [] arr : array) {
            System.out.println(Arrays.toString(arr));
        }

    }


    public static int [][] convert (int [][] array) {

        int [][] matrix = new int[array.length][array[0].length];

        for (int i = 1; i < array.length-2; i++) {

            int index = 0 ;
            boolean condition = array[index][i] == 1 ;

            while (condition && index< array.length-1) {
                matrix[index][i] = -1;
                index++;
                condition = array[index][i] == 1;
            }
        }

        for (int i = 1; i < array.length-2; i++) {

            int index = array.length-2 ;
            boolean condition = array[index+1][i] == 1  && array[index][i]==1;

            while (condition && index> 0) {
                matrix[index][i] = -1;
                index--;
                condition = array[index][i] == 1;
            }

        }

        for (int i = 1; i < array.length-2; i++) {

            int index = 1 ;
            boolean condition = array[i][index-1] == 1  && array[i][index]==1;

            while (condition && index< array[0].length-1) {
                matrix[i][index] = -1;
                index++;
                condition = array[i][index] == 1;
            }

        }

        for (int i = 1; i < array.length-2; i++) {

            int index = array[0].length-2 ;
            boolean condition = array[i][index+1] == 1  && array[i][index]==1;

            while (condition && index> 0) {
                matrix[i][index] = -1;
                index--;
                condition = array[i][index] == 1;
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (matrix[i][j] == -1)
                    array[i][j] = 0;
            }
        }

        for (int [] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("\n\n");

        return array;

    }

}
