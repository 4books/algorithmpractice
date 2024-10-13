package leetcode.string;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses s = new ValidParentheses();
        boolean valid = s.isValid("()[]{}");
        System.out.println("valid = " + valid);
    }


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                //) ] }
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if(c == ')' && pop == '('){
                    continue;
                }
                if (c == ']' && pop == '[') {
                    continue;
                }
                if (c == '}' && pop == '{') {
                    continue;
                }
                //pop = (   c = }
                return false;
            }
        }
        //(
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }


}
