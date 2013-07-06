import java.text.DecimalFormat;
import java.util.Scanner;

import com.agopinath.lthelogutil.L;


public class VocabSampleCalc implements SimpleProgram {
	@Override
	public void runProgram(Scanner input, String[] args) {
		int lowerBound = Integer.parseInt(args[0]);
		int upperBound = Integer.parseInt(args[1]);
		int sampleSize = Integer.parseInt(args[2]);
		
		int numRight = 0;
		int populationSize = upperBound-lowerBound+1;
		
		for(int i = 1; i <= sampleSize; i++) {
			int toCheck = (int) (Math.random()*(populationSize) + lowerBound);
			L.og(i + ") Verify word number: " + toCheck);
			L.ogp("Was it correct? (y/n): ");
			String response = input.next();
			if(response.equalsIgnoreCase("y")) {
				numRight++;
			} else if(response.equalsIgnoreCase("e")) { // in case early exit is desired;
				printStats(numRight, i);
				return;
			}
			
			L.ln();
		}
		
		printStats(numRight, sampleSize);
	}
	
	private void printStats(int numRight, int sampleSize) {
		double proportionRight = (double)numRight / sampleSize;
		L.og("Words right out of Total: " + numRight + " out of " + sampleSize);
		L.ogp("Percent correct: " + DecimalFormat.getPercentInstance().format(proportionRight));
	}

	@Override
	public String getInstructions() {
		String instructions = 
				"Use to generate a random sample of vocab word line numbers to be used with a " +
				"wordlist.\narg1 (int) = lower bound for line #, arg2 (int) = upper bound for line#, arg3 (int) = sample size";
						
		return instructions;
	}
	
	@Override
	public boolean isArgumentsRequired() {
		return true;
	}
}
