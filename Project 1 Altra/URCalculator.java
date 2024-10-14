import java.io.*;
import java.util.*;
import java.util.Queue;
import java.util.Scanner;


//        • Addition (+)
//        • Subtraction (-)
//        • Multiplication (*) • Division (/)
//        • Exponentiation (^) • Equal to (=)
//        • Less than (<)
//        • Greater than (>)
//        • Logical AND (&)
//        • Logical OR (|)
//        • Logical NOT (!)
//        • Parentheses (())
//        For extra credit,you may add additional operations like • Modulo (%)
//        • Sin (sin)
//        • Cin (cos)
//        • Tin (tan)


public class URCalculator {


    // Operator precedence map 判断优先级
    private static final Map<String, Integer> OPERATOR_PRECEDENCE = Map.ofEntries(

            Map.entry("+", 1),
            Map.entry("-", 1),
            Map.entry("*", 2),
            Map.entry("/", 2),
            Map.entry("^", 3),
            Map.entry("<", 4),
            Map.entry(">", 4),
            Map.entry("=", 4),
            Map.entry("&", 5),
            Map.entry("|", 5),
            Map.entry("!", 6),
            Map.entry("sin", 7),
            Map.entry("cos", 7),
            Map.entry("tan", 7)
    );


    /***
     InFix_2_PostFix
     The shunting-yard algorithm works by considering each “token” (operand or operator) from an infix expression and taking the appropriate action:
     1. If the token is an operand, enqueue it.
     2. If the token is a close-parenthesis [‘)’], pop all the stack elements and enqueue them one by one until an open-parenthesis [‘(‘] is found.
     3. If the token is an operator, pop every token on the stack and enqueue them one by one until you reach either an operator of lower precedence, or a right-associative operator of equal precedence (e.g. the logical NOT and the exponential are right-associative operators). Enqueue the last operator found, and push the original operator onto the stack.
     4. At the end of the input, pop every token that remains on the stack and add them to the queue one by one.
     The queue now holds the converted postfix expression and can be passed onto the postfix calculator for evaluation.
     */



