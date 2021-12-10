import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
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

        Day5.part1(coords);
    }

    public static void part1(List<int[][]> coords){
        List<ArrayList<Integer>> intersections = new ArrayList<>();

        // NOT FLEXIBLE! What if I wanted to reuse this for smaller coordinates?
        for(int i = 0; i < 999; i++){
            ArrayList<Integer> y = new ArrayList<>();
            for(int j = 0; j < 999; j++){
                y.add(0);
            }
            intersections.add(y);
        }


        int j;
        for(int[][] coord : coords){
            // If the x coordinates are the same, it's a vertical line
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

            // If the y coordinates are the same, it's a horizontal line
            if(coord[0][1] == coord[1][1]){
                int y = coord[0][1];
                System.out.println("Horiz. line to be processed -> x1: " + coord[0][0] + " x2: " + coord[1][0] + " y1: " + coord[0][1] + " y2: " + coord[1][1]);
                for(int x = Math.min(coord[0][0], coord[1][0]); x <= Math.max(coord[0][0], coord[1][0]); x++){
                    j = intersections.get(y).get(x);
                    intersections.get(y).set(x, j + 1);
                }
            }
        }

        // All coordinates have been processed. Total up number of points with more than 1 line
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
}
