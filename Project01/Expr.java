/*

Zhenhao Zhang
zzh133@u.rochester.edu
32277234

 */



import java.util.InputMismatchException;

public class Expr {
	public MyList<String> tokens;
	public Expr() {

	}
	public Expr(String infix_expr) {
		// read in the infix expression and store it
		// first split the expression into tokens
		// then store the tokens in a list
		// then convert the list to an array
		// then store the array
		tokens = tokenize(infix_expr);
	}

	public MyQueue<String> infix2postfix() {
		// use the Shunting yard algorithm to convert to postfix
		MyQueue<String> outputQueue = new MyQueue<String>(); // store the results
		MyStack<String> operatorStack = new MyStack<String>(); // store the operators
		// traverse the tokens
		for(int i = 0; i < tokens.size(); i++) {
			String current = tokens.get(i);
//		1. If the token is an operand, enqueue it.
			if(isOperand(current)) {
				outputQueue.enqueue(current);
				continue;
			}
//		2. If the token is a close-parenthesis [)], pop all the stack elements and enqueue them one by one until an open-parenthesis [(] is found.
			if(isClosePar(current)) {
				while(!operatorStack.isEmpty() && !isOpenPar(operatorStack.peek())) {
					outputQueue.enqueue(operatorStack.pop());
				}
				// pop the open parenthesis
				operatorStack.pop();
				continue;
			}
//		3. If the token is an operator, pop every token on the stack and enqueue them one by one until you reach either an operator of lower precedence,
//		or a right-associative operator of equal precedence (e.g. the logical NOT and the exponential are right-associative operators).
//		Enqueue the last operator found, and push the original operator onto the stack.
			if(isOperator(current)) {
				while(!operatorStack.isEmpty() && !isOpenPar(operatorStack.peek())) {
					String top = operatorStack.peek();
					if(isOperator(top)) {
						// check the precedence
						if(getPrecedence(current) > getPrecedence(top)) {
							break;
						}
						if(getPrecedence(current) == getPrecedence(top) && isRightAssociative(current)) {
							break;
						}
					}
					outputQueue.enqueue(operatorStack.pop());
				}
				operatorStack.push(current);
				continue;
			}

		}


//		4. At the end of the input, pop every token that remains on the stack and add them to the queue one by one.
		while(!operatorStack.isEmpty()) {
			outputQueue.enqueue(operatorStack.pop());
		}
		// return the queue
		return outputQueue;
	}

	public static MyList<String> tokenize(String input) {
		MyList<String> tokens = new MyList<String>();

		StringBuilder currentToken = new StringBuilder();
		// handle non-whitespace characters
		int lastCharType = -1; // 0: digit, 1: operator, 2: parenthesis, -1: initial

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			// if current char is different from last char type, then split
			if (Character.isDigit(c) || c == '.') {
				// handle the number, including xx.x
				if (lastCharType != 0 && lastCharType != -1) {
					// split
					if (!currentToken.isEmpty()) {
						tokens.add(currentToken.toString());
						currentToken.setLength(0); // reset the length
					}
				}
				lastCharType = 0;
				currentToken.append(c);
				continue;

			}

			if (isOperator(c)) {
				if (lastCharType != 1 && lastCharType != -1) {
					// split
					if (!currentToken.isEmpty()) {
						tokens.add(currentToken.toString());
						currentToken.setLength(0); // reset the length
					}
				}
				// operator might be 'sin', 'cos' and length > 1
				currentToken.append(c);
				lastCharType = 1;
				continue;
			}
			if (isParenthesis(c)) {
				// handle the rest in the stringbuilder
				if (!currentToken.isEmpty()) {
					tokens.add(currentToken.toString());
					currentToken.setLength(0); // reset the length
				}
				// Parenthesis: length can only be 1
				tokens.add(Character.toString(c));
				lastCharType = 2;
				continue;
			}
			if (Character.isWhitespace(c)) {
				// either operator or operand must have no whitespace!
				if (!currentToken.isEmpty()) {
					tokens.add(currentToken.toString());
					currentToken.setLength(0); // reset the length
				}
				lastCharType = -1;
				continue;
			}
			// other characters are not allowed
			throw new InputMismatchException("Invalid character: " + c + " at position " + i + " in " + input);
		}

		// last token
		if (!currentToken.isEmpty()) {
			tokens.add(currentToken.toString());
		}

