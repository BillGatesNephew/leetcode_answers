class Solution {
    /**
     * Solution Explanation
     * ============================
     * For every element in the array, the rotation number 
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Time Complexity:  O(n)
     * Space Complexity: O(n)
     * ============================
     */

    public int bestRotation(int[] A) {
        /**
         * For every value in A, determine the position where the value
         * will cause the overall point total to lose a point, and decrement
         * that position by one. 
         */
        int[] changes = new int[A.length];
        for(int i = 0; i < A.length; i++) {
            // The rotation number at which A[i] loses its point value
            int index = (i - A[i] + 1 + A.length) % A.length;
            changes[index] -= 1;
        }
        /**
         * Accumulate the total change resulting from a series of rotations, 
         * and determine where this change is maximum, indicating the largest
         * score possible.
         */
        int maxInd = 0;
        for(int i = 1; i < A.length; i++) {
            changes[i] += changes[i - 1] + 1;
            maxInd = changes[i] > changes[maxInd] ? i : maxInd;
        }
        return maxInd;
    }
}
