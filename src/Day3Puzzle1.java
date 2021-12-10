import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3Puzzle1 {

	public static void main(String[] args) throws FileNotFoundException{
		// read in the data
		File input = new File("20211203_Input_puzzle_3.txt");
		Scanner myReader = new Scanner(input);
		
		String text = null;
		
		int[] zeroes = new int[12];
		int[] ones = new int[12];
		
		while(myReader.hasNextLine()){
			text = myReader.nextLine();
			for (int i = 0; i < text.length(); i++){
				if(text.charAt(i) == '0'){
					zeroes[i]++;
				}else{
					ones[i]++;	
				}
			}
			/*
			System.out.println("Zeroes: " + zeroes[0] + "," + zeroes[1] + "," + zeroes[2] + "," + 
							   zeroes[3] + "," + zeroes[4] + "," +
							   zeroes[5] + "," + zeroes[6] + "," +
							   zeroes[7] + "," + zeroes[8] + "," +
							   zeroes[9] + "," + zeroes[10] + "," +
							   zeroes[11]);
				
			System.out.println("Ones: " + ones[0] + "," + ones[1] + "," + ones[2] + "," + 
							   ones[3] + "," + ones[4] + "," +
							   ones[5] + "," + ones[6] + "," +
							   ones[7] + "," + ones[8] + "," +
							   ones[9] + "," + ones[10] + "," +
							   ones[11]);
			*/
			
		}
		
		long gamma = 0;
		long epsilon = 0;
		long base = 1;
		
		for(int j = zeroes.length - 1; j >= 0; j--){
			if(zeroes[j] < ones[j]){
				gamma += 1 * base;
			}else{
				epsilon += 1 * base;	
			}
			System.out.println("gamma: " + gamma + " , epsilon: " + epsilon);
			base *= 10;
		}
		
		System.out.println(convertBinToDecimal(gamma) * convertBinToDecimal(epsilon));
		
	}
	
	
	
	public static Long convertBinToDecimal(Long binaryNum){
		// From: https://www.baeldung.com/java-binary-numbers
		Long decimal = 0L;
		int base = 1;
		
		while(binaryNum > 0){	
			Long lastDigit = binaryNum % 10;
			// Since it is an int, when divided by 10 there is no remainder
			binaryNum = binaryNum / 10;
			decimal = decimal + lastDigit * base;
			base = base * 2;
		}
		
		return decimal;
	}

}