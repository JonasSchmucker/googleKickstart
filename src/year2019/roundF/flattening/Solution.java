package year2019.roundF.flattening;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        int numberOfSections;
        int numberOfDifferences;
        int [] heights = new int[100];

        List<Gap> gaps = new ArrayList<>();

        Map<Integer, List<Integer>> levelToSections = new HashMap<>();

        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            gaps.clear();
            levelToSections.clear();
            Arrays.fill(heights, 0);
            numberOfSections = sc.nextInt();
            numberOfDifferences = sc.nextInt();

            for(int i  = 0; i < numberOfSections; i++){
                heights[i] = sc.nextInt();
            }

            for(int i = 0; i < numberOfSections; i++){
                levelToSections.computeIfAbsent(heights[i], (k)-> new ArrayList<>()).add(i);
            }

            List<Integer> currentList;
            for(Integer level : levelToSections.keySet()){
               currentList = levelToSections.get(level);
               for(int i = 0; i < currentList.size(); i++){
                   if(i == 0){
                       gaps.add(new Gap(-1, currentList.get(i), level));
                   }
                   if(i == currentList.size() - 1){
                       gaps.add(new Gap(currentList.get(i), numberOfSections, level));
                       break;
                   }
                   gaps.add(new Gap(currentList.get((i)), currentList.get(i + 1), level));
               }
            }

            printOutput(testcase, 0);
        }
    }

    public static void printOutput(int testcase, int answer){
        System.out.println("Case #" + testcase +": "+ answer);
    }

    private static class Gap {
        int length;
        int level;
        int start;
        int end;

        public Gap(int start, int end, int level) {
            this.start = start;
            this.end = end;
            this.length = end - start - 1;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Gap{" +
                    "length=" + length +
                    ", level=" + level +
                    '}';
        }
    }
}