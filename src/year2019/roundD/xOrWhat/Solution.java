package year2019.roundD.xOrWhat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    static Scanner sc;
    final static short [] bitmask = new short[31];

    static{
        for(int i = 0; i < 15; i++){
            bitmask[i] = (short) (1 << i);
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            int numberElements;
            int modifications;
            numberElements = sc.nextInt();
            modifications = sc.nextInt();

            short [] elements = new short[numberElements];
            for(int i = 0; i < numberElements; i++){
                elements[i] = (short) sc.nextInt();
            }

            int [] modificationsIndex = new int[modifications];
            int [] answers = new int[modifications];
            short [] modificationValue = new short[modifications];

            for(int i = 0; i < modifications; i++){
                modificationsIndex[i] = sc.nextInt();
                modificationValue[i] = (short) sc.nextInt();
            }

            boolean [] evenElements = new boolean[numberElements];
            for(int i = 0; i < numberElements; i++){
                evenElements[i] = bitEven(elements[i]);
            }
            boolean isEvenFalseCount = evenFalseCount(evenElements);
            boolean newElementEven;
            for(int i = 0; i < modifications; i++){
                short modValue = modificationValue[i];
                elements[modificationsIndex[i]] = modValue;
                newElementEven = bitEven(modValue);
                isEvenFalseCount ^= (newElementEven != evenElements[modificationsIndex[i]]);
                evenElements[modificationsIndex[i]] = newElementEven;

                if(isEvenFalseCount){
                    answers[i] = numberElements;
                }
                else{
                    for(int n = 0; n < numberElements; n++){
                        if(!evenElements[n] || !evenElements[numberElements - n - 1]){
                            answers[i] = numberElements - n - 1;
                            break;
                        }
                    }
                }
            }





            printOutput(testcase, answers);
        }


    }

    static boolean bitEven(short s){
        boolean even = true;
        for(int i = 0; i < 15; i++){
            even ^= (s & bitmask[i]) != 0;
        }
        return even;
    }

    static boolean evenFalseCount(boolean [] b){
        boolean even = true;
        for(int i = 0; i < b.length; i++){
            even ^= !b[i];
        }
        return even;
    }


    public static void printOutput(int testcase, int [] answer){
        System.out.print("Case #" + testcase +": ");
        for(int i = 0; i < answer.length; i++){
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}