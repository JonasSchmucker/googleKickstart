package year2019.roundE.cherriesMesh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();

        int numberCherries;
        int numberBlackStrands;
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            numberCherries = sc.nextInt();
            numberBlackStrands = sc.nextInt();

            for(int i = 0; i < numberBlackStrands; i++){

            }
        }
    }

    public static void printOutput(int testcase, int answer){
        System.out.println("Case #" + testcase +": "+ answer);
    }
}