    // infixToPostfix 堆栈
    public static URQueue<String> infixToPostfix(String[] tokens) {

        URStacks<String> operators = new URStacks<>();
        URQueue<String> postfix = new URQueue<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                postfix.enqueue(token);
            } else if (isFunction(token)) {
                operators.push(token);
            } else if (token.equals("-") && (i == 0 || tokens[i - 1].equals("("))) {
                if (i + 1 < tokens.length && isNumber(tokens[i + 1])) {
                    postfix.enqueue("-" + tokens[i + 1]);
                    i++;
                } else {
                    throw new IllegalArgumentException("Invalid expression: unary minus not followed by a number.");
                }
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && !(operators.peek().equals("(")) && isHigherPrecedence(operators.peek(), token)) {
                    postfix.enqueue(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    postfix.enqueue(operators.pop());
                }
                if (!operators.isEmpty()) {
                    operators.pop();  // Pop the '('
                }
            }

        }
        while (!operators.isEmpty()) {
            postfix.enqueue(operators.pop());
        }
        return postfix;
    }





    /***
     PostFix Evaluation
     With your postfix expression stored in the queue, the next step is to evaluate it. The algorithm for evaluating a postfix expression proceeds as follows:
     1. Get the token at the front of the queue.
     2. If the token is an operand, push it onto the stack.
     3. If the token is an operator, pop the appropriate number of operands from the stack (e.g. 2 operands for multiplication, 1 for logical NOT). Perform the operation on the popped operands, and push the resulting value onto the stack.
     Repeat steps 1-3 until the queue is empty. When it is, there should be a single value in the stack – that value is the result of the expression.
     */


    // evaluatePostfix
    public static double evaluatePostfix(URQueue<String> postfix) {
        URStacks<Double> stack = new URStacks<>();

        while (!postfix.isEmpty()) {
            String token = postfix.dequeue();
            if (isNumber(token)) {
                stack.push(Double.valueOf(token));
            } else if (isFunction(token)) {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression: not enough operands for function " + token);
                }
                double num = stack.pop();
                switch (token) {
                    case "sin": stack.push(Math.sin(Math.toRadians(num))); break;
                    case "cos": stack.push(Math.cos(Math.toRadians(num))); break;
                    case "tan": stack.push(Math.tan(Math.toRadians(num))); break;
                    default: throw new IllegalArgumentException("Invalid function: " + token);
                }
            } else {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression: not enough operands for operator " + token);
                }
                double num2 = (token.equals("!")) ? 0 : stack.pop();
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression: not enough operands for operator " + token);
                }
                double num1 = stack.pop();
                switch (token) {
                    case "+": stack.push(num1 + num2); break;
                    case "-": stack.push(num1 - num2); break;
                    case "*": stack.push(num1 * num2); break;
                    case "/": stack.push(num1 / num2); break;
                    case "^": stack.push(Math.pow(num1, num2)); break;
                    case "=": stack.push((num1 == num2) ? 1.0 : 0.0); break;
                    case "<": stack.push((num1 < num2) ? 1.0 : 0.0); break;
                    case ">": stack.push((num1 > num2) ? 1.0 : 0.0); break;
                    case "&": stack.push(((num1 != 0) && (num2 != 0)) ? 1.0 : 0.0); break;
                    case "|": stack.push(((num1 != 0) || (num2 != 0)) ? 1.0 : 0.0); break;
                    case "!": stack.push((num1 == 0) ? 1.0 : 0.0); break;
                    default: throw new IllegalArgumentException("Invalid operator: " + token);
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: More or fewer values left on the stack after evaluation.");
        }

        return stack.pop();
    }





    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private static boolean isOperator(String token) {
        return OPERATOR_PRECEDENCE.containsKey(token) && !isFunction(token);
    }


    private static boolean isFunction(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan");
    }


    private static boolean isHigherPrecedence(String op1, String op2) {
        return OPERATOR_PRECEDENCE.get(op1) >= OPERATOR_PRECEDENCE.get(op2);
    }


    // 判断是不是operator
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'
                || c == '=' || c == '<' || c == '>'
                || c == '&' || c == '|' || c == '!'
                || c == '%'
                || c == 's' || c == 'i' || c == 'n'
                || c == 'c' || c == 'o'
                || c == 't' || c == 'a' ;

    }


    private static boolean isParenthesis(char c) {
        return c == '(' || c ==  ')';
    }






    // tokenize 存放分割后返回
    public static  String [] tokenize(String expression){
        URArrayList<String> tokens = new URArrayList<String>();

        int lastCharType = -1; // 0: digit 1: operator 2: parenthesis -1: initial

        StringBuilder currentToken = new StringBuilder();

        // 正常数字
        for (int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);
            if (Character.isDigit(c)||c == '.'){
                // eg. 1.5
                if (lastCharType != 0 && lastCharType != -1){
                    if (!currentToken.isEmpty()){
                        tokens.add(currentToken.toString());
                        currentToken.setLength(0);
                    }
                }
                currentToken.append(c);
                lastCharType = 0;
                continue;
            }

            // 运算符号
            if(isOperator(c)){
                if (lastCharType != 1 && lastCharType != -1){
                    if (!currentToken.isEmpty()){
                        tokens.add(currentToken.toString());
                        currentToken.setLength(0);
                    }
                }
                currentToken.append(c);
                lastCharType = 1;
                continue;
            }

            // 括号
            if(isParenthesis(c)){
                if (!currentToken.isEmpty()){
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(Character.toString(c));
                currentToken.setLength(0);
                lastCharType = 2;
                continue;
            }

            // 空格
            if (Character.isWhitespace(c)){
                if (!currentToken.isEmpty()){
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                lastCharType = -1;
                continue;
            }

            throw new InputMismatchException("Invalid character: " + c + " at position " + i + " in " + expression);
        }

        if(!currentToken.isEmpty()){
            tokens.add(currentToken.toString());
            currentToken.setLength(0);
        }
        Object[] arr = tokens.toArray();
        String[] res = new String[arr.length];
        for(int i = 0; i < arr.length; i++) {
            res[i] = (String) arr[i];
        }

        return res;
    }




    // main function
    public static void main(String[] args) {

        if(args.length != 2) {
            throw new IllegalArgumentException("Usage: java URCalculator <input file> <output file>");
        }

        String infix_expr_file = args[0];
        String outputfile = args[1];
//        String outputfile = "my_eval.txt";


        try {
            File file = new File(infix_expr_file);
            Scanner sc = new Scanner(file);
            File output = new File(outputfile);
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while(sc.hasNextLine()){
                String infix_expr = sc.nextLine();
                double result = URCalculator.evaluatePostfix(URCalculator.infixToPostfix(URCalculator.tokenize(infix_expr)));
                bufferedWriter.write(String.format("%.2f\n",result));
            }
            sc.close();
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}


//        try (BufferedReader br = new BufferedReader(new FileReader(""))) {
//            String expression;
//            while ((expression = br.readLine()) != null) {
//                if (!expression.trim().isEmpty()) {
//                    String [] tokens = tokenize(expression);
//                    Queue<String> postfix = infixToPostfix(tokens);
//                    double result = evaluatePostfix(postfix);
//                    // System.out.println(expression + " = " + result);
//                    System.out.println(result);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





    // Test
    // only for 1 - (4 + 5)
//    public static void main(String[] args) {
//        String expression = "1 - (4 + 5)";
//        String [] tokens = tokenize(expression);
//        System.out.println(Arrays.toString(tokens));
//        Queue<String> postfix = infixToPostfixWithDebug(tokens);
//        System.out.println("Postfix: " + postfix);
//        double result = evaluatePostfix(postfix);
//        System.out.println(expression + " = " + result);
//    }



