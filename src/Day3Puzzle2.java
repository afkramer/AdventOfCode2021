import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Day3Puzzle2 {

	public static void main(String[] args) throws IOException  {
		Path inputPath = Paths.get("/home/adri/Documents/Programming/Java/AdventOfCode2021/20211203_Input_puzzle_3.txt");
		List<String> mostUsed = Files.readAllLines(inputPath,StandardCharsets.UTF_8);
		ArrayList<String> leastUsed = new ArrayList<>(mostUsed);

		String oxygen = Day3Puzzle2.reduceList(mostUsed, "most");
		String co2 = Day3Puzzle2.reduceList(leastUsed, "least");

		System.out.println("oxygen bin: " + oxygen + ", co2 bin: " + co2);
		System.out.println("oxygen dec: " + Day3Puzzle2.convertBinToDecimal(Long.parseLong(oxygen)) + ", co2 dec: " + Day3Puzzle2.convertBinToDecimal(Long.parseLong(co2)));
		System.out.println("life support: " + Day3Puzzle2.convertBinToDecimal(Long.parseLong(oxygen)) * Day3Puzzle2.convertBinToDecimal(Long.parseLong(co2)));
	}

	public static String reduceList(List<String> nums, String typeFlag){
		int numZeroes;
		int numOnes;
		char flag;

		// Loop through each digit of the binary number
		for (int i = 0; i < nums.get(0).length(); i++) {
			numZeroes = 0;
			numOnes = 0;

			// If the list is 1 then we have our number
			if (nums.size() == 1) {
				break;
			}

			// Loop through the array and count the number of ones and zeros at index i
			for (int j = 0; j < nums.size(); j++) {
				if (nums.get(j).charAt(i) == '0') {
					numZeroes++;
				} else {
					numOnes++;
				}
				System.out.println("i = " + i + ", j = " + j + ", binary: " + nums.get(j) + ", character: " + nums.get(j).charAt(i)
								+ ", numZeroes = " + numZeroes + ", numOnes = " + numOnes);
			}

			if(typeFlag.equals("most")) {
				if (numOnes >= numZeroes) {
					flag = '1';
				} else {
					flag = '0';
				}
			}else{
				if(numZeroes <= numOnes){
					flag = '0';
				}else{
					flag = '1';
				}
			}
			System.out.println("flag = " + flag);

			// According to the following website, it is recommended to use an iterator for deleting elements
			// https://www.geeksforgeeks.org/iterating-arraylists-java/

			Iterator<String> itr = nums.iterator();
			while (itr.hasNext()) {
				if (itr.next().charAt(i) != flag) {
					itr.remove();
				}
			}
		}

		System.out.println(nums);
		return nums.get(0);
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
	/*
	First idea was to override how Strings are compared and then do a kind of binary search
	But the String class is final so it's not possible to override the compare method

	class BinaryCompare implements Comparator<String>{
		@Override
		public int compare(String one, String two, int index){
			//return one.charAt(index).toString().compareTo(two.charAt(index).toString());
			return Character.toString(one.charAt(index)).compareTo(Character.toString(two.charAt(index)));
		}
	}

	public static void main(String[] args){
		new Day3Puzzle2().go();
	}

	public void go(){
		ArrayList<String> testNums = new ArrayList<>(Arrays.asList("10000", "01001", "10101", "00000", "10111"));
		BinaryCompare bc = new BinaryCompare();
		Collections.sort(testNums, bc);
		System.out.println(testNums);
	}
	*/
}