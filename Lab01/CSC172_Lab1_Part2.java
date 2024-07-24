/*
Zhenhao Zhang  zzh133@u.rochester.edu 32277234 Lab Section 24
 */

import java.util.function.Function;

public class CSC172_Lab1_Part2 {

    public static void main(String[] args) {


        Character [] charArray = {'H','E','L', 'L', 'O' };


    // Question 6 Function Interface
        Function<Character[], Character> findMax  = (Character[] characters) -> { // Function interface <输入变量，返回值>
            Character max = characters[0];
            for (int i = 1; i < characters.length; i++) {
                if (characters[i].compareTo(max) > 0) {
                    max = characters[i];
                }
            }
            return max;
        };
        System.out.println(findMax .apply(charArray));
    }
}
