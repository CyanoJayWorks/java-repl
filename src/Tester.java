import java.util.Scanner;

import com.agopinath.lthelogutil.L;


public class Tester {
	public static void main(String[] asdf) {
		Scanner s = new Scanner(System.in);
		String input = null;
		do {
			L.ogp("Enter a class to run (separate arguments by a space): ");
			input = s.nextLine().trim();
		} while(checkForSpecialInput(input));
		
		
		boolean hasArgs = input.contains(" ");
		String className = hasArgs ? input.substring(0, input.indexOf(' ')) : input;
		String args = hasArgs ? input.substring(input.indexOf(' ')+1) : null;
		
		SimpleProgram program = getProgramInstance(className);
		
		String[] arguments = null;
		if(args != null)
			arguments = args.contains(" ") ? args.split(" ") : new String[] {args};
		
		program.runProgram(s, arguments);
		s.close();
	}

	private static boolean checkForSpecialInput(String input) {
		int classNameStart = input.indexOf(' ') + 1;
		if(classNameStart <= 0) return false;
		
		String className = input.substring(classNameStart);
		if(input.toLowerCase().startsWith("help")) {
			
			SimpleProgram program = getProgramInstance(className);
			
			String instruct = program.getInstructions() + "\n";
			L.og("USE: " + instruct);  
			return true;
		}
		
		return false;
	}
	
	private static SimpleProgram getProgramInstance(String className) {
		Class<?> toRun = null;
		try {
			toRun = Class.forName(className);
		} catch (ClassNotFoundException e) {
			L.err("Class not found: " + className);
		}
		
		if(!SimpleProgram.class.isAssignableFrom(toRun)) {
			L.err("Class does not implement SimpleProgram");
		}
		
		SimpleProgram program = null;
		try {
			program = (SimpleProgram) toRun.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			L.err("Error instantiating class");
			e.printStackTrace();
		}
		
		return program;
	}
}
