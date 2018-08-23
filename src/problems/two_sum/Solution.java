import java.util.*;
class Solution {
    /**
     * Solution Explanation
     * ============================
     * The solution is very straightforward. Essentially, for a given index i, the complement of A[i]
     * is target - A[i]. In order to determine if the complement has been seen, simply maintain a map
     * that has a complement value as the key and an index as the value. The basic algorithm is as 
     * follows:
     *      
     *          1. Create new map M that goes from an integer key to an integer value.
     *          2. For every element nums[i] in the input array:
     *              i) If M contains the key nums[i], then return the array of i and the value
     *                 in M for the key nums[i].
     *              ii) Associate the key: target - num[i], with the value i.
     * 
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Let n be the length of the input array nums.
     * 
     * Time Complexity:  O(n) => The algorithm will perform a single operation for every index in the
     * input array. Therefore, the total runtime will be O(n).
     * Space Complexity: O(n) => At most, the complement for every integer in nums will need to be 
     * stored; therefore, the space complexity is O(n).
     * ============================
     */

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> remaindersToIndex = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(remaindersToIndex.containsKey(nums[i])) {
                return new int[] {remaindersToIndex.get(nums[i]), i};
            }
            remaindersToIndex.put(target - nums[i], i);
        }
        return new int[0];
    }

}
