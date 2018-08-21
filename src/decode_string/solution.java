class Solution {
    /**
     * Solution Explanation
     * ============================
     * The first thing to notice for the problem is that the brackets can be nested 
     * within one another in any order with multiple separate groupings. Thus, while a 
     * recurisve approach might be possible, the best approach would be one using two
     * parallel stacks. Now in order to parse the larger bracket groups, the inner groups
     * must be parsed first. Since inner groups of larger bracket groups will have their
     * closing brackets appear first, the proper ordering will occur naturally if a value 
     * is replaced once a closing bracket has been found. The basic algorithm is as
     * follows:
     * 
     *      1. Initialize two empty string called num, and str.
     *      2. Initialize an empty stack called stk. 
     *      3. For every character, c, in the input s do the following:
     *          i) If c is '[', then:
     *              a) Push str onto stk, and set str to be "".
     *              b) Push num onto stk, and set num to be "".
     *          ii) Else if c is ']', then:
     *              a) Set pattern to be str.
     *              b) Set repeats to be the integer parsed from popping the stack.
     *              c) Set str to be the result of popping the stack.
     *              d) For i from 1 to repeats, add pattern to the end of str.
     *          iii) Else if c is a digit, then append c to num.
     *          iv)  Otherwise, append c to str.
     *      4. Return str.
     * 
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Time Complexity: O(s) => First consider an encoded sequence 'i[...]' where i is a
     * number. Since i is restricted to being a 32 bit integer, the time to operate on 
     * the sequence is simply the time needed to iterate over every character, and we 
     * can ignore the repeat process since we know it is bounded by the maximum integer
     * size. For both composition types of the input, 'i[j[...]]' and 'i[...]j[..]', the
     * number of actions performed is still only dependent on the length of the input 
     * string. Therefore, the overall runtime will be O(s).
     * Space Complexity: O(s) => At any point in the algorithm, the combined size of str,
     * num, and stk will at most be some integer multiple of s. Therefore, the total space
     * will be O(s).
     * ============================
     */

    public String decodeString(String s) {
        // Initialize the stack and the two stringbuilders
        Stack<String> stk = new Stack<>();
        StringBuilder substringSB = new StringBuilder();
        StringBuilder numberSB = new StringBuilder();

        // Iterate over every character in the encoded string 
        for(char c : s.toCharArray()) {
            /**
             * The following cases are handled below:
             *      1. c is '['     => Beginning of encoded message contents.
             *      2. c is ']'     => End of encoded message contents.
             *      3. c is a digit => Inside of encoded message repeat number.
             *      4. c is a char  => Inside of encoded message contents.
             */
            if(c == '[') { 
                stk.push(substringSB.toString());
                stk.push(numberSB.toString());
                substringSB = new StringBuilder();
                numberSB = new StringBuilder();
            } else if(c == ']') {
                int repeats = Integer.parseInt(stk.pop());
                String pattern = substringSB.toString();
                substringSB = new StringBuilder(stk.pop());
                for(int j = 0; j < repeats; j++) substringSB.append(pattern);
            } else if(c >= '0' && c <= '9') {
                numberSB.append(c);
            } else {
                substringSB.append(c);
            }
        }
        return substringSB.toString();
    }
}
