class Solution {
    /**
     * Solution Explanation
     * ============================
     * First notice that in order to maximize the solution to the problem, the solution 
     * must try to possess as many of the leading digits of the input N as possible. In
     * order to maximize the common leading digits between the two numbers, iterate over 
     * each digit index i from n - 1 to 0 where i = n - 1 is the digit to the left of 
     * the rightmost digit and i = 0 is the leftmost digit. If digit i is less than
     * the digit at i-1, then the monotone condition has been broken in the original 
     * input between digits i and i - 1. To match the leading digits of the result and
     * the original input as much as possible, the lowest index where this condition
     * is broken needs to be found. Once that position has been found, the position must
     * be decremented by 1 in order for the result to be less than N. Finally to 
     * maximize the remaining digits of the number, the digits from the index to the 
     * right of the position just found on to the right end of the number should be set to
     * 9 in order to maximize the remaining values. The basic algorithm is as follows:
     * 
     *      1. Create the string s from the input N.
     *      2. Let l be the length of the string s.
     *      2. For i going from one index to the left of the rightmost digit to one index
     *         right of the leftmost digit, do the following:
     *          i) If the digit in s at position i is less than the digit in s at position
     *             i - 1, then:
     *              a) Set l to be i - 1.
     *              b) Set the digit in s at position i - 1 to be one less than the 
     *               current value.
     *      3. For i going from l + 1 to the rightmost digit of s, set the digit at 
     *          position i in s to be 9.
     *      4. Convert s to an integer, and return the result.
     * 
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Time Complexity:  O(log(N)) => The code iterates over every single digit within
     * the base 10 representation of the integer N. In total there will be log base 10 of 
     * N digits that are iterated over. Therefore, the total runtime will be O(log(N)).
     * Space Complexity: O(log(N)) => Each digit in the original input is altered and
     * the result is stored as a character. In total there will be O(log(N)) digits;
     * therefore, the total storage required will be O(log(N)). 
     * ============================
     */

    public int monotoneIncreasingDigits(int N) {
        // Determine the leftmost digit where the monotone condition is broken 
        char[] result = String.valueOf(N).toCharArray();
        int lastPos = result.length;
        for(int i = result.length - 1; i > 0; i--) {
            if(result[i] < result[i - 1]) {
                lastPos = i - 1;
                result[i - 1]--;
            }
        }
        // Fill in the remaining digits as the max possible value 9
        for(int i = lastPos + 1; i < result.length; i++) result[i] = '9';
        return Integer.parseInt(new String(result));
    }

}
