import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        // Create the list of coordinates to be used in the exercise
        List<int[][]> coords = Day5.readData();

        //Day5.part1(coords);
        Day5.part2(coords);
    }

    public static List<int[][]> readData() throws FileNotFoundException{
        File input = new File("/home/adri/Documents/Programming/Java/AdventOfCode2021/Inputs/20211205_Input_puzzle_5.txt");
        Scanner myReader = new Scanner(input);

        List<int[][]> coords = new ArrayList<>();
        while(myReader.hasNextLine()){
            // Write the ints to coordinate arrays
            String line = myReader.nextLine();
            String[] pair = line.split(" -> ");
            String[] point1 = pair[0].split(",");
            String[] point2 = pair[1].split(",");
            int[][] coord = {{Integer.parseInt(point1[0]), Integer.parseInt(point1[1])},
                    {Integer.parseInt(point2[0]), Integer.parseInt(point2[1])}};

            coords.add(coord);
            //System.out.println("x1: " + coord[0][0] + " y1: " + coord[0][1] + " x2: " + coord[1][0] + " y2: " + coord[1][1]);
        }

        return coords;
    }

    public static List<ArrayList<Integer>> createIntersectionsList(List<int[][]> coords){
        // Based on the minimum and maximum coordinates in the list, create the intersections list
        List<ArrayList<Integer>> intersections = new ArrayList<>();

        //Define the minimum and maximum x and y coordinates
        int maxX = 0;
        int maxY = 0;
        for(int[][] coord : coords){
            if(coord[0][0] > maxX || coord[1][0] > maxX){
                maxX = Math.max(coord[0][0], coord[1][0]);
            }
            if(coord[0][1] > maxY || coord[1][1] > maxY){
                maxY = Math.max(coord[0][1], coord[1][1]);
            }
        }

        // Using the maximum x and y coordinates from above, set up a grid for the intersecting points
        for(int i = 0; i <= maxY; i++){
            ArrayList<Integer> y = new ArrayList<>();
            for(int j = 0; j <= maxX; j++){
                y.add(0);
            }
            intersections.add(y);
        }

        return intersections;
    }

    public static void checkForHorizontals(int[][] coord, List<ArrayList<Integer>> intersections){

        // If the y coordinates are the same, it's a horizontal line
        int j;

        if(coord[0][1] == coord[1][1]){
            int y = coord[0][1];
            System.out.println("Horiz. line to be processed -> x1: " + coord[0][0] + " x2: " + coord[1][0] + " y1: " + coord[0][1] + " y2: " + coord[1][1]);
            for(int x = Math.min(coord[0][0], coord[1][0]); x <= Math.max(coord[0][0], coord[1][0]); x++){
                j = intersections.get(y).get(x);
                intersections.get(y).set(x, j + 1);
            }
        }
    }

    public static void checkForVerticals(int[][] coord, List<ArrayList<Integer>> intersections){

        // If the x coordinates are the same, it's a vertical line
        int j;

        if(coord[0][0] == coord[1][0]){
            int x = coord[0][0];
            System.out.println("Vert. line to be processed -> x1: " + coord[0][0] + " x2: " + coord[1][0] + " y1: " + coord[0][1] + " y2: " + coord[1][1]);
            // Loop through the y coordinates of the ArrayList and increment that value
            for(int y = Math.min(coord[0][1], coord[1][1]); y <= Math.max(coord[0][1], coord[1][1]); y++){
                // Note: it doesn't work this way! Can't start with an empty ArrayList and set random values -> there must be all values before that
                // Check if the value at that point is null
                //j = (intersections.get(y).get(x) == null) ? 0 : intersections.get(y).get(x);

                j = intersections.get(y).get(x);
                intersections.get(y).set(x, j + 1);
            }
        }
    }

    public static void checkForDiagonals(int[][] coord, List<ArrayList<Integer>> intersections){
        // Only check for diagonals if it's not horizontal or vertical
        if(coord[0][1] != coord[1][1] && coord[0][0] != coord[1][0]){
            // Calculate the slope of the line -> it will be -1 or 1 if the line is a 45 degree diagonal
            // The formula to calculate slope is (x2 - x1)/(y2 - y1)
            float slope = (coord[1][0] - coord[0][0]) / (coord[1][1] - coord[0][1]);

            // Only work with 45 degree diagonals
            if(slope == 1 || slope == -1){
                System.out.println("Diag. line to be processed -> x1: " + coord[0][0] + " x2: " + coord[1][0] + " y1: " + coord[0][1] + " y2: " + coord[1][1]);

                // always start with the min x and iterate ++
                // keep track of the corresponding y value to iterate through that correctly
                int x1;
                int y1;
                int x2;
                int y2;
                if(coord[0][0] > coord[1][0]){
                    x1 = coord[1][0];
                    y1 = coord[1][1];
                    x2 = coord[0][0];
                    y2 = coord[0][1];
                }else{
                    x1 = coord[0][0];
                    y1 = coord[0][1];
                    x2 = coord[1][0];
                    y2 = coord[1][1];
                }
                System.out.println("Diag. line rearranged -> x1: " + x1 + " x2: " + x2 + " y1: " + y1 + " y2: " + y2);
                int j;
                // Loop through the x coordinates
                for (int x = x1; x <= x2; x++){
                    j = intersections.get(y1).get(x);
                    intersections.get(y1).set(x, j + 1);

                    // based on whether the slope is 1 or -1, iterate ++ or -- through the y coordinates
                    if(slope == 1){
                        y1++;
                    }else{
                        y1--;
                    }
                }
            }
        }
    }

    public static void sumUpIntersections(List<ArrayList<Integer>> intersections){
        // Total up number of points with more than 1 line
        int pointsToAvoid = 0;
        for(ArrayList<Integer> intersect : intersections){
            for(Integer point : intersect){
                if(point > 1){
                    pointsToAvoid++;
                }
            }
        }

        System.out.println("Total number of points to avoid: " + pointsToAvoid);
    }

    public static void part1(List<int[][]> coords){
        // Set up the intersections list that will track all intersecting lines
        List<ArrayList<Integer>> intersections = createIntersectionsList(coords);

        for(int[][] coord : coords){
            checkForHorizontals(coord, intersections);
            checkForVerticals(coord, intersections);
        }

        // All coordinates have been processed. Calculate the intersecting points
        sumUpIntersections(intersections);
    }

    public static void part2(List<int[][]> coords){
        // Set up the intersections list that will track all intersecting lines
        List<ArrayList<Integer>> intersections = createIntersectionsList(coords);

        for(int[][] coord : coords){
            checkForHorizontals(coord, intersections);
            checkForVerticals(coord, intersections);
            checkForDiagonals(coord, intersections);
        }

        // All coordinates have been processed. Calculate the intersecting points
        sumUpIntersections(intersections);
    }
}
