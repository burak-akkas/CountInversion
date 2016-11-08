import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	static int totalCount = 0;
	
	public static void main(String[] args) {
		
		// get args
		if(args.length == 2) {
			System.out.println("File path: " + args[1]);
			System.out.println("Processing file...");
			
			String seriePath = args[1];
			
			// read file
			int[] serie = readFile(seriePath);
			
			if(serie.length > 1) {
				System.out.println("Reading complete. \nCounting began...");
				
				// runtime analysis start
				double startTime = System.nanoTime(); 
				
				// calculate
				divideAndConquer(serie);
				
				// runtime analysis end
				double estimatedTime = (System.nanoTime() - startTime) / 1000000;
				
				// program completed
				System.out.println("Algorithm took " + estimatedTime + " milliseconds to complete.");
				System.out.println("Count of inversions: " + totalCount);
				
			} else {
				System.err.println("Dataset error: Need at least 2 integer to calculate.");
			}
		} else {
			System.err.println("Please specify arguments like following: count.jar -i \"/path/to/file/filename.txt\"");
		}
	}
	
	static int[] readFile(String path) {
		
		int[] serie = null;
		
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line = "";
			
			line = bufferedReader.readLine();
				
			// split current line by space character
			String row[] = line.split(" ");
			
			serie = new int[row.length];
			
			for (int i = 0; i < row.length; i++) {
				serie[i] = Integer.parseInt(row[i]);
			}
		
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return serie;
		
	}
	
	// recursive
	public static int[] divideAndConquer(int[] input) {
		int n = input.length;
		
		if (n == 1) {
			return input;
		}
		
		int mid = n / 2;
		int[] left = new int[mid];
		int[] right = new int[n - mid];
		
		System.arraycopy(input, 0, left, 0, left.length);
		System.arraycopy(input, left.length, right, 0, right.length);
		
		divideAndConquer(left);
		divideAndConquer(right);
		
		merge(left, right, input);
		
		return input;
	}

	public static void merge(int[] left, int[] right, int[] sorted) {
		
		int i = 0, j = 0, k = 0;
		
		while (i < left.length && j < right.length) {
			
			if (left[i] < right[j]) {
				sorted[k] = left[i];
				i++;
				
			} else {
				sorted[k] = right[j];
				j++;
				
				totalCount += left.length - i;
			}
			
			k++;
		}
		
		while (i < left.length) {
			sorted[k] = left[i];
			i++; k++;
		}
		
		while (j < right.length) {
			sorted[k] = right[j];
			j++; k++;
		}
	}
}
