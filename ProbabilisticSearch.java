import java.util.Arrays;
import java.lang.Math;

public class ProbabilisticSearch {

    /**
     * 
     * Binary Search aus der Vorlesung leicht abgewandelt
     * 
     */

    public static int[] find(int[] a, int x) {

        return find0(a, x, 0, a.length - 1, 1);

    }

    public static int[] find0(int[] a, int x, int n1, int n2, int numberOfSteps) {

        int t = (n1 + n2) / 2;

        if (a[t] == x)

            return new int[] { t, numberOfSteps };

        else if (n1 >= n2)

            return new int[] { -1, numberOfSteps };

        else if (x > a[t])

            return find0(a, x, t + 1, n2, numberOfSteps + 1);

        else if (n1 < t)

            return find0(a, x, n1, t - 1, numberOfSteps + 1);

        else
            return new int[] { -1, numberOfSteps };

    }

    public static int[] probalisticSearch(int[] arr, int value) {
        // Lots of checks in the function to make sure index does not go out of bound
        int min = arr[0];
        int steps = 1;
        int max = arr[arr.length - 1];
        int length = arr.length;
        int position = (value - min) / ((max - min) / (length - 1));    //Round Formulae
        int last, first, i, pos;
        pos = position;
        i = 0;
        int got = 0;

        if (arr[position] == value) {
            return new int[] { position, steps };
        }
        while (true) {

            if (pos <= 0) { //position index out of bound check min
                steps--;
                return new int[] { -1, steps };
            }

            if (pos >= arr.length) {    //position index out of bound check max
                steps--;
                return new int[] { -1, steps };
            }

            if (value < arr[pos]) {
                steps = steps + 1;
                last = position - 1;
                pos = position - (int) Math.pow(2, i);  //Steps for 2^i
                min = pos;
                max = last;
                if (max >= arr.length) {    //max index out of bound check
                    max = arr.length - 1;
                }
                if (min <= 0) {     //min index out of bound check
                    min = 0;
                }
                int found[] = find0(arr, value, min, max, steps);
                i = i + 1;
                got = found[0]; //storing right position in got variable
                if (got == -1) {
                    got = 0;
                }
                if (pos <= 0) { //position index out of bound check
                    pos = 0;
                }
            }

            if (value > arr[pos]) { //Same for if the value is greater than position in array
                steps = steps + 1;  
                first = position + 1;
                pos = position + (int) Math.pow(2, i);
                min = first;
                max = pos;
                if (max >= arr.length) {
                    max = arr.length - 1;
                }
                if (min <= 0) {
                    min = 0;
                }
                int found[] = find0(arr, value, min, max, steps);
                i = i + 1;
                got = found[0];
                if (got == -1) {
                    got = 0;
                }
                if (pos <= 0) {
                    pos = 0;
                }
            }

            if (value == arr[got]) {    //return if successfully got the value
                return new int[] { pos, steps };
            }

        }

    }

    public static void compareApproaches(int[] arr, int min, int max) {
        int maxBinC = -9999;    //setting max to lowest value so first value is max
        int maxBinN = 0;

        int maxProbC = -9999;
        int maxProbN = 0;

        long totalBin = 0;
        long totalProb = 0;

        for (; min < max; min++) {  //going from min value to max, including all the values in between
            int bin[] = find(arr, min);
            if (bin[1] > maxBinC) {
                maxBinC = bin[1];  //max calls here
                maxBinN = min;      //number at max calls here
            }
            int prob[] = probalisticSearch(arr, min);
            if (prob[1] > maxProbC) {
                maxProbC = prob[1];
                maxProbN = min;
            }
            totalBin = totalBin + bin[1];
            totalProb = totalProb + prob[1];
        }

        System.out.println("Binary Search:");
        System.out.println("Max  Number of calls:");
        System.out.println(maxBinC);
        System.out.println("Number at max calls :");
        System.out.println(maxBinN);
        System.out.println("Total Calls :");
        System.out.println(totalBin);

        System.out.println("\n");

        System.out.println("Probablistic Search:");
        System.out.println("Max  Number of calls:");
        System.out.println(maxProbC);
        System.out.println("Number at max calls :");
        System.out.println(maxProbN);
        System.out.println("Total Calls :");
        System.out.println(totalProb);

    }

    public static void main(String[] args) {

        int[] exampleArray = new int[] { 6, 20, 22, 35, 51, 54, 59, 74, 77, 80, 87, 94, 97 };

        int num1[] = probalisticSearch(exampleArray, 22); 
        System.out.println(Arrays.toString(num1));

        int num2[] = probalisticSearch(exampleArray, 74);   //tests
        System.out.println(Arrays.toString(num2));

        int num3[] = probalisticSearch(exampleArray, 75); 
        System.out.println(Arrays.toString(num3));

        System.out.println("\n");

        compareApproaches(exampleArray, 6, 97);             //test compare approach
        
    }

}