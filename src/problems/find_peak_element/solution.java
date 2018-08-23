class Solution {
    /**
     * Solution Explanation
     * ============================
     * The requirement for the algorithm to be logarithmic implies that at every step the
     * algorithm should be able to reduce the size of the input range currently being 
     * searched in half while ensuring a peak is still contained within the range. In 
     * order to accomplish this, the solution will make sure that the lower index of the 
     * range being searched is always greater than the index to its right AND that the 
     * upper index of the range being searched is greater than the index to its right. 
     * Initially, if we let the lower and upper indices be both 0 and N - 1, respectively,
     * then the property holds. Now, the solution will then probe the middle of the range
     * to see if the middle element is less than the element to its right. If this is 
     * true, then we can make the lower index become the index to the right of the middle,
     * and we will have maintained the initial property discussed while reducing half
     * of the range being searched. If the element in the middle is greater than the 
     * element to its right, then we can set the upper index of the range to be the middle
     * index, and again we have maintained the inital property while reducing half the 
     * range being searched. In either case, it is clear that after probing the middle of 
     * the current range, the inital property is maintained. Eventually, the range being
     * searched will reduce to a range of size 1 when the two indices are equal to each 
     * other. When this occurs, a peak has been found since the initial property is 
     * maintained while the range is only of size 1. Thus, the basic algorithm is as 
     * follows: 
     * 
     *      1. Let i = 0, and j = N - 1.
     *      2. While i < j:
     *          i) Let k = (j - i) / 2 + i.
     *          ii) If A[k] < A[k + 1], then i = k + 1.
     *          iii) Otherwise, j = k.
     *      3. Return i.
     * 
     * Note: The operation performed in step (2.i) needs to be executed in the given 
     * manner as using the other format of (j + i) / 2 could potentially cause an integer
     * overflow. 
     * ============================
     *  
     * 
     * Solution Complexities
     * ============================
     * Let n be the size of the input nums.
     * 
     * Time Complexity:  O(log(n)) => At each step of the while loop, the size of the 
     * range being searched is halved, which means that it will take log(n) iterations 
     * for the range to reach a size of one. Therefore, the overall time complexity will 
     * be O(log(n)). 
     * Space Complexity: O(1) => The only data being stored are the two indices that 
     * determine the current range being searched. Thus, the space complexity is O(1).
     * ============================
     */

    public int findPeakElement(int[] nums) {
        int lowerIndex = 0;
        int upperIndex = nums.length - 1;
        while(lowerIndex < upperIndex) {
            int middleIndex = (upperIndex - lowerIndex) / 2 + lowerIndex;
            if(nums[middleIndex] < nums[middleIndex + 1]) {
                lowerIndex = middleIndex + 1;
            } else {
                upperIndex = middleIndex;
            }
        }
        return lowerIndex;
    }
}