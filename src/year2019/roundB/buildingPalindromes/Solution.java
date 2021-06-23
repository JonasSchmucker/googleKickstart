package year2019.roundB.buildingPalindromes;

import java.util.*;
import java.io.*;

public class Solution {
    static Scanner sc;
    static int [] alphabet = new int[26];
    static int answer = 0;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        int n = 0;
        int questionCount = 0;
        String text;
        int [] lowerBounds;
        int [] upperBounds;
        Thread [] threads;
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            n = sc.nextInt();
            questionCount = sc.nextInt();
            text = sc.next();
            lowerBounds = new int[questionCount];
            upperBounds = new int[questionCount];
            threads = new Thread[questionCount];
            for(int i = 0; i < questionCount; i++){
                lowerBounds[i] = sc.nextInt();
                upperBounds[i] = sc.nextInt();
            }

            answer = 0;

            for(int i = 0; i < questionCount; i++){
                int[] finalUpperBounds = upperBounds;
                int finalI = i;
                int[] finalLowerBounds = lowerBounds;
                String finalText = text;
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        answer += isPalindromeMaterial(finalText.substring(finalLowerBounds[finalI] - 1, finalUpperBounds[finalI]))?1:0;
                    }
                });
                threads[i].run();
            }

            for(int i = 0; i < questionCount; i++){
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            printOutput(testcase, answer);
        }
    }

    private static boolean isPalindromeMaterial(String substring) {
        Arrays.fill(alphabet, 0);
        for(int i = 0; i < substring.length(); i++){
            alphabet[substring.charAt(i) - 'A']++;
        }
        boolean singleLetterJoker = true;
        for(int i = 0; i < alphabet.length; i++){
            if(alphabet[i]%2 == 1){
                if(!singleLetterJoker){
                    return false;
                }else{
                    singleLetterJoker = false;
                }
            }
        }
        return true;
    }

    public static void printOutput(int testcase, int answer){
        System.out.println("Case #" + testcase +": "+ answer);
    }
}