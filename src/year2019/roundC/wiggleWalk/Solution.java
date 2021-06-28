package year2019.roundC.wiggleWalk;

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
        int instructionCount = 0;
        int rows = 0;
        int columns = 0;
        Position currentPosition;

        boolean [][] visited;

        Direction [] instructions;
        String instructionBuffer;

        for(int testcase = 1; testcase < testcases + 1; testcase++) {
            instructionCount = sc.nextInt();
            instructions = new Direction[instructionCount];
            rows = sc.nextInt();
            columns = sc.nextInt();
            visited = new boolean [rows][columns];
            for(int i = 0; i < rows; i++){
                Arrays.fill(visited[i], false);
            }
            currentPosition = new Position(sc.nextInt() - 1, sc.nextInt() - 1);
            //System.out.println("Starting at Position: " + currentPosition.toString());
            instructionBuffer = sc.next();
            for(int i = 0; i < instructionCount; i++){
                instructions[i] = Direction.fromChar(instructionBuffer.charAt(i));
            }

            for(int i = 0; i < instructionCount; i++){
                currentPosition.move(instructions[i], visited);
            }

            printOutput(testcase, currentPosition.row, currentPosition.column);
        }
    }

    public static void printOutput(int testcase, int row, int column){
        System.out.println("Case #" + testcase +": "+ (row + 1) + " " + (column + 1));
    }

    static class Position{
        int row;
        int column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        void move(Direction instruction, boolean [][] visited) {
            if(instruction == null){
                return;
            }
            visited[row][column] = true;
            switch (instruction){
                case NORTH:
                    row--;
                    break;
                case EAST:
                    column++;
                    break;
                case SOUTH:
                    row++;
                    break;
                case WEST:
                    column--;
                    break;
            }
            //System.out.println("Going " + instruction.toString() + " to Position: " + toString());
            if(visited[row][column]){
                move(instruction, visited);
            }
        }

        public String toString(){
            return "row: " + (row + 1) + " column: " + (column + 1);
        }
    }

    static enum Direction{
        NORTH, EAST, SOUTH, WEST;

        static Direction fromChar(char c){
            switch(c){
                case 'N':
                    return NORTH;
                case 'E':
                    return EAST;
                case 'S':
                    return SOUTH;
                case 'W':
                    return WEST;
            }
            return null;
        }
    }
}