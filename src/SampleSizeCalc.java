import java.util.Scanner;

import com.agopinath.lthelogutil.L;


public class SampleSizeCalc implements SimpleProgram {

	@Override
	public void runProgram(Scanner input, String[] args) {
		double lower = Double.parseDouble(args[0]);
		double upper = Double.parseDouble(args[1]);
		double critical = Double.parseDouble(args[2]);
		
		double expected = (lower+upper)/2;
		
		int sampleSize = (int) Math.round((Math.pow((Math.sqrt((expected)*(1d-expected)) / ((upper-expected)/critical)), 2)));
		
		L.og("Sample size for C.I [" + lower + ", " + upper + "] with critical value of " + critical + " is " + sampleSize);
	}
	
	@Override
	public String getInstructions() {
		String instructions = 
				"Use to calculate the sample size required for a given proportion confidence interval and critical value." +
				"\narg1 (double) = lower C.I. bound, arg2 (double) = upper C.I. bound, arg3 (double) = critical value";
						
		return instructions;
	}

	@Override
	public boolean isArgumentsRequired() {
		return true;
	}
}
