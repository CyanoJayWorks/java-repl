import java.lang.reflect.Modifier;
import java.util.Scanner;

import com.agopinath.lthelogutil.L;


public class RunnerConsole {
	private Scanner s;
	
	public RunnerConsole() {
		s = new Scanner(System.in);
	}
	
	public static void main(String[] asdf) {
		RunnerConsole console = new RunnerConsole();
		console.start();
	}
	
	private void start() {
		String input = null;
		do {
			L.ogp(" >> ");
			input = s.nextLine().trim();
		} while(parseInput(input));
	}
	
	private boolean parseInput(String input) {
		int bodyStart = input.indexOf(' ') + 1;
		
		String command = (bodyStart <= 0) ? input : input.substring(0, bodyStart-1).trim();
		String body = (bodyStart <= 0) ? null : input.substring(bodyStart).trim();
		
		switch(command.toLowerCase()) {
		case "run":
			runProgram(body);
			break;
		case "help":
			getHelp(body);
			break;
		case "exit":
		case "q":
			return false;
		}
		
		return true;
	}
	
	private void getHelp(String body) {
		String[] data = parseBody(body);
		SimpleProgram program = getProgramInstance(data[0]);
		if(program == null) return;
		
		String instruct = program.getInstructions() + "\n";
		L.og("USE: " + instruct);  
	}

	private  void runProgram(String body) {
		String[] data = parseBody(body);
		SimpleProgram program = getProgramInstance(data[0]);
		if(program == null) return;
		
		String[] arguments = null;
		if(data[1] != null)
			arguments = data[1].contains(" ") ? data[1].split(" ") : new String[] {data[1]};
		
		program.runProgram(s, arguments);
	}
	
	private String[] parseBody(String body) {
		String[] data = new String[2];
		int argsIdx = body.indexOf(' ');
		
		data[0] = (argsIdx > 0) ? body.substring(0, argsIdx) : body;
		data[1] = (argsIdx > 0) ? body.substring(argsIdx+1) : null;
		
		return data;
	}
	
	private SimpleProgram getProgramInstance(String className) {
		Class<?> toRun = null;
		try {
			toRun = Class.forName(className);
		} catch (ClassNotFoundException e) {
			L.err("Class not found: " + className);
			return null;
		}
		
		if(!SimpleProgram.class.isAssignableFrom(toRun)) {
			L.err("Class does not implement SimpleProgram");
			return null;
		}
		
		if(toRun.isInterface() || Modifier.isAbstract(toRun.getModifiers())) {
			L.err("Class cannot be instiantiated");
			return null;
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
