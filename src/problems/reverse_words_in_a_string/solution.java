class Solution {
    /**
     * Solution Explanation
     * ============================
     * The solution to the algorithm is very straightforward. Essentially, the original 
     * input just needs to be trimmed, and split by white space. The result should just 
     * then be iterated over in reverse for the solution. The basic algorithm is as 
     * follows:
     *      
     *      1. Initialize k to be an empty string.
     *      2. Let A be the array resulting from trimming s, and then splitting the result
     *         by the regex "\\s+".
     *      3. For i from the size of A - 1 to 0, do the following:
     *          i) Append A[i] to k.
     *          ii) If i is not 0, then also append a space.
     *      4. Return k.
     * 
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Let n be the length of the input string s.
     * 
     * Time Complexity:  O(n) => Trimming and splitting s will require O(n) time, while 
     * iterating over the split result will also take O(n) time. Therefore, the overall
     * time complexity will be O(n).
     * Space Complexity: O(n) => The array split always contains the separate components 
     * of the initial input s for a combined size of n. Thus, the space complexity is 
     * O(n).
     * 
     * NOTE: The algorithm can be rewritten in other languages such as C or C++ to make 
     * the space complexity O(1) by manipulating the original input. However, since 
     * Strings are immutable in Java, it is not possible to operate directly on the input
     * String.
     * 
     * ============================
     */

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] split = s.trim().split("\\s+");
        for(int i = split.length - 1; i >= 0; i--){
            sb.append(split[i]);
            if(i != 0) sb.append(" ");
        }
        return sb.toString();
    }

}
