import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Puzzle2 {
	public static void main(String[] args){
		
		int[] depthArray;
		int depthA = 0;
		int depthB = 0;
		int increases = 0;
		
		try{
			File depths = new File("20211201_Input_puzzle_1.txt");
			Scanner myReader = new Scanner(depths);
			int listLength = 0;
			
			while(myReader.hasNextLine()){
				listLength++;
				myReader.nextLine();
			}
			
			depthArray = new int[listLength];
			myReader = new Scanner(depths);
			
			for (int i = 0; i < listLength; i++){
				try{
					depthArray[i] = Integer.parseInt(myReader.nextLine());
				}catch(NumberFormatException ex){
					ex.printStackTrace();
				}
			}
			
			for(int j = 0; j < depthArray.length - 3; j++){
				depthA = depthArray[j] + depthArray[j+1] + depthArray[j+2];
				depthB = depthArray[j+1] + depthArray[j+2] + depthArray[j+3];
				
				if(depthB > depthA){
					increases++;
				}
			}
			
			System.out.println(increases);
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
	}
}