package year2019.roundC.circuitBoard;

import java.util.*;
import java.io.*;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            int rows, columns, k;
            rows = sc.nextInt();
            columns = sc.nextInt();
            k = sc.nextInt();
            int [][] values = new int [columns][rows];
            for(int r = 0; r < rows; r++){
                for(int c = 0; c < columns; c++){
                    values[c][r] = sc.nextInt();
                }
            }

            Subarray [] subarrays = new Subarray[rows];
            int highest = 0;
            int lowest = 0;
            int starting = 0;
            int currentEnd = 0;
            int currentValue;
            for(int r = 0; r < rows; r++){
                starting = 0;
                currentEnd = 0;
                lowest = values[0][r];
                highest = values[0][r];
                for(int c = 0; c < columns; c++){
                    currentValue = values[c][r];
                    if(lowest > currentEnd){
                        if(Math.abs(highest - currentValue) > k){
                            if(subarrays[r] == null){
                                subarrays[r] = new Subarray(starting, c);
                                starting = c;
                            }
                        }
                    }
                    highest = Math.max(highest, currentValue);

                }
            }
        }
    }

    static class Subarray{
        int start; //inclusive
        int end; //exclusive

        public Subarray(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int length(){
            return end - start;
        }
    }

    public static void printOutput(int testcase, int answer){
        System.out.println("Case #" + testcase +": "+ answer);
    }
}