import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WireCrossing {

    private static int[][] initialiseTable() {
        int[][] table = new int[25][25];

        for (int row = 0; row<table.length; row++) {
            for (int col = 0; col<table[row].length; col++) {
                table[row][col] = 0;
                if (row == 10 && col == 10) {
                    table[row][col] = 1;
                }
//                System.out.print(table[row][col] + "\t");
            }
//            System.out.println();
        }
        return table;
    }


    public static int[][] createWire1(String[] instructions, int[][] table, int[] currentPosition) {

        int firstDirection = instructions[0].charAt(0);
        int firstNumber = (Integer.parseInt(instructions[0].substring(1)));
        if (firstDirection == 'R') {
            for (int i = 1; i<firstNumber+1; i++) {
                table[currentPosition[0]][currentPosition[1]+1] = 2;
                currentPosition[1] = currentPosition[1]+1;
            }
        }
        if (firstDirection == 'L') {
            for (int i = 1; i<firstNumber+1; i++) {
                table[currentPosition[0]][currentPosition[1]-1] = 2;
                currentPosition[1] = currentPosition[1]-1;
            }
        }
        if (firstDirection == 'U') {
            for (int i = 1; i<firstNumber+1; i++) {
                table[currentPosition[0]-1][currentPosition[1]] = 2;
                currentPosition[0] = currentPosition[0]-1;
            }
        }
        if (firstDirection == 'D') {
            for (int i = 1; i<firstNumber+1; i++) {
                table[currentPosition[0]+1][currentPosition[1]] = 2;
                currentPosition[0] = currentPosition[0]+1;
            }
        }

        if (instructions.length>1) {
            String[] newArray = Arrays.copyOfRange(instructions, 1, instructions.length);
            return createWire1(newArray, table, currentPosition);
        }

        return table;
    }
    public static int[][] createWire2(String[] instructions, int[][] table, int[] currentPosition) {

        int firstDirection = instructions[0].charAt(0);
        int firstNumber = (Integer.parseInt(instructions[0].substring(1)));
        if (firstDirection == 'R') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]][currentPosition[1]+1] == 2) {
                    table[currentPosition[0]][currentPosition[1]+1] = 4;
                    currentPosition[1] = currentPosition[1] + 1;
                } else {
                    table[currentPosition[0]][currentPosition[1] + 1] = 3;
                    currentPosition[1] = currentPosition[1] + 1;
                }
            }
        }
        if (firstDirection == 'L') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]][currentPosition[1]-1] == 2) {
                    table[currentPosition[0]][currentPosition[1]-1] = 4;
                    currentPosition[1] = currentPosition[1] - 1;
                } else {
                    table[currentPosition[0]][currentPosition[1] - 1] = 3;
                    currentPosition[1] = currentPosition[1] - 1;
                }
            }
        }
        if (firstDirection == 'U') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]-1][currentPosition[1]] == 2) {
                    table[currentPosition[0]-1][currentPosition[1]] = 4;
                    currentPosition[0] = currentPosition[0]-1;
                } else {
                    table[currentPosition[0] - 1][currentPosition[1]] = 3;
                    currentPosition[0] = currentPosition[0] - 1;
                }
            }
        }
        if (firstDirection == 'D') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]+1][currentPosition[1]] == 2) {
                    table[currentPosition[0]+1][currentPosition[1]] = 4;
                    currentPosition[0] = currentPosition[0] + 1;
                } else {
                    table[currentPosition[0] + 1][currentPosition[1]] = 3;
                    currentPosition[0] = currentPosition[0] + 1;
                }
            }
        }

        if (instructions.length>1) {
            String[] newArray = Arrays.copyOfRange(instructions, 1, instructions.length);
            return createWire2(newArray, table, currentPosition);
        }

        return table;
    }

    public static int positionsOfCross(int[][] table) {
        int closest = 100000000;
        for (int row = 0; row<table.length; row++) {
            for (int col = 0; col<table[row].length; col++) {
                if (table[row][col]==4) {
                    int manhattan = Math.abs(row-10) + Math.abs(col-10);
                    if (manhattan<closest) {
                        closest = manhattan;
                    }
                }
            }
        }
        return closest;
    }




    public static int getCoordinatesOfFirst4(String[] instructions, int[][] table, int[] currentPosition, int[] coordinates, int count, Boolean isFirst) {
        int firstDirection = instructions[0].charAt(0);
        int firstNumber = (Integer.parseInt(instructions[0].substring(1)));
        if (firstDirection == 'R') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]][currentPosition[1]+1] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    count++;
                    isFirst = false;

                } else if(isFirst) {
                    table[currentPosition[0]][currentPosition[1] + 1] = 3;
                    currentPosition[1] = currentPosition[1] + 1;
                    System.out.println("uii");
                    System.out.println(count);
                    count++;
                }
            }
        }
        if (firstDirection == 'L') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]][currentPosition[1]-1] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    count++;
                    isFirst = false;
                }
                 else if(isFirst) {
                    table[currentPosition[0]][currentPosition[1] - 1] = 3;
                    currentPosition[1] = currentPosition[1] - 1;
                    System.out.println("rte");
                    System.out.println(count);
                    count++;
                }
            }
        }
        if (firstDirection == 'U') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]-1][currentPosition[1]] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    System.out.println("grrge");
                    System.out.println(count);

                    count++;
                    isFirst = false;
                }
                else if(isFirst){
                    table[currentPosition[0] - 1][currentPosition[1]] = 3;
                    currentPosition[0] = currentPosition[0] - 1;
                    System.out.println(count);
                    count++;
                }
            }
        }
        if (firstDirection == 'D') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]+1][currentPosition[1]] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    System.out.println("TARGET IS HIT");
                    System.out.println(count);
                    count++;
                    isFirst = false;
                }
                 else if(isFirst){
                    System.out.println(table[currentPosition[0] + 1][currentPosition[1]]);
                    table[currentPosition[0] + 1][currentPosition[1]] = 3;
                    currentPosition[0] = currentPosition[0] + 1;
                    System.out.println("rtrt");
                    System.out.println(count);
                    count++;
                }
            }
        }

        for (int row = 0; row<table.length; row++) {
            for (int col = 0; col<table[row].length; col++) {
                System.out.print(table[row][col] + "\t");
            }
            System.out.println();
        }

        System.out.println("MOOOO");
        System.out.println(instructions.length);
        if (instructions.length>1) {
            System.out.println("HI");
            String[] newArray = Arrays.copyOfRange(instructions, 1, instructions.length);
            return getCoordinatesOfFirst4(newArray, table, currentPosition, coordinates, count, isFirst);
        }
        System.out.println("count");

        System.out.println(count);
        return count;
    }

    public static int getCoordinatesOfRealFirst4(String[] instructions, int[][] table, int[] currentPosition, int[] coordinates, int count, boolean isFirst) {
        int firstDirection = instructions[0].charAt(0);
        int firstNumber = (Integer.parseInt(instructions[0].substring(1)));
        if (firstDirection == 'R') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]][currentPosition[1]+1] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    count++;
                    isFirst = false;

                } else if(isFirst) {
                    table[currentPosition[0]][currentPosition[1] + 1] = 2;
                    currentPosition[1] = currentPosition[1] + 1;
                    count++;
                }
            }
        }
        if (firstDirection == 'L') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]][currentPosition[1]-1] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    count++;
                    isFirst = false;
                }
                else if(isFirst) {
                    table[currentPosition[0]][currentPosition[1] - 1] = 2;
                    currentPosition[1] = currentPosition[1] - 1;
                    count++;
                }
            }
        }
        if (firstDirection == 'U') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]-1][currentPosition[1]] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    count++;
                    isFirst = false;
                }
                else if(isFirst){
                    table[currentPosition[0] - 1][currentPosition[1]] = 2;
                    currentPosition[0] = currentPosition[0] - 1;
                    count++;
                }
            }
        }
        if (firstDirection == 'D') {
            for (int i = 1; i<firstNumber+1; i++) {
                if (table[currentPosition[0]+1][currentPosition[1]] == 4 && isFirst) {
                    coordinates[0] = currentPosition[0];
                    coordinates[1] = currentPosition[1]+1;
                    System.out.println("4 is hit");
                    count++;
                    isFirst = false;
                }
                else if(isFirst){
                    table[currentPosition[0] + 1][currentPosition[1]] = 2;
                    currentPosition[0] = currentPosition[0] + 1;
                    count++;
                }
            }
        }


        if (instructions.length>1) {
            String[] newArray = Arrays.copyOfRange(instructions, 1, instructions.length);
            return getCoordinatesOfFirst4(newArray, table, currentPosition, coordinates, count, true);
        }
        System.out.println("gregr");

        System.out.println(count);
        return count;
    }


    public static void main(String[] args) {
        String instruction1 = "R8,U5,L5,D3";
        String instruction2 = "U7,R6,D4,L4";
        String[] listOfInstructions1 = instruction1.split(",");
        String[] listOfInstructions2 = instruction2.split(",");

        System.out.println(listOfInstructions1[1]);

        int firstNumber = (Integer.parseInt(listOfInstructions1[0].substring(1)));
        int[] originalPosition = {10,10};
        int[] currentPosition = {10,10};
        int[][] table = initialiseTable();

        createWire1(listOfInstructions1, table, currentPosition);
        createWire2(listOfInstructions2, table, originalPosition);
        int[] coordinates = {10000,10000};
        int count = 0;
        String[] newListOfInstructions2 = instruction2.split(",");
        int[] newOriginalPosition = {10,10};

        count = getCoordinatesOfFirst4(newListOfInstructions2, table, newOriginalPosition,coordinates, count, true);

        System.out.println(count);

        int count1 = 0;
        String[] newListOfInstructions1 = instruction2.split(",");
        int[] newOriginalPosition1 = {10,10};

        count1 = getCoordinatesOfFirst4(newListOfInstructions1, table, newOriginalPosition1,coordinates, count1, true);
        System.out.println(count1);

        for (int row = 0; row<table.length; row++) {
            for (int col = 0; col<table[row].length; col++) {
                System.out.print(table[row][col] + "\t");
            }
            System.out.println();
        }

        System.out.println(positionsOfCross(table));


    }
}
