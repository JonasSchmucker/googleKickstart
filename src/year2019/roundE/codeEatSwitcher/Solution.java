package year2019.roundE.codeEatSwitcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        testcases = sc.nextInt();
        int days = 0;
        int slotsPerDay = 0;

        long [] coding = new long[10000];
        long [] eating = new long[10000];

        Point [] minPoints = new Point[10000];

        Map<Long, Point> codingToPoints = new HashMap<>();
        TreeSet<Long> values = new TreeSet<>();
        ArrayList<Long> toAdd = new ArrayList<>();

        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            Arrays.fill(coding, 0);
            Arrays.fill(eating, 0);
            Arrays.fill(minPoints, null);

            days = sc.nextInt();
            slotsPerDay = sc.nextInt();
            codingToPoints.put(0L, new Point(0, 0));
            values.add(0L);

            for(int i = 0; i < slotsPerDay; i++){
                coding[i] = sc.nextInt();
                eating[i] = sc.nextInt();
            }

            for(int i = 0; i < days; i++){
                minPoints[i] = new Point(sc.nextInt(), sc.nextInt());
            }

            for(int i = 0; i < slotsPerDay; i++){
                for(Long thisKey : values){
                    long thisEating = codingToPoints.get(thisKey).eating;
                    toAdd.add(thisKey + coding[i]);
                    codingToPoints.merge(thisKey + coding[i], new Point(thisKey + coding[i], thisEating), Solution::higherEating);
                    codingToPoints.merge(thisKey, new Point(thisKey, thisEating + eating[i]), Solution::higherEating);
                }

                values.addAll(toAdd);
                toAdd.clear();

                int lastValuesSize = 0;
                while(lastValuesSize != values.size()) {
                    lastValuesSize = values.size();
                    int firstLast = 0;
                    for (Long thisValue : values) {
                        if (firstLast != 0 && firstLast != values.size() - 1) {
                            if (redundant(codingToPoints, values, codingToPoints.get(thisValue))) {
                                toAdd.add(thisValue);
                            }
                        }
                        firstLast++;
                    }

                    values.removeAll(toAdd);
                    codingToPoints.keySet().removeAll(toAdd);
                    toAdd.clear();
                }
            }
            boolean [] answers = new boolean[days];

            for(int i = 0; i < days; i++){
                answers[i] = possible(codingToPoints, values, minPoints[i]);
            }

            printOutput(testcase, answers);
        }
    }

    private static boolean possible(Map<Long, Point> codingToPoints, TreeSet<Long> values, Point minPoint) {
        long lowCoding = values.floor(minPoint.coding);
        long lowEating = codingToPoints.get(lowCoding).eating;

        if(values.last() == lowCoding){
            return false;
        }

        long highCoding = values.ceiling(minPoint.coding);
        long highEating = codingToPoints.get(highCoding).eating;

        // (minPoint.coding - lowCoding)*((highEating - lowEating) / (highCoding - lowCoding)) + lowEating >= minPoint.eating
        return (minPoint.coding - lowCoding)*(highEating - lowEating) + lowEating * (highCoding - lowCoding) >= minPoint.eating * (highCoding - lowCoding);
    }

    private static boolean redundant(Map<Long, Point> codingToPoints, TreeSet<Long> values, Point minPoint) {
        long lowCoding = values.lower(minPoint.coding);
        long lowEating = codingToPoints.get(lowCoding).eating;

        if(values.last() == lowCoding){
            return false;
        }

        long highCoding = values.higher(minPoint.coding);
        long highEating = codingToPoints.get(highCoding).eating;

        // (minPoint.coding - lowCoding)*((highEating - lowEating) / (highCoding - lowCoding)) + lowEating >= minPoint.eating
        return (minPoint.coding - lowCoding)*(highEating - lowEating) + lowEating * (highCoding - lowCoding) >= minPoint.eating * (highCoding - lowCoding);
    }

    static Point higherEating(Point p1, Point p2){
        return p2.eating > p1.eating ? p2 : p1;
    }

    static class Point{
        long coding;
        long eating;

        public Point(long coding, long eating) {
            this.eating = eating;
            this.coding = coding;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "coding=" + coding +
                    ", eating=" + eating +
                    '}';
        }
    }

    public static void printOutput(int testcase, boolean[] answers){
        System.out.print("Case #" + testcase +": ");
        for(boolean b : answers){
            System.out.print(b ? "Y" : "N");
        }
        System.out.println();
    }
}