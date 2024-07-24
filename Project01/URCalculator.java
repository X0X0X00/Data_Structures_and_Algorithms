/*

Zhenhao Zhang
zzh133@u.rochester.edu
32277234

 */



import java.io.*;
import java.util.Scanner;

public class URCalculator {


	// Compare Test
	// AssertFileEquals, ignore blank lines
//	public static void assertFileEquals(String file1, String file2) {
//		try {
//			File f1 = new File(file1);
//			File f2 = new File(file2);
//			Scanner sc1 = new Scanner(f1);
//			Scanner sc2 = new Scanner(f2);
//			while(sc1.hasNextLine() && sc2.hasNextLine()) {
//				String line1 = sc1.nextLine();
//				String line2 = sc2.nextLine();
//				if(!line1.equals(line2)) {
//					throw new RuntimeException("Expected " + line1 + " but got " + line2);
//				}
//			}
//			sc1.close();
//			sc2.close();
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//	}


	public static void main(String[] args) {
		// Sample input: java URCalculator infix_expr_short.txt my_eval.txt
		// first we need to get the args

		if(args.length != 2) {
			throw new IllegalArgumentException("Usage: java URCalculator <input file> <output file>");
		}
		 String infix_expr_file = args[0];
		 String eval_output = args[1];
//		 String eval_output = "eval_output.txt";


		// read in from the infix_expr file one by one line
		// for each line, convert it to postfix
 		try {
 			File file = new File(infix_expr_file);
 			Scanner sc = new Scanner(file);
 			File output = new File(eval_output);
 			FileWriter fileWriter = new FileWriter(output);
 			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
 			while(sc.hasNextLine()) {
 				String infix_expr = sc.nextLine();
 				Expr expr = new Expr(infix_expr);
 				MyQueue<String> pofix = expr.infix2postfix();
 //				System.out.println(pofix.toString());
 				// print the result in .2f format
 				System.out.print(infix_expr + " = ");
 				System.out.printf("%.2f\n", expr.evaluate());
 				bufferedWriter.write(String.format("%.2f\n", expr.evaluate()));
 			}
 			sc.close();
 			bufferedWriter.close();

 		} catch (FileNotFoundException e) {
 			throw new RuntimeException(e);
 		} catch (IOException e) {
 			throw new RuntimeException(e);
 		}
//		assertFileEquals(my_eval_file, eval_output);


	}
}
