package year2019.practise.numberGuessing;
import java.util.*;
import java.io.*;

public class Solution {
    final static String small = "TOO_SMALL";
    final static String large = "TOO_BIG";
    final static String correct = "CORRECT";
    static String textBuffer = "";
    static Scanner sc;

    public static void main(String[] args) {
        long testcases = 0;
        long a = 0;
        long b = 0;
        long n = 0;
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        testcases = sc.nextInt();

        for(int i = 0; i < testcases; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            n = sc.nextInt();

            guess(a, b);
        }
    }

    private static void guess(long a, long b){
        System.out.println((a+b + 1) / 2);
        textBuffer = sc.next();
        if(textBuffer.compareToIgnoreCase(small) == 0){
            guess((a+b+1) / 2, b);
        }
        else if(textBuffer.compareToIgnoreCase(large) == 0){
            guess(a, (a+b+1) / 2 - 1);
        }
        else if(textBuffer.compareToIgnoreCase(correct) == 0){
            //System.out.println("YAY");
        }
    }

}
