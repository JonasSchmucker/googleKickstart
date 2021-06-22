package year2019.roundA.parcels;
import java.awt.Point;
import java.util.*;
import java.io.*;

public class Solution {
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testcases = 0;
        int rows = 0;
        int columns = 0;
        int [][] distance;
        int [][] newDistance;
        testcases = sc.nextInt();
        for(int testcase = 0; testcase < testcases; testcase++) {
            rows = sc.nextInt();
            columns = sc.nextInt();
            distance = new int[columns][rows];
            newDistance = new int[columns][rows];
            for(int o = 0; o < columns; o++) {
                Arrays.fill(distance[o], Integer.MAX_VALUE);
                Arrays.fill(newDistance[o], Integer.MAX_VALUE);
            }
            int temp = 0;
            for (int i = 0; i < rows; i++) {
                temp = sc.nextInt();
                for(int o = 0; o < columns; o++){
                    int decPot = (int) Math.pow(10, rows - o - 1);
                    if (temp >= decPot) {
                        temp -= decPot;
                        newDistance[o][i] = 0;
                    }
                }
            }
            calculateDistance(rows, columns, distance, newDistance);

            //printArray(newDistance);

            int maxRow = 0;
            int maxColumn = 0;

            for (int i = 0; i < rows; i++) {
                for (int o = 0; o < columns; o++) {
                    if(distance[o][i] > distance[maxColumn][maxRow]){
                        maxRow = i;
                        maxColumn = o;
                    }
                }
            }
            int highest = distance[maxColumn][maxRow];

            List<Point> occurencesHighest = new ArrayList<>();

            for (int i = 0; i < rows; i++) {
                for (int o = 0; o < columns; o++) {
                    if(distance[o][i] == highest){
                        occurencesHighest.add(new Point(o,i));
                    }
                }
            }

            int targetRow = 0;
            int targetColumn = 0;

            for(Point t : occurencesHighest){
                targetRow += t.getY();
                targetColumn += t.getX();
            }
            targetRow /= occurencesHighest.size();
            targetColumn /= occurencesHighest.size();

            //System.out.println("Row: " + targetRow + " Column: " + targetColumn);

            newDistance[targetColumn][targetRow] = 0;

            calculateDistance(rows, columns, distance, newDistance);

            //printArray(newDistance);

            maxRow = 0;
            maxColumn = 0;

            for (int i = 0; i < rows; i++) {
                for (int o = 0; o < columns; o++) {
                    if(distance[o][i] > distance[maxColumn][maxRow]){
                        maxRow = i;
                        maxColumn = o;
                    }
                }
            }

            //System.out.println("Row: " + maxRow + " Column: " + maxColumn);

            System.out.println("Case #" + (testcase + 1) + ": " + distance[maxColumn][maxRow]);
        }
    }

    private static void printArray(int[][] newDistance) {
        for (int[] b : newDistance) {
            for (int bb : b) {
                System.out.print(bb + " ");
            }
            System.out.println();
        }
    }

    private static void calculateDistance(int rows, int columns, int[][] distance, int[][] newDistance) {
        while(!ArraysEqual(distance, newDistance, rows, columns)) {
            for (int i = 0; i < rows; i++) {
                for (int o = 0; o < columns; o++) {
                    distance[o][i] = newDistance[o][i];
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int o = 0; o < columns; o++) {
                    newDistance[o][i] = Math.min(newDistance[o][i], lowestSurrounding(newDistance, i, o) + 1);
                }
            }
        }
    }

    static boolean ArraysEqual(int[][] a, int[][] b, int rows, int columns){
        for (int i = 0; i < rows; i++) {
            for(int o = 0; o < columns; o++){
                if(a[o][i] != b[o][i]){
                    return false;
                }
            }
        }
        return true;
    }

    static int lowestSurrounding(int[][] array, int rows, int columns){
        int ans = Integer.MAX_VALUE - 1;
        if(columns != 0 && array[columns - 1][rows] < ans){
            ans = array[columns - 1][rows];
        }
        if(columns != array.length - 1 && array[columns + 1][rows] < ans){
            ans = array[columns + 1][rows];
        }
        if(rows != 0 && array[columns][rows - 1] < ans){
            ans = array[columns][rows - 1];
        }
        if(rows != array[0].length - 1 && array[columns][rows + 1] < ans){
            ans = array[columns][rows + 1];
        }
        return ans;
    }
}
