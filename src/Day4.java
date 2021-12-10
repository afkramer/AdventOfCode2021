import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        // Set up the scanner to read in the file
        File input = new File("/home/adri/Documents/Programming/Java/AdventOfCode2021/Inputs/20211204_Input_puzzle_4.txt");
        Scanner myReader = new Scanner(input);

        // Read in the first line and save the comma separated list as an Array and convert to a list
        String[] stringNums = myReader.nextLine().split(",");
        List<Integer> callNums = new ArrayList<>();

        // Convert the Array of strings into integers
        for (String num : stringNums) {
            callNums.add(Integer.parseInt(num));
        }

        // Set up each bingo board as an arrayList of arrays
        ArrayList<int[][]> boards = new ArrayList<>();

        while (myReader.hasNextInt()) {
            int[][] board = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = myReader.nextInt();
                    //System.out.println("i: " + i + ", j: " + j + ", val: " + board[i][j]);
                }
            }
            // add the full board to the array of boards
            boards.add(board);
        }
        //System.out.println(callNums);
        //System.out.println(boards);

        //Day4.part1(callNums, boards);
        Day4.part2(callNums, boards);
    }

    public static void part1(List<Integer> callNums, ArrayList<int[][]> boards) throws FileNotFoundException {
        // TIME TO PLAY BINGO -- this time we want to win!

        // Set up the flag to determine if we have a winning board
        boolean flag = false;

        // Keep track of which board is the winner to double check if it's plausible
        int boardNum;

        // loop through the call nums
        for (Integer num : callNums) {
            // Don't continue if a board has already been identified as the winner
            if (flag == true) {
                break;
            }

            boardNum = 0;
            //System.out.println("Num called: " + num);
            // loop through the boards to see if the number was in the board
            for (int[][] board : boards) {
                // loop through the rows
                for (int q = 0; q < 5; q++) {
                    // Loop through the columns
                    for (int r = 0; r < 5; r++) {
                        // If the number matches,
                        if (board[q][r] == num) {
                            //System.out.println("Board number: " + boardNum + " has number called: " + num);
                            board[q][r] = -1;
                        }
                    }
                }

                // check if this is the winning board
                // Check the rows to see if a whole row is -1
                for (int k = 0; k < 5; k++) {
                    if (board[k][0] == -1 && board[k][1] == -1 && board[k][2] == -1 && board[k][3] == -1 && board[k][4] == -1) {
                        //System.out.println(board[k][0] + " " + board[k][1] + " " + board[k][2] + " " + board[k][3] + " " + board[k][4]);
                        flag = true;
                    }
                }

                for (int m = 0; m < 5; m++) {
                    if (board[0][m] == -1 && board[1][m] == -1 && board[2][m] == -1 && board[3][m] == -1 && board[4][m] == -1) {
                        //System.out.println(board[0][m] + " " + board[1][m] + " " + board[2][m] + " " + board[3][m] + " " + board[4][m]);
                        flag = true;
                    }
                }

                // If the flag is true, we have a winning board
                if (flag == true) {
                    //System.out.println("Board num: " + boardNum);
                    int sum = 0;
                    for (int n = 0; n < 5; n++) {
                        for (int p = 0; p < 5; p++) {
                            //System.out.print(board[n][p] + " ");

                            if (board[n][p] > 0) {
                                //System.out.println("Adding num: " + board[i][j]);
                                sum += board[n][p];
                            }
                        }
                        //System.out.print("\n");
                    }
                    //System.out.println("Sum of nums: " + sum);
                    //System.out.println("Number called: " + num);
                    sum *= num;

                    //System.out.println("Final score: " + sum);
                    break;
                }

                boardNum++;
            }
        }
    }

    public static void part2(List<Integer> callNums, ArrayList<int[][]> boards){
        // TIME TO PLAY BINGO! This time we want to find the score of the losing card

        int boardNum;

        // Make sure not to count the same board twice
        boolean flag = false;

        // Every time a board wins, we will decrement this
        int boardsLeft = boards.size();
        System.out.println("Number of boards: " + boards.size());

        // Create an array list to keep track of which boards have already won
        ArrayList<Boolean> winningBoards = new ArrayList<>();
        for(int i = 0; i < boards.size(); i++){
            winningBoards.add(false);
        }

        // loop through the call nums
        for (Integer num : callNums) {
            // Don't continue calling numbers if the last board has finally "won"
            if (boardsLeft == 0) {
                break;
            }

            boardNum = 0;

            System.out.println("Num called: " + num);
            // loop through the boards to see if the number was in the board
            for (int[][] board : boards) {

                // Only work with the board if it hasn't won yet
                if (winningBoards.get(boardNum) == false) {

                    // loop through the rows
                    for (int q = 0; q < 5; q++) {
                        // Loop through the columns
                        for (int r = 0; r < 5; r++) {
                            // If the number matches, set the number to -1
                            if (board[q][r] == num) {
                                System.out.println("Board number: " + boardNum + " has number called: " + num);
                                //System.out.println("Check board output: " + board[q][r]);
                                board[q][r] = -1;
                            }
                        }
                    }

                    // check if this board is a winner and can be taken out of the list
                    // Check the rows to see if a whole row is -1
                    for (int k = 0; k < 5; k++) {
                        if (board[k][0] == -1 && board[k][1] == -1 && board[k][2] == -1 && board[k][3] == -1 && board[k][4] == -1) {
                            //System.out.println(board[k][0] + " " + board[k][1] + " " + board[k][2] + " " + board[k][3] + " " + board[k][4]);
                            winningBoards.set(boardNum, true);
                            flag = true;
                            System.out.println("Winning boards count: " + winningBoards);
                            boardsLeft--;
                            System.out.println("Boards left: " + boardsLeft);
                        }
                    }

                    // If the board already won, we shouldn't count it again
                    // Only check if the columns are all -1 if the row count was negative
                    if(flag == false){
                        for (int m = 0; m < 5; m++) {
                            if (board[0][m] == -1 && board[1][m] == -1 && board[2][m] == -1 && board[3][m] == -1 && board[4][m] == -1) {
                                // System.out.println(board[0][m] + " " + board[1][m] + " " + board[2][m] + " " + board[3][m] + " " + board[4][m]);
                                winningBoards.set(boardNum, true);
                                System.out.println("Winning boards count: " + winningBoards);
                                boardsLeft--;
                                System.out.println("Boards left: " + boardsLeft);
                            }
                        }
                    }

                    // reset the flag for the next board
                    flag = false;

                    // When we reach 0 boards left, it means that we are on the last board, it finally "won", and we can calculate the score
                    if (boardsLeft == 0) {
                        System.out.println("Last board num: " + boardNum);
                        int sum = 0;
                        for (int n = 0; n < 5; n++) {
                            for (int p = 0; p < 5; p++) {
                                System.out.print(board[n][p] + " ");

                                if (board[n][p] > 0) {
                                    System.out.println("Adding num: " + board[n][p]);
                                    sum += board[n][p];
                                }
                            }
                            System.out.print("\n");
                        }
                        System.out.println("Sum of nums: " + sum);
                        System.out.println("Number called: " + num);
                        sum *= num;

                        System.out.println("Final score: " + sum);

                        // Stop looping through boards
                        break;
                    }
                }

                boardNum++;
            }
        }
    }
}
