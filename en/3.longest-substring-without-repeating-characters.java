/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (36.08%)
 * Likes:    41075
 * Dislikes: 1982
 * Total Accepted:    6.9M
 * Total Submissions: 19M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not
 * a substring.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 * 
 * 
 */

// @lc code=start

import java.util.HashSet;
import java.util.Set;

class Solution {

    /*
     * To find the longest substring without repeating characters:
     * 
     * 1. Use sliding window approach with two pointers (left and right)
     * 2. Keep track of characters in current window using a Set/Map
     * 3. Move right pointer to expand window while no repeats
     * 4. When repeat found, move left pointer to shrink window
     * 5. Track max length seen so far
     * 
     * For example with "abcabcbb":
     * - Window expands: [a] -> [ab] -> [abc]  (length 3)
     * - 'a' repeats, shrink: [bc] -> [bca]
     * - 'b' repeats, shrink: [ca] -> [cab] 
     * - 'c' repeats, shrink: [ab] -> [abc]
     * - 'b' repeats...etc
     * 
     * Time complexity: O(n) - each char visited at most twice
     * Space complexity: O(min(m,n)) where m is charset size
     */
    

    public int s1(String s) {
        // Input validation
        if (s == null || s.length() == 0) return 0;

        int maxLength = 0;
        int left = 0;
        Set<Character> window = new HashSet<>();

        // Sliding window approach
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // Shrink window by moving left pointer while we see duplicates
            while (window.contains(currentChar)) {
                window.remove(s.charAt(left));
                left++;
            }
            
            // Add current char and update max length
            window.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    public int s2(String s) {
        // Input validation
        if (s == null || s.length() == 0) return 0;

        int maxLength = 0;
        int left = 0;
        int right = 0;
        Set<Character> window = new HashSet<>();

        while (right < s.length()) {
            char currentChar = s.charAt(right);

            // If character already in window, shrink window from left
            while (window.contains(currentChar)) {
                window.remove(s.charAt(left));
                left++;
            }

            // Add current char to window
            window.add(currentChar);
            
            // Update max length seen so far
            maxLength = Math.max(maxLength, right - left + 1);
            
            right++;
        }

        return maxLength;
    }
    
    public int lengthOfLongestSubstring(String s) {

        // if s is not valid, return 0;
        if (s == null || s.length() == 0) return 0;
        int ans = 0;
        int left = 0;
        int right = 0;
        Set<Character> windows = new HashSet<>();
        while(right < s.length()) {
            char c = s.charAt(right);
            // if the windows contain the curr char, 
            // then, remove char in the window from left to right.
            // until the window does not contain curr char.
            while(windows.contains(c)) {
                windows.remove(s.charAt(left));
                left++;
            }
            windows.add(c);
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
// @lc code=end

class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1: Basic case with repeating characters
        String s1 = "abcabcbb";
        int result = solution.lengthOfLongestSubstring(s1);
        System.out.println("Test Case 1: \"" + s1 + "\" -> " + result + " (Expected: 3)");
        
        // Test Case 2: All same characters
        String s2 = "bbbbb"; 
        result = solution.lengthOfLongestSubstring(s2);
        System.out.println("Test Case 2: \"" + s2 + "\" -> " + result + " (Expected: 1)");
        
        // Test Case 3: No repeating characters
        String s3 = "abcdef";
        result = solution.lengthOfLongestSubstring(s3);
        System.out.println("Test Case 3: \"" + s3 + "\" -> " + result + " (Expected: 6)");
        
        // Test Case 4: Empty string
        String s4 = "";
        result = solution.lengthOfLongestSubstring(s4);
        System.out.println("Test Case 4: \"" + s4 + "\" -> " + result + " (Expected: 0)");
        
        // Test Case 5: String with spaces and special characters
        String s5 = "ab cd!@#";
        result = solution.lengthOfLongestSubstring(s5);
        System.out.println("Test Case 5: \"" + s5 + "\" -> " + result + " (Expected: 7)");
        
        // Test Case 6: Repeating pattern
        String s6 = "pwwkew";
        result = solution.lengthOfLongestSubstring(s6);
        System.out.println("Test Case 6: \"" + s6 + "\" -> " + result + " (Expected: 3)");
    }
}


