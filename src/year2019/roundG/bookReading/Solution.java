package year2019.roundG.bookReading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        int pages= 0;
        int tornOut = 0;
        int readers = 0;

        int answer = 0;

        int [] readsPages;
        int [] tornOutPages;
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            answer = 0;
            pages = sc.nextInt();
            tornOut = sc.nextInt();
            readers = sc.nextInt();

            tornOutPages = new int [tornOut];
            readsPages = new int [readers];

            for(int i = 0; i < tornOut; i++){
                tornOutPages[i] = sc.nextInt();
            }

            for(int i = 0; i < readers; i++){
                readsPages[i] = sc.nextInt();
            }

            for(int i = 0; i < readers; i++){
               answer += (pages / readsPages[i]);
            }

            for(int i = 0; i < tornOut; i++){
                for(int p = 0; p < readers; p++){
                    answer -= ((tornOutPages[i] % readsPages[p]) == 0) ? 1 : 0;
                }
            }

            printOutput(testcase, answer);
        }
    }

    public static void printOutput(int testcase, int answer){
        System.out.println("Case #" + testcase +": "+ answer);
    }
}