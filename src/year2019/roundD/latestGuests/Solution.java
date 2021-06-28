package year2019.roundD.latestGuests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static Scanner sc;
    final static int [] bitmask = new int[32];

    static{
        for(int i = 0; i < 32; i++){
            bitmask[i] = (1 << i);
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();

        int numberConsulates, numberGuests, time;
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            numberConsulates = sc.nextInt();
            numberGuests = sc.nextInt();
            time = sc.nextInt();

            int [][] consulateMemory = new int [numberConsulates][numberGuests / 32 + 1];
            int [] answers = new int[numberGuests];

            for(int i = 0; i < numberConsulates; i++){
                Arrays.fill(consulateMemory[i], 0);
            }
            if(time > numberConsulates) {
                time = time % numberConsulates + numberConsulates;
            }

            int [] position = new int[numberGuests];
            boolean [] clockwise = new boolean[numberGuests];
            for(int i = 0; i < numberGuests; i++){
                position[i] = sc.nextInt() - 1;
                clockwise[i] = (sc.next().compareTo("C") == 0);
                consulateMemory[position[i]][i / 32] |= bitmask[i % 32];
            }

            for(int i = 0; i < time; i++){
                for(int g = 0; g < numberGuests; g++){
                    position[g] += (clockwise[g] ? 1 : (numberConsulates - 1));
                    position[g] %= numberConsulates;
                    Arrays.fill(consulateMemory[position[g]], 0);
                }

                for(int g = 0; g < numberGuests; g++){
                    consulateMemory[position[g]][g / 32] |= bitmask[g % 32];
                }
            }

            int sum = 0;
            for(int g = 0; g < numberGuests; g++){
                sum = 0;
                for(int i = 0; i < numberConsulates; i++){
                    sum += ((consulateMemory[i][g / 32] & bitmask[g % 32]) != 0) ? 1 : 0;
                }
                answers[g] = sum;
            }

            printOutput(testcase, answers);
        }
    }


    public static void printOutput(int testcase, int [] answer){
        System.out.print("Case #" + testcase +": ");
        for(int i = 0; i < answer.length; i++){
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}