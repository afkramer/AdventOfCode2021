import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2Puzzle2 {
	public static void main(String[] args){
		int horizontal = 0;
		int depth = 0;
		int aim = 0;
		
		String[] text = new String[2];
		String direction = null;
		int val = 0;
		
		try{
			File directions = new File("20211202_Input_puzzle_2.txt");
			Scanner myReader = new Scanner(directions);
			
			while(myReader.hasNext()){
				text = myReader.nextLine().split(" ");
				direction = text[0];
				
				try{
					val = Integer.parseInt(text[1]);
				}catch(NumberFormatException ex){
					ex.printStackTrace();
				}
				
				/*
				down X increases your aim by X units.
				up X decreases your aim by X units.
				forward X does two things:
					It increases your horizontal position by X units.
					It increases your depth by your aim multiplied by X.
				*/
				
				if(direction.equals("forward")){
					//System.out.println("1, " + direction + ", " + val + ", " + horizontal + ", " + depth);
					horizontal = horizontal + val;
					depth = depth + (aim * val);
				}else if(direction.equals("down")){
					//System.out.println("2, " + direction + ", " + val + ", " + horizontal + ", " + depth);
					aim = aim + val;
				}else{
					//System.out.println("3, " + direction + ", " + val + ", " + horizontal + ", " + depth);
					aim = aim - val;
				}
			}
			
			System.out.println(horizontal + ", " + depth + ", " + horizontal * depth);
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
	}
}
