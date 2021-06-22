package year2019.roundA.training;
import java.util.*;
import java.io.*;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        int players = 0;
        int n = 0;
        int[] skill;
        testcases = sc.nextInt();
        for(int i = 0; i < testcases; i++){
            n = sc.nextInt();
            players = sc.nextInt();
            skill = new int[n];
            for(int o = 0; o < n; o++){
                skill[o] = sc.nextInt();
            }

            Arrays.sort(skill);
            long cost = 0;
            int level = skill[players - 1];
            for (int o = 0; o < players; o++) {
                cost += (level - skill[o]);
            }
            int oldlevel = 0;
            long lowestCost = cost;
           for(int startPivot = 1; startPivot < n - players + 1; startPivot++) {
               oldlevel = level;
               level = skill[startPivot + players - 1];
               cost += players * (level - oldlevel);
               cost -= (skill[startPivot + players - 1] - skill[startPivot - 1]);
               if(cost < lowestCost){
                   lowestCost = cost;
               }
           }
            System.out.println("Case #" + (i + 1) + ": " + lowestCost);
        }
    }

}