		return tokens;
	}



	public double postfixEval(MyQueue<String> postfix) {
		MyStack<Double> stack = new MyStack<Double>();
		while(!postfix.isEmpty()) {
			String current = postfix.dequeue();
			if(isOperand(current)) {
				stack.push(str2num(current));
				continue;
			}
			if(isOperator(current)) {
				// get the number of operands
				int count = operandCount(current);
				// pop the operands
				double num1 = stack.pop();
				double num2 = 0;
				if(count == 2) {
					num2 = stack.pop();
				}
				double result = 0;
				switch(current) {
					case "+":
						result = num2 + num1;
						break;
					case "-":
						result = num2 - num1;
						break;
					case "*":
						result = num2 * num1;
						break;
					case "/":
						result = num2 / num1;
						break;
					case "^":
						result = Math.pow(num2, num1);
						break;
					case "=":
						result = num2 == num1 ? 1 : 0;
						break;
					case "<":
						result = num2 < num1 ? 1 : 0;
						break;
					case ">":
						result = num2 > num1 ? 1 : 0;
						break;
					case "&":
						result = num2 == 1 && num1 == 1 ? 1 : 0;
						break;
					case "|":
						result = num2 == 1 || num1 == 1 ? 1 : 0;
						break;
					case "!":
						result = num1 == 1 ? 0 : 1;
						break;
					case "%":
						result = num2 % num1;
						break;
					case "sin":
						result = Math.sin(num1);
						break;
					case "cos":
						result = Math.cos(num1);
						break;
					case "tan":
						result = Math.tan(num1);
						break;
				}
				stack.push(result);
			}
		}
		return stack.pop();
	}

	public double evaluate() {
		// evaluate the expression
		// first convert to postfix
		MyQueue<String> pofix = this.infix2postfix();
		// then evaluate the postfix
		return this.postfixEval(pofix);
	}

	public static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'
				|| c == '=' || c == '<' || c == '>'
				|| c == '&' || c == '|' || c == '!'
				|| c == '%'
				|| c == 's' || c == 'i' || c == 'n'
				|| c == 'c' || c == 'o'
				|| c == 't' || c == 'a' ;

	}

	public static boolean isParenthesis(char c) {
		return c == '(' || c == ')';
	}

	public static boolean isClosePar(String c) {
		return c.equals(")");
	}

	public static boolean isOpenPar(String c) {
		return c.equals("(");
	}

	public static boolean isOperator(String str) {
		char c = str.charAt(0);
		return isOperator(c) || isParenthesis(c);
	}

	public static boolean isOperand(String str) {
		return !isOperator(str);
	}

	public static boolean isRightAssociative(String str) {
		return str.equals("^") || str.equals("!");
	}

	public static int getPrecedence(String str) {
		if(str.equals("|")) {
			return 0;
		}
		if(str.equals("&") ) {
			return 1;
		}
		if(str.equals("=")) {
			return 2;
		}
		if(str.equals("<") || str.equals(">")) {
			return 3;
		}
		if(str.equals("!") || str.equals("+") || str.equals("-")) {
			return 4;
		}
		if(str.equals("*") || str.equals("/") || str.equals("%")) {
			return 5;
		}
		if(str.equals("^")) {
			return 6;
		}
		if(str.equals("s") || str.equals("c") || str.equals("t")) {
			return 7;
		}

		if(str.equals("(")) {
			return Integer.MAX_VALUE;
		}
		return 0;
	}

	public static int operandCount(String str) {
		if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("^") || str.equals("=") || str.equals("<") || str.equals(">") || str.equals("&") || str.equals("|") || str.equals("%")) {
			return 2;
		}
		if(str.equals("!") || str.equals("sin") || str.equals("cos") || str.equals("tan")) {
			return 1;
		}
		return 0;
	}
	public static double str2num(String number) {
		return Double.parseDouble(number);
	}
}

// Valid operators:
//  Addition (+)
//  Subtraction (-)
//  Multiplication (*)
//  Division (/)
//  Exponentiation (^)
//  Equal to (=)
//  Less than (<)
//  Greater than (>)
//  Logical AND (&)
//  Logical OR (|)
//  Logical NOT (!)
//  Parentheses (())
//For extra credit,you may add additional operations like
//  Modulo (%)
//  Sin (sin)
//  Cin (cos)
//  Tin (tan)
