import java.util.Scanner;



public interface SimpleProgram {
	public boolean isArgumentsRequired();
	public String getInstructions();
	public void runProgram(Scanner input, String[] args);
}
