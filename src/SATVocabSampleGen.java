import java.text.DecimalFormat;
import java.util.Scanner;

import com.agopinath.lthelogutil.L;


public class SATVocabSampleGen implements SimpleProgram {
	@Override
	public void runProgram(Scanner input, String[] args) {
		if(args == null) {
			L.err("need args");
			return;
		}
		
		int lowerBound = Integer.parseInt(args[0]);
		int upperBound = Integer.parseInt(args[1]);
		int sampleSize = Integer.parseInt(args[2]);
		
		int numRight = 0;
		int populationSize = upperBound-lowerBound+1;
		
		for(int i = 1; i <= sampleSize; i++) {
			int toCheck = (int) (Math.random()*(populationSize) + lowerBound);
			L.og("Verify word number: " + toCheck);
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
		L.ogp("Words right out of Total: " + numRight + " out of " + sampleSize);
		L.ogp("Percent correct: " + DecimalFormat.getPercentInstance().format(proportionRight));
	}
}
