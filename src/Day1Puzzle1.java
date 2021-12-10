import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Puzzle1 {
	public static void main(String[] args){
		
		int depthNew = 0;
		int depthOld = 0;
		int increases = 0;
		
		try {
			File depths = new File("20211201_Input_puzzle_1.txt");
			Scanner myReader = new Scanner(depths);
			
			while (myReader.hasNextLine()){
				try{
					depthNew = Integer.parseInt(myReader.nextLine());	
				} catch(NumberFormatException ex){
					ex.printStackTrace();	
				}
				
				if(depthNew > depthOld && depthOld != 0){
					increases++;	
				}
				
				depthOld = depthNew;
			}
			
			myReader.close();
			System.out.println(increases);
			
		} catch(FileNotFoundException ex){
			ex.printStackTrace();	
		}
	}
}