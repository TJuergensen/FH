package ADTStack;

import ADTStack.ListStack.ListStack;

public class PostFixInterpreter {

    private ListStack<Double> stack;
    private ListStack<Character> braceStack;
    private double tmp;

    public PostFixInterpreter() {
	stack = new ListStack<Double>();
	braceStack = new ListStack<Character>();
	tmp = 0;
    }

    public double interpret(String s) {
	for (int i = 0; i < s.length(); i++) {
	    if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
		stack.push((double) s.charAt(i) - '0');
	    } else if (s.charAt(i) == '+') {
		tmp = stack.poptop();
		stack.push(stack.poptop() + tmp);

	    } else if (s.charAt(i) == '-') {
		tmp = stack.poptop();
		stack.push(stack.poptop() - tmp);

	    } else if (s.charAt(i) == '*') {
		tmp = stack.poptop();
		stack.push(stack.poptop() * tmp);

	    } else if (s.charAt(i) == '/') {
		tmp = stack.poptop();
		stack.push(stack.poptop() / tmp);

	    } else if (s.charAt(i) == '!') {
		System.out.println("This would blow the complexity.");
	    } else if (s.charAt(i) == '^') {
		tmp = stack.poptop();
		stack.push(Math.pow(stack.poptop(), tmp));
//------------- Check Braces ------------------------------------------------------------------------------------------
	    } else if (s.charAt(i) == '(') {
		braceStack.push(s.charAt(i));
	    } else if (s.charAt(i) == '{') {
		braceStack.push(s.charAt(i));
	    } else if (s.charAt(i) == '[') {
		braceStack.push(s.charAt(i));
	    } else if (s.charAt(i) == ')') {
		if (braceStack.poptop() != '(')
		    throw new StackError("Braces are set incorrectly");
	    } else if (s.charAt(i) == '}') {
		if (braceStack.poptop() != '{')
		    throw new StackError("Braces are set incorrectly");
	    } else if (s.charAt(i) == ']') {
		if (braceStack.poptop() != '[')
		    throw new StackError("Braces are set incorrectly");
	    }
	}

	return stack.poptop();
    }

    public static void main(String[] args) {
	PostFixInterpreter interpreter = new PostFixInterpreter();
	//String calculation = "([])12+34^+";
	String calculation2 = "36/";
	System.out.println(interpreter.interpret(calculation2));
    }

}
