/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (44.61%)
 * Likes:    18621
 * Dislikes: 4669
 * Total Accepted:    4.1M
 * Total Submissions: 9.3M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters if it is non-empty.
 * 
 * 
 */

// @lc code=start
class Solution {

    /*
     * Key Points:
     * 1. Need to find common prefix among ALL strings in the array
     * 2. If any string doesn't share the prefix, return empty string
     * 3. Case sensitive - only lowercase letters
     * 4. Empty string is valid input
     * 
     * Algorithm Approaches:
     * 1. Horizontal scanning:
     *    - Take first string as prefix
     *    - Compare with each subsequent string
     *    - Update prefix to common part
     *    Time: O(S) where S is sum of all characters in all strings
     * 
     * 2. Vertical scanning:
     *    - Compare characters from first to last
     *    - Check same position in all strings
     *    - Stop when mismatch found
     *    Time: O(S) but may be faster by early termination
     * 
     * 3. Divide and conquer:
     *    - Split array into two halves
     *    - Find common prefix in each half
     *    - Merge results
     *    Time: O(S)
     * 
     * 4. Binary search:
     *    - Use binary search on length of shortest string
     *    - Check if prefix of that length is common
     *    Time: O(S*logM) where M is length of shortest string
     */
    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Take first string as initial prefix
        String prefix = strs[0];
        
        // Compare with remaining strings
        for (int i = 1; i < strs.length; i++) {
            // While current string doesn't start with prefix
            while (strs[i].indexOf(prefix) != 0) {
                // Shorten prefix by removing last char
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // If prefix becomes empty, no common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
        
    }
}
// @lc code=end

