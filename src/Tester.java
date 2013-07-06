import java.util.Scanner;

import com.agopinath.lthelogutil.L;


public class Tester {
	public static void main(String[] asdf) {
		Scanner s = new Scanner(System.in);
		L.ogp("Enter a class to run (separate arguments by a space): ");
		String input = s.nextLine().trim();
		
		boolean hasArgs = input.contains(" ");
		String className = hasArgs ? input.substring(0, input.indexOf(' ')) : input;
		String args = hasArgs ? input.substring(input.indexOf(' ')+1) : null;
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
		
		String[] arguments = null;
		if(args != null)
			arguments = args.contains(" ") ? args.split(" ") : new String[] {args};
		
		program.runProgram(s, arguments);
		s.close();
	}
}
