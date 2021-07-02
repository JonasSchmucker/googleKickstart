package year2019.roundF.codeEatSwitcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        int days = 0;
        int slotsPerDay = 0;

        long maxCoding = 0;
        long maxEating = 0;
        long bigProduct = 0;

        long [] minCoding = new long[10000];
        long [] minEating = new long[10000];

        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            days = sc.nextInt();
            slotsPerDay = sc.nextInt();

            for(int i = 0; i < slotsPerDay; i++){
                maxCoding += sc.nextInt();
                maxEating += sc.nextInt();
            }
            bigProduct = maxCoding * maxEating;

            for(int i = 0; i < days; i++){
                minCoding[i] = sc.nextInt();
                minEating[i] = sc.nextInt();
            }

            // Graph for line: maxCoding - (maxCoding / maxEating) * x, Testcase 1: 9 - (9 / 18) * x => 9 - 0.5 * x
            // Equation: maxCoding - maxCoding / maxEating * minEating >= minCoding, Testcase 1: 9 - (9 / 18) * 18 >= 0
            // Simplification: maxCoding * maxEating - maxCoding * minEating >= minCoding * maxEating, Testcase 1: 9 * 18 - 9 * 18 >= 0 * 18
            // Simplification: maxCoding * maxEating >= minCoding * maxEating + maxCoding * minEating , Testcase 1: 9 * 18 >= 0 * 18 + 9 * 18
            printOutput(testcase);

            long right;
            for(int i = 0; i < days; i++){
                right = minCoding[i] * maxEating + maxCoding * minEating[i];
                System.out.print((bigProduct >= right) ?  "Y" : "N");
            }

            System.out.println();
        }
    }

    public static void printOutput(int testcase){
        System.out.print("Case #" + testcase +": ");
    }
}