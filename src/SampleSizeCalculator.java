import java.util.Scanner;


public class SampleSizeCalculator implements SimpleProgram {

	@Override
	public void runProgram(Scanner input, String[] args) {
		
	}
	
	@Override
	public String getInstructions() {
		String instructions = 
				"Use to calculate the sample size required for a given proportion confidence interval and critical value." +
				"\n arg1 = lower C.I. bound, arg2 = upper C.I. bound, arg3 = critical value";
						
		return instructions;
	}
}
