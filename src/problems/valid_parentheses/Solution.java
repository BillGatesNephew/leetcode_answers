class Solution {
    /**
     * Solution Explanation
     * ============================
     * The solution to the problem is fairly straightforward in its approach to what is a common technical 
     * question. Essentially, the key idea is to keep track of the opening brackets seen in a first in last
     * out order, which is simple with a stack. Whenever, a closing character is seen that isn't the last 
     * one on the stack, then there must be a closing bracket before the last open bracket was closed. The 
     * basic algorithm is as follows:
     * 
     *          1. Initialize an empty stack S.
     *          2. Iterate over every character c in the input string:
     *              i) If c is one of the opening brackets, then push the brackets corresponding closing
     *                 bracket onto S.
     *              ii) Otherwise, by the definition of the input, c must be a closing bracket, so now if
     *                  S is empty, or if the last closing bracket pushed onto S isn't the same as c, then
     *                  return false.
     *          3. If S is empty, then return true. Otherwise, return false. 
     * 
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Let n be the length of the input string s.
     * 
     * Time Complexity:  O(n) => In order to determine that the string is valid, the algorithm must process 
     * every single character of the input s. Therefore, the overall time complexity will be O(n). 
     * Space Complexity: O(n) => Consider an input string consisting of only opening brackets. In this case,
     * the algorithm will place every single character of the input onto the stack before terminating. Thus,
     * the overall space used will be O(n).
     * ============================
     */

    public boolean isValid(String s) {
        Stack<Character> openBrackets = new Stack<>();
        for(char c : s.toCharArray()) {
            // If the bracket is an opening bracket, then push it's corresponding
            // closing bracket onto the stack.
            if(c == '(') {
                openBrackets.push(')');
            } else if(c == '{') {
                openBrackets.push('}');
            } else if(c == '[') {
                openBrackets.push(']');
            // If the bracket is a closing bracket, then make sure it coresponds
            // to the last open bracket placed on the stack.
            } else if(openBrackets.isEmpty() || openBrackets.pop() != c) {
                return false;
            }
        }
        // Make sure that all open brackets have been closed.
        return stack.isEmpty();
    }
}
