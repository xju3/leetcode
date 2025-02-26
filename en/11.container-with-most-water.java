/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 *
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (57.09%)
 * Likes:    30650
 * Dislikes: 1930
 * Total Accepted:    3.8M
 * Total Submissions: 6.6M
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * You are given an integer array height of length n. There are n vertical
 * lines drawn such that the two endpoints of the i^th line are (i, 0) and (i,
 * height[i]).
 * 
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 * 
 * Return the maximum amount of water a container can store.
 * 
 * Notice that you may not slant the container.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array
 * [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the
 * container can contain is 49.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: height = [1,1]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        return s1(height);
    }


    /**
    * Algorithm:
    *
    * The problem asks to find the maximum area of water that can be contained between two lines.
    * A brute-force approach would be to check every possible pair of lines, which would take O(n^2) time.
    * A more efficient approach is to use two pointers, one at the beginning and one at the end of the array.
    * Move the pointer that points to the shorter line inward.
    * The area of the container formed by the two lines is the distance between the lines multiplied by the height of the shorter line.
    * Keep track of the maximum area seen so far.
    *
    * This approach takes O(n) time.
    *
    * @param height
    * @return
    */
    private int s1(int[] height)  {

        int ans = 0;

        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return ans;

    }
}
// @lc code=end

