import java.text.DecimalFormat;
import java.util.Scanner;

import com.agopinath.lthelogutil.Fl;


public class SATVocabSampleGen implements SimpleProgram {
	@Override
	public void runProgram(Scanner input, String[] args) {
		if(args == null) {
			Fl.err("need args");
			return;
		}
		
		int lowerBound = Integer.parseInt(args[0]);
		int upperBound = Integer.parseInt(args[1]);
		int sampleSize = Integer.parseInt(args[2]);
		
		int numRight = 0;
		int populationSize = upperBound-lowerBound+1;
		
		for(int i = 1; i <= sampleSize; i++) {
			int toCheck = (int) (Math.random()*(populationSize) + lowerBound);
			System.out.println("Verify word number: " + toCheck);
			System.out.print("Was it correct? (y/n): ");
			String response = input.next();
			if(response.equalsIgnoreCase("y")) {
				numRight++;
			} else if(response.equalsIgnoreCase("e")) { // in case early exit is desired;
				printStats(numRight, i);
				return;
			}
			
			Fl.og("\n");
		}
		
		printStats(numRight, sampleSize);
	}
	
	private void printStats(int numRight, int sampleSize) {
		double proportionRight = (double)numRight / sampleSize;
		System.out.print("Words right out of Total: " + numRight + " out of " + sampleSize);
		System.out.print("Percent correct: " + DecimalFormat.getPercentInstance().format(proportionRight));
	}
}