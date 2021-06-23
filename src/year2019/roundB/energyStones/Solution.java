package year2019.roundB.energyStones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        int numberOfStones = 0;

        int energySum = 0;

        Stone [] stones;
        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            numberOfStones = sc.nextInt();
            stones = new Stone[numberOfStones];
            for(int i = 0; i < numberOfStones; i++){
                stones[i] = new Stone(sc.nextInt(), sc.nextInt(), sc.nextInt(), i);
            }

            Arrays.sort(stones);

            energySum = 0;

            for(int i = 0; i < numberOfStones; i++){
                energySum += Math.max(stones[i].energy, 0);
                for(int o = i + 1; o < numberOfStones; o++){
                    stones[o].energy -= stones[i].secondsToEat * stones[o].lostEnergy;
                }
            }

            printOutput(testcase, energySum);
            for(int i = 0; i < numberOfStones; i++){
                System.out.print(stones[i].id + " ");
            }
            System.out.println();
        }
    }

    static class Stone implements Comparable<Stone>{
        int secondsToEat, energy, lostEnergy, id;
        int value;

        public Stone(int secondsToEat, int energy, int lostEnergy, int id){
            this.id = id;
            this.secondsToEat = secondsToEat;
            this.energy = energy;
            this.lostEnergy = lostEnergy;
            if(secondsToEat == 0){
                ;
            }
            value = energy * lostEnergy / secondsToEat;
        }
        @Override
        public int compareTo(Stone o) {
            return o.value - value;
        }
    }

    public static void printOutput(int testcase, int answer){
        System.out.println("Case #" + testcase +": "+ answer);
    }
}