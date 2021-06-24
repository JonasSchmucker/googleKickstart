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
        Question [] questions;
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            n = sc.nextInt();
            questionCount = sc.nextInt();
            text = sc.next();
            questions = new Question[questionCount];
            for(int i = 0; i < questionCount; i++){
                questions[i] = new Question(sc.nextInt(), sc.nextInt());
            }

            Arrays.sort(questions);

            answer = 0;
            Arrays.fill(alphabet, 0);
            int lastLowerBound = questions[0].lowerBound;
            inputArray(text.substring(questions[0].lowerBound - 1, questions[0].upperBound));
            answer += isPalindromeInArray()?1:0;
            for(int i = 1; i < questionCount; i++){
                if(lastLowerBound == questions[i].lowerBound){
                    inputArray(text.substring(questions[i - 1].upperBound, questions[i].upperBound));
                }
                else{
                    removeArray(text.substring(lastLowerBound - 1, questions[i].lowerBound - 1));
                    if(questions[i].upperBound < questions[i - 1].upperBound){
                        removeArray(text.substring(questions[i].upperBound, questions[i - 1].upperBound));
                    }
                    else{
                        inputArray(text.substring(questions[i - 1].upperBound, questions[i].upperBound));
                    }
                    lastLowerBound = questions[i].lowerBound;
                }
                answer += isPalindromeInArray()?1:0;
            }

            printOutput(testcase, answer);
        }
    }

    private static boolean isPalindromeMaterial(String substring) {
        Arrays.fill(alphabet, 0);
        inputArray(substring);
        return isPalindromeInArray();
    }

    private static boolean isPalindromeInArray() {
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

    private static void inputArray(String substring) {
        for(int i = 0; i < substring.length(); i++){
            alphabet[substring.charAt(i) - 'A']++;
        }
    }

    private static  void removeArray(String substring){
        for(int i = 0; i < substring.length(); i++){
            alphabet[substring.charAt(i) - 'A']--;
        }
    }

    public static void printOutput(int testcase, int answer){
        System.out.println("Case #" + testcase +": "+ answer);
    }

    static class Question implements Comparable<Question>{
        int lowerBound = 0;
        int upperBound = 0;

        public Question(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public int compareTo(Question o) {
            return (lowerBound == o.lowerBound) ? upperBound - o.upperBound : lowerBound - o.lowerBound;
        }

        public String toString(){
            return "From " + lowerBound + " to " + upperBound;
        }
    }
